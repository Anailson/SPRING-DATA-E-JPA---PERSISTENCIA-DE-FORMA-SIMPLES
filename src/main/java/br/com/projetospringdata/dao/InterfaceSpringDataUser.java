package br.com.projetospringdata.dao;

import java.util.List;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.projetospringdata.model.UsuarioSpringData;

@Repository
public interface InterfaceSpringDataUser extends CrudRepository<UsuarioSpringData, Long>{  //LOG É O ID
	 
	
	//CRIANDO CONSULTA MAIS OTIMIZADAS
	//@Query(value =  "select p from UsuarioSpringData p where p.nome like %?1%  and p.idade = ?2")
	@Query(value =  "select p from UsuarioSpringData p where p.nome like %?1%")
	public List<UsuarioSpringData> buscaPorNome(String nome);

	//CONSULTA AONDE FOR IGUAL
	@Lock(LockModeType.READ)//FAZ O BLOQUEIO PARA OUTROS USUARIOS
	@Query(value = "select p from UsuarioSpringData p where p.nome = :paramnome")
	public UsuarioSpringData buscaPorNomeParam(@Param("paramnome") String paramnome);
	
	
	//QUERY PRA DELETE
	@Modifying
	@Transactional
	@Query("delete from UsuarioSpringData u where u.nome = ?1")
	public void deletePorNome(String nome);
	
	//UPDATE
	@Modifying
	@Transactional
	@Query("update UsuarioSpringData u set u.email = ?1 where u.nome = ?2")
	public void updateEmailPorNome(String email, String nome);
	
}


 



//CADA CLASSE TEM QUE TER SEU REPOSITORY