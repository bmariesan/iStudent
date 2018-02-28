/**
 * Created by Bogdan on 11/26/2017.
 */

$(document).ready(function (){

    $.ajax({
        type: "GET",
        url: "/api/report/pdf",
        dataType: "text",
        contentType: "application/json; charset=utf-8",
        success: function (data) {
        },
        error: function (e) {
            console.log("Eroare la get api/report/pdf");
        }
    });
});
