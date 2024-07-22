package ru.skillfactory.mybankservice.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skillfactory.mybankservice.persistence.entity.Account;

import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {

    Account findById(UUID id);
}
