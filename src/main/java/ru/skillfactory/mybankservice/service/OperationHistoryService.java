package ru.skillfactory.mybankservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.skillfactory.mybankservice.api.dto.operation.OperationHistoryRequestDto;
import ru.skillfactory.mybankservice.persistence.entity.OperationHistory;
import ru.skillfactory.mybankservice.persistence.repository.OperationHistoryRepository;

import java.util.List;

import static org.springframework.data.jpa.domain.Specification.where;
import static ru.skillfactory.mybankservice.persistence.specification.OperationHistorySpecification.createdAtBetween;

@Service
@RequiredArgsConstructor
public class OperationHistoryService {

    private final OperationHistoryRepository repository;

    @Transactional(readOnly = true)
    public List<OperationHistory> findAll(OperationHistoryRequestDto dto) {
        var spec = where(createdAtBetween(dto.dateFrom(), dto.dateTo()));
        return repository.findAllByAccountId(dto.accountId(), spec);
    }

    @Transactional
    public OperationHistory create(OperationHistory newOperationHistory) {
        return repository.save(newOperationHistory);
    }
}
