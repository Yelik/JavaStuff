package com.mrizen.gaming;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseReader implements MouseListener, MouseMotionListener {

	private int mouseX;
	private int mouseY;
	private int mouseB;
	private double scale;

	public MouseReader(double scale) {
		this.scale = scale;
		mouseX = -1;
		mouseY = -1;
		mouseB = -1;
	}

	public int getX() {
		return mouseX;
	}

	public int getScaledX() {
		return (int) Math.round(mouseX / scale);
	}

	public int getScaledY() {
		return (int) Math.round(mouseY / scale);
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
