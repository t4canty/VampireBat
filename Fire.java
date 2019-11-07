import java.awt.Dimension;
import java.awt.Toolkit;
public class Fire {
	private int x, y;
	private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private int table_width = (int) screenSize.getWidth(), table_height = (int) screenSize.getHeight();
	private FireEventListener mListener;
	public Fire(int x, int y){
		this.x = x;
		this.y = y;
	}

	public void registerOnFireEventListener(FireEventListener mFireEventListener) {
		this.mListener = mFireEventListener;
	}
	public void computeXY(boolean stop) {
		new Thread(new Runnable() {
			public void run() {
				while(stop) {

					try {
						Thread.sleep(2000);
						int[] XposArray = {table_width/2 - 400 +(int) (Math.random()*100) , table_width/2 + 300+(int) (Math.random()*100), 70 +(int) (Math.random()*100)};
						int[] YposArray = {table_height - 300, 0}; 
						x = XposArray[(int) (Math.random()* XposArray.length)];
						y = YposArray[(int) (Math.random()*2)];
						if(mListener != null) {
							mListener.onFireEvent();
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}

	public void init(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}

}