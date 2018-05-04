package de.ummahuesla.doiwanttolivehere.service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

	public QiResult fetch(Double lat, Double lon) {
        Set<Score> scores = Stream.of(nearbySupermarketsCollector, nearbyDoctorsCollector, sunHoursPerYearCollector)
                .map(c -> c.getScore(lat, lon))
                .collect(Collectors.toSet());
		
		QiResult qiResult = QiResult.create(lat, lon);
		return qiResult;
		
	}
	
}
