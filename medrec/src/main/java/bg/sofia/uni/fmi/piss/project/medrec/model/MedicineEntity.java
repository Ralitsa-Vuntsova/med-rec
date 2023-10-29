package bg.sofia.uni.fmi.piss.project.medrec.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "medicines")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MedicineEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "medicine_id")
    private Long id;

    @Column(name = "brand_name", nullable = false)
    private String brandName;

    @Column(name = "generic_name")
    private String genericName;

    @Column(name = "substance_name")
    private String substanceName;

    @Column(name = "manufacturer_name")
    private String manufacturerName;

    @Column(name = "dosage_form", nullable = false)
    private String dosageForm;

    @Column(name = "route", nullable = false)
    private String route;

    @Column(name = "marketing_status", nullable = false)
    private String marketingStatus;
}
