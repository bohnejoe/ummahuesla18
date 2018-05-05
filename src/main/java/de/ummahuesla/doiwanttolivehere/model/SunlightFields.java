package de.ummahuesla.doiwanttolivehere.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class SunlightFields {

    @JacksonXmlProperty(localName = "OBJECTID", isAttribute = true)
    public String objectId;
    @JacksonXmlProperty(localName = "SD_JAHR", isAttribute = true)
    public String sdJahr;
    @JacksonXmlProperty(localName = "Shape", isAttribute = true)
    public String shape;
    @JacksonXmlProperty(localName = "Shape_Length", isAttribute = true)
    public String shapeLength;
    @JacksonXmlProperty(localName = "Shape_Area", isAttribute = true)
    public String shapeArea;

}
