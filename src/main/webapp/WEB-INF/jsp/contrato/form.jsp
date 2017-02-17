<%@ include file="/templates/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
<div class="container">
<jsp:include page="/templates/menu-app.jsp"></jsp:include>
<h4 class="modal-title">Informacoes do Contrato</h4>		
<form class="form-group" action="${path}/contrato/salvar"  method="POST">
	<input type="hidden" name="contrato.id" value="${contrato.id}">	
	<div class="input-group col-sm-6">
		<label for="nome">Nome: </label> 
		<input required id="nome" class="form-control" type="text" name="contrato.nome" value="${contrato.nome}"><span></span>	
	</div>
	<div class="input-group col-sm-6">
		<label for="valor">Valor: </label> 
		<input id="valor" class="form-control" type="text" required name="contrato.valor" value="${contrato.valor}"><span></span>
	</div>
	<div class="input-group col-sm-6">
		<label for="dataInicio">Data de Início: </label> 
		<input  class="form-control" type="text" id="dataInicio" required name="contrato.dataInicio" value="${contrato.dataInicio}">
		<span></span>
	</div>
	<div class="input-group col-sm-6">
		<label for="dataFim">Data Fim: </label> 
		<input class="form-control" type="text" id="dataFim" required name="contrato.dataFim" value="${contrato.dataFim}">
		<span></span>
	</div>
	<br><br>	
	<div class="input-group col-sm-6">
		<input class="btn btn-primary" type="submit" value="Enviar"> | 
		<input class="btn btn-default" type="reset" value="Limpar">
	</div>
</form>
</div>
</body>
<%@ include file="/templates/footer.jsp"%>
<script src="<c:url value='/static/js/contrato/validacoes.js'/>"></script>
</html>