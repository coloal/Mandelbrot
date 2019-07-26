import javax.swing.*;
import javax.swing.BorderFactory; 
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;        
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import static javax.swing.GroupLayout.Alignment.*;
import javax.swing.LayoutStyle.ComponentPlacement.*;
import javax.swing.*;
import javax.swing.BorderFactory; 
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;        
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import static javax.swing.GroupLayout.Alignment.*;
import javax.swing.LayoutStyle.ComponentPlacement.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.math.BigInteger;
import java.util.concurrent.*;
import java.lang.Math;


public class Simulatore implements ActionListener{
    static JFrame frame_inicio,frame_simulacion;
    JTextField dimensionField,iter,zoom;
    JPanel pane;
    int dimension;
    float alfa_=1.0f,beta_=1.0f,gamma_=1.0f;
    static Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();  
    int iter_,zoom_;

    Simulatore()
    {
      dimension=600;
      iter_=10000;
      zoom_=175;
    }

    public void actionPerformed(ActionEvent e){
      if("simular".equals(e.getActionCommand()))
      {
        new Mandelbrot(zoom_,iter_,dimension,dimension).setVisible(true);
      }
      else if("dimension".equals(e.getActionCommand()))
      {
        dimension=Integer.parseInt(dimensionField.getText());
      }
      else if("zoom".equals(e.getActionCommand()))
      {
        zoom_=Integer.parseInt(zoom.getText()); 
      }
      else if("iter".equals(e.getActionCommand()))
      {
        iter_=Integer.parseInt(iter.getText());
      }
    }
   

    private void createPaneInicio()
    {
      pane = new JPanel(new BorderLayout());
      JButton simularButton = new JButton("crear");
      simularButton.setActionCommand("simular");
      JLabel zoomLabel=new JLabel("Zoom"),iterLabel=new JLabel("iteraciones");
      JLabel dimen=new JLabel("dimension");
     
      simularButton.addActionListener(this);
      dimensionField = new JTextField("600");
      dimensionField.addActionListener(this);
      dimensionField.setActionCommand("dimension");
      zoom=new JTextField("175");
      iter=new JTextField("10000");

      zoom.addActionListener(this);
      iter.addActionListener(this);
     
      zoom.setActionCommand("zoom");
      iter.setActionCommand("iter");
      
      GroupLayout layout = new GroupLayout(pane);
      layout.setAutoCreateGaps(true);
      layout.setAutoCreateContainerGaps(true);
      pane.setLayout(layout);

      layout.setHorizontalGroup(layout.createSequentialGroup()
      	.addGroup(layout.createParallelGroup(LEADING)
      	  .addComponent(dimen)
          .addComponent(zoomLabel)
          .addComponent(iterLabel)
        )
        .addGroup(layout.createParallelGroup(LEADING)
          .addComponent(dimensionField)
          .addComponent(zoom)
          .addComponent(iter)
          .addComponent(simularButton)
        )

      );      

      layout.setVerticalGroup(layout.createSequentialGroup()
        .addGroup(layout.createParallelGroup(BASELINE)
          .addComponent(dimen)
          .addComponent(dimensionField)
        )
        .addGroup(layout.createParallelGroup(BASELINE)
          .addComponent(zoomLabel)
          .addComponent(zoom)
        )
        .addGroup(layout.createParallelGroup(BASELINE)
          .addComponent(iterLabel)
          .addComponent(iter)
        )
        .addGroup(layout.createParallelGroup(BASELINE)
          .addComponent(simularButton)
        )
      );

      Border borde = BorderFactory.createLineBorder(new Color(51,134,202));
      TitledBorder borde_titulo = BorderFactory.createTitledBorder(borde,"Mandelbrot");
    
      pane.setBorder(borde_titulo);
      pane.setBackground(new Color(176,201,247));
    }

    private static void createAndShowGUI() {
        //Create and set up the window.
        Simulatore sim = new Simulatore();
        /*FRAME OPTIONS*/
        frame_inicio = new JFrame("SimulatoreInicio");
        frame_inicio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame_inicio.setPreferredSize(new Dimension(250,250));

        
        int x = (int)(dim.getWidth()/2);
        int y = (int)(dim.getHeight()/2);
        
        frame_inicio.setLocation(x,y);
        sim.createPaneInicio();
        frame_inicio.setContentPane(sim.pane);

        frame_inicio.pack();
        frame_inicio.setVisible(true);  
    }
 
    public static void main(String[] args) {
        
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
