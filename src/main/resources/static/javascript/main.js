var boardId;

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