package com.bridgelabz.todoApplication.label;

/**********************************************************************************
 * Created By:Medini P.D
 * Date:- 25/07/2018
 * Purpose: Label Service interface for adding label implementation
 ***********************************************************************************/
public interface LabelService {
	public void createLabel(Label label, String token);
	//public void addLabel(Label label, String noteId, String token);
	//void addLabel(com.bridgelabz.todoApplication.label.Label label, String noteId, String token);
	void addLabel(String labelName, String noteId, String token);
	void deleteLabel(String Id);
}
