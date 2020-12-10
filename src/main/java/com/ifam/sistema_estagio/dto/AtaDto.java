package com.ifam.sistema_estagio.dto;

import com.ifam.sistema_estagio.entity.Ata;
import com.ifam.sistema_estagio.entity.FichaDeAvaliacaoEstagio;
import com.ifam.sistema_estagio.entity.FichaDeAvaliacaoProjeto;
import com.ifam.sistema_estagio.util.enums.TipoServico;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AtaDto implements IObjetoDto<Ata>{
    private String id;
    private Double mediaTotal;
    private String descricao;
    private TipoServico tipo;
    private BancaDto banca;
    private List<FichaAvaliacaoEstagioDto> fichasDeEstagio;
    private List<FichaAvaliacaoProjetoDto> fichasDeProjeto;

    public AtaDto(String id){
        this.id = id;
    }

    @Override
    public Ata construirEntidade() {
        List<FichaDeAvaliacaoEstagio> fichaDeAvaliacaoEstagios = fichasDeEstagio.stream()
                .map(ficha -> ficha == null ? null : ficha.construirEntidade())
                .collect(Collectors.toList());

        List<FichaDeAvaliacaoProjeto> fichaDeAvaliacaoProjetos = fichasDeProjeto.stream()
                .map(ficha -> ficha == null ? null : ficha.construirEntidade())
                .collect(Collectors.toList());

        return Ata.builder()
                .id(id)
                .banca(banca.construirEntidade())
                .descricao(descricao)
                .fichasEstagio(fichaDeAvaliacaoEstagios)
                .fichasProjeto(fichaDeAvaliacaoProjetos)
                .mediaTotal(mediaTotal)
                .tipo(tipo)
                .build();
    }
}
