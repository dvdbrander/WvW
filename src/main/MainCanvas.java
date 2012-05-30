package main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class MainCanvas extends Canvas{
	private static final long serialVersionUID = 1L;
	public boolean run = true;
	private BufferStrategy BS;
	private Graphics G;

	public void Init(Dimension size){
		setSize(size);
		createBufferStrategy(2);
		BS = getBufferStrategy();
		G = BS.getDrawGraphics();
	}
	
	public Graphics draw(Graphics g) {
		System.out.println("Drawing....");
		g.setColor(Color.black);
		g.drawRect(0, 0, 10, 10);
		g.setColor(Color.RED);
		g.fillRect(10,10,10,10);
		return g;
	}
	
}
