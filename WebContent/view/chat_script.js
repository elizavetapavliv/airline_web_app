var websocket, userName, you;

function init() {
    websocket = new WebSocket('wss://' +  window.location.host + window.location.pathname + '/chat');
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
    document.getElementById('chat_window').value += '\r' + event.data;
}

function websocketError(event) {
    console.log("websocketError invoked");
}

function sendMessage() {
    var input = document.getElementById('chat_input');
	websocket.send(userName + ": " + input.value);
	document.getElementById('chat_window').value += '\r' + you + ': ' + input.value;
    input.value = "";
}

function closeConnection() {
    websocket.close();
}

window.addEventListener("load", init);
window.addEventListener("unload", closeConnection);