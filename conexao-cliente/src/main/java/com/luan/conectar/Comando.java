package com.luan.conectar;

import java.io.Serializable;

public class Comando implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private TipoComando tipo;
    private Object dados;

    public Comando(TipoComando tipo, Object dados) {
        this.tipo = tipo;
        this.dados = dados;
    }

    public TipoComando getTipo() {
        return tipo;
    }

    public Object getDados() {
        return dados;
    }
}
