<%@ include file="/templates/jstl.jsp"%>
<!DOCTYPE html>
<nav class="navbar navbar-default menu">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="${path}/login/formulario">Livraria</a>
    </div>
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
      <li><a href="${path}/usuarios/lista">Usuarios</a></li>
      <li><a href="${path}/livros/lista">Livros</a></li>
      <li><a href="${path}/contrato/lista">Contratos</a></li>
      <li><a href="${path}/livros/emXml">Livros em XML</a></li>
      <li><a href="${path}/livros/emJson">Livros em Json</a></li>  
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <li>
        	<form action="${path }/login/logout" method="post">
        	<input type="hidden" name="_csrf" value="${_csrf.token}">
        	<button id="btn-sair" type="submit" class="btn btn-danger glyphicon glyphicon-log-out">Logout</button>
        	</form>
        </li>
      </ul>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>
<!-- Error Messages -->
				<c:if test="${not empty errors }">
					<div class="alert alert-error">
						<button type="button" class="close" data-dismiss="alert">×</button>
						<h4>Erro(s):</h4>
						<ul class="clearfix">
							<c:forEach items="${errors}" var="error">
								<li><strong>${error.category}</strong> - ${error.message}</li>
							</c:forEach>
						</ul>
					</div>
				</c:if>
				<!-- Success Messages -->
				<c:if test="${not empty notice }">
					<div class="alert alert-success">
						<button type="button" class="close" data-dismiss="alert">×</button>
						${notice }
					</div>
				</c:if>