package com.luan.conectar;

import java.awt.Point;
import java.io.Serializable;

public class DadosMouseTeclado implements Serializable {
    private static final long serialVersionUID = 1L;

    private Point point;
    private boolean leftClick;
    private boolean rightClick;
    private char keyChar;

    public DadosMouseTeclado(Point point, boolean leftClick, boolean rightClick, char keyChar) {
        this.point = point;
        this.leftClick = leftClick;
        this.rightClick = rightClick;
        this.keyChar = keyChar;
    }

	public Point getPoint() {
		return point;
	}

	public void setPoint(Point point) {
		this.point = point;
	}

	public boolean isLeftClick() {
		return leftClick;
	}

	public void setLeftClick(boolean leftClick) {
		this.leftClick = leftClick;
	}

	public boolean isRightClick() {
		return rightClick;
	}

	public void setRightClick(boolean rightClick) {
		this.rightClick = rightClick;
	}

	public char getKeyChar() {
		return keyChar;
	}

	public void setKeyChar(char keyChar) {
		this.keyChar = keyChar;
	}

    
}
