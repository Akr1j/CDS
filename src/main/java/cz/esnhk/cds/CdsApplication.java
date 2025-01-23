package cz.esnhk.cds;

import cz.esnhk.cds.model.Users;
import cz.esnhk.cds.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class CdsApplication {
    public static void main(String[] args) {
        SpringApplication.run(CdsApplication.class, args);
    }

}
