package bg.sofia.uni.fmi.piss.project.medrec.service.fda;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class OpenFDA {
    @SerializedName("brand_name")
    private List<String> brandName;

    @SerializedName("generic_name")
    private List<String> genericName;

    @SerializedName("substance_name")
    private List<String> substanceName;

    @SerializedName("manufacturer_name")
    private List<String> manufacturerName;

    public OpenFDA(List<String> brandName, List<String> genericName, List<String> substanceName, List<String> manufacturerName) {
        this.brandName = brandName;
        this.genericName = genericName;
        this.substanceName = substanceName;
        this.manufacturerName = manufacturerName;
    }

    public List<String> getBrandName() {
        return brandName;
    }

    public void setBrandName(List<String> brandName) {
        this.brandName = brandName;
    }

    public List<String> getGenericName() {
        return genericName;
    }

    public void setGenericName(List<String> genericName) {
        this.genericName = genericName;
    }

    public List<String> getSubstanceName() {
        return substanceName;
    }

    public void setSubstanceName(List<String> substanceName) {
        this.substanceName = substanceName;
    }

    public List<String> getManufacturerName() {
        return manufacturerName;
    }

    public void setManufacturerName(List<String> manufacturerName) {
        this.manufacturerName = manufacturerName;
    }

    @Override
    public String toString() {
        return "OpenFDA [brandName=" + brandName + ", genericName=" + genericName +
                "substanceName=" + substanceName + ", manufacturerName=" + manufacturerName + "]";
    }
}
