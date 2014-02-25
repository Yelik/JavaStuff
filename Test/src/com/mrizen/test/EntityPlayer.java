package com.mrizen.test;

public class EntityPlayer extends EntityMob {

	private final Keyboard input;
	private int anim;
	private boolean walking;

	public EntityPlayer(int x, int y, Keyboard input) {
		this.y = y;
		this.x = x;
		this.input = input;
		anim = 0;
		sprite = Sprite.playerUp;
	}

	public EntityPlayer(Keyboard input) {
		this.input = input;
		anim = 0;
		sprite = Sprite.playerUp;
	}

	public void update() {
		int xa = 0, ya = 0;
		if (input.up)
			ya--;
		if (input.down)
			ya++;
		if (input.left)
			xa--;
		if (input.right)
			xa++;
		if (anim < Integer.MAX_VALUE)
			anim++;
		else
			anim = 0;
		if (xa != 0 || ya != 0) {
			move(xa, ya);
			walking = true;
		} else
			walking = false;
	}

	public void render(Screen screen) {
		boolean xFlip = false;
		boolean yFlip = false;
		if (dir == 0) {
			sprite = Sprite.playerUp;
			if (walking)
				if (anim % 20 > 10) {
					sprite = Sprite.playerUp1;
				} else {
					sprite = Sprite.playerUp2;
				}
		} else if (dir == 1) {
			sprite = Sprite.playerRight;
			if (walking)
				if (anim % 20 > 10) {
					sprite = Sprite.playerRight1;
				} else {
					sprite = Sprite.playerRight2;
				}
		} else if (dir == 2) {
			sprite = Sprite.playerDown;
			if (walking)
				if (anim % 20 > 10) {
					sprite = Sprite.playerDown1;
				} else {
					sprite = Sprite.playerDown2;
				}
		} else if (dir == 3) {
			sprite = Sprite.playerRight;
			if (walking)
				if (anim % 20 > 10) {
					sprite = Sprite.playerRight1;
				} else {
					sprite = Sprite.playerRight2;
				}
			xFlip = true;
		}

		screen.renderSprite(x - 16, y - 16, sprite, xFlip, yFlip);
	}
}
