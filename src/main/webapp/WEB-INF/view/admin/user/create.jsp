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
<div class="container mt-5">
    <div class="row">
        <div class="col-md-6 col-12 mx-auto">
            <h2>Create a User</h2>
            <form:form method="post" action="/admin/user/create" modelAttribute="newUser">
                <div class="form-group">
                    <label for="exampleInputEmail1">Email address</label>
                    <form:input path="email" type="email" class="form-control" id="exampleInputEmail1"
                                aria-describedby="emailHelp"
                                placeholder="Enter email"/>
                    <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone
                        else.</small>
                </div>
                <div class="mb-3">
                    <label>Password</label>
                    <form:input type="password" path="password" class="form-control"
                                placeholder="Password"/>
                </div>
                <div class="mb-3">
                    <label>Full name</label>
                    <form:input path="fullName" class="form-control"/>

                </div>
                <div class="mb-3">
                    <label>Address</label>
                    <form:input path="address" class="form-control"/>
                </div>
                <div class="mb-3">
                    <label>Phone</label>
                    <form:input path="phone" class="form-control"/>
                </div>
                <button type="submit" class="btn btn-success mt-3 ms-5">Create</button>
            </form:form>
        </div>

    </div>
</div>
</body>
</html>