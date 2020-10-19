package com.ifam.sistema_estagio.repository;


import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ifam.sistema_estagio.entity.Banca;
import com.ifam.sistema_estagio.entity.EstagioPCCT;

@Repository
public interface BancaRepository extends JpaRepository<Banca, Integer>{

	List<Banca> findByEstagioPcct(EstagioPCCT estagioPcct);
}
