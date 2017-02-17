<%@ include file="/templates/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
	<div class="container">
	<jsp:include page="/templates/menu-home.jsp"></jsp:include> 
		<section id="loginn" class="panel panel-primary">
		<form action="${path }/login/autentica" method="POST">

			<input type="hidden" name="id" />
			<div class="panel-body">
				<label for="usuario">Login:</label> 
				<input type="text" name="login"class="form-control" required /><br /> 
				<label for="senha">Senha:</label>
				<input type="password" name="senha" class="form-control" required/><br />
			</div>
			<div class="panel-footer">
				<input  id="btn-login" class="btn btn-primary" type="submit" value="Entrar" />
			</div>
		</form>
		</section>
	</div>
</body>
<script src="${path}/static/js/jquery.js"></script>
<script src="${path}/static/bootstrap/js/bootstrap.min.js"></script>
</html>