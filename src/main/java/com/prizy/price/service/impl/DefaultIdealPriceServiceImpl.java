package com.prizy.price.service.impl;

import java.util.Comparator;
import java.util.List;
import java.util.OptionalDouble;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.prizy.price.service.IdealPriceService;

@Service("defaultIdealPriceService")
public class DefaultIdealPriceServiceImpl implements IdealPriceService {
	/**
	 * Calculate Ideal price based on formula
	 * remove the 2 highest and 2 lowest, then doing an average with the rest and adding 20% to it.
	 */
	@Override
	public Double calculate(Set<Double> priceSet) {
		Double idealPrice = 0D;
		if(priceSet != null && priceSet.size() > 4){
			List<Double> collect = priceSet.stream()
					.sorted(Comparator.reverseOrder())//Sort the list in DESC order
					.skip(2)//Skip max 2 elements from list 
					.sorted()//Sort the list in ASEC order 
					.skip(2)//Skip min 2 elements from list
					.collect(Collectors.toList());
			OptionalDouble average = collect.stream().mapToDouble(Double::doubleValue).average();
			if(average.isPresent()){
				double asDouble = average.getAsDouble();
				idealPrice = asDouble + (asDouble/100)*20;
			}
		}
		return idealPrice;
	}

}
