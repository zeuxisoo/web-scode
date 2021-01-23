package im.ggd.scode.validation.constraints.uniq;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = UniqEmailValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD })
public @interface UniqEmail {

    public String message() default "The email already exists";

    public Class<?>[] groups() default {};

    public Class<? extends Payload>[] payload() default {};

}
