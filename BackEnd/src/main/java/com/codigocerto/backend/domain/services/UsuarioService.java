package com.codigocerto.backend.domain.services;

import com.codigocerto.backend.domain.dtos.Mapper;
import com.codigocerto.backend.domain.dtos.UsuarioRequestDto;
import com.codigocerto.backend.domain.dtos.UsuarioResponseDto;
import com.codigocerto.backend.domain.dtos.UsuarioUpdateDto;
import com.codigocerto.backend.domain.entities.Usuario;
import com.codigocerto.backend.domain.enums.Status;
import com.codigocerto.backend.domain.repositories.UsuarioRepository;
import com.codigocerto.backend.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository repository;
    private static final String NENHUM_USUARIO = "Nenhum usuario encontrada com ID: ";

    public List<UsuarioResponseDto> findAll() {
        log.info("Listando todos os usuarios");
        return repository.findAll().stream().map(Mapper::toDto).toList();
    }

    public UsuarioResponseDto findById(Long id) {
        log.info("Buscando usuario com ID: {}", id);
        return repository.findById(id).map(Mapper::toDto).orElseThrow(() -> new ResourceNotFoundException(NENHUM_USUARIO + id));
    }

    public UsuarioResponseDto create(UsuarioRequestDto usuarioRequestDto) {
        Usuario usuario = new Usuario();
        usuario.setNome(usuarioRequestDto.nome());
        usuario.setEmail(usuarioRequestDto.email());
        usuario.setSobrenome(usuarioRequestDto.sobrenome());
        usuario.setLinguagem(usuarioRequestDto.linguagem());
        usuario.setArea(usuarioRequestDto.area());
        usuario.setDescricao(usuarioRequestDto.descricao());
        usuario.setDisponibilidade(usuarioRequestDto.disponibilidade());
        usuario.setStatus(Status.FILA_DE_ESPERA);

        // Define o fuso horário para São Paulo, Brasil
        ZoneId zoneId = ZoneId.of("America/Sao_Paulo");
        ZonedDateTime zonedDateTime = ZonedDateTime.now(zoneId);

        // Converte para LocalDateTime
        LocalDateTime localDateTime = zonedDateTime.toLocalDateTime();
        usuario.setDataCadastro(localDateTime);

        log.info("Criando novo usuario");
        return Mapper.toDto(repository.save(usuario));
    }

    public UsuarioResponseDto update(Long id, UsuarioUpdateDto usuarioUpdateDto) {
        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(NENHUM_USUARIO));

        if (usuarioUpdateDto.nome() != null) {
            usuario.setNome(usuarioUpdateDto.nome());
        } else {
            usuario.setNome(usuario.getNome());
        }
        if (usuarioUpdateDto.email() != null) {
            usuario.setEmail(usuarioUpdateDto.email());
        }
        if (usuarioUpdateDto.sobrenome() != null) {
            usuario.setSobrenome(usuarioUpdateDto.sobrenome());
        }
        if (usuarioUpdateDto.linguagem() != null) {
            usuario.setLinguagem(usuarioUpdateDto.linguagem());
        }
        if (usuarioUpdateDto.area() != null) {
            usuario.setArea(usuarioUpdateDto.area());
        }
        if (usuarioUpdateDto.descricao() != null) {
            usuario.setDescricao(usuarioUpdateDto.descricao());
        }
        if (usuarioUpdateDto.disponibilidade() != null) {
            usuario.setDisponibilidade(usuarioUpdateDto.disponibilidade());
        }
        if (usuarioUpdateDto.status() != null) {
            usuario.setStatus(usuarioUpdateDto.status());
        }

        // Define o fuso horário para São Paulo, Brasil
        ZoneId zoneId = ZoneId.of("America/Sao_Paulo");
        ZonedDateTime zonedDateTime = ZonedDateTime.now(zoneId);

        // Converte para LocalDateTime
        LocalDateTime localDateTime = zonedDateTime.toLocalDateTime();
        usuario.setDataCadastro(localDateTime);

        log.info("Atualizando usuario com ID: {}", id);
        return Mapper.toDto(repository.save(usuario));
    }

}
