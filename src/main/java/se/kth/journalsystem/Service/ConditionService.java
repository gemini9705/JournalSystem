package se.kth.journalsystem.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.kth.journalsystem.DTO.ConditionDTO;
import se.kth.journalsystem.model.Condition;
import se.kth.journalsystem.repository.ConditionRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ConditionService {

    private final ConditionRepository conditionRepository;

    @Autowired
    public ConditionService(ConditionRepository conditionRepository) {
        this.conditionRepository = conditionRepository;
    }

    public List<ConditionDTO> getAllConditions() {
        return conditionRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public ConditionDTO getConditionById(Long id) {
        return conditionRepository.findById(id).map(this::convertToDTO).orElse(null);
    }

    public List<ConditionDTO> getConditionsByPatientId(Long patientId) {
        return conditionRepository.findByPatientId(patientId).stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public ConditionDTO createCondition(ConditionDTO conditionDTO) {
        Condition condition = convertFromDTO(conditionDTO);
        Condition savedCondition = conditionRepository.save(condition);
        return convertToDTO(savedCondition);
    }

    public ConditionDTO updateCondition(Long id, ConditionDTO conditionDetails) {
        Optional<Condition> condition = conditionRepository.findById(id);
        if (condition.isPresent()) {
            Condition existingCondition = condition.get();
            existingCondition.setDiagnosis(conditionDetails.getDiagnosis());
            existingCondition.setDiagnosisDate(conditionDetails.getDiagnosisDate());
            // Note: Patient-ID bör inte ändras här

            Condition updatedCondition = conditionRepository.save(existingCondition);
            return convertToDTO(updatedCondition);
        }
        return null;
    }

    public boolean deleteCondition(Long id) {
        if (conditionRepository.existsById(id)) {
            conditionRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Konverteringsmetoder
    private ConditionDTO convertToDTO(Condition condition) {
        ConditionDTO dto = new ConditionDTO();
        dto.setId(condition.getId());
        dto.setDiagnosis(condition.getDiagnosis());
        dto.setDiagnosisDate(condition.getDiagnosisDate());
        dto.setPatientId(condition.getPatient().getId());
        return dto;
    }

    private Condition convertFromDTO(ConditionDTO dto) {
        Condition condition = new Condition();
        condition.setDiagnosis(dto.getDiagnosis());
        condition.setDiagnosisDate(dto.getDiagnosisDate());
        // Patient behöver sättas från patient-ID, beroende på implementation
        // condition.setPatient(patientService.findById(dto.getPatientId()).orElseThrow(...));
        return condition;
    }
}
