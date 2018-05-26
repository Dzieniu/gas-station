package com.dzieniu2.controller.employee.transaction;

import com.dzieniu2.other.DispenserList;
import com.dzieniu2.other.FuelDispenser;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import lombok.Data;

import java.io.IOException;

@Data
public class DispenserController {

    @FXML
    private AnchorPane dispenserPane;

    @FXML
    private Label nameLabel,litersLabel,costLabel,priceLabel,fuelLeftLabel,percentLeftLabel,
        nameHlabel,litersHlabel,costHlabel,priceHlabel,fuelLeftHlabel;

    private TransactionController transactionController;

    private DispenserList dispenserList;

    private FuelDispenser fuelDispenser;

    @FXML
    public void initialize(){ }

    @FXML
    public void nextDispenser() {
        loadDispenser(dispenserList.next());
        if(fuelDispenser!=null) fuelDispenser=null;
        setBlackStyle();
    }

    @FXML
    public void previousDispenser(){
        loadDispenser(dispenserList.previous());
        if(fuelDispenser!=null) fuelDispenser=null;
        setBlackStyle();
    }

    public void refreshData(){
        if(fuelDispenser!=null){
            loadDispenser(fuelDispenser);
        }
    }

    private void loadDispenser(FuelDispenser dispenser){
        nameLabel.setText(dispenser.getContainer().getId()+" - "+dispenser.getContainer().getFuel().getName());
        litersLabel.setText((Math.round(dispenser.getTanked()*Math.pow(10, 2))/Math.pow(10, 2))+"");
        costLabel.setText((Math.round(dispenser.getCost()*Math.pow(10, 2))/Math.pow(10, 2))+"");
        priceLabel.setText(dispenser.getContainer().getFuel().getPrice()+"PLN/dm³");
        fuelLeftLabel.setText(dispenser.getContainer().getFuelLeft()+"dm³");
        percentLeftLabel.setText("( "+(Math.round((dispenser.getContainer().getFuelLeft()/dispenser.getContainer().getMaxCapacity())*Math.pow(10, 2))/Math.pow(10, 2))+"% )");
    }

    public void setTransactionController(TransactionController transactionController) {
        this.transactionController = transactionController;
        dispenserList = new DispenserList(transactionController.getEmployeeController().getDispenserList().getDispensers());
        nextDispenser();

        dispenserPane.setOnMouseClicked(event -> {
            if(fuelDispenser==null){
                fuelDispenser = dispenserList.getDispensers().get(dispenserList.getSelected());
                setWhiteStyle();
            }else {
                fuelDispenser = null;
                setBlackStyle();
            }
        });
    }

    public void setBlackStyle(){
        dispenserPane.setStyle("-fx-background-color: white;-fx-border-color: gray");
        nameLabel.setStyle("-fx-text-fill: black");
        litersLabel.setStyle("-fx-text-fill: black");
        costLabel.setStyle("-fx-text-fill: black");
        priceLabel.setStyle("-fx-text-fill: black");
        fuelLeftLabel.setStyle("-fx-text-fill: black");
        percentLeftLabel.setStyle("-fx-text-fill: black");
        nameHlabel.setStyle("-fx-text-fill: black");
        litersHlabel.setStyle("-fx-text-fill: black");
        costHlabel.setStyle("-fx-text-fill: black");
        priceHlabel.setStyle("-fx-text-fill: black");
        fuelLeftHlabel.setStyle("-fx-text-fill: black");
    }

    public void setWhiteStyle(){
        dispenserPane.setStyle("-fx-background-color: #4059a9;-fx-border-color: gray");
        nameLabel.setStyle("-fx-text-fill: white");
        litersLabel.setStyle("-fx-text-fill: white");
        costLabel.setStyle("-fx-text-fill: white");
        priceLabel.setStyle("-fx-text-fill: white");
        fuelLeftLabel.setStyle("-fx-text-fill: white");
        percentLeftLabel.setStyle("-fx-text-fill: white");
        nameHlabel.setStyle("-fx-text-fill: white");
        litersHlabel.setStyle("-fx-text-fill: white");
        costHlabel.setStyle("-fx-text-fill: white");
        priceHlabel.setStyle("-fx-text-fill: white");
        fuelLeftHlabel.setStyle("-fx-text-fill: white");
    }

    @FXML
    public void openProductsWindow() {
        transactionController.openProductWindow();
    }

    @FXML
    public void openCustomerWindow() throws IOException {
        transactionController.openCustomerWindow();
    }
}
