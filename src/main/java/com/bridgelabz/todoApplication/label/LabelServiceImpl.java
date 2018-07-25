package com.bridgelabz.todoApplication.label;

import java.util.Optional;

import javax.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.bridgelabz.todoApplication.noteservice.Note;
import com.bridgelabz.todoApplication.noteservice.NoteRepository;
import com.bridgelabz.todoApplication.userservice.User;
import com.bridgelabz.todoApplication.userservice.UserRepository;
import com.bridgelabz.todoApplication.utilservice.JWToken;
import com.bridgelabz.todoApplication.utilservice.NoteExceptionHandler;
import com.bridgelabz.todoApplication.utilservice.UserExceptionHandler;

/****************************************************************************************
 * Created By:Medini P.D 
 * Date:- 25/07/2018
 *  Purpose:Label Service implementation for the note application
 ******************************************************************************************/

@Service
public class LabelServiceImpl implements LabelService {

	@Autowired
	NoteRepository noteRepository;
	@Autowired
	LabelRepository labelRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	JWToken jwToken;

	@Override
	public void createLabel(Label label, String token) {
		String email = jwToken.verifyToken(token);
		User user = userRepository.findByEmail(email);
		if (user == null)
			throw new UserExceptionHandler("Please login");
		if (label.getLabelName() == null)
			throw new NoteExceptionHandler("Label cannot be created with empty title and description");
		try {
			label.setId(user.getId());
			labelRepository.save(label);
		} catch (DataIntegrityViolationException | ConstraintViolationException e) {
			throw new NoteExceptionHandler("Note cannot be created due to database error, please try again later");
		}
	}

	@Override
	public void addLabel(String labelName, String noteId, String token) {
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
		note.setLabelName(labelName);
		noteRepository.save(note);
	}

	@Override
	public void deleteLabel(String id) {
		Optional<Label> optionallabel = labelRepository.findById(id);
		if (!optionallabel.isPresent()) {
			throw new UsernameNotFoundException("Label not Found");
		}
		labelRepository.deleteById(id);
	}
}