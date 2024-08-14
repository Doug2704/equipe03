package com.codigocerto.backend.controllers;


import com.codigocerto.backend.domain.dtos.UsuarioRequestDto;
import com.codigocerto.backend.domain.dtos.UsuarioResponseDto;
import com.codigocerto.backend.domain.services.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/usuarios")
@Tag(name = "Usuário", description = "tratamento de dados dos usuários")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @Operation(summary = "cria um novo usuário")
    @PostMapping
    public ResponseEntity<UsuarioResponseDto> createUsuario(@Valid @RequestBody UsuarioRequestDto usuarioRequestDto) {
        UsuarioResponseDto usuarioResponseDto = usuarioService.create(usuarioRequestDto);
        return new ResponseEntity<>(usuarioResponseDto, HttpStatus.CREATED);
    }

    @Operation(summary = "lista todos os usuários")
    @GetMapping
    public ResponseEntity<List<UsuarioResponseDto>> findAll() {
        List<UsuarioResponseDto> usuarioResponseDtos = usuarioService.findAll();
        return new ResponseEntity<>(usuarioResponseDtos, HttpStatus.OK);
    }
    @Operation(summary = "busca um usuário pelo ID")
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDto> findById(@PathVariable Long id) {
        UsuarioResponseDto usuarioResponseDto = usuarioService.findById(id);
        return new ResponseEntity<>(usuarioResponseDto, HttpStatus.OK);
    }

}
