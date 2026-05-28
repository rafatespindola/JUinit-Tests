package br.com.fiap.helper;

import br.com.fiap.model.Mensagem;

import java.util.ArrayList;
import java.util.List;

public abstract class MensagemHelper {

    public static Mensagem gerarMensagem(){
        return Mensagem.builder()
                .usuario("Rafael")
                .conteudo("I like pudim")
                .build();
    }

    public static Mensagem gerarMensagem(String user, String phrase){
        return Mensagem.builder()
                .usuario(user)
                .conteudo(phrase)
                .build();
    }

    public static List<Mensagem> gerarListaMensagens() {
        Mensagem m1 = MensagemHelper.gerarMensagem("Rafael", "I like to code");
        Mensagem m2 = MensagemHelper.gerarMensagem("Natalia", "I like to study");
        Mensagem m3 = MensagemHelper.gerarMensagem("Ana", "I like to play");

        List<Mensagem> mensagens = new ArrayList<Mensagem>();

        mensagens.add(m1);
        mensagens.add(m2);
        mensagens.add(m3);

        return mensagens;
    }
}
