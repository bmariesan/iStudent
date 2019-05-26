function authenticate() {
    var username = $("#username").val();
    var email = $("#email").val();
    var password = $("#password").val();
    var phone = $("#phone-number").val();
    var address = $("#address").val();
    var gender = "F";
    var age = 20;
    var roles = ["ADMIN"];


    var params = {
        "userName":username,
        "email":email,
        "password":password,
        "address":address,
        "phoneNumber":phone,
        "age":age,
        "gender":gender,
        "roles":roles
    };


    console.log(JSON.stringify(params));


    $.ajax({
        type: "POST",
        url: "/user/save",
        data: JSON.stringify(params),
        dataType: "text",
        contentType: "application/json; charset=utf-8",
        success: function (data) {
            alert("Success");
            console.log(data);
        },
        error: function(error){
            console.log(error);
        }
    });

}


function login() {
    var username = $("#username").val();
    var email = $("#email").val();
    var password = $("#password").val();

    var params = {
        "userName": username,
        "email": email,
        "password":password
    };


    console.log(JSON.stringify(params));


    $.ajax({
        type: "POST",
        url: "/login",
        data: JSON.stringify(params),
        dataType: "text",
        contentType: "application/json; charset=utf-8",
        success: function (data) {
            alert("Success");
            console.log(data);
        },
        error: function(error){
            console.log(error);
        }
    });

}


$('.form').find('input, textarea').on('keyup blur focus', function (e) {

    var $this = $(this),
        label = $this.prev('label');

    if (e.type === 'keyup') {
        if ($this.val() === '') {
            label.removeClass('active highlight');
        } else {
            label.addClass('active highlight');
        }
    } else if (e.type === 'blur') {
        if( $this.val() === '' ) {
            label.removeClass('active highlight');
        } else {
            label.removeClass('highlight');
        }
    } else if (e.type === 'focus') {

        if( $this.val() === '' ) {
            label.removeClass('highlight');
        }
        else if( $this.val() !== '' ) {
            label.addClass('highlight');
        }
    }

});

$('.tab a').on('click', function (e) {

    e.preventDefault();

    $(this).parent().addClass('active');
    $(this).parent().siblings().removeClass('active');

    target = $(this).attr('href');

    $('.tab-content > div').not(target).hide();

    $(target).fadeIn(600);

});