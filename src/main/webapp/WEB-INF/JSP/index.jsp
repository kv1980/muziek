<%@ page contentType="text/html" pageEncoding="UTF-8" session="false"%>
<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<%@taglib prefix='spring' uri='http://www.springframework.org/tags'%> 
<!doctype html >
<html>
<head>
	<title>Startpagina muziek</title>
</head>
	<body>
		<h1>Kies je album:</h1>
		<ul>
			<c:forEach var='album' items='${albums}'>
				<li>
					<spring:url value='/album/{id}' var='url'>   
						<spring:param name='id' value='${album.id}'/> 
					</spring:url> 
					<a href='${url}'>${album.naam}</a> 
				</li>
			</c:forEach>
		</ul>
	</body>
</html>