var info;
var category;
var editInfo;
var deleteInfo;


window.onload = function () {
    info = document.getElementById("info-container").valueOf();
    category = document.getElementById("category-container").valueOf();
    editInfo = document.getElementById("edit-container").valueOf();
    deleteInfo = document.getElementById("delete-container").valueOf();

    var infoHeight = document.getElementById("info-height").valueOf();
    var footer = document.getElementById("footer").valueOf();

    if (infoHeight.clientHeight > 700) {
        footer.style.position = "static";
    }
}

function infoContainer() {
    info.style.display = "block";
    category.style.display = "none";
    editInfo.style.display = "none";
    deleteInfo.style.display = "none";
}

function categoryContainer() {
    info.style.display = "none";
    category.style.display = "block";
    editInfo.style.display = "none";
    deleteInfo.style.display = "none";
}

function editContainer() {
    info.style.display = "none";
    category.style.display = "none";
    editInfo.style.display = "block";
    deleteInfo.style.display = "none";
}

function deleteContainer() {
    info.style.display = "none";
    category.style.display = "none";
    editInfo.style.display = "none";
    deleteInfo.style.display = "block";
}

