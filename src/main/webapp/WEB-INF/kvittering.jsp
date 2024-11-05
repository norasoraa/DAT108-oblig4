<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/simple.css">
<title>Paameldingsbekreftelse</title>
</head>
<body>
	<h1>Påmeldingsbekreftelse</h1>
	<p>Påmeldingen er mottatt for</p>
	<p>
		&nbsp;&nbsp;&nbsp;${deltager.fornavn}<br />
		&nbsp;&nbsp;&nbsp;${deltager.etternavn}<br />
		&nbsp;&nbsp;&nbsp;${deltager.mobilnummer}<br /> &nbsp;&nbsp;&nbsp;${deltager.kjonn}
	</p>
	<a href="/deltagerliste">Gå til deltagerlisten</a>
</body>
</html>