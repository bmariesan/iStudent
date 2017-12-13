$(document).ready(function () {

    var url = window.location;
    var latestGreetingUrl;

    $("#btnId").click(function (event) {
        event.preventDefault();

        // Open Bootstrap Modal
        if (latestGreetingUrl) {
            openModel();
            // Get data from Server
            ajaxGet();
        }
    });

    $("#greetingForm").submit(function (event) {
        event.preventDefault();
        ajaxPost();
    });

    // Open Bootstrap Modal
    function openModel() {
        $("#modalId").modal();
    }

    // DO GET
    function ajaxGet() {
        $.ajax({
            type: "GET",
            url: latestGreetingUrl,
            success: function (data) {
                // fill data to Modal Body
                fillData(data);
            },
            error: function (e) {
                fillData(null);
            }
        });
    }

    // DO POST
    function ajaxPost() {
        var result = {};
        $.each($("#greetingForm").serializeArray(), function () {
            result[this.name] = this.value;
        });

        $.ajax({
            type: "POST",
            url: "/api/greeting",
            data: JSON.stringify(result),
            dataType: "text",
            contentType: "application/json; charset=utf-8",
            success: function (data, status, xhr) {
                latestGreetingUrl = xhr.getResponseHeader('Location');
            },
            error: function (data, status, xhr) {
                var err = eval("(" + xhr.getResponseHeader('Location') + ")");
                alert(err);
            }
        });
    }

    function fillData(data) {
        if (data != null) {
            $(".modal-body #greetingId").text(JSON.stringify(data));
        } else {
            $(".modal-body #greetingId").text("Can Not Get Data from Server!");
        }
    }
});