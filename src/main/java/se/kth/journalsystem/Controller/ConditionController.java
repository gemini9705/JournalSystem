package se.kth.journalsystem.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.kth.journalsystem.model.Condition;
import se.kth.journalsystem.service.ConditionService;

import java.util.List;

@RestController
@RequestMapping("/api/conditions")
public class ConditionController {

    private final ConditionService conditionService;

    @Autowired
    public ConditionController(ConditionService conditionService) {
        this.conditionService = conditionService;
    }

    // Get all conditions
    @GetMapping
    public List<Condition> getAllConditions() {
        return conditionService.getAllConditions();
    }

    // Get a specific condition by ID
    @GetMapping("/{id}")
    public ResponseEntity<Condition> getConditionById(@PathVariable Long id) {
        Condition condition = conditionService.getConditionById(id);
        return condition != null ? ResponseEntity.ok(condition) : ResponseEntity.notFound().build();
    }

    // Get all conditions for a specific patient by patient ID
    @GetMapping("/patient/{patientId}")
    public List<Condition> getConditionsByPatientId(@PathVariable Long patientId) {
        return conditionService.getConditionsByPatientId(patientId);
    }

    // Create a new condition
    @PostMapping
    public ResponseEntity<Condition> createCondition(@RequestBody Condition condition) {
        Condition createdCondition = conditionService.createCondition(condition);
        return ResponseEntity.ok(createdCondition);
    }

    // Update an existing condition
    @PutMapping("/{id}")
    public ResponseEntity<Condition> updateCondition(@PathVariable Long id, @RequestBody Condition conditionDetails) {
        Condition updatedCondition = conditionService.updateCondition(id, conditionDetails);
        return updatedCondition != null ? ResponseEntity.ok(updatedCondition) : ResponseEntity.notFound().build();
    }

    // Delete a condition
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCondition(@PathVariable Long id) {
        boolean deleted = conditionService.deleteCondition(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
