<%@ page import="java.util.List" %>
<%@ page import="models.Result" %>
<%@ page import="props.City" %>
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
        List<City> ls =  result.data();
        for ( City item : ls ) {
    %>
        <!-- açıklama satırı !-->
        <li> <a href="detail.jsp?city=<%=item.getCid()%>"> <%=item.getName()%> </a> </li>
    <%}%>
    </ul>
</body>
</html>
