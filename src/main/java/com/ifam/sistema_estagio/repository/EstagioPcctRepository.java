package com.ifam.sistema_estagio.repository;

import com.ifam.sistema_estagio.util.enums.Curso;
import com.ifam.sistema_estagio.util.enums.TipoServico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ifam.sistema_estagio.entity.EstagioPCCT;

import java.util.List;

@Repository
public interface EstagioPcctRepository extends JpaRepository<EstagioPCCT, String>{

    List<EstagioPCCT> findByCursoAndTipo(Curso curso, TipoServico tipo);
    List<EstagioPCCT> findByTipo(TipoServico tipo);

}
