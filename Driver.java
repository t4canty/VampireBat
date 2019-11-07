import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;


public class Driver extends JPanel implements ActionListener, KeyListener {
	static  String src = new File("").getAbsolutePath() + "/src/";
	boolean stop = true;
	Frog pFrog = new Frog(src + "Untitledbat.gif");
	static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	static int table_width = (int) screenSize.getWidth(), table_height = (int) screenSize.getHeight();
	Rectangle playeRectangle = pFrog.getRectangle();
	Enemy fire = new Enemy(src + "fire.gif", table_width/2 + 150 + 100, 450, table_height/4);
	Rectangle fireRectangle = fire.getRectangle();
	static Fire fireObj = new Fire(table_width, table_height);
	static FireEventListener mListener = new AfterFireEvent();
	static Enemy enemyList0[] = new Enemy[15];
	static Rectangle[] enemyRectangles0 = new Rectangle[15];
	static Enemy enemyList1[] = new Enemy[6];
	static Rectangle[] enemyRectangles1 = new Rectangle[6];
	static Enemy enemyList2[] = new Enemy[9];
	static Rectangle[] enemyRectangles2 = new Rectangle[9];
	int score = 0;
	int lives = (int) (Math.random()*(10) + 2);
	
	public static void main(String[] arg){
		for(int i = 0; i < enemyList0.length; i++) {
			enemyList0[i] = new Enemy(src + "Untitledbat.gif", i*150, table_height/4-100, 50);
			enemyRectangles0[i] = enemyList0[i].getRectangle();
		}
		for(int i = 0; i < enemyList1.length; i++) {
			enemyList1[i] = new Enemy(src + "Wolf.gif", i*500, table_height/2-200, 100);
			enemyRectangles1[i] = enemyList1[i].getRectangle();
		}
		for(int i = 0; i < enemyList2.length; i++) {
			enemyList2[i] = new Enemy(src + "Untitledbat.gif", i*200, table_height-250, 70);
			enemyRectangles2[i] = enemyList2[i].getRectangle();
		}
		fireObj.registerOnFireEventListener(mListener);
		Driver d = new Driver();
	}

	public Driver(){

		JFrame f = new JFrame();
		f.setTitle("Arrays");
		f.setSize(table_width, table_height);
		f.setBackground(Color.BLACK);
		f.setResizable(false);
		f.addKeyListener(this);
		f.add(this);


		t = new Timer(17,this);
		t.start();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);

	}
	Timer t;



	public void paint(Graphics g) {
		super.paintComponent(g);
		URL fontURL;
		try {
			fontURL = new URL("file:" + src + "Halloween Too.ttf");
			Font font;
			font = Font.createFont(Font.TRUETYPE_FONT, fontURL.openStream());
			font = font.deriveFont(Font.PLAIN,60);
			GraphicsEnvironment gEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
			gEnvironment.registerFont(font);
			g.setFont(font);
		} catch (FontFormatException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Graphics2D g2d = (Graphics2D) g;
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image img = toolkit.getImage(src + "UntiBG.png");
		g2d.drawImage(img, 0, 0, table_width, table_height, null);
		g.setColor(Color.DARK_GRAY);
		g.drawString("" + score, table_width-100, 50);
		if(lives > 0) {
			g.setColor(Color.red);
			g.drawString("" + lives, table_width-100, 100);
			try {
				pFrog.paint(g);
				for(int i = 0; i < enemyList0.length; i++) {
					enemyList0[i].paint(g);
				}
				for(int i = 0; i < enemyList1.length; i++) {
					enemyList1[i].paint(g);
				}
				for(int i = 0; i < enemyList2.length; i++) {
					enemyList2[i].paint(g);
				}
				fire.paint(g);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			g.setColor(Color.orange);
			g.drawString("GAME OVER", table_width/2 - 120, table_height/2);
		}
	}
	public void collide() {
		if(playeRectangle.intersects(fireRectangle)) {
			pFrog.setX(table_width/2);
			pFrog.setY(0);
			lives -=1;
		}
		
		for(int i = 0; i < enemyList0.length; i++) {
			if(playeRectangle.intersects(enemyRectangles0[i])){
				pFrog.setX(table_width/2);
				pFrog.setY(0);
				lives -=1;
			}
		}
		for(int f = 0; f < enemyList1.length; f++) {
			if(playeRectangle.intersects(enemyRectangles1[f])){
				pFrog.setX(table_width/2);
				pFrog.setY(0);
				lives -=1;
			}
		}
		for(int g = 0; g < enemyList2.length; g++) {
			if(playeRectangle.intersects(enemyRectangles2[g])){
				lives -=1;
				pFrog.setX(table_width/2);
				pFrog.setY(0);
			}
		}
	}

	public void update() throws InterruptedException{
		playeRectangle = pFrog.getRectangle();		
		for(int i=0; i < enemyList0.length; i++) {
			enemyList0[i].setVX(3);
			enemyRectangles0[i] = enemyList0[i].getRectangle();
		}
		for(int i=0; i < enemyList1.length; i++) {enemyList1[i].setVX(15); enemyRectangles1[i] = enemyList1[i].getRectangle();}
		for(int i=0; i < enemyList2.length; i++) {enemyList2[i].setVX(10); enemyRectangles2[i] = enemyList2[i].getRectangle();}
		fireObj.computeXY(stop);
		stop = mListener.onFireEvent();
		fire.setX(fireObj.getX());
		fire.setY(fireObj.getY());
		fireRectangle = fire.getRectangle();
		collide();
		if(pFrog.win()) {
			score += 1;
			lives += 1;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			update();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		repaint();

	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()){
		case 38:
			pFrog.setVY(-10);
			break;
		case 40:
			pFrog.setVY(10);
			break;
		case 37:
			pFrog.setVX(-10);
			break;
		case 39:
			pFrog.setVX(10);
			break;
		case 32:
			if(lives <= 0) {
				pFrog.setX(table_width/2);
				pFrog.setY(0);
				lives = (int) (Math.random()*(10) + 2);
				score = 0;
			}
		}


	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch(e.getKeyCode()){
		case 38:
			pFrog.setVY(0);
			break;
		case 40:
			pFrog.setVY(0);
			break;
		case 37:
			pFrog.setVX(0);
			break;
		case 39:
			pFrog.setVX(0);
		}

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
