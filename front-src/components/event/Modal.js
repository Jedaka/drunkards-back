import $ from "jquery";
window.$ = window.jQuery = $;

export default class Modal {
    static defaults = {
        selector: $(".wrap__modal"),
        data: {
            // name: null,
            // phone: null,
            description: null,
            location: null
        }
    };

    constructor(options) {
        options = options || {};

        this._options = Object.assign({}, Modal.defaults);
        this._options.selector = options.selector ? options.selector : Modal.defaults.selector;

        this._data = Object.assign({}, Modal.defaults.data);

        $(this._options.selector).modal();


    }

    addEventListener(callback) {
        $(".modal-action").off("click").click(() => {
            // this.setName($("#first_name").val());
            // this.setPhone($("#phone").val());
            this.setDescription($("#description").val());

            callback({
                description: this._data.description,
                latitude: this._data.location.lat,
                longitude: this._data.location.lng
            });
        });
    }

    openModal() {
        return $(".wrap__modal").modal('open');
    }

    closeModal() {
        return $(".wrap__modal").modal('close');
    }

    setLocation(obj) {
        this._data.location = obj;
    }

    setName(name) {
        this._data.name = name;
    }

    setPhone(phone) {
        this._data.phone = phone;
    }

    setDescription(text) {
        this._data.description = text;
    }

}