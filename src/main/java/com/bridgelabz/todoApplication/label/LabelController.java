package com.bridgelabz.todoApplication.label;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.bridgelabz.todoApplication.utilservice.Response;

/***********************************************************************************
 * Created By:Medini P.D
 * Date:- 25/07/2018
 * Purpose:Label Controller class for the label implementation
 ***************************************************************************************/
@RestController
public class LabelController {
	@Autowired
	LabelServiceImpl labelService;
	
	@RequestMapping(value = "/createlabel", method = RequestMethod.POST)
	public ResponseEntity<Response> createNote(@RequestBody Label label,
			HttpServletRequest httpServletRequest) {
		String token = httpServletRequest.getHeader("Authorization");
	labelService.createLabel(label, token);
	return new ResponseEntity<>(new Response("Label Created", HttpStatus.CREATED), HttpStatus.OK);
}
@RequestMapping(value = "/addlabel", method = RequestMethod.PUT)
	public ResponseEntity<Response> updateNote(@RequestParam String labelName, @RequestParam String noteId, 
		HttpServletRequest httpServletRequest) {
		String token=httpServletRequest.getHeader("Authorization");
		labelService.addLabel(labelName, noteId, token);
		return new ResponseEntity<>(new Response("Note Updated", HttpStatus.ACCEPTED), HttpStatus.OK);
	}

@RequestMapping(value = "/deleteLabel", method = RequestMethod.DELETE)
	public ResponseEntity<Response> deleteNote(@RequestParam String id) {
	labelService.deleteLabel(id);
	return new ResponseEntity<>(new Response("Label  Deleted", HttpStatus.ACCEPTED), HttpStatus.OK);
}
}

