package se.kth.journalsystem.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.kth.journalsystem.model.Condition;
import se.kth.journalsystem.repository.ConditionRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ConditionService {

    private final ConditionRepository conditionRepository;

    @Autowired
    public ConditionService(ConditionRepository conditionRepository) {
        this.conditionRepository = conditionRepository;
    }

    public List<Condition> getAllConditions() {
        return conditionRepository.findAll();
    }

    public Condition getConditionById(Long id) {
        Optional<Condition> condition = conditionRepository.findById(id);
        return condition.orElse(null);
    }

    public List<Condition> getConditionsByPatientId(Long patientId) {
        return conditionRepository.findByPatientId(patientId);
    }

    public Condition createCondition(Condition condition) {
        return conditionRepository.save(condition);
    }

    public Condition updateCondition(Long id, Condition conditionDetails) {
        Optional<Condition> condition = conditionRepository.findById(id);
        if (condition.isPresent()) {
            Condition existingCondition = condition.get();
            existingCondition.setDiagnosis(conditionDetails.getDiagnosis());
            existingCondition.setDiagnosisDate(conditionDetails.getDiagnosisDate());
            // Note: Patient should not change here; it's set when condition is created
            return conditionRepository.save(existingCondition);
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
}
