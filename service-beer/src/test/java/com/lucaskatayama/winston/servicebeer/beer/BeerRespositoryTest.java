package com.lucaskatayama.winston.servicebeer.beer;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class BeerRespositoryTest {

    @Autowired
    private BeerRepository beerRepository;

    @Autowired
    private TestEntityManager entityManager;


    @Test
    public void getBeerById_returnBeer() {
        Beer beer = new Beer("Tripel1", "https://photo/photo.png", "Tripel", "");
        entityManager.persistFlushFind(beer);

        Beer found = beerRepository.findOneByUid(beer.getUid());
        assertThat(found).isEqualToComparingFieldByField(beer);
    }
}
