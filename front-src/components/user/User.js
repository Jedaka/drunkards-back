import $ from "jquery";
window.$ = window.jQuery = $;

export default class User {
    static defaults = {
        location: null,
        name: null,
        phone: null,

        CAN_GET_USER_LOCATION: "geolocation" in navigator
    };

    constructor(options) {
        options = options || {};

        this._options = Object.assign({}, User.defaults);
        this._options.location = options.location ? options.location : User.defaults.location;
    }

    getUserLocation(map, callback) {
        if (User.defaults.CAN_GET_USER_LOCATION) {
            navigator.geolocation.getCurrentPosition((position) => {
                this._options.location = {lat: position.coords.latitude, lng: position.coords.longitude}

                callback(map, position);
            });
        }

        return null;

    }

    getUserInfo() {
        // AJAX
    }
}