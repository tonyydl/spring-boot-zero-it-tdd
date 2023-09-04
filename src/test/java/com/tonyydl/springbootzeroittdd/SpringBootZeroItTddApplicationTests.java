package com.tonyydl.springbootzeroittdd;

import com.tonyydl.springbootzeroittdd.data.dto.MemberDto;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
class SpringBootZeroItTddApplicationTests {

    private WebTestClient webTestClient =
            WebTestClient
                    .bindToServer()
                    .baseUrl("http://localhost:8080")
                    .build();

    @LocalServerPort
    private Integer port;

    @Container
    private static final PostgreSQLContainer postgresqlContainer = new PostgreSQLContainer("postgres:15");

    @DynamicPropertySource
    static void registerPgProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgresqlContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgresqlContainer::getUsername);
        registry.add("spring.datasource.password", postgresqlContainer::getPassword);
    }

    @Test
    void crud() {
        // arrange
        MemberDto request = MemberDto
                .builder()
                .id(null)
                .name("Tony, Yang")
                .age(18)
                .height(174F)
                .build();
        // act
        final MemberDto responseBody = webTestClient
                .post()
                .uri("http://localhost:%d/members".formatted(port))
                .bodyValue(request)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(MemberDto.class)
                .returnResult()
                .getResponseBody();

        // assert
        assertThat(responseBody.getId()).isGreaterThan(0);
        assertThat(responseBody.getName()).isEqualTo(request.getName());
        assertThat(responseBody.getAge()).isEqualTo(request.getAge());
        assertThat(responseBody.getHeight()).isEqualTo(request.getHeight());

        // act
        final MemberDto body = webTestClient
                .get()
                .uri("http://localhost:%d/members/%d".formatted(port, responseBody.getId()))
                .exchange()
                .expectStatus().isOk()
                .expectBody(MemberDto.class)
                .returnResult().getResponseBody();

        // assert
        assertThat(body.getId()).isEqualTo(responseBody.getId());
        assertThat(body.getName()).isEqualTo(responseBody.getName());
        assertThat(body.getAge()).isEqualTo(responseBody.getAge());
        assertThat(body.getHeight()).isEqualTo(responseBody.getHeight());

        // arrange
        MemberDto updateRequest = MemberDto
                .builder()
                .id(responseBody.getId())
                .name("Tony, Yang")
                .age(19)
                .height(175F)
                .build();

        // act
        final MemberDto memberDto = webTestClient
                .patch()
                .uri("http://localhost:%d/members".formatted(port))
                .bodyValue(updateRequest)
                .exchange()
                .expectStatus().isOk()
                .expectBody(MemberDto.class)
                .returnResult().getResponseBody();

        // assert
        assertThat(memberDto.getAge()).isEqualTo(updateRequest.getAge());
        assertThat(memberDto.getHeight()).isEqualTo(updateRequest.getHeight());

        // act
        webTestClient
                .delete()
                .uri("http://localhost:%d/members/%d".formatted(port, responseBody.getId()))
                .exchange()
                .expectStatus().isNoContent();

        // assert
        webTestClient
                .get()
                .uri("http://localhost:%d/members/%d".formatted(port, responseBody.getId()))
                .exchange()
                .expectStatus().isNotFound();
    }
}
