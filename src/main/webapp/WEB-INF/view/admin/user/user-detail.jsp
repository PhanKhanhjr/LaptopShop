<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>User Detail ${id}</title>
    <!-- Latest compiled and minified CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- Latest compiled JavaScript -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <%--    <link href="/css/demo.css" rel="stylesheet">--%>

</head>
<body>
<div class="container mt-5 mx-auto">
    <div class="d-flex justify-content-between align-items-center">
        <h3>User Detail with id = ${id}</h3>
    </div>
    <div>
        <div class="card mt-5" style="width: 60%">
            <div class="card-header">
                User infomation
            </div>
            <ul class="list-group list-group-flush">
                <li class="list-group-item">ID: ${user.id}</li>
                <li class="list-group-item">Email: ${user.email}</li>
                <li class="list-group-item">Full Name: ${user.fullName}</li>
                <li class="list-group-item">phone: ${user.phone}</li>
                <li class="list-group-item">Address: ${user.address}</li>
            </ul>
        </div>
    </div>
</div>

</body>
</html>