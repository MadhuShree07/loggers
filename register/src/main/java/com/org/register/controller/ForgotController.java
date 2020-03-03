package com.org.register.controller;

import java.util.Objects;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.org.register.dto.ForgotDTO;
import com.org.register.service.ForgotService;

@Component
@RequestMapping
public class ForgotController {
	private static Logger logger = Logger.getLogger(RegisterController.class);

	@Autowired
	private ForgotService service;

	public ForgotController() {
		logger.info(this.getClass().getSimpleName() + " obj created!");
	}

	@RequestMapping("/forgot.do")
	public String onForgot(ForgotDTO dto, ModelMap map) {
		logger.info("info message -->  inside getMessage()....");

		logger.warn("warn message -->  inside getMessage()....");
		logger.error("error message -->  inside getMessage()....");

		try {

			boolean valid = this.service.validateForgotPassword(dto);
			if (valid) {
				String C = this.service.validateForgot(dto);
				if (Objects.nonNull(C)) {

					ModelMap succsess = map.addAttribute("Emial exist message", "Password changed Successfully");

				} else {
					ModelMap failure = map.addAttribute("Email invalid",
							"Entered email is not valid and passwords donot match");
				}

			} else {

				String c = this.service.validateForgot(dto);
				if (!Objects.nonNull(c)) {
					ModelMap a = map.addAttribute("email not exist", "Email is not valid");
				} else {
					ModelMap b = map.addAttribute("email valid", "Passwords dont match");

				}

			}
			logger.info("Invoked onForgot method");
			logger.info(dto);

		} catch (NumberFormatException e) {

			logger.error("--Exception occured--", e);
		}
		return "Forgot";
	}
}
