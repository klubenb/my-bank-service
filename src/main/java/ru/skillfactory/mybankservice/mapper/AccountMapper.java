package ru.skillfactory.mybankservice.mapper;

import org.mapstruct.Mapper;
import ru.skillfactory.mybankservice.api.dto.account.CreateAccountDto;
import ru.skillfactory.mybankservice.persistence.entity.Account;

@Mapper
public interface AccountMapper {
    Account toEntity(CreateAccountDto dto);
}
