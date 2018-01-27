/**
 * Created by Alina on 11/22/2017.
 */

$(document).ready(function (){

    $.ajax({
        type: "GET",
        url: "/api/report",
        dataType: "text",
        contentType: "application/json; charset=utf-8",
        success: function (data) {
            $("#report").text(data);
        },
        error: function (e) {
            console.log("Eroare la get api/report");
        }
    });
});

$(document).ready(function (){

    $.ajax({
        type: "GET",
        url: "/api/json",
        dataType: "text",
        contentType: "application/json; charset=utf-8",
        success: function (data) {
            $("#report").text(data);
        },
        error: function (e) {
            console.log("Eroare la get api/report");
        }
    });
});