package clinicManagement.util.enums.specification;

import clinicManagement.entity.PatientEntity;
import org.springframework.data.jpa.domain.Specification;

public class PatientSpecification {
    public static Specification<PatientEntity> hasFullName(String fullName){
        if (fullName == null) {
            return ((root, query, criteriaBuilder) ->
                    criteriaBuilder.conjunction());
        }
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(root.get("fullName")), "%" +fullName.toLowerCase() + "%" ));
    }
    public static Specification<PatientEntity> hasPhone(String phone){
        if (phone == null) {
            return ((root, query, criteriaBuilder) ->
                    criteriaBuilder.conjunction());
        }
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get("phone"), "%" + phone + "%"));
    }
        public static Specification<PatientEntity> hasAddress(String address){
            if (address == null) {
                return ((root, query, criteriaBuilder) ->
                        criteriaBuilder.conjunction());
            }
            return ((root, query, criteriaBuilder) ->
                    criteriaBuilder.like(criteriaBuilder.lower(root.get("address")), "%" + address.toLowerCase() + "%"));
        }

}
