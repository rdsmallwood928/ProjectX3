<%--
  Created by IntelliJ IDEA.
  User: bigwood928
  Date: 4/15/14
  Time: 9:10 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ProjectX: Confirmation!</title>
</head>
<body>
<div id="header">
    <p id="name"><a href="index.jsp">ProjectX</a></p>
    <a href="createaccount.jsp"><p id="login">Create an account</p></a>
    <p id="login">|</p>
    <a href="login.jsp"><p id="login">Login</p></a>
</div>
<h1>Congrats! You are now signed up for ProjectX</h1>
<p>
    Account Summary:
<table>
    <tr><td>First Name: </td><td>${account.firstName}</td></tr>
    <tr><td>Last Name: </td><td>${account.lastName}</td></tr>
    <tr><td>email: </td><td>${account.email}</td></tr>
    <tr><td>Skills:  </td><td>${account.skills}</td></tr>
</table>
</p>
</body>
</html>
