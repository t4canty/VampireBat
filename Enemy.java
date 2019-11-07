import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Enemy {
	private int x, y, vx = 0, vy = 0;
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private int table_width = (int) screenSize.getWidth(), table_height = (int) screenSize.getHeight();
	private String fileName ;
	private Rectangle enemyRectangle;
	private int size;
	
	
	
	public Enemy(String fileName, int x, int y, int size){
		this.fileName = fileName;
		this.x = x;
		this.y = y;
		this.size = size;
		enemyRectangle = new Rectangle(x, y, size, size);
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {	
		this.x  = x;
		
	}
	public void setY(int y) {this.y = y;}
	public int getY() {
		return y;
	}
	
	public int getSize() {
		return size;
	}
	
	
	public void setVX(int Nx){
		vx = Nx;
	}
	public void setVY(int Ny){
		vy = Ny;
	}
	
	public void paint(Graphics g) throws IOException{
		if(x > table_width) {
			x = 0;
		}
		if(y > table_height) {
			y = 0;
		}
		x += vx;
		y += vy;
		enemyRectangle = new Rectangle(x, y, size, size);
		//g.drawRect(x, y, size, size);
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image img = toolkit.getImage(fileName);
		g.drawImage(img, x, y, size, size, null, null );
	}
	public Rectangle getRectangle() {
		return enemyRectangle;
	}
	
}
