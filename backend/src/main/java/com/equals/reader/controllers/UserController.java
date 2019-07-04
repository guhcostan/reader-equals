package com.equals.reader.controllers;

import com.equals.reader.models.security.User;
import com.equals.reader.services.SecurityService;
import com.equals.reader.services.UserService;
import com.equals.reader.validators.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
	@Autowired
	private UserService userService;

	@Autowired
	private SecurityService securityService;

	@Autowired
	private UserValidator userValidator;

	@PostMapping("/auth/registration")
	public ResponseEntity<String> registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult) {

		userValidator.validate(userForm, bindingResult);

		userService.save(userForm);

		securityService.autoLogin(userForm.getUsername(), userForm.getPasswordConfirm());

		return new ResponseEntity<String>("Registro realizado com sucesso.", HttpStatus.OK);
	}


	@PostMapping("/auth/login")
	public ResponseEntity<String> login(String login, String password) {

		securityService.login(login, password);


		return new ResponseEntity<String>("Login realizado com sucesso.", HttpStatus.OK);
	}

}