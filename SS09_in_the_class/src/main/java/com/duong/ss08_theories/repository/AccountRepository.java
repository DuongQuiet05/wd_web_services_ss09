package com.duong.ss08_theories.repository;

import com.duong.ss08_theories.model.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,Integer> {
}
