package mvc;


import java.awt.Dimension;

import javax.swing.JInternalFrame;

public class ViewFrame extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static int openFrameCount = 0;
	public ViewFrame(View view) {
        super(view.getClass().getName() + (++openFrameCount),
              true, //resizable
              true, //closable
              true, //maximizable
              true);//iconifiable
        setLocation(30*openFrameCount, 30*openFrameCount);
        setMinimumSize(new Dimension(200, 200));
        setContentPane(view);
	}
}
