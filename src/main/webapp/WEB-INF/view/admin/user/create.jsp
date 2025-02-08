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
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>

    <script>
        $(document).ready(() => {
            const avatarFile = $("#avatarFile");
            avatarFile.change(function (e) {
                const imgURL = URL.createObjectURL(e.target.files[0]);
                $("#avatarPreview").attr("src", imgURL);
                $("#avatarPreview").css({ "display": "block" });
            });
        });
    </script>

</head>
<body>
<div class="container mt-5">
    <div class="row">
        <div class="col-md-6 col-12 mx-auto">
            <h2>Create a User</h2>
            <form:form enctype="multipart/form-data" method="post" action="/admin/user/create" modelAttribute="newUser" class="row" >
                <div class="mb-3 col-12 col-md-6">
                    <label>Email address</label>
                    <c:set var="errEmail">
                        <form:errors path="email" cssClass="invalid-feedback" />
                    </c:set>
                    <form:input path="email" type="email" class="form-control ${not empty errEmail ? 'is-invalid' : ''}"/>
                    ${errEmail}
                </div>


                <div class="col-md-6 col-12">
                    <c:set var="errPassword">
                        <form:errors path="password" cssClass="invalid-feedback" />
                    </c:set>
                    <label>Password</label>
                    <form:input type="password" path="password" class="form-control ${not empty errPassword ? 'is-invalid' : ''}"
                                placeholder="Password"/>
                    ${errPassword}
                </div>

                <div class="col-md-6 col-12">
                    <label>Full name</label>
                    <c:set var="errFullName">
                        <form:errors path="fullName" cssClass="invalid-feedback" />
                    </c:set>
                    <form:input path="fullName" class="form-control ${not empty errFullName ? 'is-invalid' : ''}"/>
                    ${errFullName}
                </div>
                <div class="col-md-6 col-12">
                    <label>Address</label>
                    <form:input path="address" class="form-control"/>
                </div>
                <div class="col-md-6 col-12">
                    <label>Phone</label>
                    <form:input path="phone" class="form-control"/>
                </div>

                <div class="col-md-6 col-12">
                    <label class="form-label">Role</label>
                    <form:select class="form-select"  path="role.name">
                        <form:option value="USER">USER</form:option>
                        <form:option value="ADMIN">ADMIN</form:option>
                    </form:select>
                </div>
                <div class="col-md-6 col-12">
                        <label for="avatarFile" class="form-label">Choose avatar</label>
                        <input class="form-control" type="file" id="avatarFile" multiple accept=".png,.jpeg,.jpeg,image/jpeg" name="avatarFile"/>
                </div>
                <div class="col-md-6 col-12">
                    <img style="max-height: 250px;display: none" id="avatarPreview" alt="avatar preview"/>
                </div>
                <button type="submit" class="btn btn-success mt-3">Create</button>
            </form:form>
        </div>

    </div>
</div>
</body>
</html>