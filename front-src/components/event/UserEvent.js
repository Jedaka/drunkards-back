import $ from "jquery";
window.$ = window.jQuery = $;

import Modal from "./Modal";


export default class UserEvent {
    static defaults = {
        map: null,
        user: null,
        state: 0
    };

    constructor(options) {
        options = options || {};

        this._options = Object.assign({}, UserEvent.defaults);
        this._options.state = options.state ? options.state : UserEvent.defaults.state;
        this._options.map = options.map ? options.map : UserEvent.defaults.map;
        this._options.user = options.user ? options.user : UserEvent.defaults.user;

        this._modal = new Modal();
    }

    getModal() {
        return this._modal;
    }

    getModalEvents() {
        return {
            open: this._modal.openModal,
            close: this._modal.closeModal
        }
    }

    getState() {
        return this._options.state;
    }

    setState(state, json) {
        this._options.state = state;

        switch (state) {
            case 0:
                this._initInitialState(json);
                break;
            case 1:
                this._initActiveState(json);
                break;
        }
    }

    _initActiveState(json) {
        let endpoint = json.host ? "/stop" : "/leave";

        $(".wrap__action-buttons-btn--main")
            .removeClass("orange lighten-2 disabled")
            .addClass("red darken-3")
            .text("Устал пить...")
            .off("click")
            .click(() => {
                $.ajax({
                    method: "POST",
                    url: "api/events/" + json["events"][0].id + endpoint
                }).done((data) => {
                    this.getCurrentState();
                })
            });

        $(".wrap__state-header").fadeTo("fast", 0, function() {
            $(this).html("");
        });

        $(".wrap__create-event-marker").fadeTo("fast", 0, function() {
            $(this).css("display", "none");
        });

        $(".btn-floating").fadeTo("fast", 0, function() {
            $(this).css("display", "none");
        });

    }

    _initInitialState(json) {
        $(".wrap__action-buttons-btn--main")
            .addClass("orange lighten-2 disabled")
            .removeClass("red darken-3")
            .text("Принять Участие")
            .off("click");

        $(".wrap__state-header").fadeTo("fast", 0, function() {
            $(this).html("");
        });

        $(".wrap__create-event-marker").fadeTo("fast", 0, function() {
            $(this).css("display", "none");
        });

        $(".btn-floating").html("<i class='material-icons'>add</i>").fadeTo("fast", 1);

        this._options.map.setEventMarkers(json, this);
    }

    getCurrentState() {
        $.ajax({
            method: "GET",
            url: "api/events"
        }).done((json) => {
            if (json.active) this.setState(1, json);
            else this.setState(0, json);

            this._options.map.setEventMarkers(json, this);
        });
    }


}