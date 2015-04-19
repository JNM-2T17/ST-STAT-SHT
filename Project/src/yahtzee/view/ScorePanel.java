package yahtzee.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import yahtzee.controller.IController;
import yahtzee.controller.command.LockCommand;
import yahtzee.controller.command.ResetCommand;
import yahtzee.controller.command.SetScoreCommand;
import yahtzee.model.PatternFinder;
import yahtzee.model.ScoreCard;
import yahtzee.view.layout.AGBLayout;

public class ScorePanel extends JPanel implements IView {
	private static String[] rows 
		= new String[]{ ScoreCard.ACES, ScoreCard.TWOS, ScoreCard.THREES, 
						ScoreCard.FOURS, ScoreCard.FIVES, ScoreCard.SIXES,
						ScoreCard.THREE_OF_A_KIND, ScoreCard.FOUR_OF_A_KIND,
						ScoreCard.FULL_HOUSE, ScoreCard.SMALL_STRAIGHT,
						ScoreCard.LARGE_STRAIGHT, ScoreCard.YAHTZEE,
						ScoreCard.CHANCE };
	private static String[] other
		= new String[]{ "Combinations", "UPPER", "Bonus", "Upper Total", 
						"LOWER", "Extra Yahtzee", "Lower Total", 
						"Grand Total" };
	
	private ScoreCard model;
	private PatternFinder model2;
	private IController control;
	
	private JPanel[] combinations;
	private JPanel[] scores;
	
	private LabelPanel bonus;
	private LabelPanel upper;
	private LabelPanel extra;
	private LabelPanel lower;
	private LabelPanel total;
	
	private HashMap<String, JPanel> scoreMap;
	
	public ScorePanel( ScoreCard scoreCard, IController control ) {
		super( new AGBLayout() );
		model = scoreCard;
		registerController( control );
		setBackground( Color.WHITE );
		
		Color header = new Color( 155, 187, 89 );
		Color lightGreen = new Color( 239, 243, 234 );
		Color normalGreen = new Color( 222, 231, 209 );
		
		combinations = new JPanel[21];
		scores = new JPanel[21];
		scoreMap = new HashMap<String, JPanel>();
		
		for( int i = 0, j = 0, k = 0; i < 21; i++ ) {
			if( i > 1 && i < 8 || i > 10 && i < 18 ) {
				combinations[i] = new LabelPanel( rows[j] );
				scores[i] = new ScorePanelRow( rows[j], control );
				scoreMap.put( rows[j], scores[i] );
				//((ScorePanelRow)scores[i]).setButton( 0 );
				j++;
			} else {
				combinations[i] = new LabelPanel( other[k] );
				scores[i] = new LabelPanel( i == 0 ? "Scores" : "" );
				switch( other[k] ) {
					case "Combinations":
					case "UPPER":
					case "LOWER":
						combinations[i].setFont( 
							new Font("Arial", Font.BOLD, 10 ) );
						scores[i].setFont( 
							new Font("Arial", Font.BOLD, 10 ) );
						break;
					case "Bonus":
						bonus = (LabelPanel)scores[i];
						bonus.setText( "0" );	
						break;
					case "Upper Total":
						upper = (LabelPanel)scores[i];
						upper.setText( "0" );
						combinations[i].setFont( 
							new Font("Arial", Font.BOLD, 10 ) );
						scores[i].setFont( 
							new Font("Arial", Font.BOLD, 10 ) );
						break;
					case "Extra Yahtzee":
						extra = (LabelPanel)scores[i];
						break;
					case "Lower Total":
						lower = (LabelPanel)scores[i];
						lower.setText( "0" );
						combinations[i].setFont( 
							new Font("Arial", Font.BOLD, 10 ) );
						scores[i].setFont( 
							new Font("Arial", Font.BOLD, 10 ) );
						break;
					case "Grand Total":
						total = (LabelPanel)scores[i];
						total.setText( "0" );
						combinations[i].setFont( 
							new Font("Arial", Font.BOLD, 10 ) );
						scores[i].setFont( 
							new Font("Arial", Font.BOLD, 10 ) );
						break;
					default:
				}
				k++;
			}
			
			if( i == 0 ) {
				combinations[i].setBackground( header );
				scores[i].setBackground( header );
				combinations[i].setForeground( Color.WHITE );
				scores[i].setForeground( Color.WHITE );
			} else if( i % 2 == 0 ) {
				combinations[i].setBackground( lightGreen );
				scores[i].setBackground( lightGreen );
			} else {
				combinations[i].setBackground( normalGreen );
				scores[i].setBackground( normalGreen );
			}
			
			AGBLayout.addComp( this, combinations[i], 0, i, 1, 1, 100, 100, 
								GridBagConstraints.CENTER, 
								GridBagConstraints.BOTH );
			AGBLayout.addComp( this, scores[i], 1, i, 1, 1, 100, 100, 
								GridBagConstraints.CENTER, 
								GridBagConstraints.BOTH );
		}
	} 
	
	public void setModel( Object o ) {
		if( o instanceof ScoreCard ) {
			ScoreCard sc = (ScoreCard)o;
			for( String s: rows ) {
				((ScorePanelRow)scoreMap.get( s ))
					.setModel( new Integer(sc.get(s)) );	
			}
			
			bonus.setText( sc.getBonus() + "" );
			upper.setText( sc.getUpper() + "" );
			extra.setText( sc.get( ScoreCard.EXTRA_YAHTZEE ) + "" );
			lower.setText( sc.getLower() + "" );
			total.setText( sc.getTotal() + "" );
			model = sc;
		} else if( o instanceof PatternFinder ) {
			PatternFinder pf = (PatternFinder)o;
			
			Map<String, Integer> upper = pf.getUpper();
			Map<String, Integer> lower = pf.getLower();
			
			for( String s: rows ) {
				Integer i = upper.get(s);
				ScorePanelRow spr = (ScorePanelRow)scoreMap.get(s);
				if( i == null ) {
					i = lower.get(s);
				}
				
				spr.setButton( i.intValue() );
			}
			model2 = pf;
		}
		end();
	}
	
	public void registerController( IController control ) {
		this.control = control;
	}
	
	
	public void end() {
		if( model.isGameFinished() 
			&& JOptionPane.showConfirmDialog( null, "Game Over! Play Again?", 
										"Game Over", 
										JOptionPane.YES_NO_OPTION ) 
			== JOptionPane.YES_OPTION ) {
			control.executeCommand( new ResetCommand() );
		} else {
			control.executeCommand( new LockCommand() );
		}
	}
	
	private class LabelPanel extends JPanel {
		private JLabel label;
		
		public LabelPanel() {
			super( new FlowLayout( FlowLayout.CENTER ) );
			initComponents();
		}
		
		public LabelPanel( String s ) {
			initComponents();
			setText( s );
		}
		
		private void initComponents() {
			label = new JLabel();
			setFont( new Font( "Arial", Font.PLAIN, 10 ) );
			add( label );
		}
		
		public void setBackground( Color c ) {
			super.setBackground( c );
			if( label != null ) {
				label.setBackground( c );
			}
		}
		
		public void setForeground( Color c ) {
			super.setForeground( c );
			if( label != null ) {
				label.setForeground( c );
			}
		}
		
		public void setFont( Font f ) {
			super.setFont( f );
			if( label != null ) {
				label.setFont( f );
			}
		}
		
		public void setText( String s ) {
			label.setText( s );
		}
	}
	
	private	class ScorePanelRow extends JPanel implements IView2 {
		private String combo;
		private JLabel score;
		private JButton scoreButton;
		
		private IController control;
		
		public ScorePanelRow( String combo, IController control ) {
			super( new FlowLayout( FlowLayout.CENTER ) );
			this.combo = combo;
			
			Color blue = new Color(79,129,189);
			
			score = new JLabel();
			add( score );
			scoreButton = new JButton();
			scoreButton.setBackground( blue );
			scoreButton.setForeground( Color.WHITE );
			scoreButton.addActionListener( new SetScoreListener() );
			scoreButton.setVisible( false );
			add( scoreButton );
			registerController( control );
			setFont( new Font( "Arial", Font.PLAIN, 10 ) );
		}
		
		public void setBackground( Color c ) {
			super.setBackground( c );
			if( score != null ) {
				score.setBackground( c );
			}
		}
		
		public void setFont( Font f ) {
			super.setFont( f );
			if( score != null ) {
				score.setFont( f );
			}
		}
		
		public void setButton( int score ) {
			/*if score isn't set yet*/
			if( model.get(combo) == -1 ) {
				this.score.setVisible( false );
				scoreButton.setText( score + "" );
				scoreButton.setVisible(true);
			}
		}
		
		public void setModel( Object o ) {
			Integer i = (Integer)o;
			int n = i.intValue();
			
			scoreButton.setVisible(false);
			
			//set label to empty if score is not yet set
			score.setText( n != -1 ? n + "" : "" );
			score.setVisible( true );
		}
		
		public void registerController( IController control ) {
			this.control = control;
		}
		
		private class SetScoreListener implements ActionListener {
			public void actionPerformed( ActionEvent e ) {
				control.executeCommand( new SetScoreCommand( combo ) );
			}
		}
	}
}
