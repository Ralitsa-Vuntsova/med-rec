package bg.sofia.uni.fmi.piss.project.medrec.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "qr_codes")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class QrCodeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "qr_id")
    private Long id;

    @Column(name = "filename", nullable = false)
    private String filename;

    @Column(name = "user_id", nullable = false)
    private Long userId;
}
