package com.equals.reader.controllers;

import com.equals.reader.models.security.User;
import com.equals.reader.services.SecurityService;
import com.equals.reader.services.UserService;
import com.equals.reader.validators.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private SecurityService securityService;

	@Autowired
	private UserValidator userValidator;

	@PostMapping("/auth/register")
	public ResponseEntity register(@RequestBody User userForm,
		BindingResult bindingResult) {

		userValidator.validate(userForm, bindingResult);

		if (bindingResult.hasErrors()) {
			return new ResponseEntity<>(bindingResult.getAllErrors().stream().map(
				DefaultMessageSourceResolvable::getDefaultMessage), HttpStatus.NOT_ACCEPTABLE);
		}

		userService.save(userForm);

		securityService.autoLogin(userForm.getUsername(), userForm.getPasswordConfirm());

		return new ResponseEntity<>("Registro realizado com sucesso.", HttpStatus.OK);
	}


	@PostMapping("/auth/login")
	public ResponseEntity<String> login(@RequestBody User user) {

		securityService.login(user.getUsername(), user.getPassword());

		return new ResponseEntity<String>("Login realizado com sucesso.", HttpStatus.OK);
	}


	@GetMapping("/auth/isLogged")
	public ResponseEntity<String> isLogged() {

		securityService.isLogged();

		return new ResponseEntity<String>("Usuario está logado.", HttpStatus.OK);
	}

	@PostMapping("/auth/logout")
	public ResponseEntity<String> logout() {

		securityService.logout();

		return new ResponseEntity<String>("Usuario está logado.", HttpStatus.OK);
	}

}