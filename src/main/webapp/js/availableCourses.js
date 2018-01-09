$(document).ready(function () {
    console.log("available")
    getAvailableCourses()

    // DO GET
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
                button.setAttribute("data-name", course.name);
                button.setAttribute("type", "button");
                button.setAttribute("id", "course-subscribe");

                li.appendChild(button)
                li.appendChild(document.createTextNode(course.name));
                li.className = "list-group-item justify-content-between"
                li.appendChild(span)
                ul.appendChild(li);
            })

        } else {
            $("#available-courses-list").appendChild(document.createTextNode("Can Not Get Data from Server!"));
        }
    }
});