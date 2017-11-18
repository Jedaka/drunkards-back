import React, { Component } from "react";

import { Button, Icon } from 'react-materialize';

import "./ActionButton.less";

export default class ActionButton extends Component {
    static defaultProps = {
        type: "normal"
    };

    constructor(props) {
        super(props);
    }

    render() {
        let toRender = this.props.type == "floaty" ?
            (<Button className={this.props.className + " orange lighten-2"} floating large waves="light"><Icon>add</Icon></Button>)
            :
            (<Button className={this.props.className + " orange lighten-2"} large waves="light">{this.props.text}</Button>);

        return (
            toRender
        );
    }
}