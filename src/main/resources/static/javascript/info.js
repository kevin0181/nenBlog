var infoHeight;
var footer;

window.onload = function () {

    document.getElementById("category-input-email").value = '';
    document.getElementById("category-input-password").value = '';

    infoHeight = document.getElementById("info-height").valueOf();
    footer = document.getElementById("footer").valueOf();

    if (infoHeight.clientHeight > 700) {
        footer.style.position = "static";
    }
}

function infoContainer() {

    infoHeight = document.getElementById("info-height").valueOf();
    footer = document.getElementById("footer").valueOf();

    if (infoHeight.clientHeight > 700) {
        footer.style.position = "static";
    }

}

function categoryContainer() {

    infoHeight = document.getElementById("info-height").valueOf();
    footer = document.getElementById("footer").valueOf();

    if (infoHeight.clientHeight > 900) {
        footer.style.position = "static";
    } else {
        footer.style.position = "absolute";
    }

    location.href = 'infoCategory';

}

function editContainer() {

    infoHeight = document.getElementById("info-height").valueOf();
    footer = document.getElementById("footer").valueOf();

    if (infoHeight.clientHeight > 900) {
        footer.style.position = "static";
    } else {
        footer.style.position = "absolute";
    }

    location.href = 'infoChange';

}

function deleteContainer() {

    infoHeight = document.getElementById("info-height").valueOf();
    footer = document.getElementById("footer").valueOf();

    if (infoHeight.clientHeight > 900) {
        footer.style.position = "static";
    } else {
        footer.style.position = "absolute";
    }

    location.href = 'infoDelete';

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

function deleteCategory(id) {

    location.href = "deleteCategory?id=" + id;
}

