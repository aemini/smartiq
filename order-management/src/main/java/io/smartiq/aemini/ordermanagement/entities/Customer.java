package io.smartiq.aemini.ordermanagement.entities;

import io.smartiq.aemini.ordermanagement.enums.CustomerType;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="customers")
public class Customer extends Person {

	private CustomerType customerType;

}
