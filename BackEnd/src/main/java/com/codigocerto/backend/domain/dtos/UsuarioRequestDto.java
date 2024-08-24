package com.codigocerto.backend.domain.dtos;

import jakarta.validation.constraints.*;

public record UsuarioRequestDto(
    @NotBlank(message = "Nome é obrigatório")
    @Pattern(regexp = "^[a-zA-ZÀ-ÿ\\s]+$", message = "Apenas letras devem ser digitadas")
    @Size(min = 3, message = "Nome precisa ter, pelo menos, {min} caracteres.")
    String nome,

    @NotBlank(message = "Sobrenome é obrigatório")
    @Pattern(regexp = "^[a-zA-ZÀ-ÿ\\s]+$", message = "Apenas letras devem ser digitadas")
    @Size(min = 3, message = "Sobrenome precisa ter, pelo menos, {min} caracteres.")
    String sobrenome,

    @NotBlank(message = "E-mail é obrigatório")
    @Email(message = "Formato do email é inválido")
    String email,

    @NotBlank(message = "E-mail é obrigatório")
    String disponibilidade,

    @NotBlank(message = "Linguagem é obrigatório")
    String linguagem,

    @NotBlank(message = "Area é obrigatório")
    String area, 

    @NotBlank(message = "Descrição é obrigatório")
    @Size(max = 300, message = "O campo descrição não pode ter mais que {max} caracteres")
    String descricao
) {}