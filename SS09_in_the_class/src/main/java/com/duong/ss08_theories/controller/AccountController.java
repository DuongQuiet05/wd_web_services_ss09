package com.duong.ss08_theories.controller;

import com.duong.ss08_theories.model.dto.request.RegisterAccountDTO;
import com.duong.ss08_theories.model.dto.response.ApiResponse;
import com.duong.ss08_theories.model.entity.Account;
import com.duong.ss08_theories.service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/v1/accounts")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @PostMapping
    public ResponseEntity<ApiResponse<Account>> createAccount(@Valid @ModelAttribute RegisterAccountDTO dto) {
        Account account = accountService.createAccount(dto);

        return ResponseEntity.ok(ApiResponse.success(account, "Tạo account thành công"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Account>> detailAccount(@PathVariable("id") int id) {
        Account account = accountService.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Account with ID " + id + " not found"));

        return ResponseEntity.ok(ApiResponse.success(account, "Lấy thông tin account thành công"));
    }

}

