package se.kth.journalsystem.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.kth.journalsystem.DTO.ObservationDTO;
import se.kth.journalsystem.model.Observation;
import se.kth.journalsystem.repository.ObservationRepository;

import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Service
public class ObservationService {

    private final ObservationRepository observationRepository;

    @Autowired
    public ObservationService(ObservationRepository observationRepository) {
        this.observationRepository = observationRepository;
    }

    public List<ObservationDTO> getAllObservations() {
        return observationRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public ObservationDTO getObservationById(Long id) {
        return observationRepository.findById(id).map(this::convertToDTO).orElse(null);
    }

    public List<ObservationDTO> getObservationsByPatientId(Long patientId) {
        return observationRepository.findByPatientId(patientId).stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public ObservationDTO createObservation(ObservationDTO observationDTO) {
        Observation observation = convertFromDTO(observationDTO);
        observation.setObservationDate(LocalDateTime.now());
        Observation savedObservation = observationRepository.save(observation);
        return convertToDTO(savedObservation);
    }

    public ObservationDTO updateObservation(Long id, ObservationDTO observationDetails) {
        Optional<Observation> observation = observationRepository.findById(id);
        if (observation.isPresent()) {
            Observation existingObservation = observation.get();
            existingObservation.setDescription(observationDetails.getDescription());
            existingObservation.setObservationDate(observationDetails.getObservationDate());
            Observation updatedObservation = observationRepository.save(existingObservation);
            return convertToDTO(updatedObservation);
        }
        return null;
    }

    public boolean deleteObservation(Long id) {
        if (observationRepository.existsById(id)) {
            observationRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Konverteringsmetoder
    private ObservationDTO convertToDTO(Observation observation) {
        ObservationDTO dto = new ObservationDTO();
        dto.setId(observation.getId());
        dto.setDescription(observation.getDescription());
        dto.setObservationDate(observation.getObservationDate());
        dto.setPatientId(observation.getPatient().getId());
        return dto;
    }

    private Observation convertFromDTO(ObservationDTO dto) {
        Observation observation = new Observation();
        observation.setDescription(dto.getDescription());
        // Patient bör sättas baserat på patientens ID via en extern tjänst om tillgänglig
        // observation.setPatient(patientService.findById(dto.getPatientId()).orElseThrow(...));
        return observation;
    }
}
