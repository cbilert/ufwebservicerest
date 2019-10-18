package br.com.cristian.controller;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

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
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/cadastrar")
	public Response cadastrar(UF uf){
		try {
			UnidadeFederativa unidadeFederativa = new UnidadeFederativa(uf.getSigla().toUpperCase(), uf.getNomeUF());
			unidadeFederativa.validate();
			repository.salvar(unidadeFederativa);
			System.out.println("cadastrado");
			return Response.status(Status.CREATED.getStatusCode())
					.entity("{\"result\":\"Registro cadastrado com sucesso!\"}")
					.build();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("nao cadastrado");
			return Response.status(Status.INTERNAL_SERVER_ERROR.getStatusCode())
					.entity("{\"result\":\"Erro ao salvar registro! "+e.getMessage()+"\"}")
					.build();
		}
	}
 
	/**
	 * Essse m�todo altera uma uf j� cadastrada
	 * **/
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)	
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/alterar")
	public Response alterar(UnidadeFederativa uf){
		try {
			repository.alterar(uf);
			System.out.println("alterado");
			return Response.status(Status.CREATED.getStatusCode())
					.entity("{\"result\":\"Registro alterado com sucesso!\"}")
					.build();
		} catch (Exception e) {
			System.out.println("nao alterado");
			return Response.status(Status.INTERNAL_SERVER_ERROR.getStatusCode())
					.entity("{\"result\":\"Erro ao alterar registro! "+e.getMessage()+"\"}")
					.build();
		}
 
	}
	/**
	 * Esse m�todo lista todas uf cadastradas na base
	 * */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/listar")
	public List<UnidadeFederativa> listaTudo(){
		try{
			List<UnidadeFederativa> retorno = new ArrayList<UnidadeFederativa>();
			retorno = repository.listaTudo();
			System.out.println("listado");
			return retorno;
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("nao listado");
			return new ArrayList<UnidadeFederativa>();
		}
	}
 
	/**
	 * Excluindo uma UF pelo  código
	 * */
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/excluir/{sigla}")
	public Response  excluir(@PathParam("sigla") String sigla){
		try {
			repository.excluir(sigla);
			System.out.println("removido");
			return Response.status(Status.CREATED.getStatusCode())
					.entity("{\"result\":\"Registro removido com sucesso!\"}")
					.build();
		} catch (Exception e) {
			System.out.println("nao removido");
			return Response.status(Status.INTERNAL_SERVER_ERROR.getStatusCode())
					.entity("{\"result\":\"Erro ao excluir registro! "+e.getMessage()+"\"}")
					.build();
		}
	}
}
