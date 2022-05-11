<%@ page import="java.util.List" %>
<%@ page import="models.Result" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>Welcome Dashboard</h1>
    <ul>
    <%
        Result result = new Result();
        List<String> ls =  result.data();
        for ( String item : ls ) {
    %>
        <li> <%=item%> </li>
    <%}%>
    </ul>
</body>
</html>
