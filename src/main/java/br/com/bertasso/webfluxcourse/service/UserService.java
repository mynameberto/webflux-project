package br.com.bertasso.webfluxcourse.service;

import br.com.bertasso.webfluxcourse.entity.User;
import br.com.bertasso.webfluxcourse.mapper.UserMapper;
import br.com.bertasso.webfluxcourse.model.request.UserRequest;
import br.com.bertasso.webfluxcourse.repository.UserRepository;
import br.com.bertasso.webfluxcourse.service.exception.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class UserService {

    private UserRepository repository;
    private UserMapper mapper;

    public Mono<User> save(final UserRequest request) {
        return repository.save(mapper.toEntity(request));
    }

    public Mono<User> findById(final String id) {
        return repository.findById(id)
                .switchIfEmpty(Mono.error(
                        new ObjectNotFoundException(
                                format("Object not found. Id: %s, Type: %s", id, User.class.getSimpleName())
                        )
                ));
    }

    public Flux<User> findAll() {
        return repository.findAll();
    }
}
