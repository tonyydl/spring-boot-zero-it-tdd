package com.tonyydl.springbootzeroittdd;

import com.tonyydl.springbootzeroittdd.data.dto.MemberDto;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class SpringBootZeroItTddApplicationTests {

    private WebTestClient webTestClient =
            WebTestClient
                    .bindToServer()
                    .baseUrl("http://localhost:8080")
                    .build();

    @Test
    void crud() {
        webTestClient
                .post()
                .uri("/members")
                .bodyValue(
                        MemberDto
                                .builder()
                                .id(null)
                                .firstName("Tony")
                                .lastName("Yang")
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
