package com.bridgelabz.todoApplication.noteservice;
import java.text.ParseException;
/*************************************************************************************************
 * Created By:Medini P.D
 * Date:- 16/07/2018
 * Purpose: Note service class for the login and registration
 **************************************************************************************************/
import java.util.List;

import org.omg.CORBA.UserException;

public interface NoteService {
	public void createNote(Note note, String token);

	//public void updateNote(Note note, String title, String description);
	public List<Note> displayAllNotes(String token);
	void deleteNote(String noteId);
	public List<Note> displaypin(String token);
	public void updateNote(String noteId, String title, String description, String Token);
	public List<Note> displayarchive(String token1);
	void setReminder(String token, String id, String reminderTime) throws UserException, ParseException;
}