package yahtzee.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import yahtzee.controller.IController;
import yahtzee.model.DiceSet;

public class GamePanel extends JPanel implements IView2 {
	private DiceSet model;
	private boolean isKeepInput;
	private IController control;
	
	private DiePanel[] diePanels;
	private Box contentBox;
	private JPanel inputPanel;
	private JButton toggleButton;
	private JButton executeButton;
	
	public GamePanel( DiceSet diceSet ) {
		super( new BorderLayout() );
		this.model = diceSet;
		isKeepInput = true;
		
		Color bg = new Color(0,176,80);
		Color blue = new Color(79,129,189);
		
		setBackground( bg );
		
		diePanels = new DiePanel[5];
		
		contentBox = Box.createHorizontalBox();
		
		for( int i = 0; i < 5; i++ ) {
			diePanels[i] = new DiePanel( diceSet.getDie(i), i + 1 );
			contentBox.add( diePanels[i] );
		}
		
		add( contentBox, BorderLayout.CENTER );
		
		inputPanel = new JPanel( new FlowLayout( FlowLayout.CENTER ) );
		inputPanel.setBackground( bg );
		
		toggleButton = new JButton( "Input Values" );
		toggleButton.setBackground( blue );
		toggleButton.setForeground( Color.WHITE );
		toggleButton.addActionListener( new ActionListener() {
			public void actionPerformed( ActionEvent e ) {
				for( DiePanel diePanel: diePanels ) {
					diePanel.toggleInput();
				}
				toggleButton.setText( isKeepInput ? "Keep Dice"
										: "Input Values"  );
				isKeepInput = !isKeepInput;
			}
		});
		inputPanel.add( toggleButton );
		
		executeButton = new JButton("Roll the Dice!");
		executeButton.setBackground( blue );
		executeButton.setForeground( Color.WHITE );
		executeButton.addActionListener( new ActionListener() {
			public void actionPerformed( ActionEvent e ) {
				
			}
		});
		inputPanel.add( executeButton );
		
		add( inputPanel, BorderLayout.SOUTH );
	}
	
	
	
	public void setModel( Object o ) {
		
	}
	
	public void registerController( IController control ) {
		this.control = control;
	}
}