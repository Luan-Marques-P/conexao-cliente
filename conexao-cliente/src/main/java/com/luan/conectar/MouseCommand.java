package com.luan.conectar;

import java.awt.Point;

public class MouseCommand extends Comando {
    public MouseCommand(Point mousePoint, boolean leftClick, boolean rightClick) {
        super(TipoComando.MOUSE, new DadosMouseTeclado(mousePoint, leftClick, rightClick, '\0'));
    }

    private static final long serialVersionUID = 1L;
}
