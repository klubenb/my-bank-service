package ru.skillfactory.mybankservice.persistence.specification;

import lombok.experimental.UtilityClass;
import org.springframework.data.jpa.domain.Specification;
import ru.skillfactory.mybankservice.persistence.entity.OperationHistory;
import ru.skillfactory.mybankservice.persistence.entity.OperationHistory_;

import java.time.LocalDateTime;
import java.util.Objects;

@UtilityClass
public class OperationHistorySpecification {

    public static Specification<OperationHistory> createdAtBetween(LocalDateTime from, LocalDateTime to) {
        return (root, query, builder) -> {
            if (Objects.isNull(from) && Objects.isNull(to)) {
                return builder.conjunction();
            } else if (Objects.isNull(from)) {
                return builder.lessThanOrEqualTo(root.get(OperationHistory_.createdAt), to);
            } else if (Objects.isNull(to)) {
                return builder.greaterThanOrEqualTo(root.get(OperationHistory_.createdAt), from);
            }

            return builder.between(root.get(OperationHistory_.createdAt), from, to);
        };
    }
}
