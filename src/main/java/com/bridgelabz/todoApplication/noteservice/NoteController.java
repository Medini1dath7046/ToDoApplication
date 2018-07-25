package com.bridgelabz.todoApplication.noteservice;
import java.text.ParseException;
/*****************************************************************************************************
 * Created By:Medini P.D
 * Date:- 16/07/2018
 * Purpose: Controller class for the note
 ****************************************************************************************************/
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.omg.CORBA.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.todoApplication.utilservice.Response;

@RestController
    public class NoteController {
	@Autowired
	NoteService noteService;

	@RequestMapping(value = "/createnote", method = RequestMethod.POST)
    	public ResponseEntity<Response> createNote(@RequestBody Note note,
		HttpServletRequest httpServletRequest) {
		String token = httpServletRequest.getHeader("Authorization");
		noteService.createNote(note, token);
		return new ResponseEntity<>(new Response("Note Created", HttpStatus.CREATED), HttpStatus.OK);
	}

	@RequestMapping(value = "/deletenote", method = RequestMethod.DELETE)
    	public ResponseEntity<Response> deleteNote(@RequestParam String noteId) {
		noteService.deleteNote(noteId);
		return new ResponseEntity<>(new Response("Note Deleted", HttpStatus.ACCEPTED), HttpStatus.OK);
	}

	@RequestMapping(value = "/updatenote", method = RequestMethod.PUT)
	public ResponseEntity<Response> updateNote(@RequestParam String noteId, @RequestParam String title, @RequestParam String description, HttpServletRequest httpServletRequest) {
		String token=httpServletRequest.getHeader("Authorization");
		noteService.updateNote(noteId, title, description, token);
		return new ResponseEntity<>(new Response("Note Updated", HttpStatus.ACCEPTED), HttpStatus.OK);
	}
	@RequestMapping(value = "/displaynotes", method = RequestMethod.GET)
    	public List<Note> getAllNotes(HttpServletRequest httpServletRequest) {
		String token = httpServletRequest.getHeader("Authorization");
		System.out.println(httpServletRequest.getHeader("test"));
		List<Note> list = noteService.displayAllNotes(token);
		return list;
	}
	
	@RequestMapping(value = "/pinnednote", method = RequestMethod.GET)
	public List<Note> pinnedNote( 	HttpServletRequest httpServletRequest) {
	String token = httpServletRequest.getHeader("Authorization");
	//noteService.displaypin( token);
//	return new ResponseEntity<>(new Response("Note Pinned", HttpStatus.ACCEPTED), HttpStatus.OK);
	System.out.println(httpServletRequest.getHeader("test"));
	List<Note> list= noteService. displaypin(token);
	return list;
	}

	@RequestMapping(value = "/archive-note", method = RequestMethod.GET)
	public List<Note> archiveNote( HttpServletRequest httpServletRequest) {
		String token = httpServletRequest.getHeader("Authorization");
		//noteService.displayarchive( token);
		//return new ResponseEntity<>(new Response("Note archived", HttpStatus.ACCEPTED), HttpStatus.OK);
		System.out.println(httpServletRequest.getHeader("test"));
		List<Note> list= noteService. displayarchive(token);
		return list;
		}
	
	@RequestMapping(value = "/setReminder", method = RequestMethod.GET)
	public ResponseEntity<Response> setReminder(HttpServletRequest httpServletRequest ,
			@RequestParam String noteId, @RequestParam String reminderTime) throws ParseException, UserException {
		String token = httpServletRequest.getHeader("Authorization");
	noteService.setReminder( token , noteId,  reminderTime);
	return new ResponseEntity<>(new Response("Response: reminder" , HttpStatus.CREATED), HttpStatus.OK);
}
}