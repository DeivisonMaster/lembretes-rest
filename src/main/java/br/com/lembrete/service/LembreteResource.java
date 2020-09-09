package br.com.lembrete.service;

import java.util.regex.Pattern;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.lembrete.excecoes.ApiException;
import br.com.lembrete.model.Lembrete;
import br.com.lembrete.model.LembreteRepository;
import br.com.lembrete.model.Pagina;
import br.com.lembrete.orm.LembreteMapper;

@Path("/lembrete")
public class LembreteResource {
	private static final String CHARSET_UTF8 = ";charset=utf-8";
	
	@GET
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON)
	public Response get(@QueryParam("page") String page) throws ApiException {
		LembreteRepository repository = new LembreteRepository();
		
		
		/**
		 * @description Método que checa se o atributo page é nulo e esta vazio. Caso sim, retorna lista de dados no formato Json
		 * */
		if((page == null) || (page.isEmpty())) {
			return Response.ok(new Pagina(repository.getTodos())).build();  // A class Pagina foi utilizada porque o Jersey enfrentaria problemas com retorno do tipo ArrayList
		}
		
		if(page == "0") {
			throw new ApiException(400, "A página não pode ser 0. Informe um valor maior ou igual a 1.");
		}
		
		
		/**
		 * @description Método que checa via regex se há algum numero no inicio da String
		 * */
		if(!Pattern.matches("^\\d+", page)) {
			throw new ApiException(400, "Um valor inválido foi fornecido para um ou mais parâmetros");
		}
		
		return Response.ok(new Pagina(repository.getTodosPorPaginacao(Integer.parseInt(page)))).build();
			
	}
	
	
	@GET
	@Path("/{id}")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON + CHARSET_UTF8)
	public Response get(@PathParam("id") int id) {
		
		if(id == 0) {
			throw new ApiException(400, "O id deve ser maior ou igual a 1");
		}
		
		LembreteMapper mapper = new LembreteMapper();
		Lembrete lembrete = new Lembrete();
		lembrete.setId(id);
		
		lembrete = mapper.select(lembrete);
		
		if(lembrete == null) {
			throw new ApiException(204, "O lembrete especificado não existe");
		}
		
		return Response.ok(lembrete).build();
	}
	
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON + CHARSET_UTF8)
	@Produces(MediaType.APPLICATION_JSON + CHARSET_UTF8)
	public Response post(Lembrete lembrete) {
		LembreteMapper mapper = new LembreteMapper();
		
		if(lembrete.getDescricao().isEmpty() || lembrete.getTitulo().isEmpty()) {
			throw new ApiException(400, "Um ou mais parâmetros obrigatorios não foram informados");
		}
		
		mapper.insert(lembrete);
		
		return Response.ok(lembrete).build();
	}
	
	
	
}



























