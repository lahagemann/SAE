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
public class TurnOnOrOffReport extends Transaction {

	public TurnOnOrOffReport(int transactionResourceID, Date initialTime,
			Date finalTime) {
		super(transactionResourceID, initialTime, finalTime);
	}

	public TurnOnOrOffReport(int transactionResourceID, Date initialTime) {
		super(transactionResourceID, initialTime);
	}

	public TurnOnOrOffReport(int transactionResourceID) {
		super(transactionResourceID);
	}
	
	

}
