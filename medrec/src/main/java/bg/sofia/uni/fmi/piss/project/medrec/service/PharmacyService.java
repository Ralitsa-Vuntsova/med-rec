package bg.sofia.uni.fmi.piss.project.medrec.service;

import bg.sofia.uni.fmi.piss.project.medrec.dto.MedicineDto;
import bg.sofia.uni.fmi.piss.project.medrec.dto.PharmacyDto;
import bg.sofia.uni.fmi.piss.project.medrec.exceptions.*;
import bg.sofia.uni.fmi.piss.project.medrec.model.MedicineEntity;
import bg.sofia.uni.fmi.piss.project.medrec.model.PharmacyMedicineEntity;
import bg.sofia.uni.fmi.piss.project.medrec.model.Type;
import bg.sofia.uni.fmi.piss.project.medrec.model.UserEntity;
import bg.sofia.uni.fmi.piss.project.medrec.repository.MedicineRepository;
import bg.sofia.uni.fmi.piss.project.medrec.repository.PharmacyMedicineRepository;
import bg.sofia.uni.fmi.piss.project.medrec.repository.UserRepository;
import bg.sofia.uni.fmi.piss.project.medrec.service.fda.Drug;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PharmacyService {

    private final MedicineRepository medicineRepository;

    private final DrugsFDAService drugsFDAService;

    private final PharmacyMedicineRepository pharmacyMedicineRepository;

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    public void addMedicine(Long pharmacyId, String brandName)
            throws DrugNotFoundException, ExternalServiceNotAvailableException, DrugAlreadyExistsException {
        // check if already added

        Long medicineId = getMedicineId(brandName);

        if (pharmacyMedicineRepository.findByUserIdAndMedicineId(pharmacyId, medicineId).isPresent()) {
            throw new DrugAlreadyExistsException("Drug already added for this pharmacy");
        }

        if (medicineId == -1L) {
            Drug medicineDrugsFda = drugsFDAService.getDrugFromDrugsFda(brandName);
            MedicineEntity medicineEntity = toMedicineEntity(medicineDrugsFda);
            medicineId = medicineRepository.save(medicineEntity).getId();
        }

        PharmacyMedicineEntity pharmacyMedicineEntity = toPharmacyMedicineEntity(medicineId, pharmacyId);
        pharmacyMedicineRepository.save(pharmacyMedicineEntity);
    }

    @Transactional
    public void deleteMedicineForPharmacy(Long pharmacyId, Long medicineId) {
        pharmacyMedicineRepository.deleteMedicineForPharmacy(pharmacyId, medicineId);
    }

    public List<PharmacyDto> getPharmacies(String name) {
        List<UserEntity> pharmacies = userRepository.findAllByNameContainingIgnoreCaseAndType(name, Type.pharmacy);
        return pharmacies
                .stream()
                .map(this::toPharmacyDto)
                .collect(Collectors.toList());
    }

    public List<MedicineDto> getPharmacyDrugs(Long pharmacyId, String name) throws PharmacyNotFoundException {
        validatePharmacy(pharmacyId);

        List<Long> medicineIds = pharmacyMedicineRepository.findAllByUserId(pharmacyId)
                .stream()
                .map(PharmacyMedicineEntity::getMedicineId)
                .collect(Collectors.toList());

        return medicineRepository.findByIdInAndBrandNameContainingIgnoreCase(medicineIds, name)
                .stream()
                .map(this::toMedicineDto)
                .collect(Collectors.toList());
    }

    public MedicineDto getDrugInPharmacy(Long pharmacyId, Long drugId) throws PharmacyNotFoundException, MedicineNotFoundException {
        validatePharmacy(pharmacyId);
        if (pharmacyMedicineRepository.findByUserIdAndMedicineId(pharmacyId, drugId).isEmpty()) {
            throw new MedicineNotFoundException("There is no such drug in pharmacy with id:" + pharmacyId);
        }
         return toMedicineDto(medicineRepository.findById(drugId)
                 .orElseThrow(() -> new MedicineNotFoundException("There is no medicine with id:" + drugId)));
    }

    private Long getMedicineId(String brandName) {
        Optional<MedicineEntity> medicineEntity = medicineRepository.findByBrandNameContainingIgnoreCase(brandName);
        if (medicineEntity.isPresent()) {
            return medicineEntity.get().getId();
        }
        return -1L;
    }

    private UserEntity validatePharmacy(Long pharmacyId) throws PharmacyNotFoundException {
        Optional<UserEntity> pharmacy = userRepository.findById(pharmacyId);
        if (pharmacy.isEmpty()) {
            throw new PharmacyNotFoundException("There is no pharmacy with the specified id: " + pharmacyId);
        }
        return pharmacy.get();
    }

    private MedicineEntity toMedicineEntity(Drug drug) {
        return modelMapper.map(drug, MedicineEntity.class);
    }

    private PharmacyMedicineEntity toPharmacyMedicineEntity(Long medicineId, Long pharmacyId) {
        PharmacyMedicineEntity pharmacyMedicineEntity = new PharmacyMedicineEntity();
        pharmacyMedicineEntity.setMedicineId(medicineId);
        pharmacyMedicineEntity.setUserId(pharmacyId);

        return  pharmacyMedicineEntity;
    }

    private PharmacyDto toPharmacyDto(UserEntity pharmacy) {
       return modelMapper.map(pharmacy, PharmacyDto.class);
    }

    private MedicineDto toMedicineDto(MedicineEntity medicine) {
        return modelMapper.map(medicine, MedicineDto.class);
    }
}
