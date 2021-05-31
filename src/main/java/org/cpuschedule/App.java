package org.cpuschedule;

import javafx.application.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.text.*;
import javafx.scene.text.Font;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.util.converter.IntegerStringConverter;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

import org.cpuschedule.algo.*;
import org.cpuschedule.utility.InputData;
import org.cpuschedule.utility.OutputData;
import org.cpuschedule.utility.Pair;

public class App extends Application {
    //cpu schedule class
    CpuAlgo simulator;
    ArrayList<Pair> output_chart;
    int[][] output_table;

    //setup for scene
    Scene scene;

    //setup for pane
    BorderPane pane;

    //setup for number of process
    TextField input_n;
    Label input_n_label;

    //setup for quantum time
    TextField qtime;
    Label qtime_label;

    //setup for input table
    TableView<InputData> table;

    //setup for adding data input fields
    TextField pid, btime, priority;

    public static void main(String args[]){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        //setting up navigation buttons
        Button fcfs_button = new Button("FIRST COME FIRST SERVE");
        Button sfj_button = new Button("SHORTEST JOB FIRST");
        Button rr_button = new Button("ROUND ROBIN");
        Button ps_button = new Button("PRIORITY SCHEDULING");

        fcfs_button.setMaxWidth(Double.MAX_VALUE);  fcfs_button.setMinHeight(100);
        fcfs_button.setOnAction( e -> algo_btn_click(1) );

        sfj_button.setMaxWidth(Double.MAX_VALUE);   sfj_button.setMinHeight(100);
        sfj_button.setOnAction( e -> algo_btn_click(2) );

        rr_button.setMaxWidth(Double.MAX_VALUE);    rr_button.setMinHeight(100);
        rr_button.setOnAction( e -> algo_btn_click(3));

        ps_button.setMaxWidth(Double.MAX_VALUE);    ps_button.setMinHeight(100);
        ps_button.setOnAction( e -> algo_btn_click(4));

        VBox vbox = new VBox(10, fcfs_button, sfj_button, rr_button, ps_button);
        vbox.setPadding(new Insets(10));

        //setting up initial scene scene
        Text initial_msg = new Text("SELECT AN ALGORITHM TO START");
        initial_msg.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));

        pane = new BorderPane();
        pane.setLeft(vbox);
        pane.setCenter(initial_msg);

        ScrollPane root = new ScrollPane(pane);
        root.setFitToWidth(true);

        scene = new Scene(root,750, 460);
        primaryStage.setScene(scene);
        primaryStage.setTitle("CPU SCHEDULING SIMULATOR");
        primaryStage.show();
    }

    private void algo_btn_click(int algo) {
        Text algo_name = set_algoname(algo);
        HBox n_hbox = set_nfield();
        set_inptable(algo);
        HBox updatebox = set_updateinp(algo);

        VBox vbox_center;

        if( algo !=3 )
            vbox_center = new VBox(10, algo_name, n_hbox, table, updatebox);
        else {
            HBox q_hbox = set_qhbox();
            vbox_center = new VBox(10, algo_name, n_hbox, q_hbox, table, updatebox);
        }
        vbox_center.setPadding(new Insets(10));
        pane.setCenter(vbox_center);
    }
    public Text set_algoname(int algo) {
        Text algo_name = new Text();
        algo_name.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 16));

        switch ( algo ) {
            case 1:
                algo_name.setText("FIRST COME FIRST SERVE");
                break;
            case 2:
                algo_name.setText("SHORTEST JOB FIRST");
                break;
            case 3:
                algo_name.setText("ROUND ROBIN");
                break;
            case 4:
                algo_name.setText("PRIORITY SCHEDULING");
        }
        return algo_name;
    }
    public HBox set_nfield(){
        input_n = new TextField("0");
        input_n_label = new Label("NUMBER OF PROCESS: ");
        input_n.setMinWidth(100);

        return new HBox(10, input_n_label, input_n);
    }
    public HBox set_qhbox() {
        qtime = new TextField();
        qtime_label = new Label("QUANTUM TIME: ");
        qtime.setMinWidth(100);

        return new HBox(10, qtime_label, qtime);
    }
    public void set_inptable( int algo){
        table = new TableView<InputData>();
        table.setEditable(true);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.setMaxHeight(300);

        TableColumn<InputData, Integer> col_pid = new TableColumn<>("PID");
        col_pid.setCellValueFactory( new PropertyValueFactory<>("pid"));
        col_pid.setCellFactory( TextFieldTableCell.forTableColumn( new IntegerStringConverter()) );
        col_pid.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<InputData, Integer>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<InputData, Integer> edit) {
                        (edit.getTableView().getItems().get(
                                edit.getTablePosition().getRow()
                        )).setPid(edit.getNewValue());
                    }
                }
        );

        TableColumn<InputData, Integer> col_btime = new TableColumn<>("BURST TIME");
        col_btime.setCellValueFactory( new PropertyValueFactory<>("btime"));
        col_btime.setCellFactory( TextFieldTableCell.forTableColumn( new IntegerStringConverter()));
        col_btime.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<InputData, Integer>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<InputData, Integer> edit) {
                        (edit.getTableView().getItems().get(
                                edit.getTablePosition().getRow()
                        )).setBtime(edit.getNewValue());
                    }
                }
        );

        table.getColumns().add(col_pid);
        table.getColumns().add(col_btime);

        if( algo == 4 ) {
            TableColumn<InputData, Integer> col_prior = new TableColumn<>("PRIORITY");
            col_prior.setCellValueFactory( new PropertyValueFactory<>("priority"));
            col_prior.setCellFactory( TextFieldTableCell.forTableColumn( new IntegerStringConverter()));
            col_prior.setOnEditCommit(
                    new EventHandler<TableColumn.CellEditEvent<InputData, Integer>>() {
                        @Override
                        public void handle(TableColumn.CellEditEvent<InputData, Integer> edit) {
                            (edit.getTableView().getItems().get(
                                    edit.getTablePosition().getRow()
                            )).setPriority(edit.getNewValue());
                        }
                    }
            );
            table.getColumns().add(col_prior);
        }
    }
    public HBox set_updateinp(int algo) {
        pid = new TextField();
        pid.setMaxWidth(100);
        pid.setPromptText("PID");
        pid.setText(Integer.toString(Integer.parseInt(input_n.getText())+1));

        btime = new TextField();
        btime.setMaxWidth(100);
        btime.setPromptText("BURST TIME");

        Button add = new Button("ADD");
        add.setOnAction( e -> add_data( algo ) );

        Button delete = new Button("DELETE");
        delete.setOnAction( e -> delete_data() );

        Button submit = new Button("SUBMIT");
        submit.setOnAction( e -> submit_data( algo ) );

        if( algo == 4) {
            priority = new TextField();
            priority.setMaxWidth(100);
            priority.setPromptText("PRIORITY");
            return new HBox(10, pid, btime, priority, add, delete, submit);
        }
        return new HBox(10, pid, btime, add, delete, submit);
    }
    public void add_data(int algo) {
        if( !pid.getText().equals("") && !btime.getText().equals("") ) {
            InputData data = new InputData();
            data.setPid(Integer.parseInt(pid.getText()));
            data.setBtime(Integer.parseInt(btime.getText()));
            if (algo == 4) {
                if( priority.getText().equals("") ) {
                    MsgBox.show("PRIORITY IS EMPTY", "WARNING");
                    return;
                }
                data.setPriority(Integer.parseInt(priority.getText()));
                priority.clear();
            }
            table.getItems().add(data);
            btime.clear();
            input_n.setText(Integer.toString(Integer.parseInt(input_n.getText()) + 1));
            pid.setText(Integer.toString(Integer.parseInt(input_n.getText()) + 1));
        }
        else
            MsgBox.show("FILL IN THE INPUT BOXES", "WARNING");
    }
    public void delete_data() {
        ObservableList<InputData> selected, rows;
        if( table.getItems().size() > 0 ) {
            selected = table.getSelectionModel().getSelectedItems();
            if( selected.size() == 0 )
                return;
            rows = table.getItems();
            for (InputData data : selected) {
                rows.remove(data);
            }
            input_n.setText(Integer.toString(Integer.parseInt(input_n.getText())-1));
        }
        else
            MsgBox.show("NO DATA TO DELETE", "WARNING");
    }
    public void submit_data(int algo) {
        ArrayList<Integer> input_data = new ArrayList<Integer>();
        if( table.getItems().size() == Integer.parseInt(input_n.getText())) {
            input_data.add(algo);
            if( !input_n.getText().equals("") )
                input_data.add(Integer.parseInt(input_n.getText()));
            else {
                MsgBox.show("NUMBER OF PROCESS IS EMPTY", "WARNING");
                return;
            }
            if (algo == 3) {
                if (!qtime.getText().equals(""))
                    input_data.add(Integer.parseInt(qtime.getText()));
                else {
                    MsgBox.show("QUANTUM TIME IS EMPTY", "WARNING");
                    return;
                }
            }
            ObservableList<InputData> rows;
            rows = table.getItems();
            for ( InputData data : rows ){
                input_data.add(data.getPid());
                input_data.add(data.getBtime());
                if( algo == 4 ) { input_data.add(data.getPriority());}
            }
            switch ( algo ) {
                case 1:
                    simulator = new Fcfs();
                    break;
                case 2:
                    simulator = new Sjf();
                    break;
                case 3:
                    simulator = new RoundRobin();
                    break;
                case 4:
                    simulator = new PrioritySchedule();
                    break;
                default:
                    return;
            }
            simulator.get_input(input_data);
            simulator.calculate();
            output_table = simulator.getArr();
            output_chart = simulator.getChart();

            set_output(Integer.parseInt(input_n.getText()));
        }
        else
            MsgBox.show("NUMBER OF PROCESS DO NOT MATCH", "WARNING");
    }
    public void set_output(int n){
        Text text = new Text("OUTPUT");
        text.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 16));

        TableView<OutputData> out_table = new TableView<OutputData>();
        out_table.setColumnResizePolicy( TableView.CONSTRAINED_RESIZE_POLICY );
        out_table.setItems(load_outdata(output_table, n));
        out_table.setMaxHeight(200);

        TableColumn<OutputData, Integer> out_col_pid = new TableColumn<>("PID");
        out_col_pid.setCellValueFactory(new PropertyValueFactory<>("pid"));

        TableColumn<OutputData, Integer> out_col_btime = new TableColumn<>("BRUST TIMES");
        out_col_btime.setCellValueFactory( new PropertyValueFactory<>("btime"));

        TableColumn<OutputData, Integer> out_col_wait = new TableColumn<>("WAIT TIME");
        out_col_wait.setCellValueFactory(new PropertyValueFactory<>("wait"));

        TableColumn<OutputData, Integer> out_col_turn = new TableColumn<>("TURN-AROUND TIME");
        out_col_turn.setCellValueFactory(new PropertyValueFactory<>("turn"));

        out_table.getColumns().add(out_col_pid);
        out_table.getColumns().add(out_col_btime);
        out_table.getColumns().add(out_col_wait);
        out_table.getColumns().add(out_col_turn);

        //creating gantt chart
        ArrayList<String> color = new ArrayList<String>();
        rand_col(color, n);
        HBox chart = new HBox();
        chart.setMinWidth(700);
        chart.setSpacing(1);
        float width = (float)700/output_chart.get(output_chart.size()-1).getSecond();
        HBox time = new HBox();
        time.setMinWidth(700);

        Text txt = new Text("0");
        txt.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 10));
        time.getChildren().add(txt);

        for( int i=0;i<output_chart.size();i++ ){
            Button btn = new Button(Integer.toString(output_chart.get(i).getFirst()));
            btn.setMinHeight(100);

            Text time_txt = new Text(Integer.toString(output_chart.get(i).getSecond()));
            time_txt.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 10));
            time.getChildren().add(time_txt);

            if( i == 0) {
                btn.setMinWidth(output_chart.get(i).getSecond()*width);
                HBox.setMargin(time_txt, new Insets(0, 0, 0,
                        output_chart.get(i).getSecond()*width-12));
            }
            else {
                btn.setMinWidth((output_chart.get(i).getSecond() - output_chart.get(i - 1).getSecond())*width);
                HBox.setMargin(time_txt, new Insets(0, 0, 0,
                        (output_chart.get(i).getSecond() - output_chart.get(i - 1).getSecond())*width-12));
            }
            btn.setStyle(color.get(output_chart.get(i).getFirst()-1));
            chart.getChildren().add(btn);
        }

        VBox vbox_bottom = new VBox(10, text, out_table, chart, time);
        vbox_bottom.setPadding(new Insets(10));

        pane.setBottom(vbox_bottom);
    }
    public ObservableList<OutputData> load_outdata(int[][] out_table, int n) {
        ObservableList<OutputData> data = FXCollections.observableArrayList();
        for( int i=0;i<n;i++ ) {
            data.add(new OutputData(out_table[i][0], out_table[i][1], out_table[i][2], out_table[i][3]));
        }
        return data;
    }
    public void rand_col(ArrayList<String> color, int n) {
        for( int i=0;i<n;i++ ) {
            Random rand = new Random();
            float r = rand.nextFloat() / 2F + 0.5F;
            float g = rand.nextFloat() / 2F + 0.5F;
            float b = rand.nextFloat() / 2F + 0.5F;
            Color randcol = new Color(r, g, b);
            String hex = String.format("#%02x%02x%02x", randcol.getRed(), randcol.getGreen(), randcol.getBlue());
            String col = "-fx-background-color:" + hex + ";";
            color.add(col);
        }
    }
}