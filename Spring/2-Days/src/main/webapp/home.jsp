<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<h2>User Login</h2>
<%
   Object obj = request.getAttribute("error");
   if ( obj != null ) {
%>
    <div> <%=obj%> </div>
<%}%>

<form method="post" action="userLogin" >
    <input type="email" name="email" placeholder="E-mail">
    <input type="password" name="password" placeholder="Password">
    <input type="submit" value="Send">
</form>
</body>
</html>
