import React, { Component } from "react";

import "./App.less";

import Map from "./map/Map";
import ButtonWrap from "./buttons/ButtonWrap";

export default class App extends Component {

    constructor(props) {
        super(props);

        this.state = {
            currentState: 0,
        }
    }

    componentDidMount() {

    }

    render() {
        return (
            <div>
                <Map />
                <ButtonWrap  />
            </div>
        );
    }
}