package br.com.padrao.transacoes;

import javax.transaction.Transactional;

import br.com.caelum.vraptor.InterceptionException;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.controller.ControllerMethod;
import br.com.caelum.vraptor.core.InterceptorStack;
import br.com.caelum.vraptor.interceptor.Interceptor;

@Intercepts
public class TransacoesInterceptor implements Interceptor{

	
	@Override
	public boolean accepts(ControllerMethod method) {
		return method.containsAnnotation(Transactional.class);
	}

	@Override
	public void intercept(InterceptorStack stack, ControllerMethod method, Object controller) throws InterceptionException {
			//continua a execução do método do controller
			stack.next(method, controller);
			
	}

}
