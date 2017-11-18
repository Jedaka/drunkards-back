import $ from "jquery";
import "materialize-css/dist/js/materialize.min";
import "materialize-css/dist/css/materialize.min.css";

import "./index.less";

import Map from "./components/map/Map";
import Actions from "./components/buttons/Actions";
import UserEvent from "./components/event/UserEvent";

window.$ = window.jQuery = $;

$(document).ready(() => {

    var map = new Map();

    let actions = new Actions();
    let event_component = new UserEvent();

    let modal_listeners = event_component.getModalEvents();

    var position = getMarkers();
    placeMarkers(map, position);

    actions.addAction({
        type: "floaty",
        onClick: modal_listeners.open()
    });

    actions.addAction({
        className: "wrap__action-buttons-btn wrap__action-buttons-btn--full",
        text: "Принять Участие"
    })
});

function getMarkers() {
  var xhr = new XMLHttpRequest();
  xhr.open('GET', '/api/events', false);
  xhr.send();
  if (xhr.status != 200) {
    alert( xhr.status + ': ' + xhr.statusText ); // пример вывода: 404: Not Found
  } else {
    alert( xhr.responseText ); // responseText -- текст ответа.
  }
}


function placeMarkers(map, position) {

  var myLatLng = {lat: position.latitude, lng: position.longitude}

  var map = new google.maps.Map(map, {
    zoom: 4,
    center: myLatLng
  });

  var marker = new google.maps.Marker({
    position: myLatLng,
    map: map,
    title: 'Hello World!'
  });

  marker.setMap(map);
}