<%--
  Created by IntelliJ IDEA.
  User: Мария
  Date: 11.02.2018
  Time: 14:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <form action="${pageContext.servletContext.contextPath}/user/create" method="POST">
    <table>
      <tr>
        <td align="right" >Login : </td>
        <td>
          <input type="text" name="login">
        </td>
      </tr>
      <tr>
        <td align="right" >Email : </td>
        <td>
          <input type="text" name="email">
        </td>
      </tr>
      <tr>
        <td><input type="submit" align="center" value="Submit"/></td>
      </tr>
    </table>
  </form>
</html>
