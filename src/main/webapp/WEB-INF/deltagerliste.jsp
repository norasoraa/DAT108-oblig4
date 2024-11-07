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
	<p>Innlogget som: <c:out value="${deltager.mobil}"/>/ <c:out value="${deltager.fulltNavn}"/><br>


	<h1>Deltagerliste</h1>
	<table>
		<tr>
			<th>Kjønn</th>
			<th text-align="left">Navn</th>
			<th text-align="left">Mobil</th>
		</tr>
		<c:forEach var="d" items="${deltagerListe}">
		<tr style="background-color: ${d.mobil == deltager.mobil ? 'green' : 'transparent'};">
			<td style="text-align: center">
				<c:choose>
					<c:when test="${d.kjonn == 'mann'}"> &#9794; </c:when>
					<c:when test="${d.kjonn == 'kvinne'}"> &#9792; </c:when>
				</c:choose>
			</td>
			<td text-align="left">${d.fulltNavn}</td>
			<td text-align="left">${d.mobil}</td>
		</tr>
		</c:forEach>
	</table>

	<form action="logout" method="post">
		<input type="submit" value="Logg ut">
	</form>
</body>
</html>