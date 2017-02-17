<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div class="modal fade" id="modal-consumo" tabindex="-1" role="dialog" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
		<form id="form-consumo" method="POST" >
		<input id="id" name="consumo.id" type="hidden">
		<input id="idContrato" name="consumo.contrato.id" type="hidden">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title">Informacoes do Consumo</h4>
			</div>
			<div class="modal-body">
			<label for="descricao">Descrição: </label>
			<input id="descricao" name="consumo.descricao" class="form-control" required>
			
			<label for="valor">Valor do consumo: </label>
			<input id="valor" required name="consumo.valor" class="form-control">	
			</div>
			
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
				<button id="btn-salvar" type="button" class="btn btn-primary" >Salvar Informacoes</button>
			</div>
		  </form>
		</div>
	</div>
</div>