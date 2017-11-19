import $ from "jquery";
import "materialize-css/dist/js/materialize.min";
import "materialize-css/dist/css/materialize.min.css";

import "./index.less";

import Map from "./components/map/Map";
import Actions from "./components/buttons/Actions";
import UserEvent from "./components/event/UserEvent";
import User from "./components/user/User";

window.$ = window.jQuery = $;

$(document).ready(() => {
    const user = new User();

    const map = new Map();

    user.getUserLocation(map, map.setMarkerOnCurrentLocation);
    map.setEventMarkers();

    let actions = new Actions();
    let event_component = new UserEvent();
    let modal_listeners = event_component.getModalEvents();

    actions.addAction({
        type: "floaty",
        onClick: modal_listeners.open()
    });

    actions.addAction({
        className: "wrap__action-buttons-btn wrap__action-buttons-btn--full wrap__action-buttons-btn--main",
        text: "Принять Участие"
    })
});