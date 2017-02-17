<%@ include file="/templates/head.jsp"%>
<!DOCTYPE html>
<html>
<body>
<div class="container">
<jsp:include page="/templates/menu-app.jsp"></jsp:include>
<h1>tabela de livros</h1>
<a class="btn btn-primary" href="${path}/livros/form">Novo
    	<span class="glyphicon glyphicon-plus-sign"></span></a>
<table class="table table-hover table-condensed table-striped table-bordered tab">
	<thead>
		<tr id="cabecalho">
			<td>ID</td>
			<td>Capa</td>
			<td>ISBN</td>
			<td>Titulo</td>
			<td>Descrição</td>
			<td>Moeda</td>
			<td>Preço/Montante</td>
			<td>Data de publicação</td>
			<td>Ações</td>
		</tr>
	</thead>
	<tbody>
	<c:forEach items="${listaLivros}" var="livro">
    <tr>
    	<td>${livro.id}</td>
    	<td> 
    	<img src="${path }/livros/capa/${livro.isbn}" width="70" height="100"> 
    	</td>
    	<td>${livro.isbn}</td>
    	<td>${livro.titulo} </td>
    	<td>${livro.descricao}</td>
    	<td>${livro.preco.moeda.simbolo}</td>
    	<td>${livro.preco.montante}</td>
    	<td>${livro.dataPublicacao}</td>
    	<td>
    	<form action="${path }/livros/remove" method="post">
		<input name="id" value="${livro.id }" type="hidden" />
    	<a class="btn btn-warning glyphicon glyphicon-pencil" href="${path }/livros/${livro.id}">Editar</a> |
    	
    	<button type="submit" id="remove" class="btn btn-danger" name="_method" value="DELETE">remover
			<span class="glyphicon glyphicon-trash"></span>
		</button>
		</form>
	    </td>
    </tr>
	</c:forEach>
</tbody>
<tfoot>
		<tr>
			<td colspan="9">Livros cadastrados:<span id="qtd-ingrediente"> ${listaLivros.size()}</span></td>
		</tr>
		
	</tfoot>
</table>
</div>
<%@ include file="/templates/footer.jsp"%>
<script src="<c:url value='/static/js/livro/validacoes.js'/>"></script>
</body>
</html>