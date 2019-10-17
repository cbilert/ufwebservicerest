package br.com.cristian.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import br.com.cristian.model.UF;
import br.com.cristian.model.UnidadeFederativa;
import br.com.cristian.repository.UnidadeFederativaRepository;

@Path("/uf")
public class UFController {

	private final  UnidadeFederativaRepository repository = new UnidadeFederativaRepository();
	 
	/**
	 * @Consumes - determina o formato dos dados que vamos postar
	 * @Produces - determina o formato dos dados que vamos retornar
	 * 
	 * Esse m�todo cadastra uma nova UF
	 * */
	@POST
	@Consumes("application/json; charset=UTF-8")
	@Produces("application/json; charset=UTF-8")
	@Path("/cadastrar")
	public String cadastrar(UF uf){
		try {
			UnidadeFederativa unidadeFederativa = new UnidadeFederativa(uf.getSigla(),uf.getNomeUF());
			unidadeFederativa.validate();
			repository.salvar(unidadeFederativa);
			return "Registro cadastrado com sucesso!";
		} catch (Exception e) {
			e.printStackTrace();
			return "Erro ao salvar registro! "+e.getMessage();
		}
	}
 
	/**
	 * Essse m�todo altera uma uf j� cadastrada
	 * **/
	@PUT
	@Produces("application/json; charset=UTF-8")
	@Consumes("application/json; charset=UTF-8")	
	@Path("/alterar")
	public String alterar(UnidadeFederativa uf){
		try {
			repository.alterar(uf);
			return "Registro alterado com sucesso!";
		} catch (Exception e) {
			return "Erro ao alterar o registro " + e.getMessage();
		}
 
	}
	/**
	 * Esse m�todo lista todas uf cadastradas na base
	 * */
	@GET
	@Produces("application/json; charset=UTF-8")
	@Path("/todasUF")
	public List<UnidadeFederativa> listaTudo(){
		try{
			return repository.listaTudo();
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
 
	/**
	 * Esse m�todo busca uma pessoa cadastrada pelo c�digo
	 * */
	@GET
	@Produces("application/json; charset=UTF-8")
	@Path("/getPessoa/{codigo}")
	public UnidadeFederativa getUF(@PathParam("sigla") String sigla){
		try {
			return repository.getUF(sigla);
		}catch (Exception e) {
			return null;
		}
	}
 
	/**
	 * Excluindo uma pessoa pelo c�digo
	 * */
	@DELETE
	@Produces("application/json; charset=UTF-8")
	@Path("/excluir/{codigo}")	
	public String excluir(@PathParam("sigla") String sigla){
		try {
			repository.excluir(sigla);
			return "Registro excluido com sucesso!";
		} catch (Exception e) {
			return "Erro ao excluir o registro! " + e.getMessage();
		}
 
	}
}
