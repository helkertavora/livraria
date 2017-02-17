package br.com.padrao.transacoes;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.controller.ControllerMethod;
import br.com.caelum.vraptor.core.InterceptorStack;
import br.com.caelum.vraptor.interceptor.Interceptor;
import br.com.padrao.controllers.LoginController;
import br.com.padrao.validacoes.UsuarioLogado;

@Intercepts
public class AutenticacaoInterceptor implements Interceptor {
	@Inject
	private UsuarioLogado usuario;
	@Inject
	private Result result;
	@Inject
	private HttpServletRequest request;

	@Override
	public boolean accepts(ControllerMethod method) {
		String url = request.getRequestURL().toString();
		Boolean naoPodeAcessar = true;
		if (url.contains("/padrao/usuarios/form") || url.contains("/padrao/usuarios/salvar")  ||  url.contains("/padrao/login")) {
			naoPodeAcessar = false;
		}
		return naoPodeAcessar;
}
	@Override
	public void intercept(InterceptorStack stack, ControllerMethod method, Object controller) {
		if (usuario.isLogado()) {
			stack.next(method, controller);
		} else {
			result.redirectTo(LoginController.class).formulario();
		}
	}

	
}