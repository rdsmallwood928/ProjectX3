<%--
  Created by IntelliJ IDEA.
  User: bigwood928
  Date: 4/13/14
  Time: 7:30 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link type="text/css" rel="stylesheet" href="stylesheet.css"/>
    <title>Welcome to ProjectX!!!</title>
</head>
<body>
<div id="header">
    <p id="name"><a href="index.jsp">ProjectX</a></p>
    <a href="createaccount.jsp"><p id="login">Create an account</p></a>
    <p id="login">|</p>
    <a href="login.jsp"><p id="login">Login</p></a>
</div>
<div id="body">
<form action="controller" method="post">
    <table>
        <tr><td>email: <input type="text" name="email"></td><td>password: <input type="password" name="password"></td></tr>
        <tr><td><input type="submit" name="login" value="login"></td></tr>
    </table>
</form>
</div>
</body>
</html>