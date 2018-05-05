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
import de.ummahuesla.doiwanttolivehere.collectors.PlaygroundCollector;
import de.ummahuesla.doiwanttolivehere.collectors.SchoolCollector;
import de.ummahuesla.doiwanttolivehere.collectors.SunHoursPerYearCollector;
import de.ummahuesla.doiwanttolivehere.collectors.TransportConnectionCollector;
import de.ummahuesla.doiwanttolivehere.model.QiResult;
import de.ummahuesla.doiwanttolivehere.model.Score;

@Service
public class QualityIndexService {

    private Set<Collector> collectors = new HashSet<Collector>();
    
    @Autowired
    private NearbySupermarketsCollector nearbySupermarketsCollector;
    
    @Autowired
    private NearbyDoctorsCollector nearbyDoctorsCollector;
    
    @Autowired
	private SunHoursPerYearCollector sunHoursPerYearCollector;
    
    @Autowired
    private PlaygroundCollector playgroundCollector;
    
    @Autowired
    private SchoolCollector schoolCollector;
    
    @Autowired
    private TransportConnectionCollector transportConnectionCollector;
    
    @PostConstruct
    public void init() {
    	collectors.add(nearbyDoctorsCollector);
    	collectors.add(nearbySupermarketsCollector);
    	collectors.add(sunHoursPerYearCollector);
    	collectors.add(transportConnectionCollector);
    	collectors.add(playgroundCollector);
    	collectors.add(schoolCollector);
    }

	public QiResult fetch(Double lat, Double lng) {
		Set<Score> scores = collectors.stream()
				.map(c -> c.getScore(lat, lng))
				.filter(c -> c !=null && c.score() != 0)
				.collect(Collectors.toSet());
		
		OptionalDouble overallScoreAverage = scores.stream().mapToDouble(s -> s.score()).average();
		Double overallScore = 0.0;
		if(overallScoreAverage.isPresent()) {
			overallScore = overallScoreAverage.getAsDouble();
		}

		QiResult qiResult = QiResult.create(lat, lng, overallScore, scores);
		return qiResult;
	}
	
}
