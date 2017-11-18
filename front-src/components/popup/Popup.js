import React, { Component } from "react";

import { Modal } from 'react-materialize';

import ActionButton from "../buttons/ActionButton";

export default class Popup extends Component {
    constructor(props) {
        super(props);
    }

    render() {

        return (
            <Modal header="Новое событие">
                Testing!
            </Modal>
        );

    }

}