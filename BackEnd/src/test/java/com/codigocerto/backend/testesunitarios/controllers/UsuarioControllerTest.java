package com.codigocerto.backend.testesunitarios.controllers;

import com.codigocerto.backend.controllers.UsuarioController;
import com.codigocerto.backend.domain.dtos.UsuarioRequestDto;
import com.codigocerto.backend.domain.dtos.UsuarioResponseDto;
import com.codigocerto.backend.domain.services.UsuarioService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UsuarioControllerTest {

    @InjectMocks
    private UsuarioController usuarioController;

    @Mock
    private UsuarioService usuarioService;

    @Test
    void createUsuario_DeveRetornarUsuarioCriado() {

        UsuarioRequestDto usuarioRequestDto = new UsuarioRequestDto("user", "test", "user@test.com", "total", "front", "javascript", "teste");
        LocalDateTime dataCadastro = LocalDateTime.now();
        UsuarioResponseDto mockUsuarioResponse = new UsuarioResponseDto(1L, "user", "test", "user@test.com", "total", "front", "javascript", "teste", dataCadastro, "FILA_DE_ESPERA");

        when(usuarioService.create(usuarioRequestDto)).thenReturn(mockUsuarioResponse);
        ResponseEntity<UsuarioResponseDto> response = usuarioController.createUsuario(usuarioRequestDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(mockUsuarioResponse, response.getBody());
    }


    @Test
    void findById_DeveRetornarUsuarioCadastrado() {
        Long id = 1L;
        LocalDateTime dataCadastro = LocalDateTime.now();
        UsuarioResponseDto mockUsuario = new UsuarioResponseDto(id, "user", "test", "user@test.com", "total", "front", "javascript", "teste", dataCadastro, "FILA_DE_ESPERA");

        when(usuarioService.findById(id)).thenReturn(mockUsuario);
        ResponseEntity<UsuarioResponseDto> response = usuarioController.findById(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockUsuario, response.getBody());
        assertEquals(dataCadastro, response.getBody().dataCadastro());
    }
    @Test
    void findById_DeveRetornarUsuarioNaoEncontrado() {
        Long id = 1L;
        LocalDateTime dataCadastro = LocalDateTime.now();
        UsuarioResponseDto mockUsuario = new UsuarioResponseDto(2l, "user", "test", "user@test.com", "total", "front", "javascript", "teste", dataCadastro, "FILA_DE_ESPERA");

        when(usuarioService.findById(id)).thenReturn(mockUsuario);
        ResponseEntity<UsuarioResponseDto> response = usuarioController.findById(3l);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNotEquals(mockUsuario, response.getBody());
        assertNotEquals(dataCadastro, response.getBody().dataCadastro());
    }

    @Test
    void findAll_DeveRetornarListaDeUsuariosCadastrados() {

        LocalDateTime dataCadastro = LocalDateTime.now();
        UsuarioResponseDto mockUsuario1 = new UsuarioResponseDto(1l, "user1", "test1", "user1@test.com", "total", "front", "javascript", "teste1", dataCadastro, "FILA_DE_ESPERA");
        UsuarioResponseDto mockUsuario2 = new UsuarioResponseDto(2l, "user2", "test2", "user2@test.com", "total", "front", "javascript", "teste2", dataCadastro, "FILA_DE_ESPERA");

        List<UsuarioResponseDto> mockListUsuarios = new ArrayList<>();
        mockListUsuarios.add(mockUsuario1);
        mockListUsuarios.add(mockUsuario2);

        when(usuarioService.findAll()).thenReturn(mockListUsuarios);
        ResponseEntity<List<UsuarioResponseDto>> response = usuarioController.findAll();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockListUsuarios, response.getBody());
    }
}
