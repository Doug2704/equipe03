package com.codigocerto.backend.controllers;

import com.codigocerto.backend.domain.dtos.UsuarioRequestDto;
import com.codigocerto.backend.domain.dtos.UsuarioResponseDto;
import com.codigocerto.backend.domain.services.UsuarioService;
import com.codigocerto.backend.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class UsuarioControllerTest {

    private static final Long ID_USUARIO = 1L;
    private static final Long ID_NAO_EXISTENTE = 3L;
    private static final String NOME_USUARIO = "user";
    private static final String SOBRENOME_USUARIO = "test";
    private static final String EMAIL_USUARIO = "user@test.com";
    private static final String DISPONIBILIDADE = "total";
    private static final String AREA = "front";
    private static final String LINGUAGEM = "javascript";
    private static final String STATUS = "FILA_DE_ESPERA";
    private static final String MENSAGEM_USUARIO_NAO_ENCONTRADO = "Usuário não encontrado";

    @InjectMocks
    private UsuarioController usuarioController;

    @Mock
    private UsuarioService usuarioService;

    private UsuarioRequestDto usuarioRequestDto;
    private UsuarioResponseDto mockUsuarioResponse;
    private LocalDateTime dataCadastro;

    @BeforeEach
    void setUp() {
        usuarioRequestDto = new UsuarioRequestDto(NOME_USUARIO, SOBRENOME_USUARIO, EMAIL_USUARIO, DISPONIBILIDADE, AREA, LINGUAGEM, "teste");
        dataCadastro = LocalDateTime.now();
        mockUsuarioResponse = new UsuarioResponseDto(ID_USUARIO, NOME_USUARIO, SOBRENOME_USUARIO, EMAIL_USUARIO, DISPONIBILIDADE, AREA, LINGUAGEM, "teste", dataCadastro, STATUS);
    }

    @Test
    void createUsuario_DeveRetornarUsuarioCriado() {
        when(usuarioService.create(usuarioRequestDto)).thenReturn(mockUsuarioResponse);

        ResponseEntity<UsuarioResponseDto> response = usuarioController.createUsuario(usuarioRequestDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(mockUsuarioResponse, response.getBody());
    }

    @Test
    void findById_DeveRetornarUsuarioCadastrado() {
        when(usuarioService.findById(ID_USUARIO)).thenReturn(mockUsuarioResponse);

        ResponseEntity<UsuarioResponseDto> response = usuarioController.findById(ID_USUARIO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockUsuarioResponse, response.getBody());
        assertEquals(dataCadastro, Objects.requireNonNull(response.getBody()).dataCadastro());
    }

    @Test
    void findById_DeveRetornarUsuarioNaoEncontrado() {
        when(usuarioService.findById(ID_NAO_EXISTENTE)).thenThrow(new ResourceNotFoundException(MENSAGEM_USUARIO_NAO_ENCONTRADO));

        assertThrows(ResourceNotFoundException.class, () -> usuarioController.findById(ID_NAO_EXISTENTE));
    }

    @Test
    void findAll_DeveRetornarListaDeUsuariosCadastrados() {
        UsuarioResponseDto mockUsuario1 = new UsuarioResponseDto(1L, "user1", "test1", "user1@test.com", DISPONIBILIDADE, AREA, LINGUAGEM, "teste1", dataCadastro, STATUS);
        UsuarioResponseDto mockUsuario2 = new UsuarioResponseDto(2L, "user2", "test2", "user2@test.com", DISPONIBILIDADE, AREA, LINGUAGEM, "teste2", dataCadastro, STATUS);
        List<UsuarioResponseDto> mockListUsuarios = Arrays.asList(mockUsuario1, mockUsuario2);

        when(usuarioService.findAll()).thenReturn(mockListUsuarios);

        ResponseEntity<List<UsuarioResponseDto>> response = usuarioController.findAll();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockListUsuarios, response.getBody());
    }
}
