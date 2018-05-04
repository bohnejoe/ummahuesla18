package de.ummahuesla.doiwanttolivehere.service;

import java.util.HashSet;
import java.util.OptionalDouble;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import de.ummahuesla.doiwanttolivehere.collectors.Collector;
import de.ummahuesla.doiwanttolivehere.collectors.NearbyDoctorsCollector;
import de.ummahuesla.doiwanttolivehere.collectors.NearbySupermarketsCollector;
import de.ummahuesla.doiwanttolivehere.collectors.SunHoursPerYearCollector;
import de.ummahuesla.doiwanttolivehere.model.QiResult;
import de.ummahuesla.doiwanttolivehere.model.Score;

@Service
public class QualityIndexService {
	
	Set<Collector> collectors = new HashSet<Collector>();
	
	public QualityIndexService() {
		collectors.add(new NearbyDoctorsCollector());
		collectors.add(new NearbySupermarketsCollector());
		collectors.add(new SunHoursPerYearCollector());
	}

	public QiResult fetch(Double lat, Double lon) {
		Set<Score> scores = collectors.stream().map(c -> c.getScore(lat, lon)).collect(Collectors.toSet());
		OptionalDouble overallScore = scores.stream().mapToDouble(s -> s.score()).average();
		QiResult qiResult = QiResult.create(lat, lon, overallScore.getAsDouble(), scores);
		return qiResult;
		
	}
	
}
