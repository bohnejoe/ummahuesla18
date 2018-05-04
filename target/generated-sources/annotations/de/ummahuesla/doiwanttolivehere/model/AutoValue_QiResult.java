

package de.ummahuesla.doiwanttolivehere.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Set;
import javax.annotation.Generated;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
 final class AutoValue_QiResult extends QiResult {

  private final Set<QualityInidicator> inidicators;

  AutoValue_QiResult(
      Set<QualityInidicator> inidicators) {
    if (inidicators == null) {
      throw new NullPointerException("Null inidicators");
    }
    this.inidicators = inidicators;
  }

  @JsonProperty
  @Override
  public Set<QualityInidicator> inidicators() {
    return inidicators;
  }

  @Override
  public String toString() {
    return "QiResult{"
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
      return (this.inidicators.equals(that.inidicators()));
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h$ = 1;
    h$ *= 1000003;
    h$ ^= inidicators.hashCode();
    return h$;
  }

}
