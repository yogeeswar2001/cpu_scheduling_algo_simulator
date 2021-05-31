package org.cpuschedule;

import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;

public class MsgBox {
    public static void show( String msg, String title ) {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle(title);

        Text txt = new Text(msg);
        txt.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 16));

        Button btn = new Button("OK");
        btn.setMinHeight(40);btn.setMaxWidth(Double.MAX_VALUE);
        btn.setOnAction( e -> stage.close() );

        BorderPane pane = new BorderPane();
        pane.setCenter(txt);
        pane.setBottom(btn);

        Scene scene = new Scene(pane, 300, 200);
        stage.setScene(scene);
        stage.showAndWait();
    }
}
