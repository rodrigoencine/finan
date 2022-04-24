package com.example.financeiro.service.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = SetorValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface SetorInsert{
	String message() default "erro de validação";
	Class<?>[] groups() default{};
	Class<? extends Payload>[] payload() default{};
}