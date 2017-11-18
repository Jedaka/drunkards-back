import React, { Component } from "react";

import "./Map.less";

import GoogleMapReact from 'google-map-react';

export default class Map extends Component {
    static defaultProps = {
        center: {lat: 59.95, lng: 30.33},
        zoom: 11
    };

    constructor(props) {
        super(props);
    }

    mapOptions() {
        return {
            zoomControl: false,
            panControl: false,
            mapTypeControl: false,
            scrollwheel: false
        }
    }

    render() {
        return (
            <div className="map">
                <GoogleMapReact
                    bootstrapURLKeys={{key: "AIzaSyDQTDpFipi7AePgwNFUJR8eUJbDO8nZhAw"}}
                    defaultCenter={this.props.center}
                    defaultZoom={this.props.zoom}
                    options={this.mapOptions()}
                ></GoogleMapReact>
            </div>
        );
    }
}