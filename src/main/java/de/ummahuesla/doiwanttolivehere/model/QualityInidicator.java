package de.ummahuesla.doiwanttolivehere.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.auto.value.AutoValue;

import static com.google.auto.value.AutoValue.*;

@AutoValue
abstract class QualityInidicator {

    @JsonCreator public static QualityInidicator create(String name, Double qi) {
        return new AutoValue_QualityInidicator(name, qi);
    }

    @JsonProperty public abstract String name();
    @JsonProperty public abstract Double qi();

}
