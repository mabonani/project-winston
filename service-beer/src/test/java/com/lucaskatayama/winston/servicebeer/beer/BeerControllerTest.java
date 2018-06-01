package com.lucaskatayama.winston.servicebeer.beer;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.eureka.resources.InstancesResource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.IOException;
import java.util.UUID;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(BeerController.class)
public class BeerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BeerService beerService;


    @Test
    public void getBeerById_willReturnABeer() throws Exception {
        String uuid = UUID.randomUUID().toString();
        Beer beer = new Beer(1001L, uuid, "Tripel1", "https://images/image.png", "Tripel", "");
        given(beerService.getById(uuid)).willReturn(beer);


        mockMvc.perform(MockMvcRequestBuilders.get("/{uuid}", uuid))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name").value(beer.getName()))
                .andExpect(jsonPath("uid").value(beer.getUid()))
                .andExpect(jsonPath("description").value(beer.getDescription()))
                .andExpect(jsonPath("style").value(beer.getStyle()))
                .andExpect(jsonPath("photo").value(beer.getPhoto()))
                // excludes id
                .andExpect(jsonPath("id").doesNotExist());
    }

    @Test
    public void getBeerById_notFound() throws Exception {
        String uuid = UUID.randomUUID().toString();
        given(beerService.getById(uuid)).willThrow(new BeerNotFound());

        mockMvc.perform(MockMvcRequestBuilders.get("/{uuid}", uuid))
                .andExpect(status().isNotFound());
    }

    public static byte[] convertObjectToJsonBytes(Object object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper.writeValueAsBytes(object);
    }

    @Test
    public void saveBeer_willSaveOK() throws Exception {
        Beer beer = new Beer("Tripel1", "https://images/image.png", "Tripel", "");
        String uuid = UUID.randomUUID().toString();
        Beer beerSaved = new Beer(1001L, uuid, "Tripel1", "https://images/image.png", "Tripel", "");
        given(beerService.save(beer)).willReturn(beerSaved);


        RequestBuilder builder = MockMvcRequestBuilders
                .post("/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertObjectToJsonBytes(beer));

        mockMvc.perform(builder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("name").value(beerSaved.getName()))
                .andExpect(jsonPath("uid").value(beerSaved.getUid()))
                .andExpect(jsonPath("description").value(beerSaved.getDescription()))
                .andExpect(jsonPath("style").value(beerSaved.getStyle()))
                .andExpect(jsonPath("photo").value(beerSaved.getPhoto()))
                // excludes id
                .andExpect(jsonPath("id").doesNotExist());
    }

    @Test
    public void saveBeer_throwErrorBeerAlreadyExists() throws Exception {
        Beer beer = new Beer("Tripel1", "https://images/image.png", "Tripel", "");
        given(beerService.save(beer)).willThrow(new BeerAlreadyExists());


        RequestBuilder builder = MockMvcRequestBuilders
                .post("/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertObjectToJsonBytes(beer));

        mockMvc.perform(builder)
                .andExpect(status().isBadRequest());
    }
}
