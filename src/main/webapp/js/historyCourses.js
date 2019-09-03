var username = "dana";


function compareCourses(a, b) {
    if (a.active === b.active) {
        if (a.name.name < b.name.name) {
            return -1;
        } else if (a.name.name === b.name.name) {
            return 0;
        } else {
            return 1;
        }
    } else if (a.active) {
        return -1;
    } else if (b.active) {
        return 1;
    }
}


$(document).ready(function () {
    console.log("history");
    getSubscribedCourses();

    function getSubscribedCourses() {
        $.ajax({
            type: "GET",
            url: '/api/students/' + username + "/courses/",
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
            var ul = document.getElementById("subscribed-courses-list");
            data.sort(compareCourses)
                .map(function (course) {
                    var li = document.createElement("li");

                    var progress_bar = "";
                    var span_text = "Inactive";
                    if (course.active) {
                        progress_bar = "progress-bar-info";
                        span_text = course.numRegisteredStudents + " / " + course.studentLimit;
                    }

                    var span = document.createElement("span");
                    span.className = "badge " + progress_bar + " badge-pill";
                    span.innerHTML = span_text;

                    li.appendChild(document.createTextNode(course.name.name));
                    li.className = "list-group-item justify-content-between";
                    li.appendChild(span);
                    ul.appendChild(li);
                });

            if (data.length === 0) {
                ul.appendChild(document.createTextNode("No subscribed courses .."));
            }
        } else {
            $("#subscribed-courses-list").appendChild(document.createTextNode("Can Not Get Data from Server!"));
        }
    }
});
