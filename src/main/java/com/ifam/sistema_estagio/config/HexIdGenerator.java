package com.ifam.sistema_estagio.config;

import com.ifam.sistema_estagio.util.ManipularNumerosHexadecimais;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import java.io.Serializable;

public class HexIdGenerator implements IdentifierGenerator {

    public static final String nome = "hexGenerator";

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        return ManipularNumerosHexadecimais.numeroAleatorio();
    }
}
