package bg.sofia.uni.fmi.piss.project.medrec.service.fda;

import java.util.List;

public class Drug {
    private List<Result> results;
    private Error error;

    public Drug(List<Result> results, Error error) {
        this.results = results;
        this.error = error;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }

    @Override
    public String toString() {
        if (error != null) {
            return error.getMessage();
        }
        return "Drug [results=" + results + "]";
    }

    public String getBrandName() {
        if (results.isEmpty()) {
            return null;
        }
        return results.get(0).getOpenFDA().getBrandName().get(0);
    }

    public String getGenericName() {
        if (results.isEmpty()) {
            return null;
        }
        return results.get(0).getOpenFDA().getGenericName().get(0);
    }

    public String getSubstanceName() {
        if (results.isEmpty()) {
            return null;
        }
        return results.get(0).getOpenFDA().getSubstanceName().get(0);
    }

    public String getManufacturerName() {
        if (results.isEmpty()) {
            return null;
        }
        return results.get(0).getOpenFDA().getManufacturerName().get(0);
    }

    public String getDosageForm() {
        if (results.isEmpty() || results.get(0).getProducts().isEmpty()) {
            return null;
        }
        return results.get(0).getProducts().get(0).getDosageForm();
    }

    public String getMarketingStatus() {
        if (results.isEmpty() || results.get(0).getProducts().isEmpty()) {
            return null;
        }
        return results.get(0).getProducts().get(0).getMarketingStatus();
    }

    public String getRoute() {
        if (results.isEmpty() || results.get(0).getProducts().isEmpty()) {
            return null;
        }
        return results.get(0).getProducts().get(0).getRoute();
    }
}
