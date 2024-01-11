package com.luan.conectar;

import java.io.Serializable;

public class DadosTeclado implements Serializable {
    private static final long serialVersionUID = 1L;

    private char keyChar;
    private boolean keyPressed;

    public DadosTeclado(char keyChar, boolean keyPressed) {
        this.keyChar = keyChar;
        this.keyPressed = keyPressed;
    }

	public char getKeyChar() {
		return keyChar;
	}

	public void setKeyChar(char keyChar) {
		this.keyChar = keyChar;
	}

	public boolean isKeyPressed() {
		return keyPressed;
	}

	public void setKeyPressed(boolean keyPressed) {
		this.keyPressed = keyPressed;
	}

    
}
