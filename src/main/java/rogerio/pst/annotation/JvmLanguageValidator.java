package rogerio.pst.annotation;

import java.util.Arrays;
import java.util.List;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class JvmLanguageValidator implements ConstraintValidator<JvmLanguage, String> {
	private List<String> favoriteLanguages = Arrays.asList("java");

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return favoriteLanguages.stream().anyMatch(l -> l.equalsIgnoreCase(value));
	}
}