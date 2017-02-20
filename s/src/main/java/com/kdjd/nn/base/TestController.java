package com.kdjd.nn.base;

import java.util.Date;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/main")
public class TestController {

	@RequestMapping(value = "/")
	public Object main() {
		System.out.println("test request at " + new Date());
		return new Date().toString();
	}
}
