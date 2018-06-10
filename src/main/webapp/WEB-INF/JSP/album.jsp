<%@ page contentType="text/html" pageEncoding="UTF-8" session="false"%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix='spring' uri='http://www.springframework.org/tags'%> 
<!doctype html >
<html>
<head>
	<title>info album</title>
</head>
	<body>
		<h1>${album.naam}</h1>
		<h2>${album.artiest.naam}</h2>
		<ul>
			<c:forEach var='track' items='${album.tracks}'>
				<li>${track.naam}  (${track.tijd})</li>
			</c:forEach>
		</ul>
		<p>De totale duurtijd bedraagt ${album.totaleDuur} minuten</p>
	</body>
</html>