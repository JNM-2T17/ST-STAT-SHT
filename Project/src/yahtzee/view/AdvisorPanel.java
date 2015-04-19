package yahtzee.view;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import yahtzee.controller.IController;
import yahtzee.model.AdvisorModel;
import yahtzee.view.IView;

public class AdvisorPanel extends JPanel implements IView2 {
    private JTextArea text;
	private IController control;
    
    public AdvisorPanel(){
        Color darkgreen = new Color(146,208,80);
//        setSize(200, 15);
//        text = new JTextArea(200, 15);
		text = new JTextArea( 6, 30 );
        text.setEditable(false);
        text.setBackground(darkgreen);
		text.setBorder( BorderFactory.createEmptyBorder() );
        setBackground(darkgreen);
        add(text);   
    }

    @Override
    public void setModel(Object o) {
		setTextAreaText(o.toString());
    }

    public void registerController(IController control) {
		this.control = control;
    }
    
    public void setTextAreaText(String text){
        this.text.setText(text);
    }
}
