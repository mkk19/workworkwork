package application;

public class Instance {
	

	private String name;
	private String usage;
	private String vCPUs;
	private String storage;
	private String memory;
	private String runningCost;
	private String totalCost;
	
	
	public Instance() {
		this.name = "";
		this.usage = "";
		this.vCPUs = "";
		this.storage = "";
		this.memory = "";
		this.runningCost = "";
		this.totalCost = "";
	}

	
	public Instance(String name, String usage, String vCPUs, String storage, String memory, String runningCost, String totalCost) {
		this.name = name;
		this.usage = usage;
		this.vCPUs = vCPUs;	
		this.storage = storage;
		this.memory = memory;
		this.runningCost = runningCost;
		this.totalCost = totalCost;
	}

	
	public String getName() {
		return name;
	}


	public String getUsage() {
		return usage;
	}


	public String getVCPUs() {
		return vCPUs;
	}


	public String getStorage() {
		return storage;
	}


	public String getMemory() {
		return memory;
	}


	public String getRunningCost() {
		return runningCost;
	}


	public String getTotalCost() {
		return totalCost;
	}


	public void setName(String name) {
		this.name = name;
	}


	public void setUsage(String usage) {
		this.usage = usage;
	}


	public void setVCPUs(String vCPUs) {
		this.vCPUs = vCPUs;
	}


	public void setStorage(String storage) {
		this.storage = storage;
	}


	public void setMemory(String memory) {
		this.memory = memory;
	}


	public void setRunningCost(String runningCost) {
		this.runningCost = runningCost;
	}


	public void setTotalCost(String totalCost) {
		this.totalCost = totalCost;
	}



}
