var info;
var category;
var editInfo;
var deleteInfo;
var infoHeight;
var footer;
var categorySubmit;

window.onload = function () {
    info = document.getElementById("info-container").valueOf();
    category = document.getElementById("category-container").valueOf();
    categorySubmit = document.getElementById("category-submit").valueOf();
    editInfo = document.getElementById("edit-container").valueOf();
    deleteInfo = document.getElementById("delete-container").valueOf();

    document.getElementById("category-input-email").value = '';
    document.getElementById("category-input-password").value = '';


    infoHeight = document.getElementById("info-height").valueOf();
    footer = document.getElementById("footer").valueOf();

    if (infoHeight.clientHeight > 700) {
        footer.style.position = "static";
    }
}

function infoContainer() {
    info.style.display = "block";
    category.style.display = "none";
    editInfo.style.display = "none";
    deleteInfo.style.display = "none";
    categorySubmit.style.display = "none";

    infoHeight = document.getElementById("info-height").valueOf();
    footer = document.getElementById("footer").valueOf();

    if (infoHeight.clientHeight > 700) {
        footer.style.position = "static";
    }

}

function categoryContainer() {
    info.style.display = "none";
    category.style.display = "block";
    categorySubmit.style.display = "block";
    editInfo.style.display = "none";
    deleteInfo.style.display = "none";

    infoHeight = document.getElementById("info-height").valueOf();
    footer = document.getElementById("footer").valueOf();

    if (infoHeight.clientHeight > 900) {
        footer.style.position = "static";
    } else {
        footer.style.position = "absolute";
    }

}

function editContainer() {
    info.style.display = "none";
    category.style.display = "none";
    categorySubmit.style.display = "none";
    editInfo.style.display = "block";
    deleteInfo.style.display = "none";

    infoHeight = document.getElementById("info-height").valueOf();
    footer = document.getElementById("footer").valueOf();

    if (infoHeight.clientHeight > 900) {
        footer.style.position = "static";
    } else {
        footer.style.position = "absolute";
    }

}

function deleteContainer() {
    info.style.display = "none";
    category.style.display = "none";
    categorySubmit.style.display = "none";
    editInfo.style.display = "none";
    deleteInfo.style.display = "block";

    infoHeight = document.getElementById("info-height").valueOf();
    footer = document.getElementById("footer").valueOf();

    if (infoHeight.clientHeight > 900) {
        footer.style.position = "static";
    } else {
        footer.style.position = "absolute";
    }

}

function checkSize() {

    infoHeight = document.getElementById("info-height").valueOf();
    footer = document.getElementById("footer").valueOf();

    if (infoHeight.clientHeight > 700) {
        footer.style.position = "static";
    } else {
        footer.style.position = "absolute";
    }

}

