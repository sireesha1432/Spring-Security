package com.SecurityExample.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Users {
     
	@Id
	private Integer idInteger;
	private String userName;
	private String password;
	
	
	
}
