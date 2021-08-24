package com.example.demo.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import io.swagger.annotations.ApiModelProperty;
import lombok.NoArgsConstructor;

@Entity
@Table
@NoArgsConstructor 
@EntityListeners(AuditingEntityListener.class)
public class User implements Serializable{

	private static final long serialVersionUID = -4423835208077217834L;

	@Id
	@GeneratedValue
	@ApiModelProperty(value = "autogenerated id")
	private int id;
	
	@ApiModelProperty(value = "FirstName of user")
	private String firstName;
	
	@ApiModelProperty(value = "LastName of user")
	private String lastName;
	
	public int getId() {
		return id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@ApiModelProperty(value = "Email of user")
	private String emailId;

}