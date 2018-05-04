package de.ummahuesla.doiwanttolivehere.model;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.auto.value.AutoValue;

@AutoValue
public abstract class QiResult {

    @JsonCreator public static QiResult create(Double lat, Double lng, Double overallScore, Set<Score> scores) {
        return new AutoValue_QiResult(lat, lng, overallScore, scores);
    }

    @JsonProperty public abstract Double lat();

    @JsonProperty public abstract Double lng();

    @JsonProperty public abstract Double overallScore();

    @JsonProperty public abstract Set<Score> inidicators();

}
