package br.com.fiap.controller;

import br.com.fiap.exception.MensagemNotFoundException;
import br.com.fiap.model.Mensagem;
import br.com.fiap.service.MensagemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/mensagens")
@RequiredArgsConstructor
public class MensagemController {

    private final MensagemService mensagemService;

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Mensagem> registrarMensagem(
            @Valid @RequestBody Mensagem mensagem
    ) {
        var mensagemResgistrada = mensagemService.registrarMensagem(mensagem);
        return new ResponseEntity<>(mensagemResgistrada, HttpStatus.CREATED);
    }

    @GetMapping(
            value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<?> buscarMensagem(@PathVariable String id) {
        try {
            var uuid = UUID.fromString(id);
            var mensgemEncontrada = mensagemService.obterMensagem(uuid);
            return new ResponseEntity<>(mensgemEncontrada, HttpStatus.OK);
        } catch (MensagemNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

}
