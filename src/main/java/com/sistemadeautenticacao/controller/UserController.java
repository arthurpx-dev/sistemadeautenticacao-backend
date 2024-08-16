package com.sistemadeautenticacao.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sistemadeautenticacao.model.User;

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping
    public ResponseEntity<String> getUser(Authentication authentication) {
        // Acessa o usuário autenticado
        Object principal = authentication.getPrincipal();
        StringBuilder response = new StringBuilder();

        // Verifica se o principal é uma instância da sua classe personalizada
        if (principal instanceof User) {
            User user = (User) principal;
            String username = user.getName(); // Assumindo que você tem um método getName()
            String senhaToken = user.getPassword(); // Assumindo que você tem um método getPassword()

            response.append("Usuário autenticado:\n");
            response.append("Nome: ").append(username).append("\n");
            response.append("SenhaToken: ").append(senhaToken).append("\n");
        } else {
            response.append("Usuário autenticado: ").append(principal.toString()).append("\n");
        }

        return ResponseEntity.ok(response.toString());
    }

}
