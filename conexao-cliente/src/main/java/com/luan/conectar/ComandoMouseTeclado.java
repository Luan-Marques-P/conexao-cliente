package com.luan.conectar;

import java.awt.Point;

public class ComandoMouseTeclado extends Comando {
    private static final long serialVersionUID = 1L;

    public ComandoMouseTeclado(Point point, boolean leftClick, boolean rightClick, char keyChar) {
        super(TipoComando.MOUSE, new DadosMouseTeclado(point, leftClick, rightClick, keyChar));
    }
}
