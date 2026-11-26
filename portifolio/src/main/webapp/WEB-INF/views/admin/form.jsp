<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/estilo.css?v=3">
    <meta charset="UTF-8">
    <title>Pokémon</title>
</head>
<body>
    <h1>${empty pokemon ? 'Novo pokémon' : 'Editar pokémon'}</h1>

    <form action="${pageContext.request.contextPath}/admin/pokemons" method="post">
        <input type="hidden" name="id" value="${pokemon.id}">

        <p><label>Número:<br>
            <input type="number" name="numero" value="${pokemon.numero}" required></label></p>

        <p><label>Nome:<br>
            <input type="text" name="nome" value="<c:out value='${pokemon.nome}'/>" required></label></p>

        <p><label>Tipo 1:<br>
            <input type="text" name="tipo1" value="<c:out value='${pokemon.tipo1}'/>" required></label></p>

        <p><label>Tipo 2 (opcional):<br>
            <input type="text" name="tipo2" value="<c:out value='${pokemon.tipo2}'/>"></label></p>

        <p><label>Descrição:<br>
            <textarea name="descricao" rows="4" cols="40"><c:out value="${pokemon.descricao}"/></textarea></label></p>

        <p><label>Imagem (nome do arquivo, ex.: pikachu.png):<br>
            <input type="text" name="imagem" value="<c:out value='${pokemon.imagem}'/>"></label></p>

        <button type="submit">Salvar</button>
        <a href="${pageContext.request.contextPath}/admin/pokemons">Cancelar</a>
    </form>
</body>
</html>