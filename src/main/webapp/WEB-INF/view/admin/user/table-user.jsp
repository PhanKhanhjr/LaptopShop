<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>HomePage</title>
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
        <h1>User Table</h1>
        <a href="/admin/user/create" class="btn btn-primary">Create User</a>

    </div>
    <table class="table table-striped table-hover table-bordered border-primary">
        <thead>
        <tr>
            <th scope="col">ID</th>
            <th scope="col">Email</th>
            <th scope="col">Full Name</th>
            <th scope="col">Action</th>
        </tr>
        <c:forEach var="user" items="${users}">
            <tr>
                <td>${user.id}</td>
                <td>${user.email}</td>
                <td>${user.fullName}</td>
                <td>
                    <a href="/admin/user/${user.id}" class="btn btn-success">View</a>
                    <a class="btn btn-primary">Edit</a>
                    <a class="btn btn-danger">Delete</a>
                </td>
            </tr>
        </c:forEach>
        </thead>
        <tbody>
        </tbody>
    </table>
</div>

</body>
</html>