$(document).ready(function(){
    var url = "http://localhost:8080/api/news/getall";
    alert(url);
    function getall() {

        $.ajax({
            type: "GET",
            url: url,
            success:function(data){
                alert("aiki");
                $.each(data, function (index, value) {
                    $("#list").append('<li>' + value + '</li>');
                    alert(value);
                });
            },
            error: function (caca) {
                alert("caca");
            }
        });
        /*
        $.get(url,function(data, status){
            alert("Data: " + data + "\nStatus: " + status);
        });*/

    }
    getall();

})