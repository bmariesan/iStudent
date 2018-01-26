/**
 * Created by Alina on 11/22/2017.
 */

$(document).ready(function (){

    $.ajax({
        type: "GET",
        url: "/api/report",
        success: function (data) {
            $("#report").text(data);
        },
        error: function (e) {
            console.log("Error");
        }
    });
});
