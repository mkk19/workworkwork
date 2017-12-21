package application;
import javafx.fxml.Initializable;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.net.URL;
import java.security.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TextField;

/*
 * 
 * A calculator to work out the per second cost of selected AWS Instances.
 * Data retrieved from https://www.ec2instances.info
 * @author -- mark.kwesiga@ge.com
 * 
 */
public class LinuxController implements Initializable {
	
// ------------------------------------
	
	//First Tab (Linux)
	@FXML 
	private ChoiceBox ec2Select;
	@FXML 
	private Button calculateBtn = new Button();
	@FXML
	private TextField totalCost;
	@FXML
	private TextField totalCostSec;
	@FXML
	private TextField perSecCost;
	@FXML
	private TextField perDayCost;
	@FXML
	private TextField tab1Usage;
	@FXML
	private TextField numOfvCPU;
	@FXML
	private TextField amountOfStorage;
	@FXML
	private TextField amountOfMem;
	@FXML
	private Label warningMessage;
	@FXML
	private Button clearAllBtnLinux = new Button();
	@FXML
	private TableView<Instance> tableView;
	@FXML
	private TableColumn<Instance, String> instanceColumn;
	@FXML
	private TableColumn<Instance, String> usageColumn;
	@FXML
	private TableColumn<Instance, String> vCPUColumn;
	@FXML
	private TableColumn<Instance, String> storageColumn;
	@FXML
	private TableColumn<Instance, String> memoryColumn;
	@FXML
	private TableColumn<Instance, String> dailyRunningCostColumn;
	@FXML
	private TableColumn<Instance, String> totalCostColumn;
	@FXML
	private Button submitToTable= new Button();
	@FXML
	private Button deletedSelectedRowBtn = new Button();
	@FXML
	private TextField cumalativeCostLinux;
	@FXML
	private Button testTingBtn = new Button();

	
// ------------------------------------
	
	//Second Tab (Windows)
	@FXML
	private ChoiceBox ec2SelectWin;
	@FXML 
	private Button calculateBtnWin = new Button();
	@FXML
	private TextField totalCostWin;
	@FXML
	private TextField totalCostSecWin;
	@FXML
	private TextField perSecCostWin;
	@FXML
	private TextField perDayCostWin;
	@FXML
	private TextField tab2Usage;
	@FXML
	private TextField numOfvCPUWin;
	@FXML
	private TextField amountOfStorageWin;
	@FXML
	private TextField amountOfMemWin;
	@FXML
	private Label warningMessageWindows;
	@FXML
	private Button clearAllBtnWin = new Button();
	@FXML
	private TableView<Instance> tableViewWin;
	@FXML
	private TableColumn<Instance, String> instanceColumnWin;
	@FXML
	private TableColumn<Instance, String> usageColumnWin;
	@FXML
	private TableColumn<Instance, String> vCPUColumnWin;
	@FXML
	private TableColumn<Instance, String> storageColumnWin;
	@FXML
	private TableColumn<Instance, String> memoryColumnWin;
	@FXML
	private TableColumn<Instance, String> dailyRunningCostColumnWin;
	@FXML
	private TableColumn<Instance, String> totalCostColumnWin;
	@FXML
	private Button submitToTableWin= new Button();
	@FXML
	private Button deletedSelectedRowBtnWin = new Button();
	@FXML
	private TextField cumalativeCostWindows;
	
	public Double accumulation = 0.0;	

// ------------------------------------
	
	//Third Tab (EBS Volume)
	@FXML
	private ChoiceBox volumeSelect;
	@FXML 
	private Button calculateBtnEBS = new Button();
	@FXML
	private TextField tab3Usage;
	@FXML
	private TextField totalCostEBS;
	@FXML
	private TextField storageUsage;
	@FXML
	private TextField storageAmount;
	@FXML 
	private Label iopsProvision;
	@FXML
	private TextField iopsProvisionInput;
	@FXML
	private ToggleGroup tabThreeGroup = new ToggleGroup();
	@FXML
	private RadioButton generalPurposeSSDRatio;
	@FXML
	private RadioButton amazonS3SnapshotsRatio;
	@FXML
	private RadioButton coldHDDRatio;
	@FXML
	private RadioButton throughputOptimizedHDDRatio;
	@FXML
	private RadioButton provisionedIOPSSDRatio;
	@FXML
	private RadioButton selectedRadio;
	@FXML
	private RadioButton selectedRadio2;
	@FXML
	private Label individualIOPS;
	@FXML
	private TextField individualIOPSField;
	@FXML
	private Label dollarSign;
	@FXML
	private Button clearAllBtn = new Button();
	@FXML
	private Label warningMessageEBS;
	@FXML 
	private Button exportAll = new Button();
	@FXML 
	private Button exportAllWin = new Button();
	@FXML
	private Label successExport;
	@FXML
	private Label successExportWin;
	@FXML
	private Label successExportEBS;
	@FXML
	private Button exportAllEBS = new Button();
	@FXML
	private TableView<ElasticBlockStore> tableViewEBS;
	@FXML
	private TableColumn<ElasticBlockStore, String> nameColumnEBS;
	@FXML
	private TableColumn<ElasticBlockStore, String> storageColumnEBS;
	@FXML
	private TableColumn<ElasticBlockStore, String> usageColumnEBS;
	@FXML
	private TableColumn<ElasticBlockStore, String> iopsProvisionColumnEBS;
	@FXML
	private TableColumn<ElasticBlockStore, String> iopsCostColumnEBS;
	@FXML
	private TableColumn<ElasticBlockStore, String> totalCostColumnEBS;
	@FXML
	private TextField cumulativeCostEBS;
	@FXML
	private Button deletedSelectedRowBtnEBS = new Button();
	
	// counter for writeExcel methods
	public static int count = 1;
	
	//date
	Date date = new Date() ;
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd") ;
	
	//Instance List
	ObservableList<String> ec2InstanceList = FXCollections.observableArrayList("m1.small", "m1.medium", "m1.large", 
	"m1.xlarge","t1.micro", "t2.nano", "t2.micro", "t2.small", "t2.medium", "t2.large", "t2.xlarge", 
	"t2.2xlarge", "m4.large", "m4.xlarge", "c4.large", "c4.xlarge", "c4.2xlarge", "c4.4xlarge", "c4.8xlarge", 
	"r4.large", "r4.xlarge", "r4.2xlarge", "r4.4xlarge", "r4.8xlarge", "r4.16xlarge");
	
	// Volume List 
	ObservableList<String> ebsVolumeList = FXCollections.observableArrayList("EBS General Purpose SSD (gp2)", 
	"EBS Provisioned IOPS SSD (io1)", "EBS Throughput Optimized HDD (st1)", 
	"EBS Cold HDD (sc1)", "EBS Snapshots to Amazon S3");	
	 
	// Big Decimal variables 
	BigDecimal instanceType, usageInSeconds, numOfGigs, withGigsCost, storageWithUsage, 
	gigsMultipliedByType, instanceGigsMultipliedByUsage, thirtyDaysInSeconds, 
	usageInSeconds2, numOfIOPS, iopsMultipliedByCost, iopsCost, iopsValue, 
	combinedValue, ebsType;
	MathContext mc = new MathContext(4);
	MathContext mcEBS = new MathContext(8);	 

