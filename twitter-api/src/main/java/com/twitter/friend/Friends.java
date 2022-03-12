package com.twitter.friend;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.twitter.user.User;

import lombok.Data;

@Data
@Entity
public class Friends {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
    private Date createdDate;
    
    @OneToOne(cascade = CascadeType.ALL)
    User firstUser;
    
    @OneToOne(cascade = CascadeType.ALL)
    User secondUser;

}
