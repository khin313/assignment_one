<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Add Course</title>
</head>
<body>
<div class="container mt-4">
    <h1>Assignment</h1>
    <h3>Add Courses</h3>
    <%@include file="NavBar.jsp" %>

    <div class="row justify-content-center">
        <div class="col-8">
            <c:url var="save" value="courses"/>
            <form action="${save}" method="post">
                <div class="mb-3 mt-3">
                    <label for="name" class="form-label">Name:</label>
                    <input type="text" class="form-control" id="name" placeholder="Enter course name" name="name">
                </div>
                <div class="mb-3">
                    <label for="dura" class="form-label">Duration (Months):</label>
                    <input type="number" class="form-control" id="dura" placeholder="Enter Duration" name="duration">
                </div>
                <div class="mb-3">
                    <label for="fees" class="form-label">Fees:</label>
                    <input type="number" class="form-control" id="fees" placeholder="Enter Fees" name="fees">
                </div>
                <div class="mb-3">
                    <label for="des" class="form-label">Description:</label>
                    <textarea class="form-control" id="des" rows="2" name="description"></textarea>
                </div>

                <button type="submit" class="btn btn-primary">Save</button>
            </form>
        </div>
    </div>

</div>
</body>
</html>
