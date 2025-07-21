package com.duong.ss08_theories.service.impl;

import com.duong.ss08_theories.model.dto.request.RegisterAccountDTO;
import com.duong.ss08_theories.model.entity.Account;
import com.duong.ss08_theories.repository.AccountRepository;
import com.duong.ss08_theories.service.AccountService;
import com.duong.ss08_theories.service.CloudinaryService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final CloudinaryService cloudinaryService;
    static final Logger log = LoggerFactory.getLogger(AccountServiceImpl.class);

    @Override
    public List<Account> getAll() {
        return List.of();
    }

    @Override
    public Account createAccount(RegisterAccountDTO dto) {
        // 1. Hash mật khẩu
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        String hash = encoder.encode(dto.getPassword());
        dto.setPassword(hash);

        // 2. Upload ảnh lên cloud nếu có
        String imageUrl = null;
        if (dto.getFile() != null && !dto.getFile().isEmpty()) {
            imageUrl = cloudinaryService.uploadFile(dto.getFile());
        }

        // 3. Convert DTO -> Entity
        Account account = convertCreateDTOtoEntity(dto);
        account.setImage(imageUrl); // 4. Gán link ảnh vào entity

        // 5. Lưu account
        return accountRepository.save(account);
    }
    @Override
    public Optional<Account> findById(int id) {
        log.info("{} - getAccount {}", LocalDateTime.now().toString(),id);
        log.warn("Test log warning for this system!");
        return accountRepository.findById(id);
    }

    @Override
    public Account convertCreateDTOtoEntity(RegisterAccountDTO dto) {
        return new Account(
                null,
                dto.getUsername(),
                dto.getPassword(),
                dto.getEmail(),
                dto.isGender(),
                null // image sẽ gán ở bên ngoài
        );
    }
}
