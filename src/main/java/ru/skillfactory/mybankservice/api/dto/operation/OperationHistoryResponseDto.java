package ru.skillfactory.mybankservice.api.dto.operation;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import ru.skillfactory.mybankservice.persistence.entity.enumeration.OperationType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Schema(description = "Список операций")
public record OperationHistoryResponseDto(

        @Schema(description = "ID счета")
        UUID accountId,

        @Schema(description = "Тип операции")
        OperationType type,

        @Schema(description = "Измененная сумма")
        BigDecimal amount,

        @Schema(description = "Дата создания", type = "string", example = "2023-11-15 09:00:00")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime createdAt
) {
}
