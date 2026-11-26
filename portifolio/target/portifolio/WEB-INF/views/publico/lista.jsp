<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Pokédex</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/estilo.css">
</head>
<body>
    <nav class="menu">
        <ul>
            <li><a href="${pageContext.request.contextPath}/pokedex">Pokédex</a></li>
            <c:choose>
                <c:when test="${not empty sessionScope.usuarioLogado}">
                    <li><a href="${pageContext.request.contextPath}/admin/pokemons">Admin</a></li>
                    <li><a href="${pageContext.request.contextPath}/logout">Sair</a></li>
                </c:when>
                <c:otherwise>
                    <li><a href="${pageContext.request.contextPath}/login">Área do admin</a></li>
                </c:otherwise>
            </c:choose>
        </ul>
    </nav>

    <div class="container">
        <h1>Pokédex</h1>

        <div class="pokemons">
            <c:forEach var="p" items="${pokemons}">
                <div class="card">
                    <img src="${pageContext.request.contextPath}/img/${p.imagem}" alt="${p.nome}">
                    <span class="numero">#${p.numero}</span>
                    <h2><c:out value="${p.nome}"/></h2>
                    <div>
                        <span class="tipo ${p.tipo1.toLowerCase()}">${p.tipo1}</span>
                        <c:if test="${not empty p.tipo2}">
                            <span class="tipo ${p.tipo2.toLowerCase()}">${p.tipo2}</span>
                        </c:if>
                    </div>
                    <p><c:out value="${p.descricao}"/></p>
                </div>
            </c:forEach>
        </div>
    </div>

    <footer>Pokédex</footer>
</body>
</html>