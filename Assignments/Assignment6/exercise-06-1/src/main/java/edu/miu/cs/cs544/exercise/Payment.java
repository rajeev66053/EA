package edu.miu.cs.cs544.exercise;

import javax.persistence.Embeddable;

@Embeddable
public class Payment {
	private String paydate;
	private double amount;
	public String getPaydate() {
		return paydate;
	}
	public void setPaydate(String paydate) {
		this.paydate = paydate;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
}
