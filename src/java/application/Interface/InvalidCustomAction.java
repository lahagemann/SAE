/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.Interface;

/**
 *
 * @author Luiza
 */
public class InvalidCustomAction extends Exception {

	private static final long serialVersionUID = 1L;

	public InvalidCustomAction() {
		super();
	}

	public InvalidCustomAction(String message) {
		super(message);
	}	
}