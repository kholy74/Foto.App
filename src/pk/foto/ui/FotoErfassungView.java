package pk.foto.ui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import pk.foto.util.*;
import pk.foto.*;

import java.io.File;

public class FotoErfassungView extends ErfassungView {
    private String auswahl=" ";
    @Override
    public Object gibNeuesObjekt() {
        return null;
    }
    public boolean showView() {

        return false;
    }
    public static File showFileChooser(String titel, Stage stage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(titel);
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        return fileChooser.showOpenDialog(stage);
    }
    public void start(Stage stage) throws Exception {
    var l1= new Label("Name: ");
    var tx = new TextField();
        var b1 = new Button("OK");
        var b2= new Button("Abbrechen");
        var b3= new Button("Wählen");
        String name=tx.getText();
        b3.setOnAction(new EventHandler<ActionEvent>() {
                           @Override
                           public void handle(ActionEvent actionEvent) {
                           var file= showFileChooser("Datei",stage);
                               auswahl=file.getAbsolutePath();
                               try {
                                   start(stage);
                               } catch (Exception e) {
                                   e.printStackTrace();
                               }
                           }
                       });
        b1.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent actionEvent) {
                AlbumErfassungView albumErfassungView=new AlbumErfassungView();
                Album album= new Album(albumErfassungView.getName(),albumErfassungView.getBesitzer());
                try {
                    album.addFoto(new Foto(name,auswahl));
                } catch (HandledException e) {
                    e.printStackTrace();
                }
            }
        });
        b2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                stage.close();
            }
        });

        var l2= new Label("Datei: "+auswahl);
        var fp1= new FlowPane(l1,tx);
        var fp= new FlowPane(b1,b2);
        var fp2= new FlowPane(l2,b3);
        fp.setPadding(new Insets(10));
        fp.setHgap(5);
        fp.setAlignment(Pos.CENTER);
        BorderPane bp = new BorderPane();
        bp.setTop(fp1);
        bp.setCenter(fp2);
        bp.setBottom(fp);
        stage.setScene(new Scene(bp));
        stage.setTitle("Foto Hinzufügen");
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }


}
