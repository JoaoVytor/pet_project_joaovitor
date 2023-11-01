public class ImagemAnimada extends ImagemMovida implements Runnable {
	private Thread t;

	public ImagemAnimada() {
		t = new Thread(this);
		t.start();
	}

	@Override
	public void run() {
		while(true) {
			this.setX(this.getX() + 1);
		    try {
		    	Thread.sleep(2);
		    } catch (InterruptedException e) {
		    	e.printStackTrace();
		    }
		}
	}
}
