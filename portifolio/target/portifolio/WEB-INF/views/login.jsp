<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login do administrador</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/estilo.css?v=3">
</head>
<body>
    <nav class="menu">
        <ul>
            <li><a href="${pageContext.request.contextPath}/pokedex">Pokédex</a></li>
        </ul>
    </nav>

    <div class="container">
        <h1>Login do administrador</h1>

        <form class="caixa" action="${pageContext.request.contextPath}/login" method="post">
            <c:if test="${not empty erro}">
                <p class="erro"><c:out value="${erro}"/></p>
            </c:if>

            <p>
                <label>Login<br>
                <input type="text" name="login" required></label>
            </p>
            <p>
                <label>Senha<br>
                <input type="password" name="senha" required></label>
            </p>
            <p style="text-align:center; margin-top:15px;">
                <button type="submit">Entrar</button>
            </p>
        </form>

        <p><a href="${pageContext.request.contextPath}/pokedex">Voltar à pokédex</a></p>
    </div>
</body>
</html>