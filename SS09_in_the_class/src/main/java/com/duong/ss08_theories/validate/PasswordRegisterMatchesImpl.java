package com.duong.ss08_theories.validate;

import com.duong.ss08_theories.model.dto.request.RegisterAccountDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordRegisterMatchesImpl implements ConstraintValidator<PasswordRegisterMatches, RegisterAccountDTO> {
    @Override
    public boolean isValid(RegisterAccountDTO dto, ConstraintValidatorContext context) {
        if (dto.getPassword() == null || dto.getConfirmPassword() == null || dto.getConfirmPassword().isBlank()) {
            return true;
        }

        boolean match = dto.getPassword().equals(dto.getConfirmPassword());
        if (!match) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
                    .addPropertyNode("confirmPassword")
                    .addConstraintViolation();
        }

        return match;
    }
}