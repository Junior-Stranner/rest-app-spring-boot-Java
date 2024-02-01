package br.com.jujubaprojects.restappspringboot.Model.person;

import java.io.Serializable;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.dozermapper.core.Mapping;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;



@Entity
@Table(name = "person")
//@JsonPropertyOrder({"id", "first_name", "last_name", "adress", "gender"})
public class Person implements Serializable{
    
    private static final long serialVersionUID = 1L;

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Mapping("id")
	private Long key;

//	@JsonProperty("first_name")
	@Column(name = "first_name", nullable = false , length = 50)
	private String firstName;

//	@JsonProperty("last_name")
	@Column(name = "last_name",nullable = false , length = 50)
	private String lastName;

	@Column(nullable = false , length = 100)
	private String address;

	@JsonIgnore
	@Column(nullable = false , length = 6)
	private String gender;

	
	
	public Person() {}

	

	public Long getKey() {
		return key;
	}



	public void setKey(Long key) {
		this.key = key;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

}
