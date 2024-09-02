package com.rest.restapi.helloworld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

// REST API
@RestController
public class HelloWorldController {
	
	// /hello-world  -> HELLO WORLD
	@GetMapping("/hello-world")
	public String helloWorld() {
		return "Hello World";
	}
	
	@GetMapping("/hello-world-bean")
	public HelloWorldBean helloWorldbean() {
		return new HelloWorldBean("Hello World");
	}
	
	@GetMapping("/hello-world-bean/path-variable/{name}")
	public HelloWorldBean pathVariable(@PathVariable String name) {
		return new HelloWorldBean("Hello World,"+name);
	}
	
}
