<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
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
    <h3>Classes for ${course.name}</h3>
    <%@include file="NavBar.jsp" %>

    <div class="my-3">
        <c:url var="addNew" value="/class-edit">
            <c:param name="courseId" value="${course.id}"></c:param>
        </c:url>
        <a type="button" class="btn btn-primary" href="${addNew}">Add new Class</a>
    </div>

    <c:choose>
        <c:when test="${empty classes}">
            <div class="alert alert-warning">There is no class for ${course.name}, Please create new Course</div>
        </c:when>
        <c:otherwise>
            <table class="table table-hover mt-5">
                <thead>
                <tr>
                    <th>Class Id</th>
                    <th>Course Name</th>
                    <th>Duration</th>
                    <th>Fees</th>
                    <th>Description</th>
                    <th>Teacher</th>
                    <th>Start Date</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="c" items="${classes}">

                    <tr>
                        <td>${c.id}</td>
                        <td>${c.course.name}</td>
                        <td>${c.course.duration} Months</td>
                        <td>${c.course.fees}</td>
                        <td>${c.course.description}</td>
                        <td>${c.teacher}</td>
                        <td>${c.startDate}</td>
                        <td>
                            <c:url var="regs" value="/regs" >
                                <c:param name="ocId" value="${c.id}"></c:param>
                            </c:url>
                            <a type="button" class="btn btn-outline-info" href="${regs}">Make Reg</a>
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
