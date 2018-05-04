package de.ummahuesla.doiwanttolivehere.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "FeatureInfoResponse")
public class Sunlight {
    @JacksonXmlProperty(localName = "FIELDS")
    private Fields fields;
}

class Fields {

    public Fields() {
    }

    public Fields(
            @JacksonXmlProperty(localName = "OBJECTID", isAttribute = true) String objectId,
            @JacksonXmlProperty(localName = "SD_JAHR", isAttribute = true) String sdJahr,
            @JacksonXmlProperty(localName = "Shape", isAttribute = true) String shape,
            @JacksonXmlProperty(localName = "Shape_Length", isAttribute = true) String shapeLength,
            @JacksonXmlProperty(localName = "Shape_Area", isAttribute = true) String shapeArea) {
        this.objectId = objectId;
        this.sdJahr = sdJahr;
        this.shape = shape;
        this.shapeLength = shapeLength;
        this.shapeArea = shapeArea;
    }

    private String version;


    private String objectId;

    private String sdJahr;

    private String shape;

    private String shapeLength;

    private String shapeArea;
}
