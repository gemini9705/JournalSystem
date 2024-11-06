package se.kth.journalsystem.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.kth.journalsystem.model.Encounter;
import se.kth.journalsystem.Service.EncounterService;

import java.util.List;

@RestController
@RequestMapping("/api/encounters")
public class EncounterController {

    private final EncounterService encounterService;

    @Autowired
    public EncounterController(EncounterService encounterService) {
        this.encounterService = encounterService;
    }

    // Get all encounters
    @GetMapping
    public List<Encounter> getAllEncounters() {
        return encounterService.getAllEncounters();
    }

    // Get a specific encounter by ID
    @GetMapping("/{id}")
    public ResponseEntity<Encounter> getEncounterById(@PathVariable Long id) {
        Encounter encounter = encounterService.getEncounterById(id);
        return encounter != null ? ResponseEntity.ok(encounter) : ResponseEntity.notFound().build();
    }

    // Get all encounters for a specific patient by patient ID
    @GetMapping("/patient/{patientId}")
    public List<Encounter> getEncountersByPatientId(@PathVariable Long patientId) {
        return encounterService.getEncountersByPatientId(patientId);
    }

    // Get all encounters for a specific practitioner by practitioner ID
    @GetMapping("/practitioner/{practitionerId}")
    public List<Encounter> getEncountersByPractitionerId(@PathVariable Long practitionerId) {
        return encounterService.getEncountersByPractitionerId(practitionerId);
    }

    // Create a new encounter
    @PostMapping
    public ResponseEntity<Encounter> createEncounter(@RequestBody Encounter encounter) {
        Encounter createdEncounter = encounterService.createEncounter(encounter);
        return ResponseEntity.ok(createdEncounter);
    }

    // Update an existing encounter
    @PutMapping("/{id}")
    public ResponseEntity<Encounter> updateEncounter(@PathVariable Long id, @RequestBody Encounter encounterDetails) {
        Encounter updatedEncounter = encounterService.updateEncounter(id, encounterDetails);
        return updatedEncounter != null ? ResponseEntity.ok(updatedEncounter) : ResponseEntity.notFound().build();
    }

    // Delete an encounter
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEncounter(@PathVariable Long id) {
        boolean deleted = encounterService.deleteEncounter(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
