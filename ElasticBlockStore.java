package application;

public class ElasticBlockStore {
	
	private String name;
	private String storage;
	private String usage;
	private String iopsProvision;
	private String individualIOPS;
	private String totalCost;
	
	public ElasticBlockStore() {
		this.name = "";
		this.storage = "";
		this.usage = "";
		this.iopsProvision = "";
		this.individualIOPS = "";
		this.totalCost = "";
	}

	public ElasticBlockStore(String name, String storage, String usage, String iopsProvision, String individualIOPS, String totalCost) {
		this.name = name;
		this.storage = storage;
		this.usage = usage;
		this.iopsProvision = iopsProvision;
		this.individualIOPS = individualIOPS;
		this.totalCost = totalCost;
	}

	
	public String getName() {
		return name;
	}
	public String getStorage() {
		return storage;
	}
	public String getUsage() {
		return usage;
	}
	public String getIopsProvision() {
		return iopsProvision;
	}
	public String getIndividualIOPS() {
		return individualIOPS;
	}
	public String getTotalCost() {
		return totalCost;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setStorage(String storage) {
		this.storage = storage;
	}
	public void setUsage(String usage) {
		this.usage = usage;
	}
	public void setIopsProvision(String iopsProvision) {
		this.iopsProvision = iopsProvision;
	}
	public void setIndividualIOPS(String individualIOPS) {
		this.individualIOPS = individualIOPS;
	}
	public void setTotalCost(String totalCost) {
		this.totalCost = totalCost;
	}
	
}
