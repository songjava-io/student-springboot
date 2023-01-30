package com.example.lombok;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class ExampleData {

	private String name;
	private int age;
	
	@Setter(value = AccessLevel.NONE)
	private long birthDay;
	
	@Getter(value = AccessLevel.NONE)
	private String ci;
	
}
