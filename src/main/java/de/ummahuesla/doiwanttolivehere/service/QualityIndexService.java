package de.ummahuesla.doiwanttolivehere.service;

import java.util.HashSet;
import java.util.OptionalDouble;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
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
    
    @Autowired
    NearbySupermarketsCollector nearbySupermarketsCollector;
    
    @Autowired
    NearbyDoctorsCollector nearbyDoctorsCollector;
    
    @Autowired
	private SunHoursPerYearCollector sunHoursPerYearCollector;
    
    @PostConstruct
    public void init() {
    	collectors.add(nearbyDoctorsCollector);
    	collectors.add(nearbySupermarketsCollector);
    	collectors.add(sunHoursPerYearCollector);
    }

	public QiResult fetch(Double lat, Double lon) {
		Set<Score> scores = collectors.stream().map(c -> c.getScore(lat, lon)).collect(Collectors.toSet());
		
		OptionalDouble overallScoreAverage = scores.stream().mapToDouble(s -> s.score()).average();
		Double overallScore = 0.0;
		if(overallScoreAverage.isPresent()) {
			overallScore = overallScoreAverage.getAsDouble();
		}
				
		QiResult qiResult = QiResult.create(lat, lon, overallScore, scores);
		return qiResult;
	}
	
}
