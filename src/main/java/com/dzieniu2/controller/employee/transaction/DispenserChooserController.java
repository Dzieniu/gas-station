package com.dzieniu2.controller.employee.transaction;

import com.dzieniu2.other.FuelDispenser;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.List;

public class DispenserChooserController {

    @FXML
    private Label nameLabel,litersLabel,costLabel,priceLabel,fuelLeftLabel,percentLeftLabel,
    nameHlabel,litersHlabel,costHlabel,priceHlabel,fuelLeftHlabel;

    @FXML
    private Button prevDispenserButton,nextDispenserButton;

    @FXML
    private AnchorPane dispenserPane;

    private TransactionController transactionController;

    private int selected;

    private List<FuelDispenser> dispensers;

    private FuelDispenser fuelDispenser;

    @FXML
    public void initialize(){
        selected = -1;
        dispenserPane.setOnMouseClicked(event -> {
            if(transactionController.getFuelDispenser()==null){
                setWhiteStyle();
                transactionController.setFuelDispenser(this.fuelDispenser);
            }else if(transactionController.getFuelDispenser()!=null){
                setBlackStyle();
                transactionController.setFuelDispenser(null);
            }
        });
    }

    public void addDispensers(List<FuelDispenser> dispensers){
        this.dispensers = dispensers;
        FuelDispenser dispenser = next();
        this.fuelDispenser = dispenser;
        showDispenser(dispenser);
    }

    private void showDispenser(FuelDispenser dispenser){
        nameLabel.setText(dispenser.getContainer().getId()+" - "+dispenser.getContainer().getFuel().getName());
        litersLabel.setText((Math.round(dispenser.getTanked()*Math.pow(10, 2))/Math.pow(10, 2))+"");
        costLabel.setText((Math.round(dispenser.getCost()*Math.pow(10, 2))/Math.pow(10, 2))+"");
        priceLabel.setText(dispenser.getContainer().getFuel().getPrice()+"PLN/dm³");
        fuelLeftLabel.setText(dispenser.getContainer().getFuelLeft()+"dm³");
        percentLeftLabel.setText("( "+(Math.round((dispenser.getContainer().getFuelLeft()/dispenser.getContainer().getMaxCapacity())*Math.pow(10, 2))/Math.pow(10, 2))+"% )");
    }

    private FuelDispenser next(){
        if(selected>=dispensers.size()-1){
            selected = 0;
            return dispensers.get(selected);
        }else{
            selected++;
            return dispensers.get(selected);
        }
    }

    private FuelDispenser previous(){
        if (selected < 1) {
            selected = dispensers.size()-1;
            return dispensers.get(selected);
        } else {
            selected--;
            return dispensers.get(selected);
        }
    }

    @FXML
    public void showNext(){
        FuelDispenser dispenser = next();
        showDispenser(dispenser);
        this.fuelDispenser = dispenser;
        setBlackStyle();
        transactionController.setFuelDispenser(null);
    }

    @FXML
    public void showPrevious(){
        FuelDispenser dispenser = previous();
        showDispenser(dispenser);
        this.fuelDispenser = dispenser;
        setBlackStyle();
        transactionController.setFuelDispenser(null);
    }

    @FXML
    public void toProducts() throws IOException {
        transactionController.toProducts();
    }

    @FXML
    public void toClientCheck() throws IOException {
        transactionController.toClientCheck();
    }

    public void setTransactionController(TransactionController transactionController) {
        this.transactionController = transactionController;
        if(transactionController.getFuelDispenser()!=null){
            this.fuelDispenser = transactionController.getFuelDispenser();
            setWhiteStyle();
        }
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
}
