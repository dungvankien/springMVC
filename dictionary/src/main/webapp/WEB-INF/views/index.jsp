<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Dictionary</title>
</head>
<body>
<h1>Dictionary</h1>
<br/>
<form action="/search" method="post">
    <label for="en">Vocabulary: </label>
    <input type="text" id="en" placeholder="Enter vacabulary" value="${english}" name="english">
    <br><br>
    <button type="submit">Search</button>
    <br><br>
    <h3>Result: </h3>
    <span>${result}</span>
</form>
</body>
</html>