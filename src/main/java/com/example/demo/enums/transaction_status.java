package com.example.demo.enums;

public enum transaction_status {
	Failed,
	Success;
	
	public String getStatus() {
        return this.name(); // Returns the name of the enum constant
    }
}
