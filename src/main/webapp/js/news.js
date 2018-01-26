$(document).ready(function(){
    var url = "http://localhost:8080/api/news/getall";
   // alert(url);
    function getall() {
        $("tbody").empty();
        $.ajax({
            type: "GET",
            url: url,
            success:function(data){
                //alert("aiki");
                console.log(data);
                $.each(data, function (index, value) {
                    var msg= '<td>'+value.message + '</td>';
                    var tch= '<td>'+value.teacher + '</td>';
                    var crs= '<td>'+value.course + '</td>';
                    $("#list").append('<tr>'+'<td>' + value.title + '</td>'+ msg+tch+crs+'<td><button type="submit" class="btn btn-default">Delete</button></td><td><button type="submit" class="btn btn-default">Update</button></td></tr>');
                    //alert(value);
                });
            },
            error: function (data) {
                alert("Error!");
            }
        });


    }
    function postNews() {
        //alert('akolo');
        var m=$('#message').val();
        var course = $('#title').val();
        var title = $('#course').val();
        var body = {id:'',message:m,course:course,teacher:"",title:title};
        $.ajax({
            type: "POST",
            url: "http://localhost:8080/api/news",
            data: JSON.stringify(body),
            dataType: "text",
            contentType: "application/json; charset=utf-8",
            success: function (data) {
               //alert("este bine");
                getall();
            },
            error: function (data, status, xhr) {
                //var err = eval("(" + xhr.getResponseHeader('Location') + ")");
                alert(err);
            }
        });
    }
    getall();

    $("#add-btn").click(function (event) {
        event.preventDefault();
        postNews();

    });

   

    function deleteNew(newid){
        $.ajax({
            type:"POST",
            url:"http://localhost:8080/api/news/delete/"+newid,
            success:function (data) {
                getall();
            },
            error:function (data,status,xhr) {
                alert("Error");
            }
        });
    }

    function updateNew(newid){
        var m=$('#message').val();
        var course = $('#title').val();
        var title = $('#course').val();
        var body = {id:newid,message:m,course:course,teacher:"",title:title};
        $.ajax({
            type:"POST",
            url:"http://localhost:8080/api/news/update/"+newid,
            success:function (data) {
                getall();
            },
            error:function (data,status,xhr) {
                alert("Error");
            }
        });
    }

    }
);