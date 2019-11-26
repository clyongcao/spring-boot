package com.clyon.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/name")
public class ResponseBodyController {


	@RequestMapping("/sen")
	@ResponseBody
	public String getString(){

		
		return "I Love You 森";

	}

	

}
