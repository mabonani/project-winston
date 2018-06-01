package com.lucaskatayama.winston.servicebeer.beer;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BeerService {


    private final BeerRepository beerRepository;

    @Autowired
    public BeerService(BeerRepository beerRepository) {
        this.beerRepository = beerRepository;
    }

    public Beer getById(String uuid) {
        Beer beer = beerRepository.findOneByUid(uuid);
        if (beer == null) {
            throw new BeerNotFound();
        }
        return beer;
    }

    protected String generateUUID() {
        return UUID.randomUUID().toString();
    }

    public Beer save(Beer beer) {
        // copy values
        Beer beerToSave = new Beer();
        BeanUtils.copyProperties(beer, beerToSave);
        beerToSave.setUid(generateUUID());
        return beerRepository.save(beerToSave);
    }
}
