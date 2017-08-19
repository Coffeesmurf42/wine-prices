<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="dk.zenlike.wineprices.HelloAppEngine" %>
<html>
    <head>
      <link href='//fonts.googleapis.com/css?family=Marmelad' rel='stylesheet' type='text/css'>
      <title>Hello App Engine Standard Java 8</title>
    </head>
<body>
    <h1>Wine prices from Philipson</h1>



    <p>Wine price is: <%= HelloAppEngine.getPrice() %></p>
  <table>
    <tr>
      <td colspan="2" style="font-weight:bold;">Available Servlets:</td>
    </tr>
    <tr>
      <td><a href='/hello'>Hello App Engine</a></td>
    </tr>
  </table>

</body>
</html>
