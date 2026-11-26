<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/estilo.css?v=3">
    <meta charset="UTF-8">
    <title>Admin - Pokémons</title>
</head>
<body>
    <h1>Administração</h1>
    <p>
        <a href="${pageContext.request.contextPath}/admin/pokemons?acao=novo">Novo pokémon</a> |
        <a href="${pageContext.request.contextPath}/pokedex">Ver pokédex</a> |
        <a href="${pageContext.request.contextPath}/logout">Sair</a>
    </p>

    <table border="1" cellpadding="6">
        <tr>
            <th>Imagem</th><th>Nº</th><th>Nome</th><th>Tipos</th><th>Ações</th>
        </tr>
        <c:forEach var="p" items="${pokemons}">
            <tr>
                <td><img src="${pageContext.request.contextPath}/img/${p.imagem}" width="60"></td>
                <td>${p.numero}</td>
                <td><c:out value="${p.nome}"/></td>
                <td>
                    ${p.tipo1}<c:if test="${not empty p.tipo2}"> / ${p.tipo2}</c:if>
                </td>
                <td>
                    <a href="${pageContext.request.contextPath}/admin/pokemons?acao=editar&id=${p.id}">Editar</a>
                    <form action="${pageContext.request.contextPath}/admin/pokemons"
                          method="post" style="display:inline">
                        <input type="hidden" name="acao" value="excluir">
                        <input type="hidden" name="id" value="${p.id}">
                        <button type="submit"
                                onclick="return confirm('Excluir este pokémon?')">Excluir</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>