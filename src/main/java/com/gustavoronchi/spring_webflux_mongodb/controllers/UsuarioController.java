package com.gustavoronchi.spring_webflux_mongodb.controllers;

import com.gustavoronchi.spring_webflux_mongodb.dto.UsuarioDTO;
import com.gustavoronchi.spring_webflux_mongodb.services.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public Flux<UsuarioDTO> findAll() {
        return usuarioService.findAll();
    }

    @GetMapping(value = "/{id}")
    public Mono<ResponseEntity<UsuarioDTO>> findById(@PathVariable String id) {
        return usuarioService.findById(id).map(userDto -> ResponseEntity.ok().body(userDto));
    }

	/*
	@GetMapping(value = "/{id}/posts")
	public Flux<PostDTO> findPosts(@PathVariable String id) {
		return service.findPosts(id);
	}
	*/

    @PostMapping
    public Mono<ResponseEntity<UsuarioDTO>> insert(@RequestBody UsuarioDTO dto, UriComponentsBuilder builder) {
        return usuarioService.insert(dto).map(newUser -> ResponseEntity
                .created(builder.path("/users/{id}").buildAndExpand(newUser.getId()).toUri()).body(newUser));
    }

    @PutMapping(value = "/{id}")
    public Mono<ResponseEntity<UsuarioDTO>> update(@PathVariable String id, @RequestBody UsuarioDTO dto) {
        return usuarioService.update(id, dto).map(userUpdated -> ResponseEntity.ok().body(userUpdated));
    }

    @DeleteMapping(value = "/{id}")
    public Mono<ResponseEntity<Void>> delete(@PathVariable String id) {
        return usuarioService.delete(id).then(Mono.just(ResponseEntity.noContent().<Void>build()));
    }
}
