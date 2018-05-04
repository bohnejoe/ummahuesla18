package de.ummahuesla.doiwanttolivehere.collectors;

import org.springframework.beans.factory.annotation.Value;

import de.ummahuesla.doiwanttolivehere.model.Score;

public abstract class Collector {

	@Value("${score.min}")
	protected Double scoreMin;
	
	@Value("${score.max}")
	protected Double scoreMax;

	public abstract Score getScore(Double lat, Double lon);
	
}
