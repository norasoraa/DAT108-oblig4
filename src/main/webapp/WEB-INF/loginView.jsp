<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="no">

<head>
  <meta charset="UTF-8">
  <link rel="stylesheet" href="css/simple.css">
  <title>Logg inn</title>
</head>

<body>
  <h1>Logg inn</h1>
  
  <form action="login" method="post">
    <fieldset>
      <p>Mobil:</p>
      <input type="text" name="mobil" id="mobil" pattern="[0-9]{8}">
      <p>Passord:</p>
      <input type="password" name="passord" id="passord" pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9]).{8,}$">
      <p><input type="submit" value="Logg inn"></p>
    </fieldset>
  </form>
</body>

</html>