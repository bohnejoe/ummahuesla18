package de.ummahuesla.doiwanttolivehere.collectors;

import java.util.concurrent.ThreadLocalRandom;

import de.ummahuesla.doiwanttolivehere.model.Score;

public class SunHoursPerYearCollector extends Collector {

	@Override
	public Score getScore(long lat, long lon) {
		return Score.create("Sonnenstunden pro Jahr", ThreadLocalRandom.current().nextInt(scoreMin, scoreMax + 1));
	}

}
