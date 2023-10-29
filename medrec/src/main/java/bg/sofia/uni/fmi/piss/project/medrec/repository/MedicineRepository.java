package bg.sofia.uni.fmi.piss.project.medrec.repository;

import bg.sofia.uni.fmi.piss.project.medrec.model.MedicineEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MedicineRepository extends JpaRepository<MedicineEntity, Long> {

    Optional<MedicineEntity> findByBrandNameContainingIgnoreCase(String genericName);

    List<MedicineEntity> findByIdInAndBrandNameContainingIgnoreCase(List<Long> medicineIds, String brandName);
}
