package com.ifam.sistema_estagio.loads;

import org.springframework.boot.ApplicationArguments;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.ifam.sistema_estagio.entity.Funcao;
import com.ifam.sistema_estagio.entity.Papel;
import com.ifam.sistema_estagio.repository.FuncaoRepository;
import com.ifam.sistema_estagio.repository.PapelRepository;

@Component
@SuppressWarnings("unused")
public class CarregarPapeisFuncoes implements ApplicationRunner{

	@Autowired
	private PapelRepository papelRepository;

	@Autowired
	private FuncaoRepository funcaoRepository;

	@Override
	public void run(ApplicationArguments args) throws Exception {
	}
	
	private Funcao criarFuncaoSeNaoExiste(String nome) {
		Optional<Funcao> auth = funcaoRepository.findByNome(nome);
	
		if(auth.isPresent()) {
			return auth.get();
		}
		
		return funcaoRepository.save(new Funcao(nome));
	}

	private Papel criarPapelSeNaoExiste(String nome, List<Funcao> Funcao) {
		Optional<Papel> Papel = papelRepository.findByNome(nome);
		
		if(Papel.isPresent()) {
			return Papel.get();
		}
		
		return papelRepository.save(new Papel(nome, Funcao));
	}

}
