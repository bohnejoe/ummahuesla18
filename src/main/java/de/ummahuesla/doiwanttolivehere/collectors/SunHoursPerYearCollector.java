package de.ummahuesla.doiwanttolivehere.collectors;

import java.util.Objects;
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
        return sunlightRepository.fetch(lat, lng)
                .filter(s -> Objects.nonNull(s.fields))
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
                .map(this::calculateScore)
                .map(this::createScore)
                .orElse(this.createScore(0d));
    }

    private Score createScore(double score) {
        return Score.create("Sonnenstunden pro Jahr", score);
    }

    private double calculateScore(Integer result) {
        return 100d / 2000d * result / 10;
    }

}
