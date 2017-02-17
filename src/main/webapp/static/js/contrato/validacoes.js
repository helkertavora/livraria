$(document).ready(function(){
	limparModal();
});	

$('#removeContrato').on('click', function(){
	var id = $(this).parents('tr').data('id');
	var url = $(this).attr("href");
	alert(id);
	alert(url);
	$.ajax({
		url : url,
		id : id,
		type: 'DELETE',
		success: function(result){
			$('tr[data-id="'+id+'"]').remove();
			var contrato = parseInt( $('#qtd-contratos').text() );
			$('#qtd-contratos').text(passageiro - 1);
		}
	});
});

$(function() {
    $("#dataInicio, #dataFim").datepicker({
        dateFormat: 'dd/mm/yy',
        dayNames: ['Domingo','Segunda','Terça','Quarta','Quinta','Sexta','Sábado','Domingo'],
        dayNamesMin: ['D','S','T','Q','Q','S','S','D'],
        dayNamesShort: ['Dom','Seg','Ter','Qua','Qui','Sex','Sáb','Dom'],
        monthNames: ['Janeiro','Fevereiro','Março','Abril','Maio','Junho','Julho','Agosto','Setembro','Outubro','Novembro','Dezembro'],
        monthNamesShort: ['Jan','Fev','Mar','Abr','Mai','Jun','Jul','Ago','Set','Out','Nov','Dez']
    });
});

$('.buscaContrato').on('click', function(){
	var id = $(this).parents('tr').data('id');

	var url = "http://localhost:8080/padrao/contrato/busca-contrato/"+id;
	$.get(url, id).success(function(contrato){
		$('#idContrato').val(contrato.contrato.id);
		$('#modal-consumo').modal('show');
	});	
});

$('.editarConsumo').on('click', function(){
	var id = $(this).data('id');
	var url = "http://localhost:8080/padrao/contrato/editar-cunsumo/"+id;
	$.get(url).success(function(consumo){
		$('#id').val(consumo.consumo.id);
		$('#idContrato').val(consumo.consumo.contrato.id);
		$('#descricao').val(consumo.consumo.descricao);
		$('#valor').val(consumo.consumo.valor);
		$('#modal-consumo').modal('show');
	});	
});

$('#btn-salvar').on('click', function(){
	var url = 'http://localhost:8080/padrao/contrato/salvar-consumo';
	var consumo = $('#form-consumo').serialize();
	$.post(url, consumo).done(function(){
		limparModal();
		location.reload();
	}).fail(function(){
		alert('Erro ao Salvar!');
	}).always(function(){
		$('#modal-consumo').modal('hide');
	});
	
});

var limparCampos = function(){
	$('#id').val('');
	$('#idContrato').val('');
	$('#descricao').val('');
	$('#valor').val('');
};

var limparModal = function(){
	$('#modal-consumo').on('hide.bs.modal', limparCampos);
}	

$('.btn-info').on('click', function(){
	var id = $(this).parents('tr').data('id');
	var url = 'http://localhost:8080/padrao/contrato/lembrete/ativacao/'+id;

	$.post(url, id)
	.done(function(){
		location.reload();
	})
	.fail(function(){
		alert('Erro ao Salvar!');
	});
});

$(document).ready(function(){
	   $("#excluirConsumo").click( function(event) {
	      var apagar = confirm('Deseja realmente excluir este registro?');
	      if (apagar){
	    	  
	      }else{
	         event.preventDefault();
	      }	
	   });
	});