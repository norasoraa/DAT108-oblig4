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
	<h6>Innlogget som: ${deltager.mobil}/ ${deltager.fulltNavn}</h6>
	<h1>Deltagerliste</h1>
	<table>
		<tr>
			<th>Kjønn</th>
			<th align="left">Navn</th>
			<th align="left">Mobil</th>
		</tr>
		<c:forEach var="deltager" items="${deltagere}">
		<tr>
			<td style="text-align: center">
				<c:choose>
					<c:when test="${deltager.kjonn == 'mann'}"> &#9794; </c:when>
					<c:when test="${deltager.kjonn == 'kvinne'}"> &#9792; </c:when>
				</c:choose>
			</td>
			<td align="left">${deltager.fulltNavn}</td>
			<td align="left">${deltager.mobil}</td>
		</tr>
		</c:forEach>
	</table>

	<form action="logout" method="post">
		<input type="submit" value="Logg ut">
	</form>
</body>
</html>