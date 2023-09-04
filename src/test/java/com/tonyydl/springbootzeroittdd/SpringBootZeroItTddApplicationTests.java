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
        webTestClient
                .post()
                .uri("http://localhost:%d/members".formatted(port))
                .bodyValue(
                        MemberDto
                                .builder()
                                .id(null)
                                .name("Tony, Yang")
                                .age(18)
                                .height(174F)
                                .build()
                )
                .exchange()
                .expectStatus().isCreated()
                .expectBody(MemberDto.class)
                .returnResult()
                .getResponseBody();
    }
}
