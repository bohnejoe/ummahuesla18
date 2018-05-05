package de.ummahuesla.doiwanttolivehere.collectors;

import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

import de.ummahuesla.doiwanttolivehere.model.Sunlight;
import de.ummahuesla.doiwanttolivehere.util.SunlightResultParser;
import org.apache.el.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.ummahuesla.doiwanttolivehere.model.Score;
import de.ummahuesla.doiwanttolivehere.repository.SunlightRepository;

@Component
public class SunHoursPerYearCollector extends Collector {

    @Autowired
    private SunlightRepository sunlightRepository;

    @Override
    public Score getScore(Double lat, Double lng) {
        Optional<Sunlight> fetch = sunlightRepository.fetch(lat, lng);

        Integer result = fetch
                .map(s -> s.fields.sdJahr)
                .map(s -> {
                    int maxHoursPerYear = 0;
                    try {
                        maxHoursPerYear = SunlightResultParser.returnMaxHoursPerYear(s);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    return maxHoursPerYear;
                })
                .orElse(0);

        return Score.create("Sonnenstunden pro Jahr", Double.valueOf(result));
    }

}
