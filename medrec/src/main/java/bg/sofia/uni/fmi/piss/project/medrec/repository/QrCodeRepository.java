package bg.sofia.uni.fmi.piss.project.medrec.repository;

import bg.sofia.uni.fmi.piss.project.medrec.model.QrCodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface QrCodeRepository extends JpaRepository<QrCodeEntity, Long> {
    Optional<QrCodeEntity> findById(Long qrCodeId);

    Optional<List<QrCodeEntity>> findAllByUserId(Long userId);
}
