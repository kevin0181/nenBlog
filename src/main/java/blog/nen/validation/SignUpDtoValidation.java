package blog.nen.validation;

import blog.nen.dto.SignUpDto;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpDtoValidation implements Validator {

    private static final String emailCheck = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
    private Pattern pattern;

    public SignUpDtoValidation() {
        pattern = Pattern.compile(emailCheck);
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return SignUpDtoValidation.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        SignUpDto signUpDto = (SignUpDto) target;

        if (signUpDto.getEmail() == null || signUpDto.getEmail().trim().isEmpty()) {
            errors.rejectValue("email", "emailNull");
        } else {
            Matcher matcher = pattern.matcher(signUpDto.getEmail());
            if (!matcher.matches()) {
                errors.rejectValue("email", "emailBad");
            }
        }

        ValidationUtils.rejectIfEmpty(errors, "Password", "passwordNull");
        ValidationUtils.rejectIfEmpty(errors, "Phone", "phoneNull");

    }
}
