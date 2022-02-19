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
import javafx.stage.Stage;
import pk.foto.*;

import static javafx.application.Application.launch;

public class AlbumErfassungView extends ErfassungView {
    public String name;
    public String besitzer;

    @Override
    public Object gibNeuesObjekt() {
        return null;
    }
    public boolean showView() {

        return false;
    }


    public void start(Stage stage) throws Exception {
        var l1= new Label("Name: ");
        var l2= new Label("Besitzer: ");
        var tx1= new TextField();
        var tx2= new TextField();
        var b1= new Button("Okay");
        var b2= new Button("abbrechen");


        b1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                name= tx1.getText();
                besitzer= tx2.getText();
                Album album= new Album(name,besitzer);
                FotoVerwaltung fotoVerwaltung= new FotoVerwaltung();
                try {
                    var fotoerfassung = new FotoErfassungView();
                    fotoVerwaltung.addAlbum(album);
                    fotoVerwaltung.druckeAlleAlben();
                    stage.close();
                    fotoerfassung.start(new Stage());

                } catch (HandledException e) {
                    e.printStackTrace();
                } catch (Exception e) {
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

        var fp= new FlowPane(b1,b2);
        fp.setPadding(new Insets(10));
        fp.setHgap(5);
        fp.setAlignment(Pos.CENTER);
        var fp1= new FlowPane(l1,tx1);
        var fp2= new FlowPane(l2,tx2);

      //  fp.getChildren().addAll(l1, l2);

        BorderPane bp = new BorderPane();
        bp.setTop(fp1);
        bp.setCenter(fp2);

// HBox im BorderPane verschachteln
        bp.setBottom(fp);
        stage.setScene(new Scene(bp,300,150));
        stage.setTitle("Neues Album erstellen");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public String getName() {
        return name;
    }

    public String getBesitzer() {
        return besitzer;
    }
}
