package de.ummahuesla.doiwanttolivehere.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.auto.value.AutoValue;

import java.util.Collections;
import java.util.Set;

@AutoValue
public abstract class QiResult {

    @JsonCreator public static QiResult create() {
        QualityInidicator arzt = QualityInidicator.create("arzt", "1.5");
        return new AutoValue_QiResult(Collections.singleton(arzt));
    }

    @JsonProperty public abstract Set<QualityInidicator> inidicators();

}
