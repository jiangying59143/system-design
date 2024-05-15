package com.my.webfluxexplicit;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.reactive.server.WebTestClient;

/**
 * @author Rob Winch
 * @since 5.0
 */
@SpringBootTest
@AutoConfigureWebTestClient(timeout = "36000")
public class HelloSecurityTests {

    @Autowired
    WebTestClient rest;

    @Test
    void indexWhenUnAuthenticatedThenRedirect() throws Exception {
        // @formatter:off
        this.rest.get()
                .uri("/")
                .exchange()
                .expectStatus().is3xxRedirection();
        // @formatter:on
    }

    @Test
    @WithMockUser
    void indexWhenAuthenticatedThenOk() throws Exception {
        // @formatter:off
        this.rest.get()
                .uri("/")
                .exchange()
                .expectStatus().isOk();
        // @formatter:on
    }

}
