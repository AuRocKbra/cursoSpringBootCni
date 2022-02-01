package br.com.aurock.crusobackend.service.validacao;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ClienteAtualizaValidacao.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ClienteAtualiza {

    String message() default "Erro encontrado no processo de validação";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
