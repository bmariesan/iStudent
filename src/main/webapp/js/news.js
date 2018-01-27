function deleteNews(newid){
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

function updateNews(id,title,message,course){
    $('#add-btn').hide();
    $('#update-btn').show();
    $('#message').val(message);
    $('#title').val(title);
    $('#course').val(course);
    $('#id-hidden').val(id);
    return false;
}

function getall() {
    $("tbody").empty();
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/news/getall",
        success:function(data){
            //alert("aiki");
            console.log(data);
            $.each(data, function (index, value) {
                var msg= '<td>'+value.message + '</td>';
                var tch= '<td>'+value.teacher + '</td>';
                var crs= '<td>'+value.course + '</td>';
                var delClk = 'onclick=\"deleteNews(\''+value.id+'\')\"';
                //var delClk='onclick=\"fml()\"';
                var updClk= "onclick=\"updateNews('"+value.id+"','"+value.title+"','"+value.message+"','"+value.course+"')\" ";
                $("#list").append('<tr>'+'<td>' + value.title + '</td>'+ msg+tch+crs+'<td><button type="submit" class="btn btn-default" '+delClk+'>Delete</button></td><td><button type="submit" class="btn btn-default" '+updClk+'>Update</button></td></tr>');
                //alert(value);
            });
        },
        error: function (data) {
            alert("Error!");
        }
    });


}

$(document).ready(function(){
    var url = "http://localhost:8080/api/news/getall";
    $('#update-btn').hide();
   // alert(url);


    function postNews() {
        //alert('akolo');
        var m=$('#message').val();
        var title = $('#title').val();
        var course = $('#course').val();
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

   



    $('#update-btn').click(function(event){
        event.preventDefault();

        var id=$('#id-hidden').val();
        var m=$('#message').val();
        var course = $('#title').val();
        var title = $('#title').val();
        var body = {id:id,message:m,course:course,teacher:"Pop Ion",title:title};
        $.ajax({
            type:"POST",
            url:"http://localhost:8080/api/news/update/"+id,
            data:JSON.stringify(body),
            contentType: "application/json; charset=utf-8",
            success:function (data) {
                getall();
                $('#update-btn').hide();
                $('#add-btn').show();

            },
            error:function (data,status,xhr) {
                alert("Error");
            }
        });
    })

    }
);