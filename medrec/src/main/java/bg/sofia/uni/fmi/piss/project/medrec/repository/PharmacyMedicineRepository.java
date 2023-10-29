package bg.sofia.uni.fmi.piss.project.medrec.repository;

import bg.sofia.uni.fmi.piss.project.medrec.model.MedicineEntity;
import bg.sofia.uni.fmi.piss.project.medrec.model.PharmacyMedicineEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PharmacyMedicineRepository extends JpaRepository<PharmacyMedicineEntity, Long> {
    @Modifying
    @Query("delete from PharmacyMedicineEntity pm where pm.medicineId=:medicineId and pm.userId=:userId")
    void deleteMedicineForPharmacy(@Param("userId") Long pharmacyId, @Param("medicineId") Long medicineId);

    List<PharmacyMedicineEntity> findAllByUserId(Long userId);

    Optional<PharmacyMedicineEntity> findByUserIdAndMedicineId(Long userId, Long medicineId);
}
