package com.project.selfservice.domain.user.authentication;

import com.project.selfservice.domain.user.Role;
import com.project.selfservice.domain.user.User;
import com.project.selfservice.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements UserDetailsService {
        @Autowired
        private UserRepository repository;

        @Autowired
        private PasswordEncoder passwordEncoder;

        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            return repository.findByLogin(username);
        }

        public boolean isAdmin(User userlogin){
                User userAdmin = repository.findByLogin(userlogin.getLogin());
                if (userAdmin == null) {
                        System.out.println("kkk");
                        return false;
                }

                // Verifica se a senha do administrador está correta
                if (!passwordEncoder.matches(userlogin.getPassword(), userAdmin.getPassword())) {
                        System.out.println("111");
                        return false;
                }

                // Se a senha estiver correta, verifica se o usuário tem permissão de ADMIN
                if (userAdmin.getRole().equals(Role.ADMIN)){
                        return true;
                }
                System.out.println("222");
                return false;
        }
}
