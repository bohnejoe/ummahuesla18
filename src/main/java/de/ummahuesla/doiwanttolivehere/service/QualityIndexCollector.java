package de.ummahuesla.doiwanttolivehere.service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import de.ummahuesla.doiwanttolivehere.collectors.Collector;
import de.ummahuesla.doiwanttolivehere.collectors.NearbyDoctorsCollector;
import de.ummahuesla.doiwanttolivehere.collectors.NearbySupermarketsCollector;
import de.ummahuesla.doiwanttolivehere.collectors.SunHoursPerYearCollector;
import de.ummahuesla.doiwanttolivehere.model.QiResult;
import de.ummahuesla.doiwanttolivehere.model.Score;

public class QualityIndexCollector {
	
	Set<Collector> collectors = new HashSet<Collector>();
	
	public QualityIndexCollector() {
		collectors.add(new NearbyDoctorsCollector());
		collectors.add(new NearbySupermarketsCollector());
		collectors.add(new SunHoursPerYearCollector());
	}

	public QiResult collect(Double lat, Double lon) {
		
		Set<Score> scores = collectors.stream().map(c -> c.getScore(lat, lon)).collect(Collectors.toSet());
		
		QiResult qiResult = QiResult.create(lat, lon);
		return qiResult;
		
	}
	
}
