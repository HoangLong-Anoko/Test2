package projectki2aptech.KhacTu.domain;

import java.awt.Color;
import java.awt.Graphics2D;

public class Food extends Sprite
{	
	public void paint(Graphics2D g2) {
		g2.setColor(Color.red);
		g2.fillRect(x, y, width, height);
	}

}