package com.trade.metallica.repository;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.ComponentScan;

import org.springframework.test.context.junit4.SpringRunner;

import com.trade.metallica.document.Commodity;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@DataMongoTest
@RunWith(SpringRunner.class)
@ComponentScan("com.trade.mettalic")
@SpringBootApplication
public class CommodityReactiveRepositoryTest {

	@Autowired
	CommodityReactiveRepository commodityReactiveRepository;
	
   List<Commodity> commodityList = new ArrayList<Commodity>();
	
	
	@Before
	public void setup()
	{
		commodityList .add(new Commodity("gold","Metal"));
		commodityList .add(new Commodity("ABC","Enregy"));
				
		commodityReactiveRepository.deleteAll()
		.thenMany(Flux.fromIterable(commodityList))
		.flatMap(commodityReactiveRepository::save)
		.doOnNext((item -> 
		{
			System.out.println("item :: + " + item);
		}
		))
		.blockLast();
	}

	@Test
	public void getAllItem()
	{
		Flux<Commodity> itemFlux = commodityReactiveRepository.findAll();
		StepVerifier.create(itemFlux).expectNextCount(2).verifyComplete();
	}

}
