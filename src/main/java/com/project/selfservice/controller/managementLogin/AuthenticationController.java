package com.project.selfservice.controller.managementLogin;

import com.project.selfservice.domain.customer.Customer;
import com.project.selfservice.domain.customer.CustomerRepository;
import com.project.selfservice.domain.customer.DataUserCustomer;
import com.project.selfservice.domain.seller.Seller;
import com.project.selfservice.domain.seller.SellerRepository;
import com.project.selfservice.domain.user.*;
import com.project.selfservice.domain.user.authentication.AuthenticationService;
import com.project.selfservice.domain.user.authentication.DataAuthentication;
import com.project.selfservice.infra.DadosTokenJWT;
import com.project.selfservice.infra.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AuthenticationService authenticationService;


    @PostMapping("/register-seller")
    public ResponseEntity<?> registerSeller(@RequestBody @Valid DataUserSeller dataUser) {

        // Criação do usuário vendedor com a senha criptografada
        if (authenticationService.isAdmin(dataUser.userAdmin())) {
            User user = dataUser.user();
            String senhaCriptografada = passwordEncoder.encode(user.getPassword());
            user.setPassword(senhaCriptografada);

            // Salvamento do usuário
            user = userRepository.save(user);

                Seller seller = new Seller();
                // Criação do vendedor e associando o usuário
                seller.setUser(user);
                seller.setName(dataUser.name());
                seller.setCommission(0.0);
                seller.setRanking(0);
                // Salvamento do vendedor
                sellerRepository.save(seller);
            }
        else {
            return ResponseEntity.ok(false);
        }
        return ResponseEntity.ok(true);
    }

    @PostMapping("register-customer")
    public ResponseEntity<?> registerCustomer(@RequestBody @Valid DataUserCustomer dataUserCustomer) {
        if (customerRepository.existsByPhone(dataUserCustomer.phone())){
            return new ResponseEntity<>("Telefone já existente", HttpStatus.FORBIDDEN);
        }

        // Criação do usuário cliente com a senha criptografada
            User user = dataUserCustomer.user();
            String senhaCriptografada = passwordEncoder.encode(user.getPassword());
            user.setPassword(senhaCriptografada);

            // Salvamento do usuário
            user = userRepository.save(user);
            // criação do user Cliente

            Customer customer = new Customer();

            // Criação do cliente e associando o usuário
            customer.setUser(user);
            customer.setName(dataUserCustomer.name());
            customer.setPhone(dataUserCustomer.phone());

            // Salvamento do cliente
            customerRepository.save(customer);

        return ResponseEntity.ok(true);
    }

    @PostMapping("/login")
    public ResponseEntity<DadosTokenJWT> login(@RequestBody @Valid DataAuthentication data) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var authentication = manager.authenticate(authenticationToken);
        var tokenJWT = tokenService.gerarToken((User) authentication.getPrincipal());

        return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
    }








}