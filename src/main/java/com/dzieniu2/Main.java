package com.dzieniu2;

import com.dzieniu2.entity.Container;
import com.dzieniu2.entity.Fuel;
import com.dzieniu2.repository.ContainerRepository;
import com.dzieniu2.repository.EntitySingleton;
import com.dzieniu2.repository.FuelRepository;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.persistence.EntityManager;

public class Main extends Application
{

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        EntityManager em = EntitySingleton.getInstance().getEntityManager();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Login.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.show();
    }


    public static void main(String[] args)
    {
        launch(args);
    }
}
