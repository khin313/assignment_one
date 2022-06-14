<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Course List</title>
</head>
<body>
<div class="container mt-4">
    <h1>Assignment</h1>
    <h3>Courses List</h3>
    <%@include file="NavBar.jsp"%>

    <a type="button" class="btn btn-primary my-3" class="nav-link active" href="add-course">Add Course</a>
    <c:choose>
        <c:when test="${empty courses}">
            <div class="alert alert-warning">There is no course, Please create new Course</div>
        </c:when>
        <c:otherwise>
            <table class="table table-hover mt-5">
                <thead>
                <tr>
                    <th>Id</th>
                    <th>Name</th>
                    <th>Duration</th>
                    <th>Fees</th>
                    <th>Description</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="c" items="${courses}">
                    <tr>
                        <td>${c.id}</td>
                        <td>${c.name}</td>
                        <td>${c.duration} Months</td>
                        <td>${c.fees}</td>
                        <td>${c.description}</td>
                        <td>
                            <c:url var="classes" value="/classes" >
                                <c:param name="courseId" value="${c.id}"></c:param>
                            </c:url>
                            <a type="button" class="btn btn-outline-dark" href="${classes}">Open Classes</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:otherwise>
    </c:choose>


</div>
</body>
</html>
