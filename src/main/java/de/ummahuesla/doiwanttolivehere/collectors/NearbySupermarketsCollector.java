package de.ummahuesla.doiwanttolivehere.collectors;

import java.util.concurrent.ThreadLocalRandom;

import de.ummahuesla.doiwanttolivehere.model.Score;

public class NearbySupermarketsCollector extends Collector {

	@Override
	public Score getScore(long lat, long lon) {
		return Score.create("Supermärkte in der Nähe", ThreadLocalRandom.current().nextInt(scoreMin, scoreMax+ 1));
	}

}
