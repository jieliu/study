package com.tianma.spring.mvc.domain.security.validator;


import com.tianma.spring.mvc.domain.security.UserCreateForm;
import com.tianma.spring.mvc.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserCreateFormValidator implements Validator {

    private static final Logger logger = LoggerFactory.getLogger(UserCreateFormValidator.class);
    private final UserService userService;

    @Autowired
    public UserCreateFormValidator(UserService userService) {

        this.userService = userService;
    }

    public boolean supports(Class<?> clazz) {

        return clazz.equals(UserCreateForm.class);
    }

    public void validate(Object target, Errors errors) {
        logger.debug("Validating {}", target);
        UserCreateForm form = (UserCreateForm) target;
        validatePasswords(errors, form);
        validateEmail(errors, form);
    }

    private void validatePasswords(Errors errors, UserCreateForm form) {
        if (!form.getPassword().equals(form.getPasswordRepeated())) {
            errors.reject("password.no_match", "Passwords do not match");
        }
    }

    private void validateEmail(Errors errors, UserCreateForm form) {
        if (!userService.getUserByEmail(form.getEmail()).getEmail().isEmpty()) {
            errors.reject("email.exists", "User with this email already exists");
        }
    }
}
