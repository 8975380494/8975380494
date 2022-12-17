package com.masai.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import javax.persistence.GenerationType;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Fir {
    
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer firId;
	 
	@Size(min = 3, message = "Invalid crimedetails - min 3 letters required")	
	private String crimeDetail; 
	
	@Past
	private LocalDateTime timeStamp ;
	
	@Size(min = 3, message = "Invalid policeStation - min 3 letters required")
	private String policeStation; 
	
	
	private Integer userid;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<User> firAgainst;
	
}
