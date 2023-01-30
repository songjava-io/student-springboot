package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class HomeService {
	
	final Logger logger = LoggerFactory.getLogger(getClass());

	public void home() {
		logger.info("HomeService home!");
	}
}
