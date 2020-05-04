var websocket, userName, you;

function init() {
    websocket = new WebSocket('ws://airline-app-bel.herokuapp.com/chat');
    websocket.onopen = function (event) {
        websocketOpen(event);
    };
    websocket.onmessage = function (event) {
        websocketMessage(event);
    };
    websocket.onerror = function (event) {
        websocketError(event);
    };
    document.getElementById('send').onclick=sendMessage;
}

function websocketOpen(event) {
    console.log("webSocketOpen invoked");
    websocket.send(userName);
}

function websocketMessage(event) {
    console.log("websocketMessage invoked");
    document.getElementById('chatWindow').value += '\r' + event.data;
}

function websocketError(event) {
    console.log("websocketError invoked");
}

function sendMessage() {
    var input = document.getElementById('chatInput');
	websocket.send(userName + ": " + input.value);
	document.getElementById('chatWindow').value += '\r' + you + ': ' + input.value;
    input.value = "";
}

function closeConnection() {
    websocket.close();
}

window.addEventListener("load", init);
window.addEventListener("unload", closeConnection);