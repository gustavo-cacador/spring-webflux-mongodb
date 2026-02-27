package com.gustavoronchi.spring_webflux_mongodb.services;

import com.gustavoronchi.spring_webflux_mongodb.dto.PostDTO;
import com.gustavoronchi.spring_webflux_mongodb.repositories.PostRepository;
import com.gustavoronchi.spring_webflux_mongodb.services.exceptions.ResourceNotFoundException;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.concurrent.ExecutionException;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Mono<PostDTO> findById(String id) throws InterruptedException, ExecutionException {
        return postRepository.findById(id)
                .map(PostDTO::new)
                .switchIfEmpty(Mono.error(new ResourceNotFoundException("Recurso não encontrado")));
    }

    public Flux<PostDTO> findByTitle(String text) {
        return postRepository.searchTitulo(text)
                .map(PostDTO::new);
    }

    public Flux<PostDTO> fullSearch(String text, Instant minDate, Instant maxDate) {
        maxDate = maxDate.plusSeconds(86400);
        return postRepository.fullSearch(text, minDate, maxDate)
                .map(PostDTO::new);
    }

    public Flux<PostDTO> findByUser(String id) {
        return postRepository.findByUser(new ObjectId(id))
                .map(PostDTO::new);
    }
}
