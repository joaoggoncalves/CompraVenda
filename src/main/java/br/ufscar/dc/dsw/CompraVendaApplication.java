package br.ufscar.dc.dsw;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.dao.IUsuarioDAO;

@SpringBootApplication
public class CompraVendaApplication {

	public static void main(String[] args) {
		SpringApplication.run(CompraVendaApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(IUsuarioDAO dao, BCryptPasswordEncoder encoder) {
		return (args) -> {
			Usuario u1 = new Usuario();
			u1.setUsername("user");
			u1.setPassword(encoder.encode("user"));
			u1.setRole("ROLE_USER");
			u1.setNome("Kleber");
			//dao.save(u1);
			Usuario u2 = new Usuario();
			u2.setUsername("admin");
			u2.setPassword(encoder.encode("admin"));
			u2.setRole("ROLE_ADMIN");
			//dao.save(u2);
			Usuario u3 = new Usuario();
			u3.setUsername("loja");
			u3.setPassword(encoder.encode("loja"));
			u3.setRole("ROLE_LOJA");
			u3.setNome("Lojinha");
			//dao.save(u3);
		};
	}

}
