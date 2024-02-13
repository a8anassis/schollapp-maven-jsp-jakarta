<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ page pageEncoding="UTF-8" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<p>Teacher: ${requestScope.teacherDTO.id} ${requestScope.teacherDTO.firstname} ${requestScope.teacherDTO.lastname}
		was deleted</p>
	<a href="${pageContext.request.contextPath}/school/static/templates/teachersmenu.jsp">Επιστροφή</a>
</body>
</html>
