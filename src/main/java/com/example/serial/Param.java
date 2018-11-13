package com.example.serial;

import java.io.Serializable;

public class Param /*implements Serializable*/{

	
	private String cc;
	
	private int a;

	public String getCc() {
		return cc;
	}

	public void setCc(String cc) {
		this.cc = cc;
	}

	public int getA() {
		return a;
	}

	public void setA(int a) {
		this.a = a;
	}

	@Override
	public String toString() {
		return "Param [cc=" + cc + ", a=" + a + "]";
	}
	
	
}
