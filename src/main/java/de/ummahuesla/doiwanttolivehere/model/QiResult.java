package de.ummahuesla.doiwanttolivehere.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.auto.value.AutoValue;

import java.util.Collections;
import java.util.Set;

@AutoValue
public abstract class QiResult {

    @JsonCreator public static QiResult create() {
        Score arzt = Score.create("arzt", 1.5f);
        return new AutoValue_QiResult(9.9f, Collections.singleton(arzt));
    }

    @JsonProperty public abstract float overallScore();
    
    @JsonProperty public abstract Set<Score> inidicators();

}
