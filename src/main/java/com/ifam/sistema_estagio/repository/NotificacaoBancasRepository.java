package com.ifam.sistema_estagio.repository;

import com.ifam.sistema_estagio.entity.Coordenadora;
import com.ifam.sistema_estagio.entity.NotificacaoBancas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificacaoBancasRepository extends JpaRepository<NotificacaoBancas, String> {
    List<NotificacaoBancas> findByJaVisualizadoAndCoordenadora(Boolean jaVisualizado, Coordenadora coordenadora);
}
