package com.clientname.app.classes;

public class Employee {
	private String sEid; // Accenture Enterprise ID
	private String sStatus; // Myte status eg. processed. submitted. etc.
	private String sPeriod;
	private String[][] sTaskHours; // wbs and hours.

	public Employee(String pEnterpriseID, String pStatus, String pPeriod, String[][] pTasks) {

		sEid = pEnterpriseID;
		sStatus = pStatus;
		sPeriod = pPeriod;
		sTaskHours = pTasks;

	}
	
	public String getEnterpriseID() {
		return sEid;
	}
	
	public String getStatus() {
		return sStatus;
	}
	
	public String getPeriod() {
		return sPeriod;
	}
	
	public String[][] getTasks(){
		return sTaskHours;
	}

}
