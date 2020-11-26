package com.ifam.sistema_estagio.services;

import org.springframework.stereotype.Service;

import com.ifam.sistema_estagio.entity.Papel;
import com.ifam.sistema_estagio.repository.PapelRepository;

@Service
public class PapelService extends GenericService<Papel, PapelRepository>{}
