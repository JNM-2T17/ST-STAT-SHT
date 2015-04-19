package yahtzee.view;

import yahtzee.controller.IController;

/**
* interface for all views
* @author Austin Fernandez
*/
public interface IView {
	/**
	* updates the view's model
	* @param o model
	*/
	public void setModel( Object o );
}