package com.duong.ss08_theories.model.dto.request;

import com.duong.ss08_theories.validate.PasswordRegisterMatches;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@PasswordRegisterMatches(message = "Password do not match!")
public class RegisterAccountDTO {
    @NotBlank(message = "Username cannot be blank!")
    private String username;

    @NotBlank(message = "Password cannot be blank!")
    @Size( min = 6, message = "Password must be 6 characters or more!")
    private String password;

    @NotBlank(message = "Confirm password cannot be blank!")
    private String confirmPassword;

    @NotBlank(message = "Email cannot be blank!")
    @Email(message = "Email must be valid!")
    @Pattern(
            regexp = "^[a-zA-Z0-9]{5,}@gmail\\.com$",
            message = "Email must be a valid Gmail address with at least 5 characters before @"
    )
    private String email;

    private boolean gender;

    private MultipartFile file;

}
