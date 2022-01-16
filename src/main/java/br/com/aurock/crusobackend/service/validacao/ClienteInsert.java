package br.com.aurock.crusobackend.service.validacao;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ClienteInsertValidacao.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ClienteInsert {

    String message() default "Validação encontrou um erro";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
