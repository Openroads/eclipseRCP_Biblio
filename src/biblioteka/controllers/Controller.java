package biblioteka.controllers;


import javax.inject.Inject;

import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import biblioteka.interfaces.IModelData;
import biblioteka.views.PartView;

public class Controller{
	
	@Inject
	private MPart mPartView;
	private IModelData model;
	
	public Controller(){
		System.out.println("WET");
	}
	
	public void kurw()
	{
		System.out.println("WET");
		PartView partView = (PartView)mPartView.getObject();
		System.out.println(partView);
		partView.refreshView();
	}
	

}