	// get Instances mehtods 
	public ObservableList<Instance> getInstances(){
		ObservableList<Instance> instance = FXCollections.observableArrayList();	
		return instance;
	}
	
	// get Elastic Block Stores Method
	public ObservableList<ElasticBlockStore> getElasticBlockStores(){
		ObservableList<ElasticBlockStore> elasticBlockStore = FXCollections.observableArrayList();	
		return elasticBlockStore;
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	
	
	//Linux Column Names 
		instanceColumn.setCellValueFactory(new PropertyValueFactory<Instance, String>("name"));
		usageColumn.setCellValueFactory(new PropertyValueFactory<Instance, String>("usage"));
		vCPUColumn.setCellValueFactory(new PropertyValueFactory<Instance, String>("vCPUs"));
		storageColumn.setCellValueFactory(new PropertyValueFactory<Instance, String>("storage"));
		memoryColumn.setCellValueFactory(new PropertyValueFactory<Instance, String>("memory"));
		dailyRunningCostColumn.setCellValueFactory(new PropertyValueFactory<Instance, String>("runningCost"));
		totalCostColumn.setCellValueFactory(new PropertyValueFactory<Instance, String>("totalCost"));
	// Windows Column Names
		instanceColumnWin.setCellValueFactory(new PropertyValueFactory<Instance, String>("name"));
		usageColumnWin.setCellValueFactory(new PropertyValueFactory<Instance, String>("usage"));
		vCPUColumnWin.setCellValueFactory(new PropertyValueFactory<Instance, String>("vCPUs"));
		storageColumnWin.setCellValueFactory(new PropertyValueFactory<Instance, String>("storage"));
		memoryColumnWin.setCellValueFactory(new PropertyValueFactory<Instance, String>("memory"));
		dailyRunningCostColumnWin.setCellValueFactory(new PropertyValueFactory<Instance, String>("runningCost"));
		totalCostColumnWin.setCellValueFactory(new PropertyValueFactory<Instance, String>("totalCost"));
    //Elastic Block Store Column Names
		nameColumnEBS.setCellValueFactory(new PropertyValueFactory<ElasticBlockStore, String>("name"));
		storageColumnEBS.setCellValueFactory(new PropertyValueFactory<ElasticBlockStore, String>("storage"));
		usageColumnEBS.setCellValueFactory(new PropertyValueFactory<ElasticBlockStore, String>("usage"));
		iopsProvisionColumnEBS.setCellValueFactory(new PropertyValueFactory<ElasticBlockStore, String>("iopsProvision"));
		iopsCostColumnEBS.setCellValueFactory(new PropertyValueFactory<ElasticBlockStore, String>("individualIOPS"));
		totalCostColumnEBS.setCellValueFactory(new PropertyValueFactory<ElasticBlockStore, String>("totalCost"));
	// Linux Instances
		tableView.setItems(getInstances());
		tableView.setEditable(true);
		tableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
	// Windows Instances
		tableViewWin.setItems(getInstances());
		tableViewWin.setEditable(true);
		tableViewWin.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    // ElasticBlock Store
		tableViewEBS.setItems(getElasticBlockStores());
		tableViewEBS.setEditable(true);
		tableViewEBS.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		
	// TODO Auto-generated method stub
		ec2Select.setValue("m1.medium");
		ec2Select.setItems(ec2InstanceList);
		ec2SelectWin.setValue("m1.medium");
		ec2SelectWin.setItems(ec2InstanceList);
		iopsProvision.setVisible(false);
		iopsProvisionInput.setVisible(false);
		individualIOPS.setVisible(false);
		individualIOPSField.setVisible(false);
		dollarSign.setVisible(false);
		warningMessage.setVisible(false);
		warningMessageWindows.setVisible(false);
		warningMessageEBS.setVisible(false);
		successExport.setVisible(false);
		successExportWin.setVisible(false);
		successExportEBS.setVisible(false);
			
    } 
	
