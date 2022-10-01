<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Currency Conversion</title>
</head>
<body>
<h1>Currency Conversion</h1>
<br/>
<form action="/conversion" method="post">
    <label for="rate">Rate:</label>
    <input type="number" name="rate" placeholder="Rate" value="22800" id="rate">
    <br><br>
    <label for="usd">USD: </label>
    <input type="number" name="usd" id="usd" placeholder="USD" value="${usd}">
    <button type="submit">Conversion</button>
    <label for="vnd">VND:</label>
    <input readonly type="number" name="vnd" id="vnd" value="${result}">
</form>
</body>
</html>