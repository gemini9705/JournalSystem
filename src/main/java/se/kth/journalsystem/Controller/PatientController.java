package se.kth.journalsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.kth.journalsystem.model.Patient;
import se.kth.journalsystem.service.PatientService;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    private final PatientService patientService;

    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    // Hämta alla patienter (endast tillgängligt för läkare och personal)
    @GetMapping
    public List<Patient> getAllPatients() {
        return patientService.getAllPatients();
    }

    // Hämta en specifik patient med ID
    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable Long id) {
        Patient patient = patientService.getPatientById(id);
        return patient != null ? ResponseEntity.ok(patient) : ResponseEntity.notFound().build();
    }

    // Skapa en ny patient (endast tillgängligt för läkare och personal)
    @PostMapping
    public ResponseEntity<Patient> createPatient(@RequestBody Patient patient) {
        Patient createdPatient = patientService.createPatient(patient);
        return ResponseEntity.ok(createdPatient);
    }

    // Uppdatera en patients information
    @PutMapping("/{id}")
    public ResponseEntity<Patient> updatePatient(@PathVariable Long id, @RequestBody Patient patientDetails) {
        Patient updatedPatient = patientService.updatePatient(id, patientDetails);
        return updatedPatient != null ? ResponseEntity.ok(updatedPatient) : ResponseEntity.notFound().build();
    }

    // Radera en patient (administrativ funktion)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
        boolean deleted = patientService.deletePatient(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
