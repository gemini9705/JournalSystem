package se.kth.journalsystem.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.kth.journalsystem.model.Encounter;
import se.kth.journalsystem.repository.EncounterRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EncounterService {

    private final EncounterRepository encounterRepository;

    @Autowired
    public EncounterService(EncounterRepository encounterRepository) {
        this.encounterRepository = encounterRepository;
    }

    public List<Encounter> getAllEncounters() {
        return encounterRepository.findAll();
    }

    public Encounter getEncounterById(Long id) {
        Optional<Encounter> encounter = encounterRepository.findById(id);
        return encounter.orElse(null);
    }

    public List<Encounter> getEncountersByPatientId(Long patientId) {
        return encounterRepository.findByPatientId(patientId);
    }

    public List<Encounter> getEncountersByPractitionerId(Long practitionerId) {
        return encounterRepository.findByPractitionerId(practitionerId);
    }

    public Encounter createEncounter(Encounter encounter) {
        return encounterRepository.save(encounter);
    }

    public Encounter updateEncounter(Long id, Encounter encounterDetails) {
        Optional<Encounter> encounter = encounterRepository.findById(id);
        if (encounter.isPresent()) {
            Encounter existingEncounter = encounter.get();
            existingEncounter.setEncounterDate(encounterDetails.getEncounterDate());
            // Note: Patient and Practitioner should not change here; they are set when the encounter is created
            return encounterRepository.save(existingEncounter);
        }
        return null;
    }

    public boolean deleteEncounter(Long id) {
        if (encounterRepository.existsById(id)) {
            encounterRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
