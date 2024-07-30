package ru.skillfactory.mybankservice.api.dto.account;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

@Schema(description = "Схема создания счета")
public record CreateAccountDto(
        @Schema(description = "Текущий баланс", requiredMode = Schema.RequiredMode.REQUIRED)
        BigDecimal balance
) {
}