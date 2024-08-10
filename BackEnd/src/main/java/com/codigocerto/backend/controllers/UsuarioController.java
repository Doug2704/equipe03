package com.codigocerto.backend.controllers;


import com.codigocerto.backend.domain.dtos.UsuarioRequestDto;
import com.codigocerto.backend.domain.dtos.UsuarioResponseDto;
import com.codigocerto.backend.domain.dtos.UsuarioUpdateDto;
import com.codigocerto.backend.domain.entities.Usuario;
import com.codigocerto.backend.domain.repositories.UsuarioRepository;
import com.codigocerto.backend.domain.services.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final UsuarioRepository usuarioRepository;


    @PostMapping
    public ResponseEntity<UsuarioResponseDto> createUsuario(@Valid @RequestBody UsuarioRequestDto usuarioRequestDto) {
        UsuarioResponseDto usuarioResponseDto = usuarioService.create(usuarioRequestDto);
        return new ResponseEntity<>(usuarioResponseDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDto>> findAll() {
        List<UsuarioResponseDto> usuarioResponseDtos = usuarioService.findAll();
        return new ResponseEntity<>(usuarioResponseDtos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDto> findById(@PathVariable Long id) {
        UsuarioResponseDto usuarioResponseDto = usuarioService.findById(id);
        return new ResponseEntity<>(usuarioResponseDto, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDto> updateUsuario(@RequestBody UsuarioUpdateDto usuarioUpdateDto, @PathVariable Long id) {
        UsuarioResponseDto usuarioResponseDto = usuarioService.update(id, usuarioUpdateDto);
        return new ResponseEntity<>(usuarioResponseDto, HttpStatus.OK);
    }
}
