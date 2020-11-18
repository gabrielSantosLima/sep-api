package com.ifam.sistema_estagio.dto;

import com.ifam.sistema_estagio.entity.Ata;
import com.ifam.sistema_estagio.entity.Banca;
import com.ifam.sistema_estagio.entity.FichaDeAvaliacaoEstagio;
import com.ifam.sistema_estagio.entity.FichaDeAvaliacaoProjeto;
import com.ifam.sistema_estagio.util.enums.TipoServico;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class AtaDto implements IObjetoDto<Ata>{
    private Double mediaTotal;
    private String descricao;
    private TipoServico tipo;
    private BancaDto banca;
    private List<FichaAvaliacaoEstagioDto> fichasDeEstagio;
    private List<FichaAvaliacaoProjetoDto> fichasDeProjeto;

    @Override
    public Ata construirEntidade() {
        List<FichaDeAvaliacaoEstagio> fichaDeAvaliacaoEstagios = fichasDeEstagio.stream()
                .map(ficha -> ficha.construirEntidade())
                .collect(Collectors.toList());

        List<FichaDeAvaliacaoProjeto> fichaDeAvaliacaoProjetos = fichasDeProjeto.stream()
                .map(ficha -> ficha.construirEntidade())
                .collect(Collectors.toList());

        return Ata.builder()
//                .banca(banca.construirEntidade())
                .descricao(descricao)
                .fichasEstagio(fichaDeAvaliacaoEstagios)
                .fichasProjeto(fichaDeAvaliacaoProjetos)
                .mediaTotal(0)
                .tipo(tipo)
                .build();
    }
}
