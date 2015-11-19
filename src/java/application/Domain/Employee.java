/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.Domain;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Luiza
 */
public class Employee {

	private final int identifier;
	private String name;
	private String cpf;
	private String email;
	private String password;
	private int workRoomID;
	private List<CustomAction> customActionList;

	public Employee(String name, String cpf, String email, String password,
			int workRoomID, int isAdmin) {
		super();
		this.identifier = 0;
		this.name = name;
		this.cpf = cpf;
		this.email = email;
		this.password = password;
		this.workRoomID = workRoomID;
		customActionList = new ArrayList<CustomAction>();
	}

	public Employee(String name, int identifier, String cpf, String email,
			String password, int workRoomID,
			List<CustomAction> customActionList, int isAdmin) {
		super();
		this.identifier = identifier;
		this.name = name;
		this.cpf = cpf;
		this.email = email;
		this.password = password;
		this.workRoomID = workRoomID;
		this.customActionList = customActionList;
	}

	public int getIdentifier() {
		return identifier;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getWorkRoomID() {
		return workRoomID;
	}

	public void setWorkRoomID(int workRoomID) {
		this.workRoomID = workRoomID;
	}

	public void setCustomActionList(List<CustomAction> customActionList) {
		this.customActionList = customActionList;
	}

	public List<CustomAction> getCustomActionList() {
		return customActionList;
	}	

	public void addCustomAction(CustomAction element){
		this.customActionList.add(element);
	}

	public void removeCustomAction(int position){
		this.customActionList.remove(position);
	}


}
