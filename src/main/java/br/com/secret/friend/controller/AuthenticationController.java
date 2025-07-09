package br.com.secret.friend.controller;

import br.com.secret.friend.dto.DateJWT;
import br.com.secret.friend.dto.userDTO.AuthenticationData;
import br.com.secret.friend.model.User;
import br.com.secret.friend.service.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/login")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<DateJWT> efetuarLogin(@RequestBody @Valid AuthenticationData dados) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(dados.email(), dados.password());
        var authentication = manager.authenticate(authenticationToken);
        var tokenJWT = tokenService.generatedToken((User) authentication.getPrincipal());
        return ResponseEntity.ok(new DateJWT(tokenJWT));
    }
}
