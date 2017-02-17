<%@ include file="/templates/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
<div class="container">
<jsp:include page="/templates/menu-home.jsp"></jsp:include>
<div class="modal-content modal-dialog modal-lg">
<form class="form-group" action="${path}/usuarios/salvar"  method="post">
		<input type="hidden" name="usuario.id" value="${usuario.id}">
		<div class="modal-header">
				<h4 class="modal-title">Informacoes do Usuario</h4>
			</div>
		<div class="modal-body">
		<label for="nome">Nome: </label> 
		<input class="form-control" type="text" name="usuario.nome" value="${usuario.nome}">
		<label for="login">Login: </label> 
		<input class="form-control" name="usuario.login" value="${usuario.login}">
		<label for="descricao">Senha: </label> 
		<input class="form-control" type="password" name="usuario.senha" value="${usuario.senha}">
		<br /> 
		<label for="usuario.admin">ADMIN:</label>
			<select id="usuario.admin" name="usuario.admin" class="form-control">
			<option value="">---selecione---</option>
				<option value="TRUE">Administrador</option>
				<option value="FALSE">Externo</option>
			</select>
		</div>
		<br><br><br>
		<div class="modal-footer">
		<input class="btn btn-primary" type="submit" value="Enviar"> | <input class="btn btn-default" type="reset" value="Limpar">
		</div>
</form>
</div>
</div>
</body>
<script src="<c:url value='/assets/js/jquery-2.2.1.js'/>"></script>
<script src="<c:url value='/assets/js/jquery-2.1.4.min.js'/>"></script>
<script src="<c:url value='/assets/js/jquery-ui.js'/>"></script>
<script src="<c:url value='/assets/js/jquery-ui.min.js'/>"></script>
<script src="<c:url value='/assets/js/jquery.validate.js'/>"></script>
<script src="<c:url value='/assets/js/bootstrap.min.js'/>"></script>
<script src="<c:url value='/assets/js/validacoes.js'/>"></script>
</html>