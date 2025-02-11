package com.health_sync.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@RestController


public class TestController {
	
	public TestController() {
		System.out.println("In test controller");
	}
	

	@GetMapping("/test")
	public ResponseEntity<?> test(){
	  List<Integer> list  = List.of(1,2,3,4,5,6);
	  return ResponseEntity.ok(list);
	  }
}
