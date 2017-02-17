<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/templates/jstl.jsp"%>
<!DOCTYPE html>
<nav class="navbar navbar-default menu">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="${path}/login/formulario">Livraria</a>
    </div>
    
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <li>
        	<a href="${path}/usuarios/form" class="btn btn-primary log">Me Cadastrar
        	<span class="glyphicon glyphicon-plus-sign"></span></a>
        </li>       
      </ul>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>
<!-- Error Messages -->
				<c:if test="${not empty errors }">
					<div class="alert alert-danger">
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
					<div class="alert alert-info">
						<button type="button" class="close" data-dismiss="alert">×</button>
						${notice }
					</div>
				</c:if>