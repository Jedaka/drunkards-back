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
    let event_component = new UserEvent();
    let modal_listeners = event_component.getModalEvents();

    var position = getMarkers(map);

    actions.addAction({
        type: "floaty",
        onClick: modal_listeners.open()
    });

    actions.addAction({
        className: "wrap__action-buttons-btn wrap__action-buttons-btn--full",
        text: "Принять Участие"
    })
});

function getMarkers(map) {
  $.ajax({
    method: "GET",
    url: "api/events"
  }).done(function callback(json) {
    console.log(json)
    for(let i = 0; i < json.length; i++) {
      placeMarkers(
          map.getMap(),
          new google.maps.LatLng(
              parseFloat(json[i].latitude),
              parseFloat(json[i].longitude)
          ))
    }
  })
}

function placeMarkers(map, position) {

  console.log("Position, ", position)
  console.log("map, ", map)

  var marker = new google.maps.Marker({
    position: position,
    map: map
  });
}