<%@ include file="/templates/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
<div class="container">
<jsp:include page="/templates/menu-app.jsp"></jsp:include>
<div class="modal-content modal-dialog modal-lg">
<form class="form-group" action="${path}/livros/salvar"  method="POST" enctype="multipart/form-data">
		<input type="hidden" name="livro.id" value="${livro.id}">
		<div class="modal-header">
				<h4 class="modal-title">Informacoes do Livro</h4>
			</div>
		<div class="modal-body">
		<label for="capa">Capa: </label>
		<input type="file" name="capa" required class="form-control"/>
		<label for="isbn">ISBN: </label> 
		<input class="form-control" type="text" required name="livro.isbn" value="${livro.isbn}">
		<label for="titulo">Titulo: </label> 
		<input class="form-control" type="text" required name="livro.titulo" value="${livro.titulo}">
		<label for="descricao">Descrição: </label> 
		<input class="form-control" type="text" required name="livro.descricao" value="${livro.descricao}">
		
		<label for="dataPublicacao">Data de Publicação: </label> 
		<input class="form-control" type="text" id="calendario" required name="livro.dataPublicacao" value="${livro.dataPublicacao}">
		<br /> 
		<label for="livro.moeda">Moeda:</label>
			<select id="livro.preco.moeda" name="livro.preco.moeda" class="form-control">
			<option value="">---selecione---</option>
				<c:forEach items="${moedas}" var="moeda">
				<option value="${moeda}">${moeda}</option>
				</c:forEach>
			</select>
		<label for="dataPublicacao">Montante: </label> 
		<input class="form-control" required type="text" name="livro.preco.montante" value="${livro.preco.montante}"/>
		</div>
		<br><br><br>
		<div class="modal-footer">
		<input class="btn btn-primary" type="submit" value="Enviar"> | <input class="btn btn-default" type="reset" value="Limpar">
		</div>
</form>
</div>
</div>
</body>
<%@ include file="/templates/footer.jsp"%>
<script src="<c:url value='/static/js/livro/validacoes.js'/>"></script>
</html>