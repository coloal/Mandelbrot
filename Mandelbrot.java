import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import java.util.concurrent.*;
import java.awt.Dimension;

public class Mandelbrot extends JFrame {
  

  private BufferedImage Imagen;

  	class Tarea implements Runnable
	{
		int inicio,fin,ancho;
		double ZOOM = 200;
		int MAX_ITER = 100000;
		private double zx, zy, cX, cY, tmp;

		Tarea(int in,int fi,int an,int zoom,int iter)
		{
			ZOOM=zoom;
			MAX_ITER=iter;
			inicio=in;
			fin=fi;
			ancho=an;
		}

		public void run()
		{
			for (int y = inicio; y < fin; y++) {
		     for (int x = 0; x < ancho; x++) {
		     	     zx = zy = 0;
		     	     cX = (x - 400) / ZOOM;
		     	     cY = (y - 300) / ZOOM;
		     	     int iter = MAX_ITER;
		     	     while (zx * zx + zy * zy < 4 && iter > 0) {
		     	     	     tmp = zx * zx - zy * zy + cX;
		     	     	     zy = 2.0 * zx * zy + cY;
		     	     	     zx = tmp;
		     	     	     iter--;
		     	     }
		     	     Imagen.setRGB(x, y, iter | (iter << 8));
		     }
		    }
		}
	}
  
  public Mandelbrot(int zoom,int iter,int w,int h) {
    super("Conjunto de Mandelbrot");
    setBounds(100, 100, w, h);
    setPreferredSize(new Dimension(250,250));
    setResizable(false);
    
    Imagen = new BufferedImage(getWidth(), getHeight(),
   	   BufferedImage.TYPE_INT_RGB);
   	
   	int nucleos=Runtime.getRuntime().availableProcessors();
   	int ventana=getHeight()/nucleos,inicio=0,fin=ventana;
   	ThreadPoolExecutor ejecutor = new ThreadPoolExecutor(nucleos,nucleos,0L,TimeUnit.MILLISECONDS
				,new LinkedBlockingQueue<Runnable>());
   	for(int i=0; i<nucleos ; ++i)
   	{
   		ejecutor.execute(new Tarea(inicio,fin,getWidth(),zoom,iter));
   		inicio+=ventana;
   		fin+=ventana;
   	}

   	try
	{
      ejecutor.shutdown();
      ejecutor.awaitTermination(1L,TimeUnit.DAYS);
    }catch(InterruptedException e){}
  }
  public void paint(Graphics g) {
    g.drawImage(Imagen, 0, 0, this);
  }
  /*public static void main(String[] args) {
  	  new Mandelbrot().setVisible(true);
  }*/
}

 