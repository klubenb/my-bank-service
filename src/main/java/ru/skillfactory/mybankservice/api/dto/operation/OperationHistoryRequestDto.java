package ru.skillfactory.mybankservice.api.dto.operation;

import java.time.LocalDateTime;
import java.util.UUID;

public record OperationHistoryRequestDto(

        UUID accountId,

        LocalDateTime dateFrom,

        LocalDateTime dateTo
) {
}
