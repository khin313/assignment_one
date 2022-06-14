<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Classes</title>
</head>
<body>
<div class="container mt-4">
    <h1>Assignment</h1>
    <h3>Add New Class for ${course.name}</h3>

    <%@include file="NavBar.jsp" %>
    <div class="row justify-content-center">
        <div class="col-8">

            <c:url var="save" value="/classes">
                <c:param name="courseId" value="${course.id}"></c:param>
            </c:url>
            <form action="${save}" method="post">
                <input type="hidden" name="courseId" value="${course.id}">
                <div class="mb-3 mt-3">
                    <label for="sd" class="form-label">Start Date:</label>
                    <input type="date" class="form-control" id="sd" placeholder="Enter course start date" name="sd">
                </div>
                <div class="mb-3">
                    <label for="teacher" class="form-label">Teacher (Months):</label>
                    <input type="text" class="form-control" id="teacher" placeholder="Enter teacher name"
                           name="teacher">
                </div>


                <button type="submit" class="btn btn-primary">Save</button>
            </form>
        </div>
    </div>

</div>
</body>
</html>
