package com.interview;

import java.net.InetAddress;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;

@SpringBootApplication
@Slf4j
public class InterviewApplication {

	public static void main(String[] args) throws Exception{
		ConfigurableApplicationContext application = SpringApplication.run(InterviewApplication.class, args);
		Environment env = application.getEnvironment();
		log.info("\n----------------------------------------------------------\n" +
						"Application '{}' is running! Access URLs:\n" +
						"Login: \thttp://{}:{}/login\n" +
						"Default user name and password is set to be : {},{}"+
						"\n----------------------------------------------------------\n",
				env.getProperty("app.name"),
				InetAddress.getLocalHost().getHostAddress(),
				env.getProperty("server.port"),env.getProperty("sec.default.username"),env.getProperty("sec.default.password"));
	}

}
