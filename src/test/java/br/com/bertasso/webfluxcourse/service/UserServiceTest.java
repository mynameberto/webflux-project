package br.com.bertasso.webfluxcourse.service;

import br.com.bertasso.webfluxcourse.entity.User;
import br.com.bertasso.webfluxcourse.mapper.UserMapper;
import br.com.bertasso.webfluxcourse.model.request.UserRequest;
import br.com.bertasso.webfluxcourse.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Objects;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository repository;
    @Mock
    private UserMapper mapper;
    @InjectMocks
    private UserService service;


    @Test
    void testSave() {
        UserRequest request = new UserRequest("luis", "luis@mail.com", "123");
        User entity = User.builder().build();

        when(mapper.toEntity(any(UserRequest.class))).thenReturn(entity);
        when(repository.save(any(User.class))).thenReturn(Mono.just(User.builder().build()));

        Mono<User> result = service.save(request);

        StepVerifier.create(result)
                .expectNextMatches(Objects::nonNull)
                .expectComplete()
                .verify();

        Mockito.verify(repository, times(1)).save(any(User.class));
    }

    @Test
    void testFindById() {
        when(repository.findById(anyString())).thenReturn(Mono.just(User.builder().build()));

        Mono<User> result = service.findById("123");

        StepVerifier.create(result)
                .expectNextMatches(user -> user.getClass() == User.class)
                .expectComplete()
                .verify();

        Mockito.verify(repository, times(1)).findById(anyString());
    }

    @Test
    void testFindAll() {
        when(repository.findAll()).thenReturn(Flux.just(User.builder().build()));

        Flux<User> result = service.findAll();

        StepVerifier.create(result)
                .expectNextMatches(user -> user.getClass() == User.class)
                .expectComplete()
                .verify();

        Mockito.verify(repository, times(1)).findAll();
    }

}