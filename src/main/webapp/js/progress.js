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
    console.log("progress");
    getSubscribedCourses();

    function getSubscribedCourses() {
        $.ajax({
            type: "GET",
            url: '/api/students/' + username + "/courses/",
            success: function (data) {
                fillCoursesProgress(data);
            },
            error: function (e) {
                fillCoursesProgress(null);
            }
        });
    }


    function fillCoursesProgress(courses) {
        if (courses !== null) {
            var ul = document.getElementById("courses-progress");
            courses.sort(compareCourses)
                .map(function (course) {
                    var li = document.createElement("li");
                    li.setAttribute("id", "li-" + course.name.name);
                    li.appendChild(document.createTextNode(course.name.name));
                    var ulSublists = document.createElement("ul");

                    var ulFinishedAs = document.createElement("ul");
                    ulFinishedAs.setAttribute("id", "fin-as-" + course.name.name);
                    ulFinishedAs.appendChild(document.createTextNode("Finished Assignments"));
                    ulSublists.appendChild(ulFinishedAs);

                    var ulLeftAs = document.createElement("ul");
                    ulLeftAs.setAttribute("id", "left-as-" + course.name.name);
                    ulLeftAs.appendChild(document.createTextNode("Left Assignments"));
                    ulSublists.appendChild(ulLeftAs);

                    var ulFinishedExams = document.createElement("ul");
                    ulFinishedExams.setAttribute("id", "fin-ex-" + course.name.name);
                    ulFinishedExams.appendChild(document.createTextNode("Finished Exams"));
                    ulSublists.appendChild(ulFinishedExams);

                    var ulLeftExams = document.createElement("ul");
                    ulLeftExams.setAttribute("id", "left-ex-" + course.name.name);
                    ulLeftExams.appendChild(document.createTextNode("Left Exams"));
                    ulSublists.appendChild(ulLeftExams);

                    li.appendChild(ulSublists);

                    li.className = "list-group-item justify-content-between";
                    ul.appendChild(li);
                    getSublistFromCourse(course, "completed/assignments/", "fin-as-");
                    getSublistFromCourse(course, "left/assignments/", "left-as-");
                    getSublistFromCourse(course, "completed/exams/", "fin-ex-");
                    getSublistFromCourse(course, "left/exams/", "left-ex-");
                    getProgressFromCourse(course);
                    // getCompletedAssignmentsFromCourse(course);
                    // getLeftAssignmentsFromCourse(course);
                    // getCompletedExamsFromCourse(course);
                    // getLeftExamsFromCourse(course);
                });

            if (courses.length === 0) {
                ul.appendChild(document.createTextNode("No subscribed courses .."));
            }
        } else {
            $("#courses-progress").appendChild(document.createTextNode("Can Not Get Data from Server!"));
        }
    }

    function getProgressFromCourse(course) {
        var data = {
            name: course.name.name,
            "": ""
        };
        console.log(course.name);
        $.ajax({
            type: "POST",
            url: "/api/coursework/progress/" + username,
            data: JSON.stringify(data),
            dataType: "text",
            contentType: "application/json; charset=utf-8",
            success: function (data, status, xhr) {
                writeProgressForCourse(course, data);
            },
            error: function (data, status, xhr) {
                alert("Could not get progress from course" + course.name);
            }
        });
    }

    function writeProgressForCourse(course, data) {
        data = $.parseJSON(data);
        console.log(data);
        var course = document.getElementById("li-" + course.name.name);
        var div = document.createElement("div");
        div.style.color = "blue";
        div.appendChild(document.createTextNode("PROGRESS: " + data + "%"));
        course.appendChild(div);
    }

    function getSublistFromCourse(course, url, sublist) {
        var data = {
            name: course.name.name,
            "": ""
        };
        console.log(course.name);
        $.ajax({
            type: "POST",
            url: "/api/coursework/" + url + username,
            data: JSON.stringify(data),
            dataType: "text",
            contentType: "application/json; charset=utf-8",
            success: function (data, status, xhr) {
                populateSublistForCourse(course, data, sublist);
            },
            error: function (data, status, xhr) {
                alert("Could not get completed assignments from course" + course.name);
            }
        });
    }


    function populateSublistForCourse(course, data, sublist) {
        var ul = document.getElementById(sublist + course.name.name);
        data = $.parseJSON(data);
        for (i = 0; i < data.length; i++) {
            var elem = data[i];
            var li = document.createElement("li");
            var text = elem.name;
            if (elem.completed === true)
                text += "   GRADE: " + elem.grade;
            li.appendChild(document.createTextNode(text));
            ul.appendChild(li);
        }

    }

})
;
