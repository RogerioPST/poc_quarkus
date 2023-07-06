package rogerio.pst.restclient;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.enterprise.util.Nonbinding;
import jakarta.interceptor.InterceptorBinding;

@InterceptorBinding

@Retention(RetentionPolicy.RUNTIME)

@Target({ ElementType.METHOD, ElementType.TYPE })

public @interface LogTransacaoRestClient {

	@Nonbinding
	ComunicacaoApiEnum operacao() default ComunicacaoApiEnum.NOT_FOUND;

	@Nonbinding
	String sistema() default "";

}
