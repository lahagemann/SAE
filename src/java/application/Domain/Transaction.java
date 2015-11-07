/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.Domain;

import java.util.Date;

/**
 *
 * @author Luiza
 */
abstract class Transaction  {
	
	private int transactionResourceID;
	private Date initialTime, finalTime;
	
	protected Transaction(int transactionResourceID) {
		super();
        this.transactionResourceID = transactionResourceID;
		this.initialTime = new Date();
	}

    public Transaction(int transactionResourceID, Date initialTime) {
        this.transactionResourceID = transactionResourceID;
        this.initialTime = initialTime;
        this.finalTime = null;
    }
	
    public Transaction(int transactionResourceID, Date initialTime, Date finalTime) {
        this.transactionResourceID = transactionResourceID;
        this.initialTime = initialTime;
        this.finalTime = finalTime;
    }
        
	public int getTransactionResourceID() {
		return transactionResourceID;
	}
	
	public void setTransactionResourceID(int transactionResourceID) {
		this.transactionResourceID = transactionResourceID;
	}

    public Date getInitialTime() {
        return initialTime;
    }

    public void setInitialTime(Date initialTime) {
        this.initialTime = initialTime;
    }

    public Date getFinalTime() {
        return finalTime;
    }

    public void setFinalTime(Date finalTime) {
        this.finalTime = finalTime;
    }    
}