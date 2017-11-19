import $ from "jquery";
import "./Button.less";

window.$ = window.jQuery = $;

export default class Button {
    static defaults = {
        className: "wrap__action-buttons-btn",
        type: "normal",
        text: null,
        onClick: null
    }

    constructor(options) {
        options = options || {};

        this._options = Object.assign({}, Button.defaults);
        this._options.className = options.className ? options.className : Button.defaults.className;
        this._options.type = options.type ? options.type : Button.defaults.type;
        this._options.text = options.text ? options.text : Button.defaults.text;
        this._options.onClick = options.onClick ? options.onClick : Button.defaults.onClick;

        this._dom = document.createElement("button");
        switch(this._options.type) {
            case "normal":
                this._dom.className = this._options.className + " disabled btn btn-large orange lighten-2 waves-effect waves-light";
                this._dom.innerHTML = this._options.text;
                if (this._options.onClick) this._dom.addEventListener("click", this._options.onClick);
                break;
            case "floaty":
                this._dom.className = this._options.className + " btn-floating btn-large orange lighten-2 waves-effect waves-light";
                this._dom.innerHTML = "<i class='material-icons'>add</i>";
                if (this._options.onClick) this._dom.addEventListener("click", this._options.onClick);
                break;
        }
    }

    render() {
        return this._dom;
    }
}