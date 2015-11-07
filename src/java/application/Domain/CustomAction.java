/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application.Domain;

import java.util.List;

/**
 *
 * @author Luiza
 */
public class CustomAction {

	private List<Resource> resourceList;
	private final int identifier;
	private int idEmployee;


	public CustomAction(int idEmployee, List<Resource> resourceList) {
		super();
		this.identifier = 0;
		this.idEmployee = idEmployee;
		this.resourceList = resourceList;
	}


	public CustomAction(int identifier, int idEmployee, List<Resource> resourceList) {
		super();
		this.identifier = identifier;
		this.idEmployee = idEmployee;
		this.resourceList = resourceList;
	}

	public List<Resource> getResourceList() {
		return resourceList;
	}

	public int getIdentifier() {
		return identifier;
	}

	public void addResource(Resource element){
		this.resourceList.add(element);
	}

	public void removeResource(int position){
		this.resourceList.remove(position);
	}

	public int getIdEmployee() {
		return idEmployee;
	}

	public void setIdEmployee(int idEmployee) {
		this.idEmployee = idEmployee;
	}

	public String toString(){
		String buffer =  "Identifier: " + this.getIdentifier() + "\nEmployee: " + this.getIdEmployee() + "\nResources:";
		List<Resource> listOfResources = this.getResourceList();
		for(int i = 0; i < listOfResources.size(); i++){
			buffer = buffer + " " + listOfResources.get(i).getIdentifier();
		}
		return buffer;
	}
	
}