package bg.sofia.uni.fmi.piss.project.medrec.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "pharmacy_medicines")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PharmacyMedicineEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "medicine_id", nullable = false)
    private Long medicineId;

    @Column(name = "user_id", nullable = false)
    private Long userId;
}
