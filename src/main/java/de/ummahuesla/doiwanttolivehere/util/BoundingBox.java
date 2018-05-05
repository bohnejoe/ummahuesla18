package de.ummahuesla.doiwanttolivehere.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class BoundingBox {

    @Value("${boundingbox.size}")
    private double boundingBoxSize;

    public String create(double lng, double lat) {
        // number of km per degree = ~111km (111.32 in google maps, but range varies between 110.567km at the equator and 111.699km at the poles)
        // 1km in degree = 1 / 111.32km = 0.0089
        // 1m in degree = 0.0089 / 1000 = 0.0000089
        double coef = boundingBoxSize * 0.0000089;


        double new_latitude = lat + coef;
        // pi / 180 = 0.018
        double new_longitude = lng + coef / Math.cos(lat * 0.018);

        return String.format("%s,%s,%s,%s", lng, lat, new_longitude, new_latitude);
    }
}
