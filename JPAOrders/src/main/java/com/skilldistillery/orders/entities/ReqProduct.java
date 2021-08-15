package com.skilldistillery.orders.entities;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name= "req_product")
public class ReqProduct {
	
/////////////////////// FIELDS ///////////////////////////	

	@EmbeddedId
	private ReqProductId id;
	
	@Column(name="units_ordered")
	private int unitsOrdered;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="requisition_id")
	@MapsId(value="reqId")
	private Requisition req;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="product_id")
	@MapsId(value="productId")
	private Product prod;

/////////////////////// CONSTRUCTORS ///////////////////////////
	
	public ReqProduct() {}

/////////////////////// METHODS ///////////////////////////
	
	public ReqProductId getId() {
		return id;
	}

	public void setId(ReqProductId id) {
		this.id = id;
	}

	public int getUnitsOrdered() {
		return unitsOrdered;
	}

	public void setUnitsOrdered(int unitsOrdered) {
		this.unitsOrdered = unitsOrdered;
	}
	
	public Requisition getReq() {
		return req;
	}

	public void setReq(Requisition req) {
		this.req = req;
	}

	public Product getProd() {
		return prod;
	}

	public void setProd(Product prod) {
		this.prod = prod;
	}

	@Override
	public String toString() {
		return "ReqProduct [id=" + id + ", unitsOrdered=" + unitsOrdered + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		ReqProduct other = (ReqProduct) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	

}
