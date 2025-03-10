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
            <h2>Delete user</h2>
            <div class="alert alert-danger" role="alert">
                Are you sure to delete this product?
            </div>
            <form:form modelAttribute="deletePr" action="/admin/product/delete" method="post">
                <div style="display: none">
                    <form:input path="id"/>
                </div>
                <button class="btn btn-danger">Confirm</button>
            </form:form>
        </div>
    </div>
</div>
</body>
</html>