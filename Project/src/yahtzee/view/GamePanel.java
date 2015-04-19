package yahtzee.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
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
import yahtzee.controller.command.RollCommand;
import yahtzee.controller.command.SetDiceCommand;
import yahtzee.model.DiceSet;

public class GamePanel extends JPanel implements IView2 {
	private DiceSet model;
	private boolean isKeepInput;
	private IController control;
	
	private DiePanel[] diePanels;
	private Box containerBox;
	private Box contentBox;
	private JPanel rollPanel;
	private JLabel rollLabel;
	private JPanel inputPanel;
	private JButton toggleButton;
	private JButton executeButton;
	
	public GamePanel( DiceSet diceSet, IController control ) {
		super( new BorderLayout() );
		this.model = diceSet;
		registerController( control );
		isKeepInput = true;
		
		Color darkGreen = new Color(34, 139, 34);
		Color bg = new Color(0,176,80);
		Color blue = new Color(79,129,189);
		
		setBackground( bg );
		
		diePanels = new DiePanel[5];
		
		containerBox = Box.createVerticalBox();
		containerBox.setBackground( darkGreen );
		containerBox.setOpaque( true );
		containerBox.add( Box.createVerticalStrut( 15 ) );
		
		rollPanel = new JPanel( new FlowLayout( FlowLayout.CENTER ) );
		
		rollLabel = new JLabel( "No Roll Yet" );
		rollLabel.setForeground( Color.WHITE );
		rollPanel.setBackground( bg );
		rollPanel.add( rollLabel );
		rollLabel.setFont( new Font( "Arial", Font.BOLD, 24 ) ); 
		containerBox.add( rollPanel );
		
		contentBox = Box.createHorizontalBox();
		
		for( int i = 0; i < 5; i++ ) {
			diePanels[i] = new DiePanel( diceSet.getDie(i), i + 1 );
			contentBox.add( diePanels[i] );
		}
		
		containerBox.add( contentBox );
		
		add( containerBox, BorderLayout.CENTER );
		
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
				executeButton.setText( isKeepInput ? "Set Values!" : 
										"Roll the Dice!");
				isKeepInput = !isKeepInput;
			}
		});
		inputPanel.add( toggleButton );
		
		executeButton = new JButton("Roll the Dice!");
		executeButton.setBackground( blue );
		executeButton.setForeground( Color.WHITE );
		executeButton.addActionListener( new GameListener() );
		inputPanel.add( executeButton );
		
		add( inputPanel, BorderLayout.SOUTH );
		
		setModel( diceSet );
	}
	
	public void setModel( Object o ) {
		this.model = (DiceSet)o;
		for( int i = 0; i < 5; i++ ) {
			diePanels[i].setModel( model.getDie(i) );
		}
		if( model.getRollNo() == 0 ) {
			rollLabel.setText("No Roll Yet");
			for( DiePanel diePanel : diePanels ) {
				diePanel.reset();
			}
		} else {
			rollLabel.setText( "Roll " + model.getRollNo() + " of 3" );
		}
		
		executeButton.setEnabled( model.getRollNo() == 3 ? false : true );
	}
	
	public void registerController( IController control ) {
		this.control = control;
	}
	
	private class GameListener implements ActionListener {
		public void actionPerformed( ActionEvent e ) {
			if(diePanels[0].getValue() instanceof Boolean){
				boolean[] keepers = new boolean[5];
				for(int i=0; i<5; i++){
					keepers[i] = Boolean.valueOf(diePanels[i].getValue()+"");
				}
				RollCommand rc = new RollCommand(model, keepers);
				control.executeCommand(rc);
			} else{
				int[] vals = new int[5];
				for(int i=0; i<5; i++){
					vals[i] = Integer.valueOf(diePanels[i].getValue()+"");
				}
				SetDiceCommand sdc = new SetDiceCommand(model, vals);
				control.executeCommand(sdc);
			}
		}
	}
}
