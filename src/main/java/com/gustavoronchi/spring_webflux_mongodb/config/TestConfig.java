package com.gustavoronchi.spring_webflux_mongodb.config;
import com.gustavoronchi.spring_webflux_mongodb.entities.Post;
import com.gustavoronchi.spring_webflux_mongodb.entities.Usuario;
import com.gustavoronchi.spring_webflux_mongodb.repositories.PostRepository;
import com.gustavoronchi.spring_webflux_mongodb.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.Arrays;

@Configuration
public class TestConfig implements CommandLineRunner {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public void run(String... args) throws Exception {

        Mono<Void> deleteUsers = usuarioRepository.deleteAll();
        deleteUsers.subscribe();

        Mono<Void> deletePosts = postRepository.deleteAll();
        deletePosts.subscribe();

        Usuario maria = new Usuario(null, "Maria Brown", "maria@gmail.com");
        Usuario alex = new Usuario(null, "Alex Green", "alex@gmail.com");
        Usuario bob = new Usuario(null, "Bob Grey", "bob@gmail.com");

        Flux<Usuario> insertUsers = usuarioRepository.saveAll(Arrays.asList(maria, alex, bob));
        insertUsers.subscribe();

        maria = usuarioRepository.searchEmail("maria@gmail.com").toFuture().get();
        alex = usuarioRepository.searchEmail("alex@gmail.com").toFuture().get();
        bob = usuarioRepository.searchEmail("bob@gmail.com").toFuture().get();

        assert maria != null;
        Post post1 = new Post(null, Instant.parse("2022-11-21T18:35:24.00Z"), "Partiu viagem",
                "Vou viajar para São Paulo. Abraços!", maria.getId(), maria.getNome());
        Post post2 = new Post(null, Instant.parse("2022-11-23T17:30:24.00Z"), "Bom dia", "Acordei feliz hoje!",
                maria.getId(), maria.getNome());

        assert alex != null;
        post1.addComment("Boa viagem mano!", Instant.parse("2022-11-21T18:52:24.00Z"), alex.getId(), alex.getNome());
        assert bob != null;
        post1.addComment("Aproveite!", Instant.parse("2022-11-22T11:35:24.00Z"), bob.getId(), bob.getNome());

        post2.addComment("Tenha um ótimo dia!", Instant.parse("2022-11-23T18:35:24.00Z"), alex.getId(), alex.getNome());

        post1.setUsuario(usuarioRepository.searchEmail("maria@gmail.com").block());
        post2.setUsuario(usuarioRepository.searchEmail("maria@gmail.com").block());

        Flux<Post> insertPosts = postRepository.saveAll(Arrays.asList(post1, post2));
        insertPosts.subscribe();
    }
}