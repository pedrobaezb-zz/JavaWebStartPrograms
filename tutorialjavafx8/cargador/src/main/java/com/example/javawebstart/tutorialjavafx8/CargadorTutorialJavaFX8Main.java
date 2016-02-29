package com.example.javawebstart.tutorialjavafx8;

import javafx.application.Preloader;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.BorderPane;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class CargadorTutorialJavaFX8Main extends Preloader {
	private static final Logger log = LogManager.getLogger();
	
    ProgressBar bar;
    Stage stage;
    //boolean noLoadingProgress = true;

    private Scene createPreloaderScene() {
        bar = new ProgressBar(0);
        BorderPane p = new BorderPane();
        p.setCenter(bar);
        return new Scene(p, 300, 150);
    }
 
    public void start(Stage stage) throws Exception {
        log.info("Inicio cargador");
        this.stage = stage;
        stage.setScene(createPreloaderScene());
        stage.show();
    }

    @Override
    public void handleProgressNotification(ProgressNotification pn) {
        log.info("Progresando... {}", pn.getProgress());
        bar.setProgress(pn.getProgress());
    }

    @Override
    public void handleStateChangeNotification(StateChangeNotification evt) {
        if (evt.getType() == StateChangeNotification.Type.BEFORE_START) {
            stage.hide();
        }
    }

    /*@Override
    public void handleProgressNotification(ProgressNotification pn) {
        //application loading progress is rescaled to be first 50%
        //Even if there is nothing to load 0% and 100% events can be
        // delivered
        if (pn.getProgress() != 1.0 || !noLoadingProgress) {
          bar.setProgress(pn.getProgress()/2);
          if (pn.getProgress() > 0) {
              noLoadingProgress = false;
          }
        }
    }

    @Override
    public void handleStateChangeNotification(StateChangeNotification evt) {
        //ignore, hide after application signals it is ready
    }
 
    @Override
    public void handleApplicationNotification(PreloaderNotification pn) {
        if (pn instanceof ProgressNotification) {
           //expect application to send us progress notifications 
           //with progress ranging from 0 to 1.0
           double v = ((ProgressNotification) pn).getProgress();
           if (!noLoadingProgress) {
               //if we were receiving loading progress notifications 
               //then progress is already at 50%. 
               //Rescale application progress to start from 50%               
               v = 0.5 + v/2;
           }
           bar.setProgress(v);            
        } else if (pn instanceof StateChangeNotification) {
            //hide after get any state update from application
            stage.hide();
        }
    } */
 }