package com.codigocerto.backend.domain.dtos;

import com.codigocerto.backend.domain.enums.Status;
import jakarta.validation.constraints.*;

public record UsuarioUpdateDto(

        @Pattern(regexp = "^[a-zA-ZÀ-ÿ\\s]+$", message = "{campo.letras}")
        @Size(min = 3, message = "'${validatedValue}' precisa ter, pelo menos, {min} caracteres.")
        String nome,


        @Pattern(regexp = "^[a-zA-ZÀ-ÿ\\s]+$", message = "{campo.letras}")
        @Size(min = 3, message = "'${validatedValue}' precisa ter, pelo menos, {min} caracteres.")
        String sobrenome,


        @Email(message = "{email.invalido}")
        String email,


        String disponibilidade,


        String linguagem,


        String area,


        @Size(max = 300, message = "O campo '${validatedValue}' não pode ter mais que {max} caracteres")
        String descricao,


        Status status) {
}
