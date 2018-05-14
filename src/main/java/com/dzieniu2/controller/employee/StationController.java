package com.dzieniu2.controller.employee;

import javafx.animation.PauseTransition;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

public class StationController {

    @FXML
    private Label costLabel;

    @FXML
    private Label volumeLabel;

    @FXML
    private Button fillButton;

    @FXML
    private Slider volumeSlider;

    @FXML
    private Label stationNumberL;

    double volume;
    @FXML
    public void initialize() {
        double volume = 0.0;
    }

    private void addPressAndHoldHandler(Node node, Duration holdTime,
                                        EventHandler<MouseEvent> handler) {

        class Wrapper<T> { T content ; }
        Wrapper<MouseEvent> eventWrapper = new Wrapper<>();

        PauseTransition holdTimer = new PauseTransition(holdTime);
        holdTimer.setOnFinished(event -> handler.handle(eventWrapper.content));


        node.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> {
            eventWrapper.content = event ;
            holdTimer.playFromStart();
        });
        node.addEventHandler(MouseEvent.MOUSE_RELEASED, event -> holdTimer.stop());
        node.addEventHandler(MouseEvent.DRAG_DETECTED, event -> holdTimer.stop());
    }

    @FXML
    void fillBtnPress() {
        addPressAndHoldHandler(fillButton, Duration.seconds(1),
                (MouseEvent event) ->
                        {
                            volume+=0.1;
                            String volStr = String.valueOf(volume);
                            volumeLabel.setText(volStr);
                        }
                );
    }

    @FXML
    void fillBtnRele(MouseEvent event) {

    }

}
