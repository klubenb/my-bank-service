package ru.skillfactory.mybankservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.skillfactory.mybankservice.api.dto.operation.OperationHistoryRequestDto;
import ru.skillfactory.mybankservice.persistence.entity.OperationHistory;
import ru.skillfactory.mybankservice.persistence.repository.OperationHistoryRepository;

import java.util.List;

import static org.springframework.data.jpa.domain.Specification.where;
import static ru.skillfactory.mybankservice.persistence.specification.OperationHistorySpecification.accountIdEquals;
import static ru.skillfactory.mybankservice.persistence.specification.OperationHistorySpecification.createdAtBetween;

@Service
@RequiredArgsConstructor
@Slf4j
public class OperationHistoryService {

    private final OperationHistoryRepository repository;

    @Transactional(readOnly = true)
    public List<OperationHistory> findAll(OperationHistoryRequestDto dto) {
        log.info("Find operation history for account: {}", dto.accountId());
        var spec = where(accountIdEquals(dto.accountId()))
                .and(createdAtBetween(dto.dateFrom(), dto.dateTo()));
        return repository.findAll(spec);
    }

    @Transactional
    public OperationHistory save(OperationHistory newOperationHistory) {
        log.info("Save operation history: {}", newOperationHistory);
        return repository.save(newOperationHistory);
    }
}
