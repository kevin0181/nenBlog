var boardId;

window.onload = function () {
    var mainHeight = document.getElementById("main-height").valueOf();
    var footer = document.getElementById("footer").valueOf();

    if (mainHeight.clientHeight > 700) {
        footer.style.position = "static";
    }
}

function delete_alert(id) {
    var alert = document.getElementsByClassName("modal")[0].valueOf();
    alert.style.display = "block";
    boardId = id;
}

function cancel_alert() {
    var alert = document.getElementsByClassName("modal")[0].valueOf();
    alert.style.display = "none";
}

function Board_delete_get() {
    location.href = "boardDelete?id=" + boardId;
}