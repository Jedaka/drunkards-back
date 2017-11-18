import $ from "jquery";
window.$ = window.jQuery = $;

export default class Modal {
    static defaults = {
        selector: $(".wrap__modal"),
        fields: {

        }
    };

    constructor(options) {
        options = options || {};

        this._options = Object.assign({}, Modal.defaults);
        this._options.selector = options.selector ? options.selector : Modal.defaults.selector;

        this._data = Object.assign({}, Modal.defaults.data);

        $(this._options.selector).modal();


    }

    openModal() {
        return (e) => {
            $(Modal.defaults.selector).modal('open');
        }
    }
}