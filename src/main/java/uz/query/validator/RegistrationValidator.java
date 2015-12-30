package uz.query.validator;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import uz.query.models.RegistrationForm;
import uz.query.models.User;
import uz.query.repositories.UserRepository;

/**
 * Created by Vejon on 18.12.2015.
 */
@Component
public class RegistrationValidator implements Validator {
    @Autowired
    private UserRepository userRepository;

    public boolean supports(Class<?> clazz) {
        return RegistrationForm.class.isAssignableFrom(clazz);
    }

    public void validate(Object target, Errors errors) {
        RegistrationForm registrationForm = (RegistrationForm) target;
//        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "displayName", "userName.empty", "Username bo'sh bo'lishi mumkin emas.");
//        String userName = registrationForm.getUserName();
//        if ((userName.length()) > 16) {
//            errors.rejectValue("userName", "userName.tooLong", "UserName 16 simboldan uzun bo'lishi mumkin emas.");
//        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "password.empty", "Parol bo'sh bo'lishi mumkin emas.");
        if (!(registrationForm.getPassword()).equals(registrationForm
                .getConfirmPassword())) {
            errors.rejectValue("confirmPassword", "confirmPassword.passwordDontMatch", "Parollar mos emas.");
        }

        if (!EmailValidator.getInstance().isValid(registrationForm.getEmail())) {
            errors.rejectValue("email", "email.notValid", "Email manzil xato kiritilgan.");
        } else {
//            User user = userRepository.findByUserName(registrationForm.getUserName());
//            User user = userRepository.findByUserName("");
//            if (user != null)
//                if (user.getId() > 0) {
//                    errors.rejectValue("userName", "userName.notValid", "Bunday username mavjud.");
//
//                }
            User user = userRepository.findByEmail(registrationForm.getEmail());
            if (user != null)
                if (user.getId() > 0) {
                    errors.rejectValue("email", "email.notValid", "Bunday email mavjud.");
                }
        }
    }
}
