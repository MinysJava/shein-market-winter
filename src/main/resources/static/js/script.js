var stompClient = null;

window.onload = connect();

function connect() {
    var socket = new SockJS('/shop');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function(frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/greetings', function(greeting){
            showGreeting(JSON.parse(greeting.body).content);
        });
    });
}

function sendProduct(id) {
    // var name = 'товар';
    stompClient.send("/app/shop", {}, JSON.stringify({ 'id': id }));
}

function showGreeting(message) {
    console.log(message);
    document.getElementById("resultInput").value=message;
}