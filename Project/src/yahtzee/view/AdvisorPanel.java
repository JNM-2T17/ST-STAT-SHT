package yahtzee.view;

import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import yahtzee.controller.IController;
import yahtzee.model.AdvisorModel;
import yahtzee.view.IView;

public class AdvisorPanel extends JPanel implements IView {
    private JTextArea text;
	private IController control;
    
    public AdvisorPanel(){
        Color darkgreen = new Color(34, 139, 34);
//        setSize(200, 15);
//        text = new JTextArea(200, 15);
        text.setEditable(false);
        text.setBackground(darkgreen);
        text.setForeground(Color.WHITE);
        setBackground(darkgreen);
        add(text);   
    }

    @Override
    public void setModel(Object o) {
		setTextAreaText(((AdvisorModel)o).getProbs());
    }

    public void registerController(IController control) {
		this.control = control;
    }
    
    public void setTextAreaText(String text){
        this.text.setText(text);
    }
}
