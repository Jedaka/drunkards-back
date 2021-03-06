import $ from "jquery";
window.$ = window.jQuery = $;

import "./Map.less";

export default class Map {
    static defaults = {
        selector: document.getElementsByClassName("wrap__map")[0],
        zoom: 11,
        center: {lat: 59.932495, lng: 30.348950}
    };

    constructor(options) {
        options = options || {};

        this._options = Object.assign({}, Map.defaults);
        this._options.selector = options.selector ? options.selector : Map.defaults.selector;
        this._options.zoom = options.zoom ? options.zoom : Map.defaults.zoom;
        this._options.center = options.center ? options.center : Map.defaults.center;


        this._map = new google.maps.Map(this._options.selector, {
            center: this._options.center,
            zoom: this._options.zoom,
            streetViewControl: false,
            mapTypeControl: false,
            disableDefaultUI: true,
            styles: [
                {
                    "featureType": "water",
                    "elementType": "geometry",
                    "stylers": [
                        {
                            "color": "#e9e9e9"
                        },
                        {
                            "lightness": 17
                        }
                    ]
                },
                {
                    "featureType": "landscape",
                    "elementType": "geometry",
                    "stylers": [
                        {
                            "color": "#f5f5f5"
                        },
                        {
                            "lightness": 20
                        }
                    ]
                },
                {
                    "featureType": "road.highway",
                    "elementType": "geometry.fill",
                    "stylers": [
                        {
                            "color": "#ffffff"
                        },
                        {
                            "lightness": 17
                        }
                    ]
                },
                {
                    "featureType": "road.highway",
                    "elementType": "geometry.stroke",
                    "stylers": [
                        {
                            "color": "#ffffff"
                        },
                        {
                            "lightness": 29
                        },
                        {
                            "weight": 0.2
                        }
                    ]
                },
                {
                    "featureType": "road.arterial",
                    "elementType": "geometry",
                    "stylers": [
                        {
                            "color": "#ffffff"
                        },
                        {
                            "lightness": 18
                        }
                    ]
                },
                {
                    "featureType": "road.local",
                    "elementType": "geometry",
                    "stylers": [
                        {
                            "color": "#ffffff"
                        },
                        {
                            "lightness": 16
                        }
                    ]
                },
                {
                    "featureType": "poi",
                    "elementType": "geometry",
                    "stylers": [
                        {
                            "color": "#f5f5f5"
                        },
                        {
                            "lightness": 21
                        }
                    ]
                },
                {
                    "featureType": "poi.park",
                    "elementType": "geometry",
                    "stylers": [
                        {
                            "color": "#dedede"
                        },
                        {
                            "lightness": 21
                        }
                    ]
                },
                {
                    "elementType": "labels.text.stroke",
                    "stylers": [
                        {
                            "visibility": "on"
                        },
                        {
                            "color": "#ffffff"
                        },
                        {
                            "lightness": 16
                        }
                    ]
                },
                {
                    "elementType": "labels.text.fill",
                    "stylers": [
                        {
                            "saturation": 36
                        },
                        {
                            "color": "#333333"
                        },
                        {
                            "lightness": 40
                        }
                    ]
                },
                {
                    "elementType": "labels.icon",
                    "stylers": [
                        {
                            "visibility": "off"
                        }
                    ]
                },
                {
                    "featureType": "transit",
                    "elementType": "geometry",
                    "stylers": [
                        {
                            "color": "#f2f2f2"
                        },
                        {
                            "lightness": 19
                        }
                    ]
                },
                {
                    "featureType": "administrative",
                    "elementType": "geometry.fill",
                    "stylers": [
                        {
                            "color": "#fefefe"
                        },
                        {
                            "lightness": 20
                        }
                    ]
                },
                {
                    "featureType": "administrative",
                    "elementType": "geometry.stroke",
                    "stylers": [
                        {
                            "color": "#fefefe"
                        },
                        {
                            "lightness": 17
                        },
                        {
                            "weight": 1.2
                        }
                    ]
                }
            ]
        });

        this._markers = [];
        this._infoWindow = null;
    }

    getMap() {
        return this._map;
    }

    setZoom(zoom) {
        this._options.zoom = zoom;
        this._map.setZoom(zoom);
    }

    setCenter(coords) {
        this._options.center = coords;
        this._map.setCenter(coords);
    }

    setMarkerOnCurrentLocation(inst, position) {
        new google.maps.Marker({
            map: inst.getMap(),
            position: {lat: position.coords.latitude, lng: position.coords.longitude},
            icon: {
                url: "/assets/img/current.png",
                scaledSize: new google.maps.Size(32, 32),
                origin: new google.maps.Point(0, 0),
                anchor: new google.maps.Point(16, 16),
                scale: 1
            }
        });

        inst.setCenter({lat: position.coords.latitude, lng: position.coords.longitude});
        inst.setZoom(13);
    }

    setEventMarkers(json, event_component) {
        this.hideAllMarkers();
        this._markers = [];

        let events = json["events"];
        for(let i = 0; i < events.length; i++) {
            if (events[i].eventStatus === "ACTIVE") {
                let marker = new google.maps.Marker({
                    position: new google.maps.LatLng(parseFloat(events[i].latitude), parseFloat(events[i].longitude)),
                    map: this._map,
                    icon: {
                        url: "/assets/img/beer.png",
                        scaledSize: new google.maps.Size(32, 32),
                        origin: new google.maps.Point(0, 0),
                        anchor: new google.maps.Point(16, 16),
                        scale: 1
                    }
                });

                console.log(events[i]);
                this._infoWindow = new google.maps.InfoWindow({
                    content: "<div style='line-height: 1.8rem;'><b style='margin-bottom: 5px; font-size: 1.2rem;'>" + events[i].host.firstName + " " + events[i].host.lastName + "</b></div><div style='margin-bottom: 10px;'>" + events[i].description + "</div><div>Количество людей: " + (events[i].guestList.length + 1) + "</div>"
                });

                marker.mid = i;
                let that = this;

                if (event_component.getState() === 0) {
                    marker.addListener('click', function() {

                        $(".wrap__action-buttons-btn--main").removeClass("disabled");
                        $(".wrap__action-buttons-btn--main").off("click");

                        that._infoWindow.open(that._map, marker);

                        $(".wrap__action-buttons-btn--main").click(() => {
                            that._infoWindow.close();
                            that._infoWindow = null;

                            $.ajax({
                                method: "POST",
                                url: "api/events/" + events[i].id + "/join"
                            }).done((data) => {
                                that._markers.forEach(marker => {
                                    if (marker.mid !== this.mid) {
                                        marker.setMap(null);
                                    }
                                });
                                event_component.getCurrentState();
                            });
                        });
                    });

                    google.maps.event.addListener(this._infoWindow,'closeclick',function() {
                        $(".wrap__action-buttons-btn--main").addClass("disabled");
                    });

                } else {
                    google.maps.event.clearInstanceListeners(marker);
                }

                this._markers.push(marker);

            }
        }
    }

    hideAllMarkers() {
        for (let i = 0; i < this._markers.length; i++) {
            this._markers[i].setMap(null);
        }
    }

    getCenter() {
        return {lat: this._map.getCenter().lat(), lng: this._map.getCenter().lng()};
    }

}