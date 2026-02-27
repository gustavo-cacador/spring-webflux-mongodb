package com.gustavoronchi.spring_webflux_mongodb.repositories;

import com.gustavoronchi.spring_webflux_mongodb.entities.Post;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

import java.time.Instant;

public interface PostRepository extends ReactiveMongoRepository<Post, String> {

    @Query("{ 'user' : ?0 }")
    Flux<Post> findByUser(ObjectId id);

    @Query("{ 'title': { $regex: ?0, $options: 'i' } }")
    Flux<Post> searchTitulo(String text);

    Flux<Post> findByTituloContainingIgnoreCase(String text);

    @Query("{ $and: [ { date: {$gte: ?1} }, { date: { $lte: ?2} } , { $or: [ { 'title': { $regex: ?0, $options: 'i' } }, { 'body': { $regex: ?0, $options: 'i' } }, { 'comments.text': { $regex: ?0, $options: 'i' } } ] } ] }")
    Flux<Post> fullSearch(String text, Instant minDate, Instant maxDate);
}
