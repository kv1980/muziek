<%@ page contentType="text/html" pageEncoding="UTF-8" session="false"%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix='spring' uri='http://www.springframework.org/tags'%> 
<!doctype html >
<html>
<head>
	<title>Startpagina muziek</title>
</head>
	<body>
		<c:if test="${not empty param.fout}">
			<div>${param.fout}</div>
		</c:if>
		<h1>Kies je album:</h1>
		<ul>
			<c:forEach var='album' items='${albums}'>
				<spring:url value='/album/{id}' var='url'>   
					<spring:param name='id' value='${album.id}'/> 
				</spring:url> 
				<li>
					<a href='${url}'>${album.naam}</a> (${album.artiest.naam})
				</li>
			</c:forEach>
		</ul>
	</body>
</html>