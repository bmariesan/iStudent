$(document).ready(function () {
    var url = window.location + '';
    var res = url.split("/");
    // console.log("YOLO");
    // alert(res);
    // alert(res.slice(-1).pop());

    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/news/comments/" + res.slice(-1).pop(),
        data: "",
        contentType: "application/json; charset=utf-8",
        success: function (data) {
            console.log(data);
            // getall();
            // $('#update-btn').hide();
            // $('#add-btn').show();

        },
        error: function (data, status, xhr) {
            alert("Error");
        }
    });
});