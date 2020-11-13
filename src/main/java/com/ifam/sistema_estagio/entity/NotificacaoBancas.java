package com.ifam.sistema_estagio.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Builder
@Table(name="notificacao_bancas")
public class NotificacaoBancas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, name = "data_notificacao")
    private String dataNotificacao;

    @Column(nullable = false, name = "id_processo")
    private String idProcesso;

    @ManyToOne
    @JoinColumn(name = "coordenadora_id")
    private Coordenadora coordenadora;
}
