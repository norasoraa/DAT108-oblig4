<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="no">

<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="css/simple.css">
	<title>Påmelding</title>
</head>

<body>
	<h1>Påmelding</h1>
	<p style="color:red;">
		<c:forEach var="feilmelding" items="${feilmeldinger}">
			${feilmelding}<br>
		</c:forEach>
	</p>

	<form action="paameld" method="post">
		<fieldset>
			<p>Fornavn</p>
			<input type="text" name="fornavn"  id="fornavn" pattern="[A-ZÆØÅ][a-zæøåA-ZÆØÅ\\- ]{1,19}" title="2 til 20 tegn. Første tegn skal være en stor bokstav. Kan bare inneholde bokstaver, bindestrek og mellomrom" value="${deltager.fornavn}" required>
			<p>Etternavn</p>
			<input type="text" name="etternavn"  id="etternavn" pattern="[A-ZÆØÅ][a-zæøåA-ZÆØÅ\\-]{1,19}" title="2 til 20 tegn. Første tegn skal være en stor bokstav. Kan bare inneholde bokstaver og bindestrek" value="${deltager.etternavn}" required>
			<p>Mobil (8 siffer)</p>
			<input type="text" name="mobilnummer" id="mobilnummer" pattern="[0-9]{8}" title="Mobilnummer må være eksakt 8 sifre" value="${deltager.mobilnummer}" required>
			<p>Passord</p>
			<input type="password" name="passord" id="passord" pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9]).{8,}$" title="Minst 8 tegn, med minst én stor bokstav, én liten bokstav og et tall" value="${deltager.passord}" required>
			<p>Passord repetert</p>
			<input type="password" name="repetertPassord" id="repetertPassord" pattern="^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9]).{8,}$" value="${deltager.repetertPassord}" required>
			<p>Kjønn</p>
			<p>
				<input type="radio" name="kjonn" id="mann" value="mann" required/>mann
				<input type="radio" name="kjonn" id="kvinne" value="kvinne" required/>kvinne
			</p>

			<input type="submit" value="Meld meg på">
		</fieldset>
	</form>

</body>
<script src="js/paamelding.js" defer></script> 

</html>