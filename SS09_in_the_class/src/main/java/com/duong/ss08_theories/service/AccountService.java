package com.duong.ss08_theories.service;

import com.duong.ss08_theories.model.dto.request.RegisterAccountDTO;
import com.duong.ss08_theories.model.entity.Account;

import java.util.List;
import java.util.Optional;

public interface AccountService {
    List<Account> getAll ();
    Account createAccount (RegisterAccountDTO dto);
    Optional<Account> findById (int id);

    Account convertCreateDTOtoEntity (RegisterAccountDTO dto);
}
