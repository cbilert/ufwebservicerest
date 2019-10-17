package br.com.cristian.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import br.com.cristian.model.UnidadeFederativa;

public class UnidadeFederativaRepository {
	
	private final EntityManagerFactory entityManagerFactory;
	private final EntityManager entityManager;
	
	
	public UnidadeFederativaRepository() {
		/*CRIANDO O EntityManagerFactory COM AS PORPRIEDADOS DO ARQUIVO persistence.xml */
		this.entityManagerFactory = Persistence.createEntityManagerFactory("persistence_unit_db_celk");
		this.entityManager = this.entityManagerFactory.createEntityManager();
	}
	
	/**
	 * CRIA UM NOVO REGISTRO NO BANCO DE DADOS
	 * @throws Exception 
	 * */
	public void salvar(UnidadeFederativa unidadeFederativa) throws Exception{
		try {
		UnidadeFederativa uf = getUF(unidadeFederativa.getSigla());
		if(uf!=null) {
			throw new Exception("Esta UF ja foi cadastrada!");
		}
		}catch (NoResultException e) {
			save(unidadeFederativa);
		}
	}
		
	private void save(UnidadeFederativa unidadeFederativa) {
		this.entityManager.getTransaction().begin();
		this.entityManager.persist(unidadeFederativa);
		this.entityManager.getTransaction().commit();	
	}
 
	/**
	 * ALTERA UM REGISTRO CADASTRADO
	 * */
	public void alterar(UnidadeFederativa unidadeFederativa){
 
		this.entityManager.getTransaction().begin();
		this.entityManager.merge(unidadeFederativa);
		this.entityManager.getTransaction().commit();
	}
 
	/**
	 * RETORNA TODAS AS UNIDADES FEDERATIVAS CADASTRADAS NO BANCO DE DADOS 
	 * */
	@SuppressWarnings("unchecked")
	public List<UnidadeFederativa> listaTudo(){
		List<UnidadeFederativa> retorno = this.entityManager.createQuery("SELECT p FROM UnidadeFederativa p ORDER BY p.sigla").getResultList(); 
		return retorno;
	}
 
	/**
	 * CONSULTA UMA UNIDADE FEDERATIVA CADASTRA PELA SIGLA
	 * */
	public UnidadeFederativa getUF(String sigla){
		Query query = this.entityManager.createQuery("SELECT p FROM UnidadeFederativa p where p.sigla like :sigla ORDER BY p.sigla");
		query.setParameter("sigla", sigla);
		return (UnidadeFederativa) query.getSingleResult();
	}
 
	/**
	 * EXCLUINDO UM REGISTRO PELA SIGLA
	**/
	public void excluir(String sigla){
		UnidadeFederativa  uf = this.getUF(sigla);
		this.entityManager.getTransaction().begin();
		this.entityManager.remove(uf);
		this.entityManager.getTransaction().commit();
	}
}