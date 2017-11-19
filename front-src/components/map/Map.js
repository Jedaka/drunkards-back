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
                url: "/assets/img/current.svg",
                scaledSize: new google.maps.Size(20, 20),
                origin: new google.maps.Point(0, 0),
                anchor: new google.maps.Point(10, 10),
                scale: 1
            }
        });

        inst.setCenter({lat: position.coords.latitude, lng: position.coords.longitude});
        inst.setZoom(13);
    }

    setEventMarkers() {
        $.ajax({
            method: "GET",
            url: "api/events"
        }).done((json) => {
            for(let i = 0; i < json.length; i++) {
                if (json[i].eventStatus === "ACTIVE") {
                    let marker = new google.maps.Marker({
                        position: new google.maps.LatLng(parseFloat(json[i].latitude), parseFloat(json[i].longitude)),
                        map: this._map
                    });

                    let infoWindow = new google.maps.InfoWindow({
                        content: json[i].hostUserName + " " + json[i].description
                    });

                    marker.addListener('click', function() {
                        $(".wrap__action-buttons-btn").removeClass("disabled");
                        infoWindow.open(this._map, marker);
                    });

                    google.maps.event.addListener(infoWindow,'closeclick',function() {
                        $(".wrap__action-buttons-btn").addClass("disabled");
                    });

                    this._markers.push(marker);

                }
            }
        })
    }
}