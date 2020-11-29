package com.ifam.sistema_estagio.entity;

import com.ifam.sistema_estagio.config.HexIdGenerator;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Builder
@Table(name="notificacao_bancas")
public class NotificacaoBancas {

    @Id
    @GeneratedValue(generator = HexIdGenerator.nome)
    @GenericGenerator(
            name = HexIdGenerator.nome,
            strategy = "com.ifam.sistema_estagio.config.HexIdGenerator"
    )
    @Column(length = 24)
    private String id;

    @Column(nullable = false, name = "data_notificacao")
    private String dataNotificacao;

    @Column(nullable = false, name = "id_processo")
    private String idProcesso;

    @Column(nullable = false, name = "ja_visualizado")
    private Boolean jaVisualizado;

    @ManyToOne
    @JoinColumn(name = "coordenadora_id")
    private Coordenadora coordenadora;
}
