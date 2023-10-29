package bg.sofia.uni.fmi.piss.project.medrec.service;

import bg.sofia.uni.fmi.piss.project.medrec.exceptions.DrugNotFoundException;
import bg.sofia.uni.fmi.piss.project.medrec.exceptions.ExternalServiceNotAvailableException;
import bg.sofia.uni.fmi.piss.project.medrec.service.fda.Drug;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class DrugsFDAService {
    private static final String BASE_ENDPOINT = "https://api.fda.gov/drug/drugsfda.json";
    private static final String SEARCH_BY_BRAND_NAME = "?search=products.brand_name:";
    private static final String LIMIT_SEARCHES = "&limit=1";

    private HttpClient client;

    public DrugsFDAService(HttpClient client) {
        this.client = client;
    }

    public Drug getDrugFromDrugsFda(String brandName)
            throws ExternalServiceNotAvailableException, DrugNotFoundException {

        Drug drug = getDrugInformation(brandName);
        if (drug.getError() != null) {
            throw new DrugNotFoundException("Drug is not available in DrugsFDA.");
        }

        return drug;
    }

    private Drug getDrugInformation(String brandName) throws ExternalServiceNotAvailableException {
        String jsonResponse = retrieveInformationFromDrugsFda(brandName);

        return new Gson().fromJson(jsonResponse, Drug.class);
    }

    private String retrieveInformationFromDrugsFda(String brandName) throws ExternalServiceNotAvailableException {
        String analyzerURIAsString = buildURIString(brandName);
        URI analyzerURI = URI.create(analyzerURIAsString);
        HttpRequest analyzerRequest = HttpRequest.newBuilder().uri(analyzerURI).build();

        try {
            return client.send(analyzerRequest, HttpResponse.BodyHandlers.ofString()).body();
        } catch (IOException|InterruptedException e) {
            throw new ExternalServiceNotAvailableException("Error in connecting to DFC");
        }
    }

    private String buildURIString(String searchInput) {
        return BASE_ENDPOINT + SEARCH_BY_BRAND_NAME + searchInput + LIMIT_SEARCHES;
    }
}
