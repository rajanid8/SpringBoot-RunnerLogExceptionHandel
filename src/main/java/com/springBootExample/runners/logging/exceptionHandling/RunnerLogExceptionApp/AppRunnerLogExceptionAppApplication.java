package com.springBootExample.runners.logging.exceptionHandling.RunnerLogExceptionApp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppRunnerLogExceptionAppApplication implements ApplicationRunner {

	private static final Logger logger = LoggerFactory.getLogger(AppRunnerLogExceptionAppApplication.class);

	@Value("${spring.application.name}") //gives the name of the application which was mentioned in application.properties file
	private String name;

	public static void main(String[] args) {
		SpringApplication.run(AppRunnerLogExceptionAppApplication.class, args);
	}

	//Method from ApplicationRunner interface
	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.out.println("Hello World from Application Runner");
		System.out.println("Name of the Application: "+name);
	}

	/* Following code for run another Runner implementation. We can use either above ApplicationRunner or below CommnadLineRunner.
	public class CLIRunnerLogExceptionAppApplication  implements CommandLineRunner {

		public static void main(String[] args) {
			SpringApplication.run(CLIRunnerLogExceptionAppApplication.class, args);
		}

		@Override
		public void run(String... args) throws Exception {
			System.out.println("Hello world from Command Line Runner");
		}
	}*/
}
