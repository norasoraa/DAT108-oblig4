<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="css/simple.css">
	<title>Deltagerliste</title>
</head>

<body>
	<p>Innlogget som:</p>
	<p>Mobil <c:out value="${deltager.mobil}"/><br>
	<p>Navn <c:out value="${deltager.fulltNavn}"/><br>

	<h1>Deltagerliste</h1>
	<table>
		<tr>
			<th>Kjønn</th>
			<th text-align="left">Navn</th>
			<th text-align="left">Mobil</th>
		</tr>
		<c:forEach var="deltager" items="${deltagerListe}">
		<tr>
			<td style="text-align: center">
				<c:choose>
					<c:when test="${deltager.kjonn == 'mann'}"> &#9794; </c:when>
					<c:when test="${deltager.kjonn == 'kvinne'}"> &#9792; </c:when>
				</c:choose>
			</td>
			<td text-align="left">${deltager.fulltNavn}</td>
			<td text-align="left">${deltager.mobil}</td>
		</tr>
		</c:forEach>
	</table>

	<form action="logout" method="post">
		<input type="submit" value="Logg ut">
	</form>
</body>
</html>