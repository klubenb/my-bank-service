package ru.skillfactory.mybankservice.persistence.repository;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.skillfactory.mybankservice.persistence.entity.OperationHistory;

import java.util.List;
import java.util.UUID;

public interface OperationHistoryRepository extends JpaRepository<OperationHistory, UUID>, JpaSpecificationExecutor<OperationHistory> {

    List<OperationHistory> findAllByAccountId(UUID accountId, Specification<OperationHistory> specification);
}
