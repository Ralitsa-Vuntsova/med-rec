package bg.sofia.uni.fmi.piss.project.medrec.service.fda;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Result {
    @SerializedName("openfda")
    private OpenFDA openFDA;

    private List<Product> products;

    public Result(OpenFDA openFDA, List<Product> products) {
        this.openFDA = openFDA;
        this.products = products;
    }

    public OpenFDA getOpenFDA() {
        return openFDA;
    }

    public void setOpenFDA(OpenFDA openFDA) {
        this.openFDA = openFDA;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Result [openFDA=" + openFDA + ", products=" + products + "]";
    }
}
