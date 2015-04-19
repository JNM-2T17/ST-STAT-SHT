package yahtzee.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class MainFrame extends JFrame {
	private JPanel mainContainer;
	private JPanel mainPanel;
	private JPanel southPanel;
	private JPanel eastPanel;
	
	public MainFrame() {
		setTitle( "Yahtzee" );
		setDefaultCloseOperation( EXIT_ON_CLOSE );
		
		Color bg = new Color(34, 139, 34);
		
		mainContainer = new JPanel( new BorderLayout() );
		mainContainer.setBackground( bg );
		
		mainPanel = new JPanel();
		mainPanel.setBackground( bg );
		//mainPanel.setBorder( BorderFactory.createLineBorder( Color.BLACK ) );
		mainContainer.add( mainPanel, BorderLayout.CENTER );
		
		southPanel = new JPanel();
		southPanel.setBackground( bg );
		//southPanel.setBorder( BorderFactory.createLineBorder( Color.BLACK ) );
		mainContainer.add( southPanel, BorderLayout.SOUTH );
		
		add( mainContainer, BorderLayout.CENTER );
		
		eastPanel = new JPanel();
		eastPanel.setBackground( bg );
		JScrollPane scrollPane = 
			new JScrollPane( eastPanel, 
								JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, 
								JScrollPane.HORIZONTAL_SCROLLBAR_NEVER );
		scrollPane.setBorder( BorderFactory.createEmptyBorder() );
		//eastPanel.setBorder( BorderFactory.createLineBorder( Color.BLACK ) );
		Dimension d = scrollPane.getPreferredSize();
		d.width = 150;
		d.height = 700;
		scrollPane.setPreferredSize( d );
		add( scrollPane, BorderLayout.EAST );
		
		center();
		setVisible( true );
	}
	
	public void center() {
		setSize( 700, 400 );
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
