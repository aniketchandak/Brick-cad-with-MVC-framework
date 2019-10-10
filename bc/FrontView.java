package bc;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Observable;

import mvc.View;
public class FrontView extends View {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FrontView(Brick b) {
		super(b);
		// TODO Auto-generated constructor stub
	}

	
	public FrontView() {
		// TODO Auto-generated constructor stub
		super();
	}


	//Update is called when there is change in model. When there is change we need to redraw updated rectangle
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		repaint(); // Repaint function calls to paintComponent
	}

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		// Call g.fillect
		// Use model to get dimenison
		super.paintComponent(g);
		if (!(model instanceof Brick)){
			try {
				throw new Exception("Model expected to be a brick");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		else{
			Brick b = (Brick) model;
			g.setColor(Color.red);
			g.fillRect(20, 20, b.getLength().intValue(),b.getHeight().intValue());
		}

		

	}

}
