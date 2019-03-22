package com.clyon.items.login.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clyon.common.RespData;

@RestController
@RequestMapping(value = "/login")
public class LoginController {

	@RequestMapping("/userLogin")
	public RespData userLogin() throws Exception {

		return new RespData<>();
	}

}