	public void setLabels() {
		
		// Sets IOPS modules to visible only when option is selected
				provisionedIOPSSDRatio.setOnAction(a -> {
					iopsProvision.setVisible(true);
					iopsProvisionInput.setVisible(true);
					individualIOPS.setVisible(true);
					individualIOPSField.setVisible(true);
					dollarSign.setVisible(true);
			    });
				generalPurposeSSDRatio.setOnAction(a -> {
					iopsProvision.setVisible(false);
					iopsProvisionInput.setVisible(false);
					individualIOPS.setVisible(false);
					individualIOPSField.setVisible(false);
					dollarSign.setVisible(false);
			    });
				amazonS3SnapshotsRatio.setOnAction(a -> {
					iopsProvision.setVisible(false);
					iopsProvisionInput.setVisible(false);
					individualIOPS.setVisible(false);
					individualIOPSField.setVisible(false);
					dollarSign.setVisible(false);
			    });
				throughputOptimizedHDDRatio.setOnAction(a -> {
					iopsProvision.setVisible(false);
					iopsProvisionInput.setVisible(false);
					individualIOPS.setVisible(false);
					individualIOPSField.setVisible(false);
					dollarSign.setVisible(false);
			    });
				coldHDDRatio.setOnAction(a -> {
					iopsProvision.setVisible(false);
					iopsProvisionInput.setVisible(false);
					individualIOPS.setVisible(false);
					individualIOPSField.setVisible(false);
					dollarSign.setVisible(false);
			    });		
	}
	// Method to Calculate the cost of Linux Instances 
	public void calculateLinuxCost(){
		/*
		 * instanceType stores the per second cost of running selected instance
		 * usageInSeconds takes user input of number of seconds running the selected instance 
		 * totalCost is the entire cost of running the instance 
		*
		*/

		// private TextField numOfvCPU, amountOfStorage, amountOfMem;
		String selectedInstanceType = (String) ec2Select.getValue();

		switch(selectedInstanceType) {
		case "m1.small" : {
		instanceType = new BigDecimal("0.00001222222");
		 usageInSeconds = new BigDecimal(tab1Usage.getText());
		 BigDecimal value = instanceType.multiply(usageInSeconds, mc);
		 numOfvCPU.setText("1");
		 amountOfStorage.setText("EBS Only");
		 amountOfMem.setText("1.7");
		 totalCost.setText(String.valueOf(value));
		 perDayCost.setText(String.valueOf(1.056000));
		break;
		}
		case "m1.medium" : {
		instanceType = new BigDecimal("0.00002416666");
		usageInSeconds = new BigDecimal(tab1Usage.getText());
		BigDecimal value = instanceType.multiply(usageInSeconds, mc);
		numOfvCPU.setText("1");
		amountOfStorage.setText("EBS Only");
		amountOfMem.setText("3.75");
		totalCost.setText(String.valueOf(value));
		perDayCost.setText(String.valueOf(2.088000));
		break;
		}
		case "m1.large" : {
		  instanceType = new BigDecimal("0.00004861111");
		  usageInSeconds = new BigDecimal(tab1Usage.getText());
		  BigDecimal value = instanceType.multiply(usageInSeconds, mc);
		  numOfvCPU.setText("2");
		  amountOfStorage.setText("EBS Only");
		  amountOfMem.setText("7.5");
		  totalCost.setText(String.valueOf(value));
		  perDayCost.setText(String.valueOf(4.200000));
		break; }
		case "m1.xlarge" : {
		instanceType = new BigDecimal("0.00009722222");
		usageInSeconds = new BigDecimal(tab1Usage.getText());
		BigDecimal value = instanceType.multiply(usageInSeconds, mc);
		numOfvCPU.setText("4");
		amountOfStorage.setText("EBS Only");
		amountOfMem.setText("15");
		totalCost.setText(String.valueOf(value));
		perDayCost.setText(String.valueOf(8.400000));
		break; }
		  case "t1.micro" : {
		instanceType = new BigDecimal("0.00000555555");
		 usageInSeconds = new BigDecimal(tab1Usage.getText());
		 BigDecimal value = instanceType.multiply(usageInSeconds, mc);
		 totalCost.setText(String.valueOf(value));
		 perDayCost.setText(String.valueOf(0.480000));
		           break; }
		           case "t2.nano" : {
		           instanceType = new BigDecimal("0.00000161111");
		   usageInSeconds = new BigDecimal(tab1Usage.getText());
		   BigDecimal value = instanceType.multiply(usageInSeconds, mc);
		   numOfvCPU.setText("1");
		   amountOfStorage.setText("EBS Only");
		   amountOfMem.setText("0.5");
		   totalCost.setText(String.valueOf(value));
		   perDayCost.setText(String.valueOf(0.139200));
		                    break; }
		  case "t2.micro" : {
		  instanceType = new BigDecimal("0.00000322222");
		  usageInSeconds = new BigDecimal(tab1Usage.getText());
		  BigDecimal value = instanceType.multiply(usageInSeconds, mc);
		  numOfvCPU.setText("1");
		  amountOfStorage.setText("EBS Only");
		  amountOfMem.setText("1");
		  totalCost.setText(String.valueOf(value));
		  perDayCost.setText(String.valueOf(0.278400));
		  break; }
		  case "t2.small" : {
		  instanceType = new BigDecimal("0.00000638888");
		     usageInSeconds = new BigDecimal(tab1Usage.getText());
		     numOfvCPU.setText("1");
		     amountOfStorage.setText("EBS Only");
		     amountOfMem.setText("2");
		     BigDecimal value = instanceType.multiply(usageInSeconds, mc);
		     totalCost.setText(String.valueOf(value));
		     perDayCost.setText(String.valueOf(0.552000));
		  break; }
		  case "t2.medium" : {
		  instanceType = new BigDecimal("0.00001288888");
		    usageInSeconds = new BigDecimal(tab1Usage.getText());
		    BigDecimal value = instanceType.multiply(usageInSeconds, mc);
		    numOfvCPU.setText("2");
		    amountOfStorage.setText("EBS Only");
		    amountOfMem.setText("4");
		    totalCost.setText(String.valueOf(value));
		    perDayCost.setText(String.valueOf(1.113600));
		  break; }
		  case "t2.large" : {
		  instanceType = new BigDecimal("0.00002577777");
		      usageInSeconds = new BigDecimal(tab1Usage.getText());
		      BigDecimal value = instanceType.multiply(usageInSeconds, mc);
		      numOfvCPU.setText("2");
		      amountOfStorage.setText("EBS Only");
		      amountOfMem.setText("8");
		      totalCost.setText(String.valueOf(value));
		      perDayCost.setText(String.valueOf(2.227200));
		  break; }
		  case "t2.xlarge" : {
		  instanceType = new BigDecimal("0.00005155555");
		   usageInSeconds = new BigDecimal(tab1Usage.getText());
		   BigDecimal value = instanceType.multiply(usageInSeconds, mc);
		   numOfvCPU.setText("2");
		   amountOfStorage.setText("EBS Only");
		   amountOfMem.setText("16");
		   totalCost.setText(String.valueOf(value));
		   perDayCost.setText(String.valueOf(4.454400));
		  break; }
		  case "t2.2xlarge" : {
		  instanceType = new BigDecimal("0.00010311111");
		   usageInSeconds = new BigDecimal(tab1Usage.getText());
		   BigDecimal value = instanceType.multiply(usageInSeconds, mc);
		   numOfvCPU.setText("8");
		   amountOfStorage.setText("EBS Only");
		   amountOfMem.setText("32");
		   totalCost.setText(String.valueOf(value));
		   perDayCost.setText(String.valueOf(8.908800));
		  break; }
		  case "m4.large" : {
		  instanceType = new BigDecimal("0.00002777777");
		    usageInSeconds = new BigDecimal(tab1Usage.getText());
		    BigDecimal value = instanceType.multiply(usageInSeconds, mc);
		    numOfvCPU.setText("2");
		    amountOfStorage.setText("EBS Only");
		    amountOfMem.setText("8");
		    totalCost.setText(String.valueOf(value));
		    perDayCost.setText(String.valueOf(2.400000));
		  break; }
		  case "m4.xlarge" : {
		  instanceType = new BigDecimal("0.00005555555");
		   usageInSeconds = new BigDecimal(tab1Usage.getText());
		   BigDecimal value = instanceType.multiply(usageInSeconds, mc);
		   numOfvCPU.setText("4");
		   amountOfStorage.setText("EBS Only");
		   amountOfMem.setText("16");
		   totalCost.setText(String.valueOf(value));
		   perDayCost.setText(String.valueOf(9.600000)); 
		  break; }
		  case "c4.large" : {
		  instanceType = new BigDecimal("0.00002777777");
		    usageInSeconds = new BigDecimal(tab1Usage.getText());
		    BigDecimal value = instanceType.multiply(usageInSeconds, mc);
		    numOfvCPU.setText("2");
		    amountOfStorage.setText("EBS Only");
		    amountOfMem.setText("3.75");
		    totalCost.setText(String.valueOf(value));
		    perDayCost.setText(String.valueOf(2.400000));
		  break; }
		  case "c4.xlarge" : {
		  instanceType = new BigDecimal("0.00005527777");
		    usageInSeconds = new BigDecimal(tab1Usage.getText());
		    BigDecimal value = instanceType.multiply(usageInSeconds, mc);
		    numOfvCPU.setText("4");
		    amountOfStorage.setText("EBS Only");
		    amountOfMem.setText("7.5");
		    totalCost.setText(String.valueOf(value));
		    perDayCost.setText(String.valueOf(4.776000));
		  break; }
		  case "c4.2xlarge" : {
		  instanceType = new BigDecimal("0.00011055555");
		  usageInSeconds = new BigDecimal(tab1Usage.getText());
		  BigDecimal value = instanceType.multiply(usageInSeconds, mc);
		  numOfvCPU.setText("8");
		  amountOfStorage.setText("EBS Only");
		  amountOfMem.setText("15");
		  totalCost.setText(String.valueOf(value));
		  perDayCost.setText(String.valueOf(9.552000));
		  break; }
		  case "c4.4xlarge" : {
		  instanceType = new BigDecimal("0.00022111111");
		   usageInSeconds = new BigDecimal(tab1Usage.getText());
		   BigDecimal value = instanceType.multiply(usageInSeconds, mc);
		   numOfvCPU.setText("16");
		   amountOfStorage.setText("EBS Only");
		   amountOfMem.setText("30");
		   totalCost.setText(String.valueOf(value));
		   perDayCost.setText(String.valueOf(19.104000));
		  break; }
		  case "c4.8xlarge" : {
		  instanceType = new BigDecimal("0.00044194444");
		   usageInSeconds = new BigDecimal(tab1Usage.getText());
		   BigDecimal value = instanceType.multiply(usageInSeconds, mc);
		   numOfvCPU.setText("36");
		   amountOfStorage.setText("EBS Only");
		   amountOfMem.setText("60");
		   totalCost.setText(String.valueOf(value));
		   perDayCost.setText(String.valueOf(38.184000));
		  break; }
		  case "r4.large" : {
		  instanceType = new BigDecimal("0.00003694444");
		    usageInSeconds = new BigDecimal(tab1Usage.getText());
		    BigDecimal value = instanceType.multiply(usageInSeconds, mc);
		    numOfvCPU.setText("2");
		    amountOfStorage.setText("EBS Only");
		    amountOfMem.setText("15.25");
		    totalCost.setText(String.valueOf(value));
		    perDayCost.setText(String.valueOf(3.192000));
		  break; }
		  case "r4.xlarge" : {
		  instanceType = new BigDecimal("0.00007388888");
		   usageInSeconds = new BigDecimal(tab1Usage.getText());
		   BigDecimal value = instanceType.multiply(usageInSeconds, mc);
		   numOfvCPU.setText("4");
		   amountOfStorage.setText("EBS Only");
		   amountOfMem.setText("30.5");
		   totalCost.setText(String.valueOf(value));
		   perDayCost.setText(String.valueOf(6.384000));
		  break; }
		  case "r4.2xlarge" : {
		  instanceType = new BigDecimal("0.00014777777");
		  usageInSeconds = new BigDecimal(tab1Usage.getText());
		  BigDecimal value = instanceType.multiply(usageInSeconds, mc);
		  numOfvCPU.setText("8");
		  amountOfStorage.setText("EBS Only");
		  amountOfMem.setText("61");
		  totalCost.setText(String.valueOf(value));
		  perDayCost.setText(String.valueOf(12.768000));
		  break; }
		  case "r4.4xlarge" : {
		  instanceType = new BigDecimal("0.00029555555");
		    usageInSeconds = new BigDecimal(tab1Usage.getText());
		    BigDecimal value = instanceType.multiply(usageInSeconds, mc);
		    numOfvCPU.setText("16");
		    amountOfStorage.setText("EBS Only");
		    amountOfMem.setText("122");
		    totalCost.setText(String.valueOf(value));
		    perDayCost.setText(String.valueOf(25.536000));
		  break; }
		  case "r4.8xlarge" : {
		  instanceType = new BigDecimal("0.00059111111");
		    usageInSeconds = new BigDecimal(tab1Usage.getText());
		    BigDecimal value = instanceType.multiply(usageInSeconds, mc);
		    numOfvCPU.setText("64");
		    amountOfStorage.setText("EBS Only");
		    amountOfMem.setText("488");
		    totalCost.setText(String.valueOf(value));
		    perDayCost.setText(String.valueOf(51.072000));
		  break; }
		  case "r4.16xlarge" : {
		  // BEGIN INSTANCE TYPE COST 
		    instanceType = new BigDecimal("0.00118222222");
		    // END INSTANCE TYPE COST
		    usageInSeconds = new BigDecimal(tab1Usage.getText());
		    BigDecimal value = instanceType.multiply(usageInSeconds, mc);
		    totalCost.setText(String.valueOf(value));
		    perDayCost.setText(String.valueOf(102.144000));
		  break; }
		        default :
		     }
		  }
	// begin method to calculate the cost of Windows instances
	public void calculateWindowsCost() {
		String selectedInstanceType = (String) ec2SelectWin.getValue();

		switch(selectedInstanceType) {
		case "m1.small" :{
		// BEGIN INSTANCE TYPE COST 
						instanceType = new BigDecimal("0.00002083333");
						// END INSTANCE TYPE COST
						usageInSeconds = new BigDecimal(tab2Usage.getText());
						BigDecimal value = instanceType.multiply(usageInSeconds, mc);
						numOfvCPUWin.setText("1");
						amountOfStorageWin.setText("160 GiB HDD + 900MB swap");
						amountOfMemWin.setText("1.7");
						totalCostWin.setText(String.valueOf(value));
						perDayCostWin.setText(String.valueOf(1.800));
		break; }
		case "m1.medium" : {
		// BEGIN INSTANCE TYPE COST 
						instanceType = new BigDecimal("0.00004138888");
						// END INSTANCE TYPE COST
						usageInSeconds = new BigDecimal(tab2Usage.getText());
						BigDecimal value = instanceType.multiply(usageInSeconds, mc);
						numOfvCPUWin.setText("1");
						amountOfStorageWin.setText("410 GiB HDD");
						amountOfMemWin.setText("3.75");
						totalCostWin.setText(String.valueOf(value));
						perDayCostWin.setText(String.valueOf(3.576000));
		break; }
		case "m1.large" : {
		// BEGIN INSTANCE TYPE COST 
						instanceType = new BigDecimal("0.00008305555");
						// END INSTANCE TYPE COST
						usageInSeconds = new BigDecimal(tab2Usage.getText());
						BigDecimal value = instanceType.multiply(usageInSeconds, mc);
						  numOfvCPUWin.setText("2");
						  amountOfStorageWin.setText("840 GiB (2 * 420 GiB HDD)");
						  amountOfMemWin.setText("7.5");
						totalCostWin.setText(String.valueOf(value));
						perDayCostWin.setText(String.valueOf(7.176000));
		break; }
		case "m1.xlarge" : {
		// BEGIN INSTANCE TYPE COST 
						instanceType = new BigDecimal("0.00016611111");
						// END INSTANCE TYPE COST
						usageInSeconds = new BigDecimal(tab2Usage.getText());
						BigDecimal value = instanceType.multiply(usageInSeconds, mc);
						numOfvCPUWin.setText("4");
					    amountOfStorageWin.setText("1680 GiB (4 * 420 GiB HDD)");
					    amountOfMemWin.setText("15");
						totalCostWin.setText(String.valueOf(value));
						perDayCostWin.setText(String.valueOf(14.35200));
		break; }
		  case "t1.micro" : {
		  // BEGIN INSTANCE TYPE COST 
						instanceType = new BigDecimal("0.00000555555");
						// END INSTANCE TYPE COST
						usageInSeconds = new BigDecimal(tab2Usage.getText());
						BigDecimal value = instanceType.multiply(usageInSeconds, mc);
						numOfvCPUWin.setText("1");
					    amountOfStorageWin.setText("EBS Only");
					    amountOfMemWin.setText("0.613");
						totalCostWin.setText(String.valueOf(value));
						perDayCostWin.setText(String.valueOf(0.480000));
		  break; }
		           case "t2.nano" : {
		           // BEGIN INSTANCE TYPE COST 
						instanceType = new BigDecimal("0.00000225");
						// END INSTANCE TYPE COST
						usageInSeconds = new BigDecimal(tab2Usage.getText());
						BigDecimal value = instanceType.multiply(usageInSeconds, mc);
						numOfvCPUWin.setText("1");
					    amountOfStorageWin.setText("EBS Only");
					    amountOfMemWin.setText("0.5");
						totalCostWin.setText(String.valueOf(value));
						perDayCostWin.setText(String.valueOf(0.194400));
		                    break; }
		  case "t2.micro" : {
		  // BEGIN INSTANCE TYPE COST 
		  				instanceType = new BigDecimal("0.0000045");
		  				// END INSTANCE TYPE COST
		  				usageInSeconds = new BigDecimal(tab2Usage.getText());
		  				BigDecimal value = instanceType.multiply(usageInSeconds, mc);
		  			    numOfvCPUWin.setText("1");
		  			    amountOfStorageWin.setText("EBS Only");
		  			    amountOfMemWin.setText("1");
		  				totalCostWin.setText(String.valueOf(value));
		  				perDayCostWin.setText(String.valueOf(0.388800));
		  break; }
		  case "t2.small" : {
		  // BEGIN INSTANCE TYPE COST 
						instanceType = new BigDecimal("0.00000888888");
						// END INSTANCE TYPE COST
						usageInSeconds = new BigDecimal(tab2Usage.getText());
						BigDecimal value = instanceType.multiply(usageInSeconds, mc);
					    numOfvCPUWin.setText("1");
					    amountOfStorageWin.setText("EBS Only");
					    amountOfMemWin.setText("2");
						totalCostWin.setText(String.valueOf(value));
						perDayCostWin.setText(String.valueOf(0.768000));
		  break; }
		  case "t2.medium" : {
		  // BEGIN INSTANCE TYPE COST 
						instanceType = new BigDecimal("0.00001788888");
						// END INSTANCE TYPE COST
						usageInSeconds = new BigDecimal(tab2Usage.getText());
						BigDecimal value = instanceType.multiply(usageInSeconds, mc);
					    numOfvCPUWin.setText("2");
					    amountOfStorageWin.setText("EBS Only");
					    amountOfMemWin.setText("4");
						totalCostWin.setText(String.valueOf(value));
						perDayCostWin.setText(String.valueOf(1.545600));
		  break; }
		  case "t2.large" : {
		  // BEGIN INSTANCE TYPE COST 
						instanceType = new BigDecimal("0.00003355555");
						// END INSTANCE TYPE COST
						usageInSeconds = new BigDecimal(tab2Usage.getText());
						BigDecimal value = instanceType.multiply(usageInSeconds, mc);
						numOfvCPUWin.setText("2");
					    amountOfStorageWin.setText("EBS Only");
					    amountOfMemWin.setText("8");
						totalCostWin.setText(String.valueOf(value));
						perDayCostWin.setText(String.valueOf(2.899200));     
		  break; }
		  case "t2.xlarge" : {
		  // BEGIN INSTANCE TYPE COST 
						instanceType = new BigDecimal("0.00006294444");
						// END INSTANCE TYPE COST
						usageInSeconds = new BigDecimal(tab2Usage.getText());
						BigDecimal value = instanceType.multiply(usageInSeconds, mc);
						 numOfvCPUWin.setText("2");
						 amountOfStorageWin.setText("EBS Only");
						 amountOfMemWin.setText("16");
						totalCostWin.setText(String.valueOf(value));
						perDayCostWin.setText(String.valueOf(5.438400));
		  break; }
		  case "t2.2xlarge" : {
		  // BEGIN INSTANCE TYPE COST 
		  				instanceType = new BigDecimal("0.00012033333");
		  				// END INSTANCE TYPE COST
		  				usageInSeconds = new BigDecimal(tab2Usage.getText());
		  				BigDecimal value = instanceType.multiply(usageInSeconds, mc);
		  				 numOfvCPUWin.setText("8");
		  				 amountOfStorageWin.setText("EBS Only");
		  				 amountOfMemWin.setText("32");
		  				totalCostWin.setText(String.valueOf(value));
		  				perDayCostWin.setText(String.valueOf(10.396800));
		  break; }
		  case "m4.large" : {
		  // BEGIN INSTANCE TYPE COST 
						instanceType = new BigDecimal("0.00005333333");
						// END INSTANCE TYPE COST
						usageInSeconds = new BigDecimal(tab2Usage.getText());
						BigDecimal value = instanceType.multiply(usageInSeconds, mc);
						numOfvCPUWin.setText("2");
					    amountOfStorageWin.setText("EBS Only");
					    amountOfMemWin.setText("8");
						totalCostWin.setText(String.valueOf(value));
						perDayCostWin.setText(String.valueOf(4.608000));
		  break; }
		  case "m4.xlarge" : {
		  // BEGIN INSTANCE TYPE COST 
						instanceType = new BigDecimal("0.00010666666");
						// END INSTANCE TYPE COST
						usageInSeconds = new BigDecimal(tab2Usage.getText());
						BigDecimal value = instanceType.multiply(usageInSeconds, mc);
						numOfvCPUWin.setText("4");
						amountOfStorageWin.setText("EBS Only");
						amountOfMemWin.setText("16");
						totalCostWin.setText(String.valueOf(value));
						perDayCostWin.setText(String.valueOf(9.216000));
		  break; }
		  case "c4.large" : {
		  // BEGIN INSTANCE TYPE COST 
						instanceType = new BigDecimal("0.00005333333");
						// END INSTANCE TYPE COST
						usageInSeconds = new BigDecimal(tab2Usage.getText());
						BigDecimal value = instanceType.multiply(usageInSeconds, mc);
						numOfvCPUWin.setText("2");
					    amountOfStorageWin.setText("EBS Only");
					    amountOfMemWin.setText("3.75");
						totalCostWin.setText(String.valueOf(value));
						perDayCostWin.setText(String.valueOf(4.608));
		  break; }
		  case "c4.xlarge" : {
		  // BEGIN INSTANCE TYPE COST 
						instanceType = new BigDecimal("0.00010638888");
						// END INSTANCE TYPE COST
						usageInSeconds = new BigDecimal(tab2Usage.getText());
						BigDecimal value = instanceType.multiply(usageInSeconds, mc);
						  numOfvCPUWin.setText("4");
						    amountOfStorageWin.setText("EBS Only");
						    amountOfMemWin.setText("7.5");
						totalCostWin.setText(String.valueOf(value));
						perDayCostWin.setText(String.valueOf(9.192));
		  break; }
		  case "c4.2xlarge" : {
		  // BEGIN INSTANCE TYPE COST 
						instanceType = new BigDecimal("0.00021277777");
						// END INSTANCE TYPE COST
						usageInSeconds = new BigDecimal(tab2Usage.getText());
						BigDecimal value = instanceType.multiply(usageInSeconds, mc);
						numOfvCPUWin.setText("8");
					    amountOfStorageWin.setText("EBS Only");
					    amountOfMemWin.setText("15");
						totalCostWin.setText(String.valueOf(value));
						perDayCostWin.setText(String.valueOf(18.38));
		  break; }
		  case "c4.4xlarge" : {
		  // BEGIN INSTANCE TYPE COST 
		      instanceType = new BigDecimal("0.00042555555");
		      // END INSTANCE TYPE COST
		      usageInSeconds = new BigDecimal(tab2Usage.getText());
		      BigDecimal value = instanceType.multiply(usageInSeconds, mc);
		      numOfvCPUWin.setText("16");
		        amountOfStorageWin.setText("EBS Only");
		        amountOfMemWin.setText("30");
		      totalCostWin.setText(String.valueOf(value));
		      perDayCostWin.setText(String.valueOf(36.77));
		  break; }
		  case "c4.8xlarge" : {
		  // BEGIN INSTANCE TYPE COST 
						instanceType = new BigDecimal("0.00085861111");
						// END INSTANCE TYPE COST
						usageInSeconds = new BigDecimal(tab2Usage.getText());
						BigDecimal value = instanceType.multiply(usageInSeconds, mc);
						numOfvCPUWin.setText("36");
					    amountOfStorageWin.setText("EBS Only");
					    amountOfMemWin.setText("60");
						totalCostWin.setText(String.valueOf(value));
						perDayCostWin.setText(String.valueOf(74.18));
		  break; }
		  case "r4.large" : {
		  // BEGIN INSTANCE TYPE COST 
						instanceType = new BigDecimal("0.0000625");
						// END INSTANCE TYPE COST
						usageInSeconds = new BigDecimal(tab2Usage.getText());
						BigDecimal value = instanceType.multiply(usageInSeconds, mc);
						numOfvCPUWin.setText("2");
						amountOfStorageWin.setText("EBS Only");
						amountOfMemWin.setText("15.25");
						totalCostWin.setText(String.valueOf(value));
						perDayCostWin.setText(String.valueOf(5.400000));
		  break; }
		  case "r4.xlarge" : {
		  // BEGIN INSTANCE TYPE COST 
						instanceType = new BigDecimal("0.000125");
						// END INSTANCE TYPE COST
						usageInSeconds = new BigDecimal(tab2Usage.getText());
						BigDecimal value = instanceType.multiply(usageInSeconds, mc);
						numOfvCPUWin.setText("4");
					    amountOfStorageWin.setText("EBS Only");
					    amountOfMemWin.setText("30.5");
						totalCostWin.setText(String.valueOf(value));
						perDayCostWin.setText(String.valueOf(10.800000));
		  break; }
		  case "r4.2xlarge" : {
		  // BEGIN INSTANCE TYPE COST 
						instanceType = new BigDecimal("0.00025");
						// END INSTANCE TYPE COST
						usageInSeconds = new BigDecimal(tab2Usage.getText());
						BigDecimal value = instanceType.multiply(usageInSeconds, mc);
						numOfvCPUWin.setText("8");
					    amountOfStorageWin.setText("EBS Only");
					    amountOfMemWin.setText("61");
						totalCostWin.setText(String.valueOf(value));
						perDayCostWin.setText(String.valueOf(21.600000));
		  break; }
		  case "r4.4xlarge" : {
		  // BEGIN INSTANCE TYPE COST 
						instanceType = new BigDecimal("0.0005");
						// END INSTANCE TYPE COST
						usageInSeconds = new BigDecimal(tab2Usage.getText());
						BigDecimal value = instanceType.multiply(usageInSeconds, mc);
						numOfvCPUWin.setText("16");
					    amountOfStorageWin.setText("EBS Only");
					    amountOfMemWin.setText("122");
						totalCostWin.setText(String.valueOf(value));
						perDayCostWin.setText(String.valueOf(43.200000));
		  break; }
		  case "r4.8xlarge" : {
		  // BEGIN INSTANCE TYPE COST 
						instanceType = new BigDecimal("0.001");
						// END INSTANCE TYPE COST
						usageInSeconds = new BigDecimal(tab2Usage.getText());
						BigDecimal value = instanceType.multiply(usageInSeconds, mc);
						numOfvCPUWin.setText("32");
						amountOfStorageWin.setText("EBS Only");
						amountOfMemWin.setText("244");
						totalCostWin.setText(String.valueOf(value));
						perDayCostWin.setText(String.valueOf(86.400000));
		  break; }
		  case "r4.16xlarge" : {
		  // BEGIN INSTANCE TYPE COST 
						instanceType = new BigDecimal("0.002");
						// END INSTANCE TYPE COST
						usageInSeconds = new BigDecimal(tab2Usage.getText());
						BigDecimal value = instanceType.multiply(usageInSeconds, mc);
						  numOfvCPUWin.setText("64");
						    amountOfStorageWin.setText("EBS Only");
						    amountOfMemWin.setText("488");
						totalCostWin.setText(String.valueOf(value));
						perDayCostWin.setText(String.valueOf(172.800000));
		  break; }
		        default :
		     }
		  }	// end of calculateWindows Method 
	public void calculateEBSCost() {	
	// Gets the Selected Radio Button
		selectedRadio = (RadioButton)tabThreeGroup.getSelectedToggle();
		
		if (selectedRadio.getText().equals("EBS General Purpose SSD (gp2)")) {
			ebsType = new BigDecimal("0.000000038580246913580246913580246913580246913580246913580246");
			thirtyDaysInSeconds = new BigDecimal("2592000");
			numOfGigs = new BigDecimal(storageAmount.getText());
	        usageInSeconds = new BigDecimal(tab3Usage.getText());			
		// Number of Gigs * Volume Price 
			BigDecimal gigsMultipliedByType = ebsType.multiply(numOfGigs, mc);
			// (Number of Gigs * Volume Price)  * usage 
			BigDecimal value = gigsMultipliedByType.multiply(usageInSeconds, mc);	
				//	BigDecimal value = gigsMultipliedByType.usageInSeconds(usageInSeconds, mc);
			totalCostEBS.setText(value.toPlainString());			
		} else if (selectedRadio.getText().equals("EBS Throughput Optimized HDD (st1)")) {
			ebsType = new BigDecimal("0.000000017361111111111111111111111111111111111111111111111111");
			thirtyDaysInSeconds = new BigDecimal("2592000");
			numOfGigs = new BigDecimal(storageAmount.getText());
	        usageInSeconds = new BigDecimal(tab3Usage.getText());			
		// Number of Gigs * Volume Price 
			BigDecimal gigsMultipliedByType = ebsType.multiply(numOfGigs, mc);
			// (Number of Gigs * Volume Price)  * usage 
			BigDecimal value = gigsMultipliedByType.multiply(usageInSeconds, mc);	
				//	BigDecimal value = gigsMultipliedByType.usageInSeconds(usageInSeconds, mc);
			totalCostEBS.setText(value.toPlainString());
		} else if (selectedRadio.getText().equals("EBS Cold HDD (sc1)")) {
			ebsType = new BigDecimal("0.0000000096450617283950617283950617283950617283950617283950617");
			thirtyDaysInSeconds = new BigDecimal("2592000");
			numOfGigs = new BigDecimal(storageAmount.getText());
	        usageInSeconds = new BigDecimal(tab3Usage.getText());			
		// Number of Gigs * Volume Price 
			BigDecimal gigsMultipliedByType = ebsType.multiply(numOfGigs, mc);
			// (Number of Gigs * Volume Price)  * usage 
			BigDecimal value = gigsMultipliedByType.multiply(usageInSeconds, mc);	
				//	BigDecimal value = gigsMultipliedByType.usageInSeconds(usageInSeconds, mc);
			totalCostEBS.setText(value.toPlainString());
		} else if (selectedRadio.getText().equals("EBS Amazon S3 Snapshots")) {
			ebsType = new BigDecimal("0.000000019290123456790123456790123456790123456790123456790123");
			thirtyDaysInSeconds = new BigDecimal("2592000");
			numOfGigs = new BigDecimal(storageAmount.getText());
	        usageInSeconds = new BigDecimal(tab3Usage.getText());			
		// Number of Gigs * Volume Price 
			BigDecimal gigsMultipliedByType = ebsType.multiply(numOfGigs, mc);
			// (Number of Gigs * Volume Price)  * usage 
			BigDecimal value = gigsMultipliedByType.multiply(usageInSeconds, mc);	
				//	BigDecimal value = gigsMultipliedByType.usageInSeconds(usageInSeconds, mc);
			totalCostEBS.setText(value.toPlainString());
		} else if (selectedRadio.getText().equals("EBS Provisioned IOPS SSD (io1)")) {
			// allows user to enter IOPS information 
			iopsProvision.setVisible(true);
			iopsProvisionInput.setVisible(true);
			ebsType = new BigDecimal("0.000000048225308641975308641975308641975308641975308641975308");
			thirtyDaysInSeconds = new BigDecimal("2592000");
			numOfGigs = new BigDecimal(storageAmount.getText());
	        usageInSeconds = new BigDecimal(tab3Usage.getText());		
	        numOfIOPS = new BigDecimal(iopsProvisionInput.getText());   
	        //iops cost per second
	        iopsCost = new BigDecimal("0.000000025077160493827160493827160493827160493827160493827160");
	        // iops cost * IOPSProvision
	        BigDecimal iopsMultipliedByCost = iopsCost.multiply(numOfIOPS, mc);	        
	        //iops usage
	        BigDecimal iopsValue = iopsMultipliedByCost.multiply(usageInSeconds, mc);	        
		// Number of Gigs * Volume Price 
			BigDecimal gigsMultipliedByType = ebsType.multiply(numOfGigs, mc);
			// (Number of Gigs * Volume Price)  * usage 
			BigDecimal value = gigsMultipliedByType.multiply(usageInSeconds, mc);				
			BigDecimal combinedValue = iopsValue.add(value);			
				//	BigDecimal value = gigsMultipliedByType.usageInSeconds(usageInSeconds, mc);
			totalCostEBS.setText(combinedValue.toPlainString());
			individualIOPSField.setText(iopsValue.toPlainString());		
		}
	}
	// clears the textfield so user can perform next calculation
public void clearAll() {
	storageAmount.setText("");
	tab3Usage.setText("");
	totalCostEBS.setText("");
	iopsProvisionInput.setText("");
	individualIOPSField.setText("");
	tab2Usage.setText("");
	tab1Usage.setText("");
	perDayCost.setText("");
	numOfvCPU.setText("");
	cumalativeCostLinux.setText("");
	cumalativeCostWindows.setText("");
	amountOfMem.setText("");
	amountOfStorage.setText("");
	perDayCostWin.setText("");
	numOfvCPUWin.setText("");
	amountOfMemWin.setText("");
	amountOfStorageWin.setText("");
	totalCost.setText("");
	totalCostWin.setText("");
	cumulativeCostEBS.setText("");
	successExport.setVisible(false);	
	successExportWin.setVisible(false);	
	successExportEBS.setVisible(false);
	
	accumulation = 0.0;
}

public void linuxCheckIfNumerical() {	
	
	String input = String.valueOf(tab1Usage.getText());
	
	if (input.matches("[0-9]+") && input.length() > 0) {
		warningMessage.setVisible(false);
		successExport.setVisible(false);
		this.calculateLinuxCost();
	} else {
		warningMessage.setVisible(true);
		successExport.setVisible(false);
	}
}
public void windowsCheckIfNumerical() {	
	String input = String.valueOf(tab2Usage.getText());
	
	if (input.matches("[0-9]+") && input.length() > 0) {
		warningMessageWindows.setVisible(false);
	    successExportWin.setVisible(false);
		this.calculateWindowsCost();
	} else {
		warningMessageWindows.setVisible(true);
	    successExportWin.setVisible(false);
	}
}
public void EBSCheckIfNumerical() {	
	String inputUsage = String.valueOf(tab3Usage.getText());
	String inputStorage = String.valueOf(storageAmount.getText());
	String inputIOPS = String.valueOf(iopsProvisionInput.getText());
    successExportEBS.setVisible(false);

 selectedRadio = (RadioButton)tabThreeGroup.getSelectedToggle();
 
	if ((selectedRadio.getText().equals("EBS Provisioned IOPS SSD (io1)")) && inputUsage.matches("[0-9]+") && inputUsage.length() > 0 && inputStorage.matches("[0-9]+") && inputStorage.length() > 0 && inputIOPS.matches("[0-9]+") && inputIOPS.length() > 0){
		warningMessageEBS.setVisible(false);	
		this.calculateEBSCost();
	} else if(!(selectedRadio.getText().equals("EBS Provisioned IOPS SSD (io1)")) && inputUsage.matches("[0-9]+") && inputUsage.length() > 0 && inputStorage.matches("[0-9]+") && inputStorage.length() > 0) {
		warningMessageEBS.setVisible(false);
		this.calculateEBSCost();
	} else {
		warningMessageEBS.setVisible(true);
	}
}

public void addToTableLinux() {
	String selectedInstanceType = (String) ec2Select.getValue();
    
	accumulation += Double.parseDouble(totalCost.getText());
	
	//debugging
	// System.out.println("First value of" + accumulation);
	Instance instance = new Instance();
	instance.setName(selectedInstanceType);
	instance.setUsage(tab1Usage.getText());
	instance.setVCPUs(numOfvCPU.getText());
	instance.setMemory(amountOfMem.getText());
	instance.setStorage(amountOfStorage.getText());
	instance.setRunningCost(perDayCost.getText());
	instance.setTotalCost(totalCost.getText());
	tableView.getItems().add(instance);
	cumalativeCostLinux.setText(Double.toString(accumulation));
//	System.out.println("Second value of Accy" + accumulation);
	
	tab1Usage.clear();
	numOfvCPU.clear();
	amountOfMem.clear();
	amountOfStorage.clear();
	perDayCost.clear();
	totalCost.clear();
}

public void deleteSelected() {
    ObservableList<Instance> selectedRows, allInstances;
    double numToSubtract = 0.0;
    allInstances = tableView.getItems();
    
    //this gives us the rows that were selected
    selectedRows = tableView.getSelectionModel().getSelectedItems();
   
    numToSubtract = Double.parseDouble(tableView.getSelectionModel().getSelectedItem().getTotalCost());
    
    //loop over the selected rows and remove the Instance objects from the table
    for (Instance instance: selectedRows)
    {
        allInstances.remove(instance);
        Double updatedCost = accumulation - numToSubtract;
        cumalativeCostLinux.setText(Double.toString(updatedCost));
        
    }
}
public void addToTableWindows() {
	String selectedInstanceType = (String) ec2SelectWin.getValue();
    
	accumulation += Double.parseDouble(totalCostWin.getText());
	
	//debugging
	// System.out.println("First value of" + accumulation);
	
	Instance instance = new Instance();
	instance.setName(selectedInstanceType);
	instance.setUsage(tab2Usage.getText());
	instance.setVCPUs(numOfvCPUWin.getText());
	instance.setMemory(amountOfMemWin.getText());
	instance.setStorage(amountOfStorageWin.getText());
	instance.setRunningCost(perDayCostWin.getText());
	instance.setTotalCost(totalCostWin.getText());
	tableViewWin.getItems().add(instance);
	cumalativeCostWindows.setText(Double.toString(accumulation));
	System.out.println("Second value of Accy" + accumulation);
	
	tab1Usage.clear();
	numOfvCPU.clear();
	amountOfMem.clear();
	amountOfStorage.clear();
	perDayCost.clear();
	totalCost.clear();
}
public void deleteSelectedWindows() {
    ObservableList<Instance> selectedRows, allInstances;
    double numToSubtract = 0.0;
    allInstances = tableViewWin.getItems();
    
    //this gives us the rows that were selected
    selectedRows = tableViewWin.getSelectionModel().getSelectedItems();
   
    numToSubtract = Double.parseDouble(tableViewWin.getSelectionModel().getSelectedItem().getTotalCost());
    
    
    //loop over the selected rows and remove the Instance objects from the table
    for (Instance instance: selectedRows)
    {
        allInstances.remove(instance);
        Double updatedCost = accumulation - numToSubtract;
        cumalativeCostWindows.setText(Double.toString(updatedCost));
        
    }
}
public void addToTableEBS() {
	selectedRadio = (RadioButton)tabThreeGroup.getSelectedToggle();
	  
	accumulation += Double.parseDouble(totalCostEBS.getText());
	
/*	//debugging
	System.out.println("First value of" + accumulation);*/
	
	ElasticBlockStore EBS = new ElasticBlockStore();
	EBS.setName(selectedRadio.getText());
	EBS.setStorage(storageAmount.getText());
	EBS.setUsage(tab3Usage.getText());
	EBS.setIopsProvision(iopsProvisionInput.getText());
	EBS.setIndividualIOPS(individualIOPSField.getText());
	EBS.setTotalCost(totalCostEBS.getText());
	tableViewEBS.getItems().add(EBS);
	cumulativeCostEBS.setText(Double.toString(accumulation));
	
	storageAmount.clear();
	tab3Usage.clear();
	totalCostEBS.clear();
	iopsProvisionInput.clear();
	individualIOPSField.clear();
}
public void deleteSelectedEBS() {
    ObservableList<ElasticBlockStore> selectedRows, allElasticBlockStores;
    double numToSubtract = 0.0;
    allElasticBlockStores = tableViewEBS.getItems();
    
    //this gives us the rows that were selected
    selectedRows = tableViewEBS.getSelectionModel().getSelectedItems();
   
    numToSubtract = Double.parseDouble(tableViewEBS.getSelectionModel().getSelectedItem().getTotalCost());
    
    
    //loop over the selected rows and remove the Instance objects from the table
    for (ElasticBlockStore ElasticBlockStore: selectedRows)
    {
    	
    	if (!selectedRows.isEmpty()) {
        allElasticBlockStores.remove(ElasticBlockStore);
        Double updatedCost = accumulation - numToSubtract;
        cumulativeCostEBS.setText(Double.toString(updatedCost));
    	} else {
    		cumulativeCostEBS.setText("");
    	}
        
    }
}
public void writeExcel() throws Exception {
	List<Instance> allInstances = tableView.getItems();
	 File file = new File(dateFormat.format(date) + "Linux_Instances" + count + ".csv");
	 count++;
	  
     FileWriter fw = new FileWriter(file);
     BufferedWriter bw = new BufferedWriter(fw);
      
	try {
        
        String columnHeader = "Instance Name" + "," + "Usage in Seconds" + "," + 
		        "vCPUs" + ","  + "Storage" + ","  + "Memory(GiB)" + "," + "Daily Running Cost ($)" 
		        + "," + "Total Cost ($)" + "\n";
        
        bw.write(columnHeader);
        
        for (Instance instance : allInstances) {
    
	        String text = instance.getName() + "," + instance.getUsage() + "," + instance.getVCPUs() + "," + instance.getStorage() + "," + instance.getMemory() + "," + instance.getRunningCost() + "," + instance.getTotalCost() + "\n";

	        bw.write(text);
        }
    } catch (Exception ex) {
        ex.printStackTrace();
    }
    finally {
       
        bw.flush();
        bw.close();
    } 
	successExport.setText("Data has been exported to " + file);
    successExport.setVisible(true);
}
public void writeExcelWindows() throws Exception {
	List<Instance> allInstances = tableViewWin.getItems();
	 File file = new File(dateFormat.format(date) + "Windows_Instances" + count + ".csv");
	 count++;
	  
     FileWriter fw = new FileWriter(file);
     BufferedWriter bw = new BufferedWriter(fw);
     
	
    
	try { 
       String columnHeader = "Instance Name" + "," + "Usage in Seconds" + "," + 
		        "vCPUs" + ","  + "Storage" + ","  + "Memory(GiB)" + "," + "Daily Running Cost ($)" 
		        + "," + "Total Cost ($)" + "\n";
        
        bw.write(columnHeader);    
        for (Instance instance : allInstances) { 	
	        String text = instance.getName() + "," + instance.getUsage() + "," + instance.getVCPUs() + "," + instance.getStorage() + "," + instance.getMemory() + "," + instance.getRunningCost() + "," + instance.getTotalCost() + "\n";

	        bw.write(text);
        }
    } catch (Exception ex) {
        ex.printStackTrace();
    }
    finally {
       
        bw.flush();
        bw.close();
    } 
	successExportWin.setText("Data has been exported to " + file);
    successExportWin.setVisible(true);
}

public void writeExcelEBS() throws Exception {
	List<ElasticBlockStore> allElasticBlockStores = tableViewEBS.getItems();
	 File file = new File(dateFormat.format(date) + "EBS_Volumes" + count + ".csv");
	 count++;
	  
     FileWriter fw = new FileWriter(file);
     BufferedWriter bw = new BufferedWriter(fw);
     
	try {		 
        String columnHeader = "EBS Name" + "," + "Storage" + "," + 
		        "Usage (in Seconds)" + ","  + "IOPS Porovision" + ","  + "Individual IOPS Cost ($)" + "," + "Total Cost ($)" + "\n";
        
        bw.write(columnHeader);      
        for (ElasticBlockStore EBS : allElasticBlockStores) {
        	
        	  String text = EBS.getName() + "," + EBS.getStorage() + "," + EBS.getUsage() + "," + EBS.getIopsProvision() + "," + EBS.getIndividualIOPS() + "," + EBS.getTotalCost() + "\n";
        	  
	        bw.write(text);
        }
    } catch (Exception ex) {
        ex.printStackTrace();
    }
    finally {
       
        bw.flush();
        bw.close();
    } 
    successExportEBS.setText("Data has been exported to " + file);
    successExportEBS.setVisible(true);
}
}
