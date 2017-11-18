import $ from "jquery";
window.$ = window.jQuery = $;

import "materialize-css/dist/js/materialize.min";
import "materialize-css/dist/css/materialize.min.css";

import React from "react";
import ReactDOM from "react-dom";

import App from "./components/App";

ReactDOM.render(
    <App />,
    document.getElementById("app")
);