package com.lucaskatayama.winston.servicebeer.beer;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

@RunWith(MockitoJUnitRunner.class)
public class BeerServiceTest {

    BeerService beerService;

    BeerRepository beerRepository;


    @Before
    public void setUp() throws Exception {
        beerRepository = Mockito.mock(BeerRepository.class);
        beerService = new BeerService(beerRepository);
    }


    @Test
    public void getBeer_getBeerById() {
        String uuid = UUID.randomUUID().toString();
        Beer expected = new Beer(1001L, uuid, "Tripel1", "https://photo/photo.png", "Tripel", "");
        given(beerRepository.findOneByUid(uuid)).willReturn(expected);


        Beer actual = beerService.getById(uuid);
        assertThat(actual).isSameAs(expected);
    }

    @Test(expected = BeerNotFound.class)
    public void getBeer_beerNotFound() {
        String uuid = UUID.randomUUID().toString();
        given(beerRepository.findOneByUid(uuid)).willReturn(null);
        beerService.getById(uuid);
    }

    @Test
    public void saveBeer_saveOk() {
        beerService = spy(beerService);
        UUID uuid = UUID.randomUUID();
        Beer expected = new Beer(1001L, uuid.toString(), "Tripel1", "https://photo/photo.png", "Tripel", "");
        Beer toSave = new Beer("Tripel1", "https://photo/photo.png", "Tripel", "");
        doReturn(uuid.toString()).when(beerService).generateUUID();
        given(beerRepository.save(any())).willReturn(expected);


        Beer actual = beerService.save(toSave);
        assertThat(actual).isEqualToComparingFieldByField(expected);
    }
}
