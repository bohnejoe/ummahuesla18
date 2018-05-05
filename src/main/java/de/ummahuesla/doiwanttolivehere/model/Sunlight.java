package de.ummahuesla.doiwanttolivehere.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.google.auto.value.AutoValue;

@JacksonXmlRootElement(localName = "FeatureInfoResponse")
public class Sunlight {

    @JacksonXmlProperty(localName = "FIELDS")
    public SunlightFields fields;

}
