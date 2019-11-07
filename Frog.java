import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Frog {
	private int x, y, vx = 0, vy = 0;
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private int table_width = (int) screenSize.getWidth(), table_height = (int) screenSize.getHeight();
	private String fileName;
	private Rectangle playerRectangle;
	
	public Frog(String fileName){
		this.fileName = fileName;
		x = table_width/2;
		y = 0;
		playerRectangle = new Rectangle(x, y, 50, 50);
	}
	
	public Frog(String fileName, int x, int y){
		this.fileName = fileName;
		this.x = x;
		this.y = y;
		playerRectangle = new Rectangle(x, y, 100, 100);
	}
	
	public void setX(int x) {this.x = x;}
	public void setY(int y) {this.y = y;}
	public void setVX(int Nx){
		vx = Nx;
	}
	public void setVY(int Ny){
		vy = Ny;
	}
	public void paint(Graphics g) throws IOException{
		if(x < 0) {
			x = 0;
		}
		if(x > table_width - 50) {
			x = table_width - 50;
		}
		if(y < 0) {
			y = 0;
		}
		
		x += vx;
		y += vy;
		playerRectangle = new Rectangle(x, y, 25, 25);
		//g.drawRect(x, y, 25, 25);
		Graphics2D g2d = (Graphics2D) g;
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image img = toolkit.getImage(fileName);
		g2d.drawImage(img, x, y, 50, 50, null, null );
	}
	
	public Rectangle getRectangle() {
		return playerRectangle;
	}
	
	public boolean win() {
		if(y > table_height - 50) {
			x = table_width/2;
			y = 0;
			return true;
		}
		else {
			return false;
		}
	}

}
