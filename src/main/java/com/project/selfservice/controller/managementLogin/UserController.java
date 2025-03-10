package com.project.selfservice.controller.managementLogin;

import com.project.selfservice.domain.seller.SellerRepository;
import com.project.selfservice.domain.user.User;
import com.project.selfservice.domain.user.UserRepository;
import com.project.selfservice.domain.user.authentication.AuthenticationService;
import com.project.selfservice.domain.user.authentication.ChangePasswordRequest;
import com.project.selfservice.domain.user.authentication.DeleteLogin;
import com.project.selfservice.infra.TokenService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@SecurityRequirement(name = "bearer-key")
@RequestMapping("/admin")
public class UserController {
    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SellerRepository sellerRepository;

    // trocar senhas
    @PostMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestBody @Valid ChangePasswordRequest request) {
        // Recupera o usuário que terá a senha alterada
        User userChange = userRepository.findByLogin(request.login());
        if (userChange == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado.");
        }

        User userLogin = request.user();

        if (authenticationService.isAdmin(userLogin)) {

            // Atualiza a senha do usuário que será alterado
            String senhaCriptografada = passwordEncoder.encode(request.newPassword());
            userChange.setPassword(senhaCriptografada);
            userRepository.save(userChange);

            // Retorna uma resposta com o token JWT
            return ResponseEntity.ok("Senha alterada com sucesso!");
        }

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Usuário não autorizado.");
    }


    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteLogin(@RequestBody @Valid DeleteLogin deleteLogin) {
        System.out.println("ok");
        User userDelete = userRepository.findByLogin(deleteLogin.login());

        // Verifica se o usuário a ser deletado existe
        if (userDelete == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado.");
        }

        // Verifica se quem está tentando excluir é um ADMIN
        if (authenticationService.isAdmin(deleteLogin.user())) {
            // deleta seller e user

            sellerRepository.deleteByUserId(userDelete.getId());

            userRepository.deleteById(userDelete.getId());
            return ResponseEntity.ok("Usuário deletado com sucesso.");
        }

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Usuário não autorizado.");
    }

}
