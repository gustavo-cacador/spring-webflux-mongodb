package com.gustavoronchi.spring_webflux_mongodb.services;

import com.gustavoronchi.spring_webflux_mongodb.dto.UsuarioDTO;
import com.gustavoronchi.spring_webflux_mongodb.entities.Usuario;
import com.gustavoronchi.spring_webflux_mongodb.repositories.UsuarioRepository;
import com.gustavoronchi.spring_webflux_mongodb.services.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Flux<UsuarioDTO> findAll() {
        return usuarioRepository.findAll().map(UsuarioDTO::new);
    }

    public Mono<UsuarioDTO> findById(String id) {
        return usuarioRepository.findById(id)
                .map(UsuarioDTO::new)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Recurso não encontrado")));
    }

	/*
	public Flux<PostDTO> findPosts(String id) {
		return usuarioRepository.findById(id)
				.flatMapMany(existingUser -> {
					if (existingUser.getPosts().isEmpty())
						Flux.empty();
					List<PostDTO> list = existingUser.getPosts().stream().map(x -> new PostDTO(x)).toList();
					return Flux.fromIterable(list);
				})
				.switchIfEmpty(Mono.error(new ResourceNotFoundException("Recurso não encontrado")));
	}
	*/

    public Mono<UsuarioDTO> insert(UsuarioDTO dto) {
        Usuario entity = new Usuario();
        copyDtoToEntity(dto, entity);
        Mono<UsuarioDTO> result = usuarioRepository.save(entity).map(UsuarioDTO::new);
        return result;
    }

    public Mono<UsuarioDTO> update(String id, UsuarioDTO dto) {
        return usuarioRepository.findById(id)
                .flatMap(existingUser -> {
                    existingUser.setNome(dto.getNome());
                    existingUser.setEmail(dto.getEmail());
                    return usuarioRepository.save(existingUser);
                })
                .map(UsuarioDTO::new)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Recurso não encontrado")));
    }

    public Mono<Void> delete(String id) {
        return usuarioRepository.findById(id)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Recurso não encontrado")))
                .flatMap(usuarioRepository::delete);
    }

    private void copyDtoToEntity(UsuarioDTO dto, Usuario entity) {
        entity.setNome(dto.getNome());
        entity.setEmail(dto.getEmail());
    }
}
