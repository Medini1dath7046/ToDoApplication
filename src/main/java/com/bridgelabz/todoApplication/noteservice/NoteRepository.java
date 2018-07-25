package com.bridgelabz.todoApplication.noteservice;
/*************************************************************************************************
 * Created By:Medini P.D
 * Date:- 16/07/2018
 * Purpose: Note repository class for the login and registration
 ***********************************************************************************************/
import java.util.List;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepository extends MongoRepository<Note, String> {
// @Query("{description:?0 }")
	public List<Note> findNotesByUserId(String userId);
	public Optional<Note> findById(String Id);
//public List<Note> findNotesBydescription(String description);
	public Object findByUserId(String userId);


}
	