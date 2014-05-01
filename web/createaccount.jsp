<%--
  Created by IntelliJ IDEA.
  User: bigwood928
  Date: 4/15/14
  Time: 8:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link type="text/css" rel="stylesheet" href="stylesheet.css"/>
    <title>ProjectX: Create Account</title>
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
        <tr><td>First Name:</td> <td><input type="text" name="firstname" value="${param.firstname}"></td></tr>
        <tr><td>Last Name:</td><td><input type="text" name="lastname" value="${param.lastname}"></td></tr>
        <tr><td>email: </td><td><input type="text" name="email" value="${param.email}"></td></tr>
        <tr><td>Skills: </td><td><input type="text" name="skills" values="${param.skills}"></td></tr>
        <tr><td></td><td><input type="submit" name="createaccount" value="submit"></td></tr>
    </table>
</form>
</div>
</body>
</html>
