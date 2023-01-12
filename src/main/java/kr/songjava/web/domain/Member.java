package kr.songjava.web.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Member {

	private String name;
	private String age;
	
	@Setter(value = AccessLevel.NONE)
	private String ci;
	
	@Getter(value = AccessLevel.NONE)
	private String di;
	
	
}
