package de.ummahuesla.doiwanttolivehere.service;

import java.util.*;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
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

    final private Collection<Collector> collectors = new HashSet<>();

    @Autowired
    private ApplicationContext appContext;

    @PostConstruct
    public void init() {
        collectors.addAll(appContext.getBeansOfType(Collector.class).values());
    }

    public QiResult fetch(Double lat, Double lng) {
        Set<Score> scores = collectors.stream()
                .map(c -> c.getScore(lat, lng))
                .filter(c -> c != null && c.score() != 0)
                .collect(Collectors.toSet());

        OptionalDouble overallScoreAverage = scores.stream().mapToDouble(s -> s.score()).average();
        Double overallScore = 0.0;
        if (overallScoreAverage.isPresent()) {
            overallScore = overallScoreAverage.getAsDouble();
        }

        QiResult qiResult = QiResult.create(lat, lng, overallScore, scores);
        return qiResult;
    }

}
