package com.dzieniu2.controller.employee;

import com.dzieniu2.other.FuelDispenser;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;

public class DispenserController {

    private FuelDispenser fuelDispenser;

    @FXML
    private Label volumeLabel,costLabel,stationNumber,priceLabel;

    @FXML
    private Button fillButton;

    @FXML
    private BorderPane borderPane;

    @FXML
    public void initialize(){
        volumeLabel.setText(0.0+"");
        costLabel.setText(0.0+"");

        borderPane.setStyle("-fx-background-image: url(/images/dispenser-experimental.jpg);-fx-background-size: 1400 1000");

        class Wrapper<T> { T content ; }
        Wrapper<MouseEvent> eventWrapper = new Wrapper<>();

        PauseTransition holdTimer = new PauseTransition(Duration.seconds(0.01));
        holdTimer.setOnFinished(event -> {
            tank();
            holdTimer.playFromStart();
        });

        fillButton.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> {
            eventWrapper.content = event ;
            holdTimer.playFromStart();
        });
        fillButton.addEventHandler(MouseEvent.MOUSE_RELEASED, event -> holdTimer.stop());
    }

    public void setFuelDispenser(FuelDispenser fuelDispenser){
        this.fuelDispenser = fuelDispenser;
        stationNumber.setText(fuelDispenser.getContainer().getId()+" - "+ fuelDispenser.getContainer().getFuel().getName());
        priceLabel.setText(fuelDispenser.getContainer().getFuel().getPrice()+"");
        refresh();
    }

    public void tank(){
        fuelDispenser.tank();
        refresh();
    }

    private void refresh(){
        volumeLabel.setText((Math.round(fuelDispenser.getTanked()*Math.pow(10, 2))/Math.pow(10, 2))+"");
        costLabel.setText((Math.round(fuelDispenser.getCost()*Math.pow(10, 2))/Math.pow(10, 2))+"");
    }
}
