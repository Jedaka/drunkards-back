import React, { Component } from "react";

import "./ButtonWrap.less";

import ActionButton from "./ActionButton";
import Popup from "../popup/Popup";

export default class ButtonWrap extends Component {
    constructor(props) {
        super(props);
    }



    render() {
        return (
            <div>
                <ActionButton className="buttons__btn buttons__btn--large-thin" type="floaty"/>
                <ActionButton className="buttons__btn" text="Найти Событие"/>
            </div>
        );
    }
}