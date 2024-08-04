package ru.skillfactory.mybankservice.persistence.specification;

import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import lombok.experimental.UtilityClass;
import org.springframework.data.jpa.domain.Specification;
import ru.skillfactory.mybankservice.persistence.entity.Account;
import ru.skillfactory.mybankservice.persistence.entity.Account_;
import ru.skillfactory.mybankservice.persistence.entity.OperationHistory;
import ru.skillfactory.mybankservice.persistence.entity.OperationHistory_;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@UtilityClass
public class OperationHistorySpecification {

    public static Specification<OperationHistory> accountIdEquals(UUID accountId) {
        return (root, query, builder) -> {
            if (Objects.isNull(accountId)) {
                return builder.conjunction();
            }

            Join<OperationHistory, Account> account = root.join(OperationHistory_.account, JoinType.LEFT);
            return builder.equal(account.get(Account_.id), accountId);
        };
    }

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
