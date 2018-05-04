package de.ummahuesla.doiwanttolivehere.collectors;

import java.util.concurrent.ThreadLocalRandom;

import org.springframework.stereotype.Component;

import de.ummahuesla.doiwanttolivehere.model.Score;

@Component
public class SchoolCollector extends Collector {

	@Override
	public Score getScore(Double lat, Double lon) {
		return Score.create("Schulen in der Umgebung", ThreadLocalRandom.current().nextDouble(scoreMin, scoreMax+ 1));
	}

}
