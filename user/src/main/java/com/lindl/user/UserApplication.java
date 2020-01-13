package com.lindl.user;

import com.lindl.user.common.exception.MyCodeMsg;
import com.lindl.user.common.exception.MyExceptionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class UserApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserApplication.class, args);
	}

	@Bean
	public MyExceptionFactory buildMyExceptionFactory() {
		return new MyExceptionFactory(new MyCodeMsg());
	}

}
