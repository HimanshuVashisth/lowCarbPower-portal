package com.lowCarbPower.lowCarbPowerportal.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.lowCarbPower.lowCarbPowerportal.audit.AbstractAuditedEntity;

@Entity
@Table(name="block_price")
//@Audited
//@AuditTable("block_price_history")
public class PriceEntity  { // extends AbstractAuditedEntity

	/**
	 * 
	 */
	private static final long serialVersionUID = 7602288893963356575L;

	public PriceEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PriceEntity(Long id, String unit, double value) {
		super();
		this.id = id;
		this.unit = unit;
		this.value = value;
	}

	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name="unit",nullable=false)
	private String unit;
	
	@Column(name="value",nullable=false)
	private double value;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}
}
