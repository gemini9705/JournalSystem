package se.kth.journalsystem;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import org.hl7.fhir.r4.model.Patient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FhirService {
    private final IGenericClient client;

    public FhirService() {
        FhirContext context = FhirContext.forR4();  // Skapar ett FHIR-kontekst för R4
        String baseURL = "https://hapi-fhir.app.cloud.cbh.kth.se/fhir";
        this.client = context.newRestfulGenericClient(baseURL);  // Skapar HAPI-klienten
    }

    public List<Patient> getAllPatients() {
        var bundle = client.search()
                .forResource(Patient.class)
                .returnBundle(org.hl7.fhir.r4.model.Bundle.class)
                .execute();

        return bundle.getEntry().stream()
                .map(entry -> (Patient) entry.getResource())
                .toList();
    }
}
