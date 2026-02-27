package com.gustavoronchi.spring_webflux_mongodb.repositories;

import com.gustavoronchi.spring_webflux_mongodb.entities.Usuario;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface UsuarioRepository extends ReactiveMongoRepository<Usuario, String> {

    @Query("{ 'email': { $regex: ?0, $options: 'i' } }")
    Mono<Usuario> searchEmail(String email);
}
