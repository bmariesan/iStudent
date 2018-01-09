$(document).ready(function () {
    console.log("available")
    getAvailableCourses()

    function getAvailableCourses() {
        $.ajax({
            type: "GET",
            url: '/api/subscribe/courses/available/dana',
            success: function (data) {
                // fill data to Modal Body
                fillCourses(data);
            },
            error: function (e) {
                fillCourses(null);
            }
        });
    }

    function fillCourses(data) {
        if (data != null) {
            var ul = document.getElementById("available-courses-list");
            data.map(function(course) {
                var li = document.createElement("li");

                var span = document.createElement("span");
                span.className = "badge badge-default badge-pill"
                span.innerHTML = course.studentLimit

                var button = document.createElement("button")
                button.className = "btn btn-primary"
                button.innerHTML = "Subscribe"
                button.setAttribute("data-id", course.name);
                button.setAttribute("type", "button");
                button.setAttribute("id", "course-subscribe");

                li.appendChild(button)
                li.appendChild(document.createTextNode(course.name));
                li.className = "list-group-item justify-content-between"
                li.appendChild(span)
                ul.appendChild(li);
            })

            if(data.length === 0){
                ul.appendChild(document.createTextNode("No available courses .."))
            }
            addEventListener()
        } else {
            $("#available-courses-list").appendChild(document.createTextNode("Can Not Get Data from Server!"));
        }
    }

    function removeCourseList() {
        document.getElementById("available-courses-list").innerHTML = ""
    }


    function addEventListener() {

        $("#course-subscribe").click(function (event) {
            event.preventDefault();
            console.log("SUBSCRIBE")
            var courseName = $("#course-subscribe").attr('data-id')
            console.log("course name", courseName)

            var data = {
                name: courseName,
                "":""
            }

            $.ajax({
                type: "POST",
                url: "/api/subscribe/dana",
                data: JSON.stringify(data),
                dataType: "text",
                contentType: "application/json; charset=utf-8",
                success: function (data, status, xhr) {
                    alert("Subscribed!")
                    removeCourseList()
                    getAvailableCourses()
                },
                error: function (data, status, xhr) {
                    alert("Could not subscribe to course!");
                }
            });
        });
    }

});