package io.smartiq.aemini.ordermanagement.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name="orders")
public class Order {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss.S")
	@Column(name="order_date", nullable=false, columnDefinition="TIMESTAMP WITH TIME ZONE")
	private OffsetDateTime orderDate;

	@OneToMany(cascade={CascadeType.DETACH, CascadeType.MERGE}, fetch= FetchType.LAZY, mappedBy="order")
	private Set<OrderItem> items;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public OffsetDateTime getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(OffsetDateTime orderDate) {
		this.orderDate = orderDate;
	}

	public Set<OrderItem> getItems() {
		return items;
	}

	public void setItems(Set<OrderItem> items) {
		this.items = items;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Order order = (Order) o;
		return id.equals(order.id) && orderDate.equals(order.orderDate);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, orderDate);
	}

	@Override
	public String toString() {
		return "Order{" +
				"id=" + id +
				", orderDate=" + orderDate +
				'}';
	}

}
