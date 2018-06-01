package com.lucaskatayama.winston.servicebeer.beer;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BeerRepository extends CrudRepository<Beer, Long> {
    Beer findOneByUid(String uuid);
}
