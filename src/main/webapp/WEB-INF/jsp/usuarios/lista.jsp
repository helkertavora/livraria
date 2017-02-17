<%@ include file="/templates/head.jsp"%>
<!DOCTYPE html>
<html>
<body>
<div class="container">
<jsp:include page="/templates/menu-app.jsp"></jsp:include>

<h1>tabela de Usuarios</h1>
<table class="table table-hover table-condensed table-striped table-bordered">
	<thead>
		<tr id="cabecalho">
			<td>ID</td>
			<td>Nome</td>
			<td>Login</td>
			<td>Senha</td>
			<td>É Admin?</td>
			<td>Ações</td>
		</tr>
	</thead>
	<tbody>
	<c:forEach items="${usuarios}" var="usuario">
    <tr>
    	<td>${usuario.id}</td>
    	<td>${usuario.nome}</td>
    	<td>${usuario.login} </td>
    	<td>${usuario.senha}</td>
    	<td>${usuario.admin}</td>
       	<td>
    	<form action="${path }/usuarios/remove" method="post">
    	<a class="btn btn-warning glyphicon glyphicon-pencil" href="${path }/usuarios/${usuario.id}">Editar</a> |
    	
    	<input name="id" value="${usuario.id}" type="hidden" />
    	<button class="btn btn-danger glyphicon glyphicon-trash" type="submit" name="_method" value="DELETE">remover</button>
		</form>
	    </td>
    </tr>
	</c:forEach>
</tbody>
<tfoot>
		<tr>
			<td colspan="6">usuarios cadastrados:<span id="#"> ${usuarios.size()}</span></td>
		</tr>
		
	</tfoot>
</table>
</div>
</body>
</html>