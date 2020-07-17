package br.com.projetospringdata.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.projetospringdata.model.UsuarioSpringData;

@Repository
public interface InterfaceSpringDataUser extends CrudRepository<UsuarioSpringData, Long>{  //LOG É O ID
	 
	
	//CRIANDO CONSULTA MAIS OTIMIZADAS
	//@Query(value =  "select p from UsuarioSpringData p where p.nome like %?1%  and p.idade = ?2")
	@Query(value =  "select p from UsuarioSpringData p where p.nome like %?1%")
	public List<UsuarioSpringData> buscaPorNome(String nome);

	//CONSULTA AONDE FOR IGUAL
	@Query(value = "select p from UsuarioSpringData p where p.nome = :paramnome")
	public UsuarioSpringData buscaPorNomeParam(@Param("paramnome") String paramnome);
	
}


//CADA CLASSE TEM QUE TER SEU REPOSITORY