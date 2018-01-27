function fillLabels(){
    var url = window.location + '';
    var res = url.split("/");
    var id =res.slice(-1).pop();
    $.ajax({
        type:"GET",
        url:"http://localhost:8080/api/news/"+id,
        success:function(data){
            console.log(data.new.title);
            $('#title').val(data.new.title);
            $('#message').val(data.new.message);
            $('#course').val(data.new.course);
        },
        error:function (data, status, xhr){
            alert("Error");
        }
    })
}

function loadAll(){
    var url = window.location + '';
    var res = url.split("/");
    $('#list-comm').empty();
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/news/comments/" + res.slice(-1).pop(),
        data: "",
        contentType: "application/json; charset=utf-8",
        success: function (data) {
            console.log(data);
            if(data===undefined){
                alert("Undefined");
            }
            else {
                console.log(data);
                if(data.comments.length!=0) {
                    $.each(data.comments, function (index, value) {
                        var msg = value.message;
                        var author = value.posterName;
                        $('#list-comm').append('<tr><td>' + msg + '</td><td>' + author + '</td></tr>');
                    });
                }
            }

        },
        error: function (data, status, xhr) {
            alert("Error");
        }
    });
}
$(document).ready(function () {

    loadAll();
    fillLabels();
    $('#add-comm-btn').click(function(event)
    {
        event.preventDefault();
        var url = window.location + '';
        var res = url.split("/");
        var msg = $('#comm-msg').val();
        var body = {id:'',newsId:res.slice(-1).pop(),message:msg,posterName:'Pop Ion'};
        $.ajax({
            type:"POST",
            url:"http://localhost:8080/api/news/comments",
            contentType: "application/json; charset=utf-8",
            data:JSON.stringify(body),
            success:function(data){
                loadAll();
            },
            error:function(data,status,xhr){
                alert("Error");
            }
        })
    })
});