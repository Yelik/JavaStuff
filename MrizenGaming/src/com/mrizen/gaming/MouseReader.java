package com.mrizen.gaming;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseReader implements MouseListener, MouseMotionListener {

	private int mouseX;
	private int mouseY;
	private int mouseB;
	private double hScale;
	private double vScale;

	public MouseReader() {
		hScale = 1;
		vScale = 1;
		mouseX = -1;
		mouseY = -1;
		mouseB = -1;
	}

	public void setScale(double hScale, double vScale) {
		this.hScale = hScale;
		this.vScale = vScale;
	}

	public int getX() {
		return mouseX;
	}

	public int getScaledX() {
		return (int) Math.round(mouseX / hScale);
	}

	public int getScaledY() {
		return (int) Math.round(mouseY / vScale);
	}

	public int getY() {
		return mouseY;
	}

	public int getButton() {
		return mouseB;
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		mouseB = e.getButton();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		mouseB = -1;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
	}
}
