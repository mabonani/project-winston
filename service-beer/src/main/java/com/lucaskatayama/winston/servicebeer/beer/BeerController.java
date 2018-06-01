package com.lucaskatayama.winston.servicebeer.beer;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class BeerController {

    @Autowired
    BeerService beerService;

    @GetMapping("/{uuid}")
    public Beer getBeerById(@PathVariable("uuid") String uuid) {
        Beer beer = beerService.getById(uuid);
        return beer;
    }

    @PostMapping("")
    public Beer saveBeer(@RequestBody Beer beer) {
        return beerService.save(beer);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void beerNotFound(BeerNotFound ex) {

    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void beerAlreadyExists(BeerAlreadyExists ex) {

    }
}
