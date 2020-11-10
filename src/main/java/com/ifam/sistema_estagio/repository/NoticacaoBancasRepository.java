package com.ifam.sistema_estagio.repository;

import com.ifam.sistema_estagio.entity.NoticacaoBancas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticacaoBancasRepository extends JpaRepository<NoticacaoBancas, Integer> {
}
