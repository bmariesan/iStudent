/**
 * Created by Alina on 11/22/2017.
 */

$(document).ready(function (){

    $.ajax({
        type: "GET",
        url: "/api/report/html",
        dataType: "text",
        contentType: "application/json; charset=utf-8",
        success: function (data) {
            $("#reportHtmlCode").text(data);
            $("#reportHtml").html(data);
        },
        error: function (e) {
            console.log("Eroare la get api/report");
        }
    });

    $("#see-more-button").click(function(){
        Module.publicMethod();
    })
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
var Module = (function () {
    var privateMethod = function () {
        var report = "<h3> Studenti picati </h3><br/>" +
                "<ul><li><h3> 2018</h3>" +
                "<h4>PPD: 2</h4></li>" +
                "<li><h3> 2018</h3>" +
                "<h4>Mobile: 12</h4></li>" +
                "</ul><br/>";
        var code = $("#reportHtmlCode").text();
        var html = $("#reportHtml").html();
        report = code + report;
        $("#reportHtmlCode").text(report);
        $("#reportHtml").html(report);
    };
    return {
        publicMethod: function () {
            privateMethod();
        },
    };
})();
