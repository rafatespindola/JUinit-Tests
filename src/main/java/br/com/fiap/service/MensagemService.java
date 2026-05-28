package br.com.fiap.service;

import br.com.fiap.model.Mensagem;

import java.util.List;
import java.util.UUID;

public interface MensagemService {

    Mensagem registrarMensagem(Mensagem mensagem);
    Mensagem obterMensagem(UUID id);
    List<Mensagem> obterMensages();
    Mensagem alterarMensagem(Mensagem mensagem, UUID id);
    void deletarMensagem(UUID id);
}
