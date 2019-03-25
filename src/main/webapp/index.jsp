<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>BaLiK Sandbox</title>
    <style type="text/css">
        <%@include file="bootstrap/css/bootstrap.min.css" %>
        <%@include file="bootstrap/css/bootstrap-grid.min.css" %>
    </style>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="bootstrap/js/bootstrap.bundle.min.js"></script>
    <script src="bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
<nav class="navbar navbar-dark bg-dark">
    <h1 style="color:white">Employee Table</h1>
</nav>
<table class="table table-striped table-dark">
    <thead>
    <tr>
        <th scope="col">ID</th>
        <th scope="col">Login</th>
        <th scope="col">Email</th>
        <th scope="col">Score</th>
        <th scope="col">Reg. date</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="employee" items="${employees}">
        <tr>
            <th scope="row">${employee.ID}</th>
            <td>${employee.login}</td>
            <td>${employee.email}</td>
            <td>${employee.rank}</td>
            <td>${employee.registrationDate}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<footer class="page-footer font-small special-color-dark pt-4">
    <div class="footer-copyright text-center py-3">© 2019 Copyright:
        BaLiK
    </div>
</footer>
</body>
<style>

</style>
</html>
