<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Create Registration</title>
</head>
<body>

<div class="container mt-4">
    <h1>Assignment</h1>
    <h3>Add New Registration for ${ocourse.teacher} class</h3>

    <%@include file="NavBar.jsp" %>

    <div class="row justify-content-center">
        <div class="col-8">

            <c:url var="save" value="/regs">
                <c:param name="ocId" value="${ocourse.id}"></c:param>
            </c:url>

            <form action="${save}" method="post">
                <input type="hidden" name="ocId" value="${ocourse.id}">
                <div class="mb-3 mt-3">
                    <label for="sn" class="form-label">Student Name:</label>
                    <input type="text" class="form-control" id="sn" placeholder="Enter Student name" name="sn">
                </div>
                <div class="mb-3">
                    <label for="ph" class="form-label">Phone:</label>
                    <input type="text" class="form-control" id="ph" placeholder="Enter teacher name"
                           name="ph">
                </div>
                <div class="mb-3">
                    <label for="email" class="form-label">Email:</label>
                    <input type="text" class="form-control" id="email" placeholder="Enter Email"
                           name="email">
                </div>



                <button type="submit" class="btn btn-primary">Save</button>
            </form>
        </div>
    </div>

</div>


</body>
</html>
