package com.prizy.price.service.impl;

import java.util.Comparator;
import java.util.List;
import java.util.OptionalDouble;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.prizy.price.service.IdealPriceService;

@Service("sampleIdealPriceService")
public class SampleIdealPriceServiceImpl implements IdealPriceService {
	/**
	 * Calculate Ideal price based on formula
	 * remove the 1 highest and 1 lowest, then doing an average with the rest and adding 30% to it.
	 */
	@Override
	public Double calculate(Set<Double> priceSet) {
		Double idealPrice = 0D;
		if(priceSet != null && priceSet.size() > 4){
			List<Double> collect = priceSet.stream()
					.sorted(Comparator.reverseOrder())//Sort the list in DESC order
					.skip(1)//Skip max elements from list 
					.sorted()//Sort the list in ASEC order 
					.skip(1)//Skip min elements from list
					.collect(Collectors.toList());
			OptionalDouble average = collect.stream().mapToDouble(Double::doubleValue).average();
			if(average.isPresent()){
				double asDouble = average.getAsDouble();
				idealPrice = asDouble + (asDouble/100)*30;
			}
		}
		return idealPrice;
	}

}
