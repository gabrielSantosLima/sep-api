package com.ifam.sistema_estagio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ifam.sistema_estagio.entity.EstagioPCCT;

@Repository
public interface EstagioPcctRepository extends JpaRepository<EstagioPCCT, Integer>{

}
