package com.ifam.sistema_estagio.reports.messages;

import com.ifam.sistema_estagio.dto.UsuarioDto;
import com.ifam.sistema_estagio.reports.fields.AtaEstagioFields;
import com.ifam.sistema_estagio.dto.BancaDto;
import com.ifam.sistema_estagio.util.FormatarData;
import com.ifam.sistema_estagio.util.enums.FuncaoEstagio;
import lombok.val;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AtaEstagioBuilderMessage implements IBuilderMessage<List<AtaEstagioFields>, BancaDto> {

    @Override
    public List<AtaEstagioFields> retornarMensagem(BancaDto o) {
        val atas = new ArrayList<AtaEstagioFields>();
        val data = Utils.retornarDataPadraoNomeCidade(o);
        val membro1 = retornarColaboradores(o).get(0).getNome();
        val membro2 = retornarColaboradores(o).get(1).getNome();
        val presidente = Utils.retornarOrientador(o).getNome();
        val coordenador = o.getCoordenadora().getNome();
        val horaFinalizado = FormatarData.porMascaraHoraPadrao(o.getHoraFinalizado());

        Utils.retornarDiscentes(o).forEach(discente -> {
            val mensagemCabecalho = retornarMensagemCabecalho(discente);
            val mensagem = retornarMensagemCompleta(o, discente);
            val mensagemChefe = retornarMensagemChefe(o, discente);
            val curso = Utils.retornarCurso(o);

            atas.add(AtaEstagioFields.builder()
                    .aluno(discente.getNome())
                    .coordenadora(coordenador)
                    .curso(curso)
                    .data(data)
                    .horaFinalizado(horaFinalizado)
                    .media(o.getAta().getMediaTotal().toString())
                    .membro_1(membro1)
                    .membro_2(membro2)
                    .mensagem(mensagem)
                    .mensagemChefe(mensagemChefe)
                    .presidente(presidente)
                    .titulo(mensagemCabecalho)
                    .build());
        });
        return atas;
    }

    @Override
    public List<AtaEstagioFields> retornarMensagemParaPreencher(BancaDto o) {
        return new ArrayList<>();
    }

    private String retornarMensagemCabecalho(UsuarioDto discente){
        return "<b>Ata de Defesa</b> do Relatório de Estágio do(a) discente <b>" +
                discente.getNome() +
                "</b>, do " +
                discente.getCurso().retornarNomeCurso(discente.getModalidadeCurso()) +
                ".";
    }

    private String retornarMensagemCompleta(BancaDto o, UsuarioDto discente){
        return "Aos <b>" +
                FormatarData.porMascaraDataPadraoSemCidade(o.getData()) +
                "</b>, <b>" +
                FormatarData.porMascaraHoraPadrao(o.getHoraInicio()) +
                "-" +
                FormatarData.porMascaraHoraPadrao(o.getHoraFinalizado()) +
                "</b>, no <b>" +
                o.getEstagioPCCT().getLocal() +
                "</b> do IFAM/Campus Manaus Centro, realizou-se a Defesa Pública do Relatório de Estágio" +
                " Supervisionado do(a) discente <b>" +
                discente.getNome() +
                "</b>, sendo que a composição da Banca Examinadora contou com os seguintes membros " +
                Utils.retornarNomeEFuncaoAvaliadoresComVirgula(o) + "." +
                " O presidente da banca deu início aos trabalhos, seguindo a metodologia apropriada ao ato." +
                " Após a apresentação, a Banca Examinadora se reuniu para deliberação, divulgando o resultado de" +
                " sua avaliação nos seguintes termos: o Relatório De Estágio Supervisionado de <b>" +
                discente.getNome() +
                "</b> fora:";
    }

    private String retornarMensagemChefe(BancaDto o, UsuarioDto discente){
        return "E, para constar, eu, <b>" +
                Utils.retornarOrientador(o).getNome() +
                "</b>, lavrei a presente ata que foi lida e aprovada por todos os membros da Banca Examinadora" +
                ", e pela discente, a quem, mediante cumprimento de todas as exigências institucionais, será" +
                " conferido o diploma de <b>" +
                discente.getCurso().retornarNomeTecnico(discente.getModalidadeCurso()) +
                "</b>.";
    }

    private List<UsuarioDto> retornarColaboradores(BancaDto o){
        val colaboradores = new ArrayList<UsuarioDto>();
        val fichas = o.getAta()
                .getFichasDeEstagio()
                .stream()
                .filter(ficha -> ficha.getAvaliador().getFuncao() == FuncaoEstagio.COLABORADOR)
                .collect(Collectors.toList());
        fichas.forEach(ficha -> colaboradores.add(ficha.getAvaliador()));
        return colaboradores;
    }
}
