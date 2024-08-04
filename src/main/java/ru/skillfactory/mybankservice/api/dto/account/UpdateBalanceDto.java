package ru.skillfactory.mybankservice.api.dto.account;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.util.UUID;

@Schema(description = "Изменение баланса")
public record UpdateBalanceDto(

        @Schema(description = "Id пользователя", requiredMode = Schema.RequiredMode.REQUIRED)
        UUID id,

        @Schema(description = "Сумма", requiredMode = Schema.RequiredMode.REQUIRED)
        BigDecimal amount
) {
}
