package bc;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

import javax.swing.JLabel;
import javax.swing.JTextField;

import mvc.Command;
import mvc.CommandProcessor;
import mvc.Utilities;
import mvc.View;

public class DimensionalView extends View {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField heightField,widthField,lengthField;
	public DimensionalView(Brick b) {
		super(b);
		// TODO Auto-generated constructor stub
	}

	
	public DimensionalView() {
		// TODO Auto-generated constructor stub
		super();
		this.setPackable(true);
		this.setLayout(new GridBagLayout());
		
		GridBagConstraints g=new GridBagConstraints();
		JLabel setHeight= new JLabel("Height");
		JLabel setLength=new JLabel("Length");
		JLabel setWidth=new JLabel("Width");
		JLabel title=new JLabel("Brick CAD Attributes");
		g.gridx=0;
		g.gridy=0;
		title.setLabelFor(this);
		this.add(title,g);
		
		
		heightField=new JTextField(10);
		heightField.addActionListener(new HeightHandler() );
		setHeight.setLabelFor(heightField);
		g.gridy=1;
		this.add(setHeight,g);
		g.gridx=2;
        this.add(heightField,g);
        
        widthField=new JTextField(10);
        widthField.addActionListener(new WidthHandler());
        setWidth.setLabelFor(widthField);
        g.gridx=0;
		g.gridy=2;
        this.add(setWidth,g);
        g.gridx=2;
        this.add(widthField,g);
        
        lengthField=new JTextField(10);
        lengthField.addActionListener(new LengthHandler());
        g.gridx=0;
		g.gridy=3;
        this.add(setLength,g);
        g.gridx=2;
        this.add(lengthField,g);
        setLength.setLabelFor(lengthField);
        setVisible(true);
        
        
		
	}


	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		Brick b=(Brick) model;
		heightField.setText(b.getHeight().toString());
		lengthField.setText(b.getLength().toString());
		widthField.setText(b.getWidth().toString());
		
	}
	
	class HeightHandler implements ActionListener {
    	public void actionPerformed(ActionEvent e){
        	String newHeight=e.getActionCommand();
        	Command cmmd = null;
        	Double height;
        	try{
        		height=Double.parseDouble(newHeight);
        		if(height<=0) throw new Exception("Dimensiom must be greater than 0");
        		cmmd=new SetHeight(height);
        		
        	}
        	catch(NumberFormatException numbrExcptn ){
    			Utilities.error("Text is not allowed. Please enter integers.");
    		}
    		catch(Exception execption){
    			Utilities.error(execption.getMessage());
    			
    		}
        	
        	if (cmmd!=null){
        		cmmd.setModel(model);
            	CommandProcessor c=CommandProcessor.makeCommandProcessor();
				c.execute(cmmd);
        	}
			
    	}
    }
	
	class LengthHandler implements ActionListener {
    	public void actionPerformed(ActionEvent e){     	
        	String newLength=e.getActionCommand();
        	Command cmmd = null;
        	Double length;
        	try{
        		length=Double.parseDouble(newLength);
        		if(length<=0) throw new Exception("Dimensiom must be greater than 0");
        		cmmd=new SetLength(length);
        	}
        	catch(NumberFormatException numbrExcptn ){
    			Utilities.error("Text is not allowed. Please enter integers.");
    		}
    		catch(Exception execption){
    			Utilities.error(execption.getMessage());
    			
    		}
        	
        	if (cmmd!=null){
        		
        		cmmd.setModel(model);
        		CommandProcessor c=CommandProcessor.makeCommandProcessor();
				c.execute(cmmd);
        	}
    	}
    }
	
	class WidthHandler implements ActionListener {
    	public void actionPerformed(ActionEvent e){
    		
        	String newWidth=e.getActionCommand();
        	Command cmmd = null;
        	Double width;
        	try{
        		width=Double.parseDouble(newWidth);
        		if(width<=0) throw new Exception("Dimensiom must be greater than 0");
        		cmmd=new SetWidth(width);
        	}
        	catch(NumberFormatException numbrExcptn ){
    			Utilities.error("Text is not allowed. Please enter integers.");
    		}
    		catch(Exception execption){
    			Utilities.error(execption.getMessage());
    			
    		}
        	
        	if (cmmd!=null){
        		cmmd.setModel(model);
        		CommandProcessor c=CommandProcessor.makeCommandProcessor();
        		c.execute(cmmd);
        	}
        	
    	}
    }
	
}
