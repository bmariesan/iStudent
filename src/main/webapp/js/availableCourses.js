var username = "dana";


function compareCourses(a, b) {
    if (a.name < b.name) {
        return -1;
    } else if (a.name === b.name) {
        return 0;
    } else {
        return 1;
    }
}


$(document).ready(function () {
    console.log("available");
    getAvailableCourses();

    function getAvailableCourses() {
        $.ajax({
            type: "GET",
            url: '/api/subscribe/courses/available/' + username,
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
        if (data !== null) {
            var ul = document.getElementById("available-courses-list");
            course_index = 0;
            data.sort(compareCourses)
                .map(function (course) {
                    var li = document.createElement("li");

                    var span = document.createElement("span");
                    span.className = "badge progress-bar-success badge-pill";
                    span.innerHTML = course.numRegisteredStudents + " / " + course.studentLimit;

                    var button = document.createElement("button");
                    button.className = "btn btn-primary";
                    button.innerHTML = "Subscribe";
                    button.setAttribute("data-id", course.name);
                    button.setAttribute("type", "button");
                    button.setAttribute("id", "course-subscribe-" + course_index);

                    li.appendChild(button);
                    li.appendChild(document.createTextNode(course.name));
                    li.className = "list-group-item justify-content-between";
                    li.appendChild(span);
                    ul.appendChild(li);

                    addEventListener(course_index);
                    course_index += 1
                });

            if (data.length === 0) {
                ul.appendChild(document.createTextNode("No available courses .."));
            }
        } else {
            $("#available-courses-list").appendChild(document.createTextNode("Can Not Get Data from Server!"));
        }
    }

    function removeCourseList() {
        document.getElementById("available-courses-list").innerHTML = "";
    }


    function addEventListener(course_index) {

        $("#course-subscribe-" + course_index).click(function (event) {
            event.preventDefault();
            console.log("SUBSCRIBE");
            var courseName = $("#course-subscribe-" + course_index).attr('data-id');
            console.log("course name", courseName);

            var data = {
                name: courseName,
                "": ""
            };

            $.ajax({
                type: "POST",
                url: "/api/subscribe/" + username,
                data: JSON.stringify(data),
                dataType: "text",
                contentType: "application/json; charset=utf-8",
                success: function (data, status, xhr) {
                    // alert("Subscribed!")
                    removeCourseList();
                    getAvailableCourses()
                },
                error: function (data, status, xhr) {
                    alert("Could not subscribe to course!");
                }
            });
        });
    }

});