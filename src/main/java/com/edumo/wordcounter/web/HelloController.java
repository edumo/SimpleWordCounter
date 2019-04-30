package com.edumo.wordcounter.web;

import java.util.Locale;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edumo.wordcounter.service.WordCounterService;

@RestController
public class HelloController {
	
	@Autowired
	WordCounterService wordCounter;
	
    @RequestMapping("/words/{fileName}")
	public String hello(@PathVariable("fileName") String fileName) {
    	
    	return wordCounter.countWordsOnFile(fileName).toString();
//		return "Hello World";
	}

}