package yahtzee.view;

import java.awt.BorderLayout;
//import java.awt.Color;
//import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame {
	private JPanel mainPanel;
	private JPanel southPanel;
	private JPanel eastPanel;
	
	public MainFrame() {
		setTitle( "Yahtzee" );
		setDefaultCloseOperation( EXIT_ON_CLOSE );
		
		mainPanel = new JPanel();
		//mainPanel.setBorder( BorderFactory.createLineBorder( Color.BLACK ) );
		add( mainPanel, BorderLayout.CENTER );
		
		southPanel = new JPanel();
		//southPanel.setBorder( BorderFactory.createLineBorder( Color.BLACK ) );
		add( southPanel, BorderLayout.SOUTH );
		
		eastPanel = new JPanel();
		//eastPanel.setBorder( BorderFactory.createLineBorder( Color.BLACK ) );
		add( eastPanel, BorderLayout.EAST );
		
		center();
		setVisible( true );
	}
	
	public void center() {
		pack();
		setLocationRelativeTo( null );
	}
	
	public void setMain( JPanel panel ) {
		mainPanel.removeAll();
		mainPanel.add( panel );
		mainPanel.repaint();
		mainPanel.revalidate();
		center();
	}
	
	public void setSouth( JPanel panel ) {
		southPanel.removeAll();
		southPanel.add( panel );
		southPanel.repaint();
		southPanel.revalidate();
		center();
	}
	
	public void setEast( JPanel panel ) {
		eastPanel.removeAll();
		eastPanel.add( panel );
		eastPanel.repaint();
		eastPanel.revalidate();
		center();
	}
}
