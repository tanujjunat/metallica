package com.trade.metallica.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.trade.metallica.document.Commodity;

@Repository
public interface CommodityReactiveRepository extends ReactiveMongoRepository<Commodity, String>{

}
