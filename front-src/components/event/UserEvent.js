import $ from "jquery";
window.$ = window.jQuery = $;

import Modal from "./Modal";


export default class UserEvent {
    static defaults = {
        data: {
            name: null,
            phone: null,
            description: null,
            location: null
        }
    };

    constructor(options) {
        options = options || {};

        this._options = Object.assign({}, Event.defaults);

        this._modal = new Modal();
    }

    getModalEvents() {
        return {
            open: this._modal.openModal,

        }
    }


}