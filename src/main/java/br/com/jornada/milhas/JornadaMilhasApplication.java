package br.com.jornada.milhas;

import br.com.jornada.milhas.config.ConfigEnv;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JornadaMilhasApplication {

	public static void main(String[] args) {
		ConfigEnv configEnv = new ConfigEnv();
		SpringApplication.run(JornadaMilhasApplication.class, args);
	}

}
