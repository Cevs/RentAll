package com.cevs.rentall.dto.com.cevs.rentall.dto.validation;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;

@Constraint(validatedBy = PasswordMatchesValidator.class)
@Target({TYPE, ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordMatches {
    String message() default "Password don't match";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
