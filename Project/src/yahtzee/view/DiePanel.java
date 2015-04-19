package yahtzee.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import yahtzee.controller.IController;
import yahtzee.model.Die;

public class DiePanel extends JPanel implements IView {
	private Die model;
	private boolean keep;
	private boolean isKeepInput;
	
	private static ImageIcon die1;
	private static ImageIcon die2;
	private static ImageIcon die3;
	private static ImageIcon die4;
	private static ImageIcon die5;
	private static ImageIcon die6;
	
	private Box contentBox;
	private JPanel numberPanel;
	private JLabel dieNumber;
	private JPanel diePanel;
	private JLabel dieImage;
	private JPanel actionPanel;
	private JSpinner valueSpinner;
	private JButton keepButton;
	
	public DiePanel( Die die, int number ) {
		this.model = die;
		keep = false;
		isKeepInput = true;
		
		Color bg = new Color(0,176,80);
		Color blue = new Color(79,129,189);
		
		setBackground( bg );
		setOpaque( true );
		
		contentBox = Box.createVerticalBox();
		contentBox.setBackground( bg );
		
		numberPanel = new JPanel( new FlowLayout( FlowLayout.CENTER ) );
		numberPanel.setBackground( bg );
		dieNumber = new JLabel( "Die #" + number );
		dieNumber.setForeground( Color.WHITE );
		numberPanel.add( dieNumber );
		contentBox.add( numberPanel );
		
		diePanel = new JPanel( new FlowLayout( FlowLayout.CENTER ) );
		diePanel.setBackground( bg );
		dieImage = new JLabel();
		diePanel.add( dieImage );
		contentBox.add( diePanel );
		
		actionPanel = new JPanel( new FlowLayout( FlowLayout.CENTER ) );
		actionPanel.setBackground( bg );
		
		valueSpinner = new JSpinner( 
						new SpinnerNumberModel( die.getValue(), 1, 6, 1 ) );
		valueSpinner.setVisible( false );
		actionPanel.add( valueSpinner );
		
		keepButton = new JButton("Keep");
		keepButton.setBackground( blue );
		keepButton.setForeground( Color.WHITE );
		keepButton.addActionListener( new ActionListener() {
			public void actionPerformed( ActionEvent e ) {
				keepButton.setText( keep ? "Keep" : "Don't Keep" );
				keep = !keep;
			}
		});
		actionPanel.add( keepButton );
		contentBox.add( actionPanel );
		
		add( contentBox );
		
		setModel( die );
	}
	
	private void loadResources() {
		die1 = new ImageIcon( getClass().getResource( "/DICE/1.png" ) );
		die2 = new ImageIcon( getClass().getResource( "/DICE/2.png" ) );
		die3 = new ImageIcon( getClass().getResource( "/DICE/3.png" ) );
		die4 = new ImageIcon( getClass().getResource( "/DICE/4.png" ) );
		die5 = new ImageIcon( getClass().getResource( "/DICE/5.png" ) );
		die6 = new ImageIcon( getClass().getResource( "/DICE/6.png" ) );
	}
	
	public void reset() {
		keep = false;
		keepButton.setText( "Keep" );
		keepButton.setEnabled( false );
	}
	
	public void toggleInput() {
		if( isKeepInput ) {
			keepButton.setVisible( false );
			valueSpinner.setVisible( true );
		} else {
			keepButton.setVisible( true );
			valueSpinner.setVisible( false );
		}
		
		isKeepInput = !isKeepInput;
	}
	
	public Object getValue() {
		if( isKeepInput ) {
			return new Boolean( keep );
		} else {
			return new Integer( valueSpinner.getValue().toString() );
		}
	}
	
	public void setModel( Object o ) {
		model = (Die)o;
		ImageIcon image;
		
		if( die1 == null ) {
			loadResources();
		}
		
		switch( model.getValue() ) {
			case 1:
				dieImage.setIcon( die1 );
				break;
			case 2:
				dieImage.setIcon( die2 );
				break;
			case 3:
				dieImage.setIcon( die3 );
				break;
			case 4:
				dieImage.setIcon( die4 );
				break;
			case 5:
				dieImage.setIcon( die5 );
				break;
			case 6:
				dieImage.setIcon( die6 );
				break;
			default:
		}
		
		valueSpinner.setValue( new Integer( model.getValue() ) );
		keepButton.setEnabled( true );
	}
}
