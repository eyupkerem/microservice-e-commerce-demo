package com.malkoc.costumerservice.repository;

import com.malkoc.costumerservice.entity.Costumer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CostumerRepository extends MongoRepository<Costumer,String> {
}
