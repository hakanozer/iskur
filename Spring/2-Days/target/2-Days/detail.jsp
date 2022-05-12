<%@ page import="models.Result" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%
        String city = request.getParameter("city");
        Result result = new Result();
        String dt =  result.single(city, response);
    %>
    <h2> Detail Page - <%=dt%> </h2>
</body>
</html>
