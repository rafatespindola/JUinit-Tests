package br.com.fiap.repository;

import br.com.fiap.helper.MensagemHelper;
import br.com.fiap.model.Mensagem;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

public class MensagemRepositoryTest {

    @Mock
    private MensagemRepository mensagemRepository;

    AutoCloseable mock;

    @BeforeEach
    void setup(){
        mock = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void teardown() throws Exception {
        mock.close();
    }

    @Test
    void devePermitirRegistrarMensagem(){
        // Arrange
        var mensagem = MensagemHelper.gerarMensagem();

        when(mensagemRepository.save(any(Mensagem.class))).thenReturn(mensagem);

        // Act
        var mensagemArmazenada = mensagemRepository.save(mensagem);

        // Assert
        verify(mensagemRepository, times(1)).save(mensagem);
    }


    @Test
    void devePermitirConsultarMensagem(){
        // Arrange
        var id = UUID.randomUUID();
        var mensagem = MensagemHelper.gerarMensagem();
        mensagem.setId(id);

        when(mensagemRepository.findById(any(UUID.class))).thenReturn(Optional.of(mensagem));

        // Act
        var mensagemEncontrada = mensagemRepository.findById(id);

        // Assert
        assertThat(mensagemEncontrada)
                .isPresent()
                .containsInstanceOf(Mensagem.class)
                .contains(mensagem);
    }

    @Test
    void devePermitirApagarMensagem(){
        // Arrange
        var id = UUID.randomUUID();
        var mensagem = MensagemHelper.gerarMensagem();
        mensagem.setId(id);

        doNothing().when(mensagemRepository).deleteById(any(UUID.class));

        // Act
        mensagemRepository.deleteById(id);

        // Assert
        verify(mensagemRepository, times(1)).deleteById(id);
    }

}
