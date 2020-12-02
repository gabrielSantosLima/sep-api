package com.ifam.sistema_estagio;

import com.ifam.sistema_estagio.entity.*;
import com.ifam.sistema_estagio.services.*;
import com.ifam.sistema_estagio.util.enums.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.*;

//@Component
public class CarregarEntidadesTeste implements ApplicationRunner{

	@Autowired
	private BancaService bancaService;

	@Autowired
	private AlunoService alunoService;

	@Autowired
	private CoordenadoraService coordenadoraService;

	@Autowired
	private ProfessorService professorService;

	@Autowired
	private EstagioPcctService estagioPcctService;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		Aluno aluno =  Aluno.builder()
				.turma("IINF31B")
				.modalidadeCurso(ModalidadeCurso.INTEGRADO)
				.curso(Curso.INF)
				.dataConclusao(new Date())
				.build();

		aluno.setNome("Neila Batista Xavier");
		aluno.setEmail("teste.lima@gmail.com");
		aluno.setGrau(GrauAcademico.ENSINO_MEDIO);
		aluno.setCpf("XXX.XXX.XXX-XX");
		aluno.setMatricula("2018324100");
		aluno.setTipo(FuncaoEstagio.DISCENTE);

		Aluno alunoCriado = criarAluno(aluno);

		Aluno outroAluno =  Aluno.builder()
				.turma("IINF31B")
				.modalidadeCurso(ModalidadeCurso.INTEGRADO)
				.curso(Curso.IMEC)
				.dataConclusao(new Date())
				.build();

		outroAluno.setNome("Neila Batista Xavier");
		outroAluno.setEmail("teste.lima@gmail.com");
		outroAluno.setGrau(GrauAcademico.ENSINO_MEDIO);
		outroAluno.setCpf("XXX.XXX.XXX-XX");
		outroAluno.setMatricula("2018324100");
		outroAluno.setTipo(FuncaoEstagio.DISCENTE);

		Aluno outroAlunoCriado = criarAluno(outroAluno);

		Coordenadora coordenadora = Coordenadora.builder().build();
		coordenadora.setNome("Neila Batista Xavier");
		coordenadora.setEmail("teste.lima@gmail.com");
		coordenadora.setCpf("XXX.XXX.XXX-XX");
		coordenadora.setGrau(GrauAcademico.ENSINO_MEDIO);
		coordenadora.setMatricula("2018324100");
		coordenadora.setTipo(FuncaoEstagio.COORDENADOR);

		Coordenadora coordenadoraCriada = criarCoordenadora(coordenadora);

		Professor professor = Professor.builder().build();
		professor.setNome("Neila Batista Xavier");
		professor.setEmail("teste.lima@gmail.com");
		professor.setCpf("XXX.XXX.XXX-XX");
		professor.setGrau(GrauAcademico.ENSINO_MEDIO);
		professor.setMatricula("2018324100");
		professor.setTipo(FuncaoEstagio.ORIENTADOR);

		Professor professorCriado = criarProfessor(professor);

		Professor outroProfessor = Professor.builder().build();
		outroProfessor.setNome("Neila Batista Xavier");
		outroProfessor.setEmail("teste.lima@gmail.com");
		outroProfessor.setCpf("XXX.XXX.XXX-XX");
		outroProfessor.setGrau(GrauAcademico.ENSINO_MEDIO);
		outroProfessor.setMatricula("2018324100");
		outroProfessor.setTipo(FuncaoEstagio.COLABORADOR);

		Professor outroProfessorCriado = criarProfessor(outroProfessor);

		Professor maisOutroProfessor = Professor.builder().build();
		maisOutroProfessor.setNome("Neila Batista Xavier");
		maisOutroProfessor.setEmail("teste.lima@gmail.com");
		maisOutroProfessor.setCpf("XXX.XXX.XXX-XX");
		maisOutroProfessor.setGrau(GrauAcademico.ENSINO_MEDIO);
		maisOutroProfessor.setMatricula("2018324100");
		maisOutroProfessor.setTipo(FuncaoEstagio.COLABORADOR);

		Professor maisOutroProfessorCriado = criarProfessor(maisOutroProfessor);

		List<Aluno> participantes = new ArrayList<>();
		participantes.add(alunoCriado);

		EstagioPCCT estagioPCCT = criarEstagio(EstagioPCCT.builder()
				.responsavel(professorCriado)
				.titulo("Estágio no departamento")
				.tipo(TipoServico.ESTAGIO)
				.alunos(Arrays.asList(alunoCriado, outroAlunoCriado))
				.descricao("Concertar o mesmo computador")
				.local("Lab V")
				.concluido(false)
				.cargaHoraria(200)
				.anexo(null)
				.bancas(null)
				.modalidadeCurso(ModalidadeCurso.INTEGRADO)
				.build());

		Banca banca = criarBanca(Banca.builder()
				.coordenadora(coordenadoraCriada)
				.ata(null)
				.estagioPcct(estagioPCCT)
				.horaInicio(new Date())
				.horaFinalizado(new Date())
				.tipo(TipoServico.PROJETO)
				.local("No Laboratório principal")
				.data(new Date())
				.curso(Curso.INF)
				.avaliadores(Arrays.asList(outroProfessorCriado, maisOutroProfessorCriado))
				.build());
	}

	private Banca criarBanca(Banca novaBanca) throws Exception {
		return bancaService.salvar(novaBanca);
	}

	private Aluno criarAluno(Aluno novoAluno) throws Exception {
		return alunoService.salvar(novoAluno);
	}

	private Coordenadora criarCoordenadora(Coordenadora novoCoordenadora) throws Exception {
		return coordenadoraService.salvar(novoCoordenadora);
	}

	private Professor criarProfessor(Professor novoProfessor) throws Exception {
		return professorService.salvar(novoProfessor);
	}

	private EstagioPCCT criarEstagio(EstagioPCCT novoEstagioPCCT) throws Exception {
		return estagioPcctService.salvar(novoEstagioPCCT);
	}
}
