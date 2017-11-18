package com.jedakah.drunkards.to.event;

import com.jedakah.drunkards.entity.Event;
import lombok.Data;

import javax.validation.constraints.NotNull;

import static java.lang.Math.*;


@Data
public class EventsFilter {

    private static final double EARTH_RADIUS = 6371000;

    @NotNull
    private final String lat;

    @NotNull
    private final String lng;

    @NotNull
    private final String radiusInMeters;

    public boolean isEventInRadius(Event event) {

        double lat1 = Double.parseDouble(lat);
        double lng1 = Double.parseDouble(lng);
        double maxRadius = Double.parseDouble(radiusInMeters);

        double lat2 = Double.parseDouble(event.getLocation().getLatitude());
        double lng2 = Double.parseDouble(event.getLocation().getLongitude());

        double distance = 2 * EARTH_RADIUS
                * asin(sqrt(hav(lat2 - lat1) + cos(lat1) * cos(lat2) * hav(lng2 - lng1)));

        return distance <= maxRadius;
    }

    private double hav(double degrees) {

        double radians = toRadians(degrees);

        return pow(sin(radians / 2), 2);
    }
}
