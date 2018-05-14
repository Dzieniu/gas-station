package com.dzieniu2.controller.employee;

import com.dzieniu2.other.RandomString;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.util.concurrent.ThreadLocalRandom;

public class AddClientController {


        @FXML
        private Button GenButton;


        @FXML
        void generateUID(ActionEvent event) {
            RandomString gen = new RandomString(16, ThreadLocalRandom.current());
            String gen2 = gen.toString();
            String substr = gen2.substring(gen2.length() - 16);
            GenButton.setText(substr);
        }

    }


