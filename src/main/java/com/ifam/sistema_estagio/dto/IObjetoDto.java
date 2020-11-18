package com.ifam.sistema_estagio.dto;

import java.io.Serializable;

public interface IObjetoDto<E> extends Serializable {
    E construirEntidade();
}
