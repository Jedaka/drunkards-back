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

    let actions = new Actions();
    let event_component = new UserEvent({map: map, user: user});

    event_component.getCurrentState();

    let modal = event_component.getModalEvents();

    actions.addAction({
        type: "floaty",
        onClick: function() {
            $(".wrap__state-header").html("Выберите место").fadeTo("fast", 1);

            $(this).fadeTo("fast", 0);
            map.hideAllMarkers();
            map.setZoom(map.getMap().getZoom() > 15 ? map.getMap().getZoom() : 15);

            let mainAction = actions.getAction(1);
            $(mainAction.render()).removeClass("disabled").text("Буду здесь");

            $(".wrap__create-event-marker").fadeTo("fast", 1);

            mainAction.changeOnClick((e) => {
                event_component.getModal().setLocation(map.getCenter());
                modal.open();

                event_component.getModal().addEventListener((data) => {
                    $.ajax({
                        url: "api/events",
                        method: "POST",
                        data: JSON.stringify(data),
                        contentType: "application/json; charset=utf-8",
                        dataType: "json"
                    }).done(() => {
                        modal.close(),
                        event_component.getCurrentState();
                    })
                });
            });

        }
    });

    actions.addAction({
        className: "wrap__action-buttons-btn wrap__action-buttons-btn--full wrap__action-buttons-btn--main",
        text: "Принять Участие"
    })
});