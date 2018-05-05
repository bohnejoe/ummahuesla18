package de.ummahuesla.doiwanttolivehere.collectors;

import java.util.concurrent.ThreadLocalRandom;

import org.springframework.stereotype.Component;

import de.ummahuesla.doiwanttolivehere.model.Score;

@Component
public class NearbySupermarketsCollector extends Collector {

	@Override
	public Score getScore(Double lat, Double lon) {
		double nextDouble = ThreadLocalRandom.current().nextDouble(scoreMin, scoreMax + 0.1);
		
		//return null randomly
		if(nextDouble > 0.5) {
			return null;
		}
		
		return Score.create("Supermärkte in der Nähe", nextDouble);
	}

}
