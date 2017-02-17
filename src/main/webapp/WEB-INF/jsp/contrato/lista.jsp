<%@ include file="/templates/head.jsp"%>
<!DOCTYPE html>
<html>
<body>
<div class="container">
<jsp:include page="/templates/menu-app.jsp"></jsp:include>
<h3>Tabela de Contratos</h3>
<a class="btn btn-primary" href="${path}/contrato/form">Novo
    	<span class="glyphicon glyphicon-plus-sign"></span></a>
<table class="table table-hover table-condensed table-striped table-bordered tab">
	<thead>
		<tr id="cabecalho">
			<td>ID</td>
			<td>Plano</td>
			<td>Valor</td>
			<td>Data de Inicio</td>
			<td>Data de Fim</td>
			<td>Usuario</td>
			<td>Ações</td>
		</tr>
	</thead>
	<tbody>
	<c:forEach items="${listaContratos}" var="contrato">
    <tr data-id="${contrato.id}">
    	<td>${contrato.id}</td>
    	<td><a data-toggle="collapse" data-parent="#accordion" href="#collapse${contrato.id}">${contrato.nome}</a></td>
    	<td><fmt:formatNumber value="${contrato.valor}" type="currency"/></td>
    	<td><fmt:formatDate value="${contrato.dataInicio }" pattern="dd/MM/yyyy"/></td>
    	<td><fmt:formatDate value="${contrato.dataFim }" pattern="dd/MM/yyyy"/></td>
    	<td>${contrato.usuario.nome}</td>
    	<td>
	    	<a class="btn btn-primary buscaContrato" href="#" >
	    		<span class="glyphicon glyphicon-plus"></span>
    		</a>
    		<a class="btn btn-warning" href="${path }/contrato/${contrato.id}">
    			<span class="glyphicon glyphicon-pencil"></span>
    		</a>
    		<a id="removeContrato" href="${path }/contrato/remove" class="btn btn-danger">
    		<span class="glyphicon glyphicon-trash"></span>
    		</a>
    	<c:choose>
	    	<c:when test="${contrato.lembrete.ativo == false }">
				<a  href="#" class="btn btn-info" title="Ativar lembrete">
		    	<span class="glyphicon glyphicon-flash"></span>
		    	</a>
	    	</c:when>
	    	<c:otherwise>
		    	<a  href="#" class="btn btn-info" title="Desativar lembrete">
		    	<span class="glyphicon glyphicon-info-sign"></span>
		    	</a>
	    	</c:otherwise>
    	</c:choose>
	    </td>
    </tr>
    <tr>
    	<td colspan="6" >
				<div id="collapse${contrato.id }" class="panel-collapse collapse">
				<c:forEach var="consumo" items="${consumos }">
				<c:choose>
					<c:when test="${contrato.id == consumo.contrato.id }">
						<div class="panel panel-default">
							<div data-id="${contrato.id }" class="panel-body" style="color: blue;">Descrição : 
							<strong  style="color: black;">${consumo.descricao } </strong>       
							&nbsp;/&nbsp;Valor pago :
							<strong style="color: black;"><fmt:formatNumber value="${consumo.valor}" type="currency"/></strong>
							<a href="#" data-id="${consumo.id }" title="Editar Consumo" class="btn btn-warning btn-sm pull-right editarConsumo">
								<span class="glyphicon glyphicon-edit"></span></a>
								
							<form action="${path }/contrato/consumo/excluir" method="post">
							<input name="id" value="${consumo.id }" type="hidden" />
							<button type="submit" id="excluirConsumo" class="btn btn-danger btn-sm pull-right" name="_method" value="DELETE">
								<span class="glyphicon glyphicon-trash"></span>
							</button>
							</form>	
							</div>
						</div>	
				</c:when>
				</c:choose>
				</c:forEach>
				</div>
			</td>
   		 </tr>
	</c:forEach>
	<jsp:include page="_modalConsumo.jsp" />
</tbody>
<tfoot>
		<tr>
			<td colspan="9">Contratos cadastrados:<span id="qtd-contratos"> ${listaContratos.size()}</span></td>
		</tr>
		
	</tfoot>
</table>
</div>
<%@ include file="/templates/footer.jsp"%>
<script src="<c:url value='/static/js/contrato/validacoes.js'/>"></script>
</body>
</html>