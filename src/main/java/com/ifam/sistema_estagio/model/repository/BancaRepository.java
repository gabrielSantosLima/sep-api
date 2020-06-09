package com.ifam.sistema_estagio.model.repository;


import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ifam.sistema_estagio.model.entity.Banca;
import com.ifam.sistema_estagio.model.entity.EstagioPCCT;

@Repository
public interface BancaRepository extends JpaRepository<Banca, Integer>{

	List<Banca> findByEstagioPCCT(EstagioPCCT estagioPcct);
}
