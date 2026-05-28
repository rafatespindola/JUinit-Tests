package br.com.fiap.service;

import br.com.fiap.exception.MensagemNotFoundException;
import br.com.fiap.model.Mensagem;
import br.com.fiap.repository.MensagemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service
@RequiredArgsConstructor
public class MensagemServiceImp implements MensagemService{

    private final MensagemRepository mensagemRepository;

    @Override
    public Mensagem registrarMensagem(Mensagem mensagem) {
        return mensagemRepository.save(mensagem);
    }

    @Override
    public Mensagem obterMensagem(UUID id){
        return mensagemRepository.findById(id)
                .orElseThrow( () -> new MensagemNotFoundException("Mensagem não encontrada"));
    }

    @Override
    public List<Mensagem> obterMensages() {
        return mensagemRepository.findAll();
    }

    @Override
    public Mensagem alterarMensagem(Mensagem mensagemNova, UUID uuid) {
        Mensagem mensagemExistente = mensagemRepository.findById(uuid)
                .orElseThrow(() -> new MensagemNotFoundException("Mensagem não encontrada"));

        mensagemExistente.setConteudo(mensagemNova.getConteudo());

        return mensagemRepository.save(mensagemExistente);
    }

    @Override
    public void deletarMensagem(UUID id) {
        mensagemRepository.findById(id)
                        .orElseThrow(() -> new MensagemNotFoundException("Mensagem não encontrada"));
        mensagemRepository.deleteById(id);
    }
}




















