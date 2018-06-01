package com.lucaskatayama.winston.servicebeer.beer;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IntegrationTest {


    @Autowired
    private TestRestTemplate restTemplate;


    @Test
    public void getPerson() {
        //arrange
        Beer beerToSave = new Beer("Tripel1", "http://photo/photo.png", "Tripel", "");
        ResponseEntity<Beer> savedBeer = restTemplate.postForEntity("/", beerToSave, Beer.class);

        //act
        ResponseEntity<Beer> response =
                restTemplate.getForEntity("/{uuid}", Beer.class, savedBeer.getBody().getUid());

        //assert
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Beer beer = response.getBody();
        assertThat(beer).isEqualToComparingFieldByField(savedBeer);
    }
}
