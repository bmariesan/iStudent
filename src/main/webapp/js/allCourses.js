$(document).ready(function () {
    console.log("courses")
    getCourses()

    function getCourses() {
        $.ajax({
            type: "GET",
            url: '/api/courses',
            success: function (data) {
                fillCourses(data);
            },
            error: function (e) {
                fillCourses(null);
            }
        });
    }

    function fillCourses(data) {
        if (data != null) {
            var ul = document.getElementById("courses-list");
            data.map(function(course) {
                var li = document.createElement("li");
                li.appendChild(document.createTextNode(course.name));
                li.className = "list-group-item justify-content-between"

                var span = document.createElement("span");
                span.className = "badge badge-default badge-pill"
                span.innerHTML = course.numRegisteredStudents + " / " + course.studentLimit

                li.appendChild(span)
                ul.appendChild(li);
            })

        } else {
            $("#courses-list").appendChild(document.createTextNode("Can Not Get Data from Server!"));
        }
    }
});