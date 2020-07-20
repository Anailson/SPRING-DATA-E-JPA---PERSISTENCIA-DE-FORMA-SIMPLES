package br.com.projetospringdata;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.projetospringdata.dao.InterfaceSpringDataUser;
import br.com.projetospringdata.dao.InterfaceTelefone;
import br.com.projetospringdata.model.Telefone;
import br.com.projetospringdata.model.UsuarioSpringData;

@RunWith(SpringJUnit4ClassRunner.class)  //INTEGRA O SPRING COM O JUNIT
@ContextConfiguration(locations = {"classpath:META-INF/spring-config.xml"}) //LER O ARQUIVO spring-config.xml
public class AppSpringDataTest {
	
	@Autowired
	private InterfaceSpringDataUser interfaceSpringDataUser;  //INJEÇÃO DE DEPENDENCIA DO REPOSITORY
	
	@Autowired
	private InterfaceTelefone interfaceTelefone;
	
	@Test
	public void testeConexao() {
		System.out.println("Inicou spring com sucesso!");
	}
	
	@Test
	public void testeInsert() {
		
		UsuarioSpringData usuarioSpringData = new UsuarioSpringData();
		
		usuarioSpringData.setEmail("anailson@gmail.com");
		usuarioSpringData.setIdade(33);
		usuarioSpringData.setLogin("teste 123");
		usuarioSpringData.setSenha("123");
		usuarioSpringData.setNome("usuariofone");
		
		interfaceSpringDataUser.save(usuarioSpringData);
		
		System.out.println("Usuario Cadastrado ->" + interfaceSpringDataUser.count());//MOSTRA A QTD DE REGISTROS NO BANCO
		
			
	}
	
	@Test
	public void testeConsulta() {
		
		Optional<UsuarioSpringData> usuarioSpringData = interfaceSpringDataUser.findById(1L); 	//CONSULTANDO DADOS DO ID 1
		
		System.out.println(usuarioSpringData.get().getIdade());
		System.out.println(usuarioSpringData.get().getEmail());
		System.out.println(usuarioSpringData.get().getLogin());
		System.out.println(usuarioSpringData.get().getSenha());
		System.out.println(usuarioSpringData.get().getNome());
		System.out.println("-------------------------------------------------------");
		
		//TELEFONE
		for (Telefone telefone : usuarioSpringData.get().getTelefones()) {
			System.out.println(telefone.getNumero());
			System.out.println(telefone.getTipo());
		}
		
	}
	
	@Test
	public void testeConsultaTodos() {
		
		Iterable<UsuarioSpringData> lista = interfaceSpringDataUser.findAll();
		
		for (UsuarioSpringData usuarioSpringData : lista) {
			
			System.out.println(usuarioSpringData.getEmail());
			System.out.println(usuarioSpringData.getIdade());
			System.out.println(usuarioSpringData.getLogin());
			System.out.println(usuarioSpringData.getNome());
			System.out.println(usuarioSpringData.getSenha());
			System.out.println(usuarioSpringData.getId());
			System.out.println("------------------------------------------------");
		}
		
	}
	
	@Test
	public void testeUpdate() {
		
		Optional<UsuarioSpringData> usuarioSpringData = interfaceSpringDataUser.findById(3L);//CONSULTA O 3
		
		UsuarioSpringData data = usuarioSpringData.get();
		
		data.setNome("Atualizando o nome do usuario");
		
		interfaceSpringDataUser.save(data);
		
		
	}
	
	@Test
	public void testeDelete() {
		
		Optional<UsuarioSpringData> usuarioSpringData = interfaceSpringDataUser.findById(4L);//CONSULTA O 	
		
		interfaceSpringDataUser.delete(usuarioSpringData.get()); //DELETANDO O QUE FOR CONSULTADO
		
		
		System.out.println("Usuario Cadastrado ->" + interfaceSpringDataUser.count());//MOSTRA A QTD DE REGISTROS NO BANCO
		 
	}
	
	@Test
	public void testeConsultaNome() {
		
		//CHAMANDO A CODIÇÃO CRIADA NA QUERY BUSCANDO POR NOME
		List<UsuarioSpringData> list = interfaceSpringDataUser.buscaPorNome("Anailson");
		

		for (UsuarioSpringData usuarioSpringData : list) {
			
			System.out.println(usuarioSpringData.getEmail());
			System.out.println(usuarioSpringData.getIdade());
			System.out.println(usuarioSpringData.getLogin());
			System.out.println(usuarioSpringData.getNome());
			System.out.println(usuarioSpringData.getSenha());
			System.out.println(usuarioSpringData.getId());
			System.out.println("------------------------------------------------");
		}
		
		
	}
	
	@Test
	public void testeConsultaNomeParam() {
		
		//CHAMANDO A CODIÇÃO CRIADA NA QUERY BUSCANDO POR NOME
	       UsuarioSpringData usuarioSpringData = interfaceSpringDataUser.buscaPorNomeParam("Anailson");
		
	      	System.out.println(usuarioSpringData.getEmail());
			System.out.println(usuarioSpringData.getIdade());
			System.out.println(usuarioSpringData.getLogin());
			System.out.println(usuarioSpringData.getNome());
			System.out.println(usuarioSpringData.getSenha());
			System.out.println(usuarioSpringData.getId());
			System.out.println("------------------------------------------------");
				
		
	}
	

	@Test
	public void testeDeletePorNome() {
		
		interfaceSpringDataUser.deletePorNome("testedelete");	
		
		System.out.println("Usuario Cadastrado ->" + interfaceSpringDataUser.count());//MOSTRA A QTD DE REGISTROS NO BANCO

		
	}
	
	@Test
	public void testeUpdateEmailPorNome() {
		
		//ATUALIZAR O EMAIL AONDE O NOME FOR - Anailson Ribeiro
		interfaceSpringDataUser.updateEmailPorNome("testeupdate@gmail.com", "Anailson Ribeiro");
		
		
		System.out.println("Usuario Cadastrado ->" + interfaceSpringDataUser.count());//MOSTRA A QTD DE REGISTROS NO BANCO
		
	}
	
	/*------------------TELEFONE----------------*/
	
	@Test
	public void testeInsertTelefone() {
		
		Optional<UsuarioSpringData> usuarioSpringData = interfaceSpringDataUser.findById(2L);
		
		Telefone telefone = new Telefone();
		telefone.setTipo("Casa");
		telefone.setNumero("3232424");
		telefone.setUsuarioSpringData(usuarioSpringData.get());
		
		interfaceTelefone.save(telefone);
		
	}
	

}
