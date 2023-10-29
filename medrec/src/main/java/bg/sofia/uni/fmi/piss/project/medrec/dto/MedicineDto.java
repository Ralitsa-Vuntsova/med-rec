package bg.sofia.uni.fmi.piss.project.medrec.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MedicineDto {
    private Long id;

    private String brandName;

    private String genericName;

    private String substanceName;

    private String manufacturerName;

    private String dosageForm;

    private String route;

    private String marketingStatus;
}
