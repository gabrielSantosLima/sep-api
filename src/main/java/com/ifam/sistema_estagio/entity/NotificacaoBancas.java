package com.ifam.sistema_estagio.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.ifam.sistema_estagio.config.IdentificadorHexadecimalGenerator;
import com.ifam.sistema_estagio.util.ManipularNumerosHexadecimais;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="notificacoes_bancas")
public class NotificacaoBancas {

    @Id
    @GeneratedValue(generator = IdentificadorHexadecimalGenerator.nome)
    @GenericGenerator(
            name = IdentificadorHexadecimalGenerator.nome,
            strategy = "com.ifam.sistema_estagio.config.IdentificadorHexadecimalGenerator"
    )
    @Column(length = ManipularNumerosHexadecimais.TAMANHO_NUMERO_ALEATORIO)
    private String id;

    @Column(nullable = false, name = "data_notificacao")
    private String dataNotificacao;

    @Column(nullable = false, name = "id_processo")
    private String idProcesso;

    @Column(nullable = false, name = "ja_visualizado")
    private Boolean jaVisualizado;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "coordenadora_id")
    private Coordenadora coordenadora;
}
