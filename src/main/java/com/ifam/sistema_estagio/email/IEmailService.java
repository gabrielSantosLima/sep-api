package com.ifam.sistema_estagio.email;

import com.ifam.sistema_estagio.dto.EmailSimplesDto;

public interface IEmailService {
    void enviar(EmailSimplesDto email) throws Exception;
}
