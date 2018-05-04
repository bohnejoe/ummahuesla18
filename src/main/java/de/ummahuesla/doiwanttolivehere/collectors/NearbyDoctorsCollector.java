package de.ummahuesla.doiwanttolivehere.collectors;

import java.util.concurrent.ThreadLocalRandom;

import de.ummahuesla.doiwanttolivehere.model.Score;

public class NearbyDoctorsCollector extends Collector {

	@Override
	public Score getScore(long lat, long lon) {
		return Score.create("Ärtzte in der Nähe", ThreadLocalRandom.current().nextInt(scoreMin, scoreMax+ 1));
	}

}
