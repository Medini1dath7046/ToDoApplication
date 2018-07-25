package com.bridgelabz.todoApplication.label;

import javax.persistence.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created By:Medini P.D 
 * Date:- 25/07/2018 
 * Purpose:Lable pojo class for creating a label
 */

@Document(collection = "label")
public class Label {
	@Id
	@ApiModelProperty(hidden = true)
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	private String labelName;

	public String getLabelName() {
		return labelName;
	}

	public void setLabelName(String lableName) {
		this.labelName = lableName;
	}
}
