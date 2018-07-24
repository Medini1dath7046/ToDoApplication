package com.bridgelabz.todoApplication.noteservice;

import java.text.ParseException;
import java.text.SimpleDateFormat;
/****************************************************************************************************
 * Created By:Medini P.D
 * Date:- 16/07/2018
 * Purpose: Note Service implementation class for the login and registration
 *****************************************************************************************************/
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;

import javax.validation.ConstraintViolationException;

import org.assertj.core.util.Preconditions;
import org.omg.CORBA.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.bridgelabz.todoApplication.userservice.User;
import com.bridgelabz.todoApplication.userservice.UserRepository;
import com.bridgelabz.todoApplication.utilservice.JWToken;
import com.bridgelabz.todoApplication.utilservice.NoteExceptionHandler;
import com.bridgelabz.todoApplication.utilservice.UserExceptionHandler;

@Service
public class NoteServiceImpl implements NoteService {

	@Autowired
	NoteRepository noteRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	JWToken jwToken;
	private Timer timer;

	@Override
	public void createNote(Note note, String token) {
		String email = jwToken.verifyToken(token);
		User user = userRepository.findByEmail(email);
		if (user == null)
			throw new UserExceptionHandler("Please login");
		if (note.getTitle() == null && note.getDescription() == null)
			throw new NoteExceptionHandler("Note cannot be created with empty title and description");
		note.setCreatedDate(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date()));
		note.setUserId(user.getId());
		try {
			noteRepository.save(note);
		} catch (DataIntegrityViolationException | ConstraintViolationException e) {
			throw new NoteExceptionHandler("Note cannot be created due to database error, please try again later");
		}
	}

	@Override
	public void deleteNote(String noteId) {
		Optional<Note> optionalnote = noteRepository.findById(noteId);
		if (!optionalnote.isPresent()) {
			throw new UsernameNotFoundException("User not Found");
		}
		if (!optionalnote.get().isTrash()) {
			throw new NoteExceptionHandler("Note is not present in trash");
		}
		noteRepository.deleteById(noteId);
	}

	@Override
	public void updateNote(String noteId, String title, String description, String token) {
		String email = jwToken.verifyToken(token);
		User user = userRepository.findByEmail(email);
		if (user == null) {
			throw new NoteExceptionHandler("Login once again");
		}
		Optional<Note> note1 = noteRepository.findById(noteId);
		if (!note1.isPresent()) {
			throw new UsernameNotFoundException("User not Found");
		}
		Note note = note1.get();
		note.setTitle(title);
		note.setDescription(description);
		noteRepository.save(note);
	}

	@Override
	public List<Note> displayAllNotes(String token) {
		String email = jwToken.verifyToken(token);
		User user = userRepository.findByEmail(email);
		Long userId = user.getId();
		List<Note> noteList = noteRepository.findNotesByUserId(userId);
		System.out.println(noteList);
		return noteList;
	}

	@Override
	public List<Note> displaypin(String token) {
		String email = jwToken.verifyToken(token);
		User user = userRepository.findByEmail(email);
		Long userId = user.getId();
		List<Note> noteList = noteRepository.findNotesByUserId(userId);
		List<Note> pinnedlist = new ArrayList<Note>();

		for (Note n : noteList) {
			if (n.getPin().equals("yes")) {
				pinnedlist.add(n);
			}
		}
		return pinnedlist;
	}

	@Override
	public List<Note> displayarchive(String token) {
		String email = jwToken.verifyToken(token);
		User user = userRepository.findByEmail(email);
		Long userId = user.getId();
		List<Note> noteList = noteRepository.findNotesByUserId(userId);
		List<Note> archiveList = new ArrayList<Note>();
		for (Note n : noteList) {
			if (n.getArchive().equals("yes")) {
				archiveList.add(n);
			}
		}
		System.out.println(archiveList);
		return archiveList;
	}
	
	@Override
    public void setReminder(String token, String id, String reminderTime) throws UserException, ParseException {
        Optional<Note> note = noteRepository.findById(id);
        Timer timer = null;
        if (note.isPresent()) {
            Date reminder = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(reminderTime);
            long timeDifference = reminder.getTime() - new Date().getTime();
            timer = new Timer();
            timer.schedule(new TimerTask() {

                @Override
                public void run() {
                    System.out.println("Reminder task:" + note);
                }
            }, timeDifference);
        }
	}
}


	
	