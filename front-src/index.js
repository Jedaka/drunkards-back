import $ from "../node_modules/jquery";
window.$ = window.jQuery = $;

import "../node_modules/materialize-css/dist/js/materialize.min";
import "../node_modules/materialize-css/dist/css/materialize.min.css";

import React from "../node_modules/react";
import ReactDOM from "../node_modules/react-dom";

ReactDOM.render(
    <h1>Hello, React!</h1>,
    document.getElementById("app")
);