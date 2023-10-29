package bg.sofia.uni.fmi.piss.project.medrec.service.fda;

import com.google.gson.annotations.SerializedName;

public class Product {
    @SerializedName("dosage_form")
    private String dosageForm;

    private String route;

    @SerializedName("marketing_status")
    private String marketingStatus;

    public Product(String dosageForm, String route, String marketingStatus) {
        this.dosageForm = dosageForm;
        this.route = route;
        this.marketingStatus = marketingStatus;
    }

    public String getDosageForm() {
        return dosageForm;
    }

    public void setDosageForm(String dosageForm) {
        this.dosageForm = dosageForm;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getMarketingStatus() {
        return marketingStatus;
    }

    public void setMarketingStatus(String marketingStatus) {
        this.marketingStatus = marketingStatus;
    }

    @Override
    public String toString() {
        return "Product [dosageForm=" + dosageForm + ", route=" + route + ", marketingStatus=" + marketingStatus + "]";
    }
}
