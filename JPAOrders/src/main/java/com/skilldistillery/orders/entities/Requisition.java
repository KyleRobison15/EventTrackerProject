package com.skilldistillery.orders.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Requisition {

/////////////////////// FIELDS ///////////////////////////
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="date_placed")
	private LocalDateTime datePlaced;
	
	@Column(name="due_date")
	private LocalDateTime dueDate;
	
	private boolean completed;
	
	@ManyToOne
	@JoinColumn(name="customer_id")
	private Customer customer;
	
	@OneToMany(mappedBy = "req")
	private List<ReqProduct> reqProducts;
	
	@ManyToMany(mappedBy="reqs")
	private List<Product> products;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;

/////////////////////// CONSTRUCTORS ///////////////////////////
	
	public Requisition() {}

/////////////////////// Methods ///////////////////////////
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDateTime getDatePlaced() {
		return datePlaced;
	}

	public void setDatePlaced(LocalDateTime datePlaced) {
		this.datePlaced = datePlaced;
	}

	public LocalDateTime getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDateTime dueDate) {
		this.dueDate = dueDate;
	}
	
	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	public List<ReqProduct> getReqProducts() {
		return reqProducts;
	}

	public void setReqProducts(List<ReqProduct> reqProducts) {
		this.reqProducts = reqProducts;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void addProduct(Product product) {
		if (products == null) {
			products = new ArrayList<>();
		}
		if (!products.contains(product)) {
			products.add(product);
			product.addReq(this);
		}

	}

	public void removeProduct(Product product) {
		if (products != null && products.contains(product)) {
			products.remove(product);
			product.removeReq(this);
		}
	}

	@Override
	public String toString() {
		return "Requisition [id=" + id + ", datePlaced=" + datePlaced + ", dueDate=" + dueDate + ", completed="
				+ completed + ", customer=" + customer + ", user=" + user + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Requisition other = (Requisition) obj;
		if (id != other.id)
			return false;
		return true;
	}

	
}
