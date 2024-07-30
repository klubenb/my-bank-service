package ru.skillfactory.mybankservice.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.skillfactory.mybankservice.api.dto.account.BaseResponseDto;
import ru.skillfactory.mybankservice.api.dto.operation.OperationHistoryRequestDto;
import ru.skillfactory.mybankservice.api.dto.operation.OperationHistoryResponseDto;
import ru.skillfactory.mybankservice.mapper.OperationHistoryMapper;
import ru.skillfactory.mybankservice.service.OperationHistoryService;

import java.util.List;

@Tag(name = "OperationHistory", description = "API истории изменений")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/operation")
public class OperationHistoryController {

    private final OperationHistoryService service;
    private final OperationHistoryMapper mapper;

    @PostMapping("")
    @ApiResponse(description = "Успешно", responseCode = "200")
    @Operation(summary = "Поллучить список операций по счету")
    public BaseResponseDto<List<OperationHistoryResponseDto>> getOperationList(@RequestBody OperationHistoryRequestDto dto) {
        return BaseResponseDto.asSuccess(service.findAll(dto).stream().map(mapper::toDto));
    }
}
