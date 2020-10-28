package com.ifam.sistema_estagio.services.email;

import com.ifam.sistema_estagio.dto.EmailSimplesDto;

public interface IEmailService {
    void send(EmailSimplesDto email);
}
