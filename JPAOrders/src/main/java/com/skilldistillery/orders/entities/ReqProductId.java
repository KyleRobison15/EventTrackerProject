package com.skilldistillery.orders.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ReqProductId implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Column(name = "requisition_id")
	private int reqId;
	
	@Column(name = "product_id")
	private int productId;
	
	public ReqProductId() {}

	public ReqProductId(int reqId, int productId) {
		super();
		this.reqId = reqId;
		this.productId = productId;
	}

	public int getReqId() {
		return reqId;
	}

	public void setReqId(int reqId) {
		this.reqId = reqId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "ReqProductId [reqId=" + reqId + ", productId=" + productId + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + productId;
		result = prime * result + reqId;
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
		ReqProductId other = (ReqProductId) obj;
		if (productId != other.productId)
			return false;
		if (reqId != other.reqId)
			return false;
		return true;
	}
	
	
	
}
