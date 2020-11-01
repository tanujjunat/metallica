package com.trade.metallica.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trade.metallica.constants.TradingConstants;
import com.trade.metallica.document.Commodity;
import com.trade.metallica.repository.CommodityReactiveRepository;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@RestController
@Slf4j
public class CommodityController {

	@Autowired
	CommodityReactiveRepository commodityReactiveRepository; 
	
	@GetMapping(TradingConstants.COMMODITY_END_POINT_V1)
	public Flux<Commodity> getAllItem()
	{
		return commodityReactiveRepository.findAll();
	}
	
}
