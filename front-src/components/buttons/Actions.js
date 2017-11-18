import $ from "jquery";
window.$ = window.jQuery = $;

import Button from "./Button";

import "./Actions.less";

export default class Actions {
    static defaults = {
        selector: $(".wrap__action-buttons")
    };

    constructor(options) {
        options = options || {};

        this._options = Object.assign({}, Actions.defaults);
        this._options.selector = options.selector ? options.selector : Actions.defaults.selector;

        this._buttons = [];

    }

    addAction(options) {
        this._buttons.push(new Button(options));

        $(this._options.selector).append(this._buttons[this._buttons.length - 1].render());
    }
}