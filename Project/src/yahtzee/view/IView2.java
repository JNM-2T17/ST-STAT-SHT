package yahtzee.view;

import yahtzee.controller.IController;

/**
* interface for all views with controller
* @author Austin Fernandez
*/
public interface IView2 extends IView {
	/**
	* registers a controller
	* @param control controller
	*/
	public void registerController( IController control );
}
