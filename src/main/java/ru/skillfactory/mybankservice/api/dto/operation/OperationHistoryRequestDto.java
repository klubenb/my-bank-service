package ru.skillfactory.mybankservice.api.dto.operation;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.UUID;

@Schema(description = "Поиск операций")
public record OperationHistoryRequestDto(

        @Schema(description = "Id счета", requiredMode = Schema.RequiredMode.REQUIRED)
        UUID accountId,

        @Schema(description = "С даты создания", type = "string", example = "2023-11-15 09:00:00")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime dateFrom,

        @Schema(description = "По дату создания", type = "string", example = "2023-11-15 09:00:00")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime dateTo
) {
}
