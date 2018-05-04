

package de.ummahuesla.doiwanttolivehere.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

@Generated("com.google.auto.value.processor.AutoValueProcessor")
 final class AutoValue_QualityInidicator extends QualityInidicator {

  private final String name;
  private final String qi;

  AutoValue_QualityInidicator(
      String name,
      String qi) {
    if (name == null) {
      throw new NullPointerException("Null name");
    }
    this.name = name;
    if (qi == null) {
      throw new NullPointerException("Null qi");
    }
    this.qi = qi;
  }

  @JsonProperty
  @Override
  public String name() {
    return name;
  }

  @JsonProperty
  @Override
  String qi() {
    return qi;
  }

  @Override
  public String toString() {
    return "QualityInidicator{"
         + "name=" + name + ", "
         + "qi=" + qi
        + "}";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }
    if (o instanceof QualityInidicator) {
      QualityInidicator that = (QualityInidicator) o;
      return (this.name.equals(that.name()))
           && (this.qi.equals(that.qi()));
    }
    return false;
  }

  @Override
  public int hashCode() {
    int h$ = 1;
    h$ *= 1000003;
    h$ ^= name.hashCode();
    h$ *= 1000003;
    h$ ^= qi.hashCode();
    return h$;
  }

}
