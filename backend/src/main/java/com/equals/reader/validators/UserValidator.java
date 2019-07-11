package com.equals.reader.validators;

import com.equals.reader.models.security.User;
import com.equals.reader.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

	@Autowired
	private UserService userService;

	@Override
	public boolean supports(Class<?> aClass) {
		return User.class.equals(aClass);
	}

	@Override
	public void validate(Object o, Errors errors) {
		User user = (User) o;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty",
			"Campos usuario não deve ser vazio.");
		if (user.getUsername().length() < 6 || user.getUsername().length() > 32) {
			errors.rejectValue("username", "Size.userForm.username",
				"O campo de usuario deve conter de 6 a 32 digitos.");
		}
		if (userService.findByUsername(user.getUsername()) != null) {
			errors.rejectValue("username", "Duplicate.userForm.username",
				"Nome de usuário não disponivel.");
		}

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty",
			"Campos senha não deve ser vazio.");
		if (user.getPassword().length() < 8 || user.getPassword().length() > 32) {
			errors.rejectValue("password", "Size.userForm.password",
				"O campo de usuario deve conter de 8 a 32 digitos.");
		}

		if (!user.getPasswordConfirm().equals(user.getPassword())) {
			errors.rejectValue("passwordConfirm", "Diff.userForm.passwordConfirm",
				"Confirmação de senha invalida.");
		}
	}
}