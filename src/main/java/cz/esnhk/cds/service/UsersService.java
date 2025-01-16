package cz.esnhk.cds.service;

import cz.esnhk.cds.model.Users;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UsersService extends UserDetailsService {
    Users findByUsername(String username);

    void save(Users user);
}