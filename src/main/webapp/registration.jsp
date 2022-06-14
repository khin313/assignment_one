<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Registration</title>
</head>
<body>

<div class="container mt-4">
    <h1>Assignment</h1>
    <h3>Classes for ${ocourse}</h3>
    <%@include file="NavBar.jsp" %>

    <div class="my-3">
        <c:url var="addNew" value="/reg-edit">
            <c:param name="ocId" value="${ocourse.id}"></c:param>
        </c:url>
        <a type="button" class="btn btn-primary" href="${addNew}">Add new Reg</a>
    </div>

    <c:choose>
        <c:when test="${empty regs}">
            <div class="alert alert-warning">There is no class for ${ocourse.startDate}, Please Register</div>
        </c:when>
        <c:otherwise>
            <table class="table table-hover mt-5">
                <thead>
                <tr>
                    <th>Reg Id</th>
                    <th>Student Name</th>
                    <th>Phone</th>
                    <th>Email</th>
                    <th>Start Date</th>
                    <th>Teacher</th>
                    <th>OCID</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="r" items="${regs}">

                    <tr>
                        <td>${r.id}</td>
                        <td>${r.student}</td>
                        <td>${r.phone}</td>
                        <td>${r.email}</td>
                        <td>${r.openClass.startDate}</td>
                        <td>${r.openClass.teacher}</td>
                        <td>${r.openClass.id}</td>
                    </tr>
                </c:forEach>

                </tbody>
            </table>
        </c:otherwise>
    </c:choose>

</div>
</body>
</html>
