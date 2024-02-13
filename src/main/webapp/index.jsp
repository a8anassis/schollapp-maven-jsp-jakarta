<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>

</head>
<body>

<!-- response.sendRedirect("http://localhost:8080/SchoolApp5-maven-jsp-test/login"); -->
<%
    String redirectUrl = request.getContextPath() + "/login";
    response.sendRedirect(redirectUrl);
%>

</body>
</html>