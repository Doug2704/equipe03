package com.codigocerto.backend.domain.dtos;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public record UsuarioResponseDto(

        Long idUsuario,
        String nome,
        String sobrenome,
        String email,
        String disponibilidade,
        String area,
        String linguagem,
        String descricao,
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime dataCadastro,
        String status
) {}