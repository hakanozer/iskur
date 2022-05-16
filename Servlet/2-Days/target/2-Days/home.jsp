<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <!-- CSS only -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>
<div class="container">

    <div class="row">
        <div class="col-sm-4"></div>
        <div class="col-sm-4">
            <h2>User Login</h2>
            <%
                Object obj = request.getAttribute("error");
                if ( obj != null ) {
            %>
            <div> <%=obj%> </div>
            <%}%>

            <form method="post" action="userLogin" >
                <input class="form-control mb-3" type="email" name="email" placeholder="E-mail">
                <input class="form-control mb-3" type="password" name="password" placeholder="Password">
                <input class="btn btn-success" type="submit" value="Send">
            </form>
        </div>
        <div class="col-sm-4"></div>
    </div>



</div>
</body>
</html>
