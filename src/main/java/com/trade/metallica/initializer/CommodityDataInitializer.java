package com.trade.metallica.initializer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.trade.metallica.document.Commodity;
import com.trade.metallica.repository.CommodityReactiveRepository;

import reactor.core.publisher.Flux;

@Component
public class CommodityDataInitializer implements CommandLineRunner {

	@Autowired
	CommodityReactiveRepository commodityReactiveRepository;
	
	
	@Override
	public void run(String...args)
	{
		initializeDataSetup();
	}
	
	public void initializeDataSetup()
	{
		commodityReactiveRepository.deleteAll()
		.thenMany(Flux.fromIterable(commodityRecords()))
				.flatMap(commodityReactiveRepository::save)
				.thenMany(commodityReactiveRepository.findAll())
				.subscribe(item->	 {
				 System.out.println("Item Inserted from Command Runner" + item);
				});
								
	}
	
	public List<Commodity> commodityRecords()
	{
		List<Commodity> commodityList = new ArrayList<Commodity>();
		
		Commodity commodity = new Commodity("gold","Metal");
		commodityList.add(commodity);
		commodity = new Commodity(null,"Energy");
		commodityList.add(commodity);
				
		return commodityList;
	}

}
