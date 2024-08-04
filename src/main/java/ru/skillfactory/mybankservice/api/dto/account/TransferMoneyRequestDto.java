package ru.skillfactory.mybankservice.api.dto.account;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.util.UUID;

@Schema(description = "Перевод денег")
public record TransferMoneyRequestDto(

        @Schema(description = "Перевод от", requiredMode = Schema.RequiredMode.REQUIRED)
        UUID accountFrom,

        @Schema(description = "Перевод к", requiredMode = Schema.RequiredMode.REQUIRED)
        UUID accountTo,

        @Schema(description = "Сумма", requiredMode = Schema.RequiredMode.REQUIRED)
        BigDecimal amount
) {
}
