

package de.ummahuesla.doiwanttolivehere.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Set;
import javax.annotation.Generated;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
 final class AutoValue_QiResult extends QiResult {

  private final Double lat;
  private final Double lng;
  private final Double overallScore;
  private final Set<Score> inidicators;

  AutoValue_QiResult(
      Double lat,
      Double lng,
      Double overallScore,
      Set<Score> inidicators) {
    if (lat == null) {
      throw new NullPointerException("Null lat");
    }
    this.lat = lat;
    if (lng == null) {
      throw new NullPointerException("Null lng");
    }
    this.lng = lng;
    if (overallScore == null) {
      throw new NullPointerException("Null overallScore");
    }
    this.overallScore = overallScore;
    if (inidicators == null) {
      throw new NullPointerException("Null inidicators");
    }
    this.inidicators = inidicators;
  }

  @JsonProperty
  @Override
  public Double lat() {
    return lat;
  }

  @JsonProperty
  @Override
  public Double lng() {
    return lng;
  }

  @JsonProperty
  @Override
  public Double overallScore() {
    return overallScore;
  }

  @JsonProperty
  @Override
  public Set<Score> inidicators() {
    return inidicators;
  }

  @Override
  public String toString() {
    return "QiResult{"
         + "lat=" + lat + ", "
         + "lng=" + lng + ", "
         + "overallScore=" + overallScore + ", "
         + "inidicators=" + inidicators
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof QiResult) {
      QiResult that = (QiResult) o;
      return (this.lat.equals(that.lat()))
           && (this.lng.equals(that.lng()))
           && (this.overallScore.equals(that.overallScore()))
           && (this.inidicators.equals(that.inidicators()));
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h$ = 1;
    h$ *= 1000003;
    h$ ^= lat.hashCode();
    h$ *= 1000003;
    h$ ^= lng.hashCode();
    h$ *= 1000003;
    h$ ^= overallScore.hashCode();
    h$ *= 1000003;
    h$ ^= inidicators.hashCode();
    return h$;
  }

}
