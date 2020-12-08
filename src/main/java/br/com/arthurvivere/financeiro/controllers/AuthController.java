package br.com.arthurvivere.financeiro.controllers;

import br.com.arthurvivere.financeiro.config.security.TokenService;
import br.com.arthurvivere.financeiro.models.Usuario;
import br.com.arthurvivere.financeiro.models.dtos.TokenDto;
import br.com.arthurvivere.financeiro.models.forms.LoginForm;
import br.com.arthurvivere.financeiro.services.UsuarioServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioServices usuarioServices;

    @PostMapping
    public ResponseEntity<TokenDto> autenticar(@RequestBody @Valid LoginForm loginForm) {
        UsernamePasswordAuthenticationToken dadosLogin = new UsernamePasswordAuthenticationToken(
                loginForm.getLogin(),
                loginForm.getSenha());
        try {
            Authentication authentication = authManager.authenticate(dadosLogin);

            Optional<Usuario> usuarioOptional = usuarioServices.findByLogin(loginForm.getLogin());

            if(usuarioOptional.isPresent()) {
                Usuario usuario = usuarioOptional.get();

                if(loginForm.getSenha().equals(usuario.getPassword()) && usuario.getStatus() == 'A'){
                    String token = tokenService.gerarToken(authentication);
                    return ResponseEntity.ok().body(new TokenDto(token, "Bearer"));
                }
            }
            return ResponseEntity.badRequest().build();
        }catch(AuthenticationException ex) {
            return ResponseEntity.badRequest().build();
        }
    }
}
