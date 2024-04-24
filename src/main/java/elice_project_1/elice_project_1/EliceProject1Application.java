package elice_project_1.elice_project_1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class EliceProject1Application {

	public static void main(String[] args) {
		SpringApplication.run(EliceProject1Application.class, args);
	}

}
