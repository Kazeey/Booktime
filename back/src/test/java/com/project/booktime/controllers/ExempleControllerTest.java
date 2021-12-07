package com.project.booktime.controllers;

import com.project.booktime.params.Constants;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ExempleControllerTest {

    @Autowired
    private TestRestTemplate template;

    @Test
    void getExemple() {
        ResponseEntity<String> response = template.getForEntity(Constants.BACK_URL + Constants.EXEMPLE_CONTROLLER_URL, String.class);
        assertThat(response.getBody()).isEqualTo("Controller exemple");
    }
}
