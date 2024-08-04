package ru.skillfactory.mybankservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.skillfactory.mybankservice.api.dto.operation.OperationHistoryResponseDto;
import ru.skillfactory.mybankservice.persistence.entity.OperationHistory;

@Mapper
public interface OperationHistoryMapper {

    @Mapping(target = "accountId", source = "account.id")
    OperationHistoryResponseDto toDto(OperationHistory operationHistory);
}
