package io.smartiq.aemini.ordermanagement.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

import java.util.Objects;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@Table(name="people", indexes={
		@Index(name="idx_people_email", columnList="email")
}, uniqueConstraints={
		@UniqueConstraint(name="u_people_email", columnNames="email")
})
public class Person {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column(name="given_name", length=31)
	private String givenName;

	@Column(name="additional_name", length=31)
	private String additionalName;

	@Column(name="family_name", length=31)
	private String familyName;

	@Column(name="email", length=63)
	private String email;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGivenName() {
		return givenName;
	}

	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}

	public String getAdditionalName() {
		return additionalName;
	}

	public void setAdditionalName(String additionalName) {
		this.additionalName = additionalName;
	}

	public String getFamilyName() {
		return familyName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Person person = (Person) o;
		return id.equals(person.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public String toString() {
		return "Person{" +
				"id=" + id +
				", givenName='" + givenName + '\'' +
				", additionalName='" + additionalName + '\'' +
				", familyName='" + familyName + '\'' +
				", email='" + email + '\'' +
				'}';
	}

}
