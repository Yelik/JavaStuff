package com.mrizen.dungeoncrawler;

import com.mrizen.gaming.*;

public class Entity {
	private Sprite sprite;
	private int xPos;
	private int yPos;
	private int startXPos;
	private int startYPos;
	private int targetXPos;
	private int targetYPos;
	private Map map;
	private long anim;

	public Entity(Sprite sprite, int xPos, int yPos, Map map) {
		this.sprite = sprite;
		this.map = map;
		port(xPos, yPos);
		this.map.addEntity(this);
	}

	private void setPos(int xPos, int yPos) {
		this.xPos = xPos;
		this.yPos = yPos;
	}

	public int getXPos() {
		return xPos;
	}

	public void update(long anim) {
		
	}

	public int getYPos() {
		return yPos;
	}

	public void render(Screen screen) {
		screen.drawSprite(sprite, xPos, yPos);
	}

	public void addXPos(int xPos) {
		setXPos(getXPos() + xPos);
	}

	private void setXPos(int xPos) {
		this.xPos = xPos;
	}

	public void addYPos(int yPos) {
		setYPos(getYPos() + yPos);
	}

	private void setYPos(int yPos) {
		this.yPos = yPos;
	}

	public void move(int xPos, int yPos, long anim) {
		setTargetPos(xPos, yPos);
		this.anim = anim;
	}

	public void port(int xPos, int yPos) {
		setPos(xPos, yPos);
		setStartPos(xPos, yPos);
		setTargetPos(xPos, yPos);
	}

	private void setStartPos(int xPos, int yPos) {
		startXPos = xPos;
		startYPos = yPos;
	}

	private void setTargetPos(int xPos, int yPos) {
		targetXPos = xPos;
		targetYPos = yPos;
	}
}