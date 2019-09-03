function compareCourses(a, b) {
    if (a.active === b.active) {
        if (a.name < b.name) {
            return -1;
        } else if (a.name === b.name) {
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
    console.log("courses");
    getCourses();

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
        console.log("Data", data)
        if (data !== null) {
            var ul = document.getElementById("courses-list");
            data.sort(compareCourses)
                .map(function (course) {
                    var li = document.createElement("li");

                    li.appendChild(document.createTextNode(course.name.name));
                    li.className = "list-group-item justify-content-between";

                    var progress_bar = "";
                    var span_text = "Inactive";
                    if (course.active) {
                        if (course.numRegisteredStudents === course.studentLimit) {
                            progress_bar = "progress-bar-danger";
                        } else {
                            progress_bar = "progress-bar-success";
                        }
                        span_text = course.numRegisteredStudents + " / " + course.studentLimit;
                    }

                    var span = document.createElement("span");
                    span.className = "badge " + progress_bar + " badge-pill";
                    span.innerHTML = span_text;

                    li.appendChild(span);
                    ul.appendChild(li);
                })

        } else {
            $("#courses-list").appendChild(document.createTextNode("Can Not Get Data from Server!"));
        }
    }
});