package de.ummahuesla.doiwanttolivehere.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.auto.value.AutoValue;

@AutoValue
public abstract class Score {

    @JsonCreator public static Score create(String name, Double score) {
        return new AutoValue_Score(name, score);
    }

    @JsonProperty public abstract String name();
    @JsonProperty abstract Double score();
}
