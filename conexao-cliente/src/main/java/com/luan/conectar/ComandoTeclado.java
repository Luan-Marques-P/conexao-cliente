package com.luan.conectar;

import java.io.Serializable;

public class ComandoTeclado extends Comando {
    private static final long serialVersionUID = 1L;

    public ComandoTeclado(char keyChar, boolean keyPressed) {
        super(TipoComando.TECLADO, new DadosTeclado(keyChar, keyPressed));
    }
}
