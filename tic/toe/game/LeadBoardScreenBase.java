package tic.toe.game;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

public class LeadBoardScreen extends BorderPane {

    protected final TableView<Player> tableView;
    protected final TableColumn<Player, String> nameColumn;
    protected final TableColumn<Player, Integer> scoreColumn;
    protected final Button btn_back;
    private ArrayList<Player> players;

    public LeadBoardScreen() {
        tableView = new TableView<>();
        nameColumn = new TableColumn<>("Player");
        scoreColumn = new TableColumn<>("Score");
        btn_back = new Button();

        // Initialize the player list
        players = new ArrayList<>();
        players.add(new Player("Amr", 4374354));
        players.add(new Player("alaa",24 ));
        players.add(new Player("sami",5454 ));

        nameColumn.setPrefWidth(297.0);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        scoreColumn.setPrefWidth(302.0);
        scoreColumn.setCellValueFactory(new PropertyValueFactory<>("score"));

        tableView.getColumns().add(nameColumn);
        tableView.getColumns().add(scoreColumn);

        //
        Collections.sort(players, new Comparator<Player>() {
            @Override
            public int compare(Player p1, Player p2) {
                return Integer.compare(p1.getScore(), p2.getScore());
            }
        });
        Collections.reverse(players);
        
        // Add data to TableView
        ObservableList<Player> playerList = FXCollections.observableArrayList(players);
        tableView.setItems(playerList);

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(400.0);
        setPrefWidth(600.0);

        BorderPane.setAlignment(tableView, javafx.geometry.Pos.CENTER);
        tableView.setPrefHeight(200.0);
        tableView.setPrefWidth(200.0);
        setCenter(tableView);

        BorderPane.setAlignment(btn_back, javafx.geometry.Pos.CENTER);
        btn_back.setMnemonicParsing(false);
        btn_back.setOnAction(this::btn_onBack);
        btn_back.setText("Back");
        setTop(btn_back);
    }

    protected void btn_onBack(javafx.event.ActionEvent actionEvent) {}
}
