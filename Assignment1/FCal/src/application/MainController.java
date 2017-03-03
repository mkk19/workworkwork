package application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class MainController implements Initializable {

	///// Variables for Tab 1
	@FXML
	private TextArea tab1Amount;
	@FXML
	private TextArea tab1InterestRate;
	@FXML
	private TextArea userPeriods;
	@FXML
    private ToggleGroup futureOrPresent;
    @FXML
    private RadioButton selectedRadio;
    @FXML
    private TextField result;
    
    ///// Variables for Tab 2
	@FXML
	private TextArea tab2Amount;
	@FXML
	private TextArea tab2Value;
	@FXML
    private ToggleGroup tab2Selection;
    @FXML
    private RadioButton currentRatio;
    @FXML
    private RadioButton workingCapitalRatio;
    @FXML
    private RadioButton debtToEquityRatio;
    @FXML
    private RadioButton grossProfitMargin;
    @FXML
    private Label label1;
    @FXML
    private Label label2;
    @FXML
    private TextField answer;
    
    
	
	public void calculateFuture(){
		double value = 0.0;
		double amountInvested;
		double interestRate;
		double periods;
		
		
		if(!tab1Amount.getText().isEmpty()){
			amountInvested = Double.valueOf(tab1Amount.getText());
		}else{
			result.setText("Add Amount in Field"); // error handling
			return;
		}
		
		if(!tab1InterestRate.getText().isEmpty()){
			interestRate = Double.valueOf(tab1InterestRate.getText());
		}else{
			result.setText("Add Interest Rate in Field");
			return;
		}
		
		if(!userPeriods.getText().isEmpty()){
			periods = Double.valueOf(userPeriods.getText());
		}else{
			result.setText("Add Period in Field");
			return;
		}
		
		value = ((amountInvested)*(Math.pow((1+(interestRate)), periods)));
		
		// 
		selectedRadio = (RadioButton)futureOrPresent.getSelectedToggle();
		if(selectedRadio.getText().equals("Future Value")){
			result.setText(value+"");
			System.out.println("future");
		}else{
			value = value / ((1+interestRate)*periods) ;
			result.setText(value+"");
			System.out.println("present");
		}
		
		
	}
	
	public void calculateRatios(){
		
		//calculates the ratios for second tab
		
		double ans = 0.0;
		double amount;
		double value;
		//intialises selected radio button 
		selectedRadio = (RadioButton)tab2Selection.getSelectedToggle();
		
		if(!tab2Amount.getText().isEmpty()){
			amount  = Double.valueOf(tab2Amount.getText());
		}else{
			answer.setText("Please add amount in Field");
			return;
		}
		
		if(!tab2Value.getText().isEmpty()){
			value = Double.valueOf(tab2Value.getText());
		}else{
			answer.setText("Please add a value in field");
			return;
		}
			
		
		if(selectedRadio.getText().equals("Working Capital Ratio")){
			ans = amount - value;
			answer.setText(ans+"");
			
		}else{
			ans = amount / value ;
			answer.setText(ans+"");
		}
		
		
	}
	
	public void resetTabOneFields(){
		
		//Clears the fields in case  the user wants to enter a new set of values
		
		tab1Amount.setText("");
		tab1InterestRate.setText("");
		userPeriods.setText("");
		result.setText("");
	
	}
	
public void resetTabTwoFields(){
		
		//Clears the fields in case the user wants to enter a new set of values
		
	    tab2Amount.setText("");
	    tab2Value.setText("");
	    answer.setText("");
	
	}
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
        // Sets fields to ones dependent on user choice
		currentRatio.setOnAction(a -> {
	        label1.setText("Current Assets");
	        label2.setText("Current Liabilities");
	    });
		workingCapitalRatio.setOnAction(a -> {
	        label1.setText("Current Assets");
	        label2.setText("Current Liabilities");
	    });
		debtToEquityRatio.setOnAction(a -> {
	        label1.setText("Total Debit");
	        label2.setText("Total Equity");
	    });
		grossProfitMargin.setOnAction(a -> {
	        label1.setText("Gross profit");
	        label2.setText("Revenue");
	    });
    }
	
	
}

