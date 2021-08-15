package com.skilldistillery.orders.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

/////////////////////// FIELDS ///////////////////////////

@Entity
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	@Column(name="unit_quantity")
	private int unitQuantity;
	
	@Column(name="unit_price")
	private double unitPrice;
	
	@Column(name="image_url")
	private String imageUrl;
	
	@JsonIgnore
	@OneToMany(mappedBy = "prod")
	private List<ReqProduct> reqProducts;
	
	@JsonIgnore
	@ManyToMany(cascade = CascadeType.PERSIST) // If I have an actor, and it has a list of films. ALSO persist my list of films
	@JoinTable(name="req_product", 
		joinColumns=@JoinColumn(name= "product_id"), 
		inverseJoinColumns =@JoinColumn(name="requisition_id"))
	private List<Requisition> reqs;

/////////////////////// CONSTRUCTORS ///////////////////////////	
	
	public Product() {}

/////////////////////// METHODS ///////////////////////////	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getUnitQuantity() {
		return unitQuantity;
	}

	public void setUnitQuantity(int unitQuantity) {
		this.unitQuantity = unitQuantity;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
	public List<ReqProduct> getReqProducts() {
		return reqProducts;
	}

	public void setReqProducts(List<ReqProduct> reqProducts) {
		this.reqProducts = reqProducts;
	}

	public List<Requisition> getReqs() {
		return reqs;
	}

	public void setReqs(List<Requisition> reqs) {
		this.reqs = reqs;
	}
	
	public void addReq(Requisition req) {
		if (reqs == null) {
			reqs = new ArrayList<>();
		}
		if (!reqs.contains(req)) {
			reqs.add(req);
			req.addProduct(this);
		}

	}

	public void removeReq(Requisition req) {
		if (reqs != null && reqs.contains(req)) {
			reqs.remove(req);
			req.removeProduct(this);
		}
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", unitQuantity=" + unitQuantity + ", unitPrice=" + unitPrice
				+ ", imageUrl=" + imageUrl + "]";
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
		Product other = (Product) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
}
