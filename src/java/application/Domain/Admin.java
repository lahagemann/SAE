/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.Domain;

/**
 *
 * @author Luiza
 */
public class Admin extends Employee {

	public Admin(String name, String cpf, String email, String password,
			int workRoomID, int isAdmin) {
		super(name, cpf, email, password, workRoomID, isAdmin);
	}

	public Admin(String name, int identifier, String cpf, String email,
			String password, int wordRoomID, int isAdmin) {
			super(name,cpf,email,password,wordRoomID, isAdmin);
	}
	
}
