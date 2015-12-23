package com.tdt.recording.ui;


import javafx.application.Application;
import javafx.embed.swing.SwingNode;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;

public class RecordingApp extends Application {

    protected static JComponent component;

    public RecordingApp() {
    }

    public RecordingApp(JComponent component) {
        this.component = component;
    }

    public void setComponent(JComponent component) {
        this.component = component;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            final SwingNode swingNode = new SwingNode();
            EventQueue.invokeLater(new Runnable() {
                public void run() {
                    swingNode.setContent(RecordingApp.this.component);
                }
            });
            StackPane stackPane = new StackPane();
            stackPane.getChildren().add(swingNode);
            Scene scene = new Scene(stackPane, 800, 300);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}
