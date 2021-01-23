package im.ggd.scode.validation.constraints.uniq;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import im.ggd.scode.service.UserService;

public class UniqUsernameValidator implements ConstraintValidator<UniqUsername, String> {

    @Autowired
    private UserService userService;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return !userService.isUsernameExists(value);
    }

}
