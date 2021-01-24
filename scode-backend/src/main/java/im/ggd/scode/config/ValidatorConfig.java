package im.ggd.scode.config;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.hibernate.validator.HibernateValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.SpringConstraintValidatorFactory;

@Configuration
public class ValidatorConfig {

    @Autowired
    private AutowireCapableBeanFactory autowireCapableBeanFactory;

    @Bean
    public Validator validator() {
        ValidatorFactory validatorFactory;

        validatorFactory = Validation.byProvider(HibernateValidator.class)
            .configure()
            .constraintValidatorFactory(
                new SpringConstraintValidatorFactory(autowireCapableBeanFactory)
            )
            .addProperty("hibernate.validator.fail_fast", "false")
            .buildValidatorFactory();

        return validatorFactory.getValidator();
    }

}
