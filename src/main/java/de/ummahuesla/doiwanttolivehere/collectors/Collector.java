package de.ummahuesla.doiwanttolivehere.collectors;

import org.springframework.beans.factory.annotation.Value;

import de.ummahuesla.doiwanttolivehere.model.Score;

public abstract class Collector {

	@Value("score.min")
	protected int scoreMin;
	
	@Value("score.max")
	protected int scoreMax;
	
	public abstract Score getScore(long lat, long lon);
	
}
