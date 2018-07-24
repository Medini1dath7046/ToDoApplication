package com.bridgelabz.todoApplication.noteservice;

import org.springframework.data.mongodb.core.mapping.Document;
import io.swagger.annotations.ApiModelProperty;

/*************************************************************************************************************
 * Created By:Medini P.D
 * Date:- 16/07/2018
 * Purpose:Pojo class for the Note
 ***************************************************************************************************************/
import javax.persistence.Id;

@Document(collection = "note")
public class Note
{	
	@Id
	@ApiModelProperty(hidden = true)
	private String id;
	
	@ApiModelProperty(hidden = true)
	private String createdDate;
	
	@ApiModelProperty(hidden = true)
	private boolean Trash;

	
	
	private String archive;
	private String description;
	private String title;
	private String pin;
	
		public String getId() {
		return id;
	}

	public void setTrash(boolean trash) {
		Trash = trash;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getArchive() {
		return archive;
	}

	public void setArchive(String archive) {
		this.archive = archive;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	@ApiModelProperty(hidden = true)
	private Long userId;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String string) {
		this.createdDate = string;
	}

	public Long getuserId() {
		return userId;
	}
	public void setUserId(Long long1) {
		this.userId = long1;
	}

	public boolean isTrash() {
		return false;
	}

	@Override
	public String toString() {
		return "Note [id=" + id + ", createdDate=" + createdDate + ", Trash=" + Trash + ", title=" + title
				+ ", description=" + description + ", pin=" + pin + ", archive=" + archive + ", userId=" + userId + "]";
	}
}