package de.ummahuesla.doiwanttolivehere.collectors;

import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import de.ummahuesla.doiwanttolivehere.model.QiResult;
import de.ummahuesla.doiwanttolivehere.model.Score;
import de.ummahuesla.doiwanttolivehere.repository.SunlightRepository;

public class SunHoursPerYearCollector extends Collector {

	@Autowired
    private SunlightRepository sunlightRepository;
	
	@Override
	public Score getScore(Double lat, Double lon) {
//		Optional<Sunlight> fetch = sunlightRepository.fetch();

        ObjectMapper objectMapper = new ObjectMapper();
        QiResult result = QiResult.create(lat, lon);

        try {
			objectMapper.writeValueAsString(result);
		} catch (JsonProcessingException e) {
		
			return null;
			
		}
        
		return Score.create("Sonnenstunden pro Jahr", ThreadLocalRandom.current().nextDouble(scoreMin, scoreMax + 1));
	}

}
