package com.ifam.sistema_estagio.dto;

import com.ifam.sistema_estagio.entity.NotificacaoBancas;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NotificacaoBancasDto implements IObjetoDto<NotificacaoBancas>{
    private String id;
    private String dataNotificacao;
    private String idProcesso;
    private UsuarioDto coordenadora;

    public NotificacaoBancasDto(String id){
        this.id = id;
    }

    @Override
    public NotificacaoBancas construirEntidade() {
        return NotificacaoBancas.builder()
                .id(id)
                .dataNotificacao(dataNotificacao)
                .idProcesso(idProcesso)
                .coordenadora(coordenadora == null ? null : coordenadora.construirCoordenadora())
                .build();
    }
}
