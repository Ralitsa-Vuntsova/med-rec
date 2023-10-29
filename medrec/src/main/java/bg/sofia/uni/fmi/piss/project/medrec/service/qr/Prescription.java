package bg.sofia.uni.fmi.piss.project.medrec.service.qr;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Prescription {
    @SerializedName("dg")
    private String drugName;

    private String in;

    @SerializedName("dt")
    private String date;
}