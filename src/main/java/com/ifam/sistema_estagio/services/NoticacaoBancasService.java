package com.ifam.sistema_estagio.services;

import com.ifam.sistema_estagio.entity.NotificacaoBancas;
import com.ifam.sistema_estagio.repository.NotificacaoBancasRepository;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticacaoBancasService extends GenericService<NotificacaoBancas, NotificacaoBancasRepository>{

    @Autowired
    private NotificacaoBancasRepository notificacaoBancasRepository;

    @Autowired
    private CoordenadoraService coordenadoraService;

    public List<NotificacaoBancas> listar(String idCoordenadora, Boolean jaVisualizado) throws Exception {
        val coordenadora = coordenadoraService.encontrarPorId(idCoordenadora);
        val coordenadoraNaoExiste = !coordenadora.isPresent();
        if(coordenadoraNaoExiste) throw new Exception("Coordenadora não existe");
        return notificacaoBancasRepository.findByJaVisualizadoAndCoordenadora(
                jaVisualizado,
                coordenadora.get()
        );
    }

    public List<NotificacaoBancas> listar(Boolean jaVisualizado) throws Exception {
        return notificacaoBancasRepository.findByJaVisualizado(jaVisualizado);
    }

    public NotificacaoBancas visualizarNotificacao(String id) throws Exception {
        val notificacaoBanca = encontrarPorId(id);
        val notificacaoNaoExiste = !notificacaoBanca.isPresent();
        if(notificacaoNaoExiste) throw new Exception("Notificação não existe");
        if(notificacaoBanca.get().getJaVisualizado()) return notificacaoBanca.get();
        notificacaoBanca.get().setJaVisualizado(true);
        return atualizar(id, notificacaoBanca.get());
    }
}
