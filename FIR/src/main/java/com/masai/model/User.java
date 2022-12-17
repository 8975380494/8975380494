package com.masai.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer userid;
	
	@Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Invalid name")
	@Size(min = 3,max = 20, message = "Invalid Fisrtname - min 3 letters and max 20 required")
	private String firstName; 
	
	@Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Invalid name")
	@Size(min = 3,max = 20, message = "Invalid lastname - min 3 letters and max 20 required")	
	private String lastName ;
	
	@Pattern(regexp = "[6-9]{1}[0-9]{9}", message = "Mobile number must have exact 10 digits")
	private String mobileNumber;
	
    @NotBlank
	private String address  ;
	
    @Min(value =8, message="Minimum value for age is 8 please enter age above 8years " )
	private Integer age;
	
    @Pattern(regexp = "(?:Male|Female|Transgender)$", message = "Invalid Gender")
	private String gender;
	
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{6,12}", message = "Invalid Paasword")
	private String password; 
    
    @OneToMany(cascade = CascadeType.ALL)
    private List<Fir> fir;
    
    
}
