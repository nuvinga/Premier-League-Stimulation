package applications;

import utilities.comparators.CompareGoals;
import utilities.comparators.CompareWins;
import entities.Football.FootballClub;
import services.LeagueManager;
import entities.Match;
import services.PremierLeagueManager;
import entities.SportsClub;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.*;

public class GraphicalApplicationJavaFX extends Application{

    private static final LeagueManager premierLeagueManager = PremierLeagueManager.getInstance();
    private static List<SportsClub> tempList = new ArrayList<>();

    public static void main(String[]args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            premierLeagueManager.loadSeasonStatus();
            tempList = (((PremierLeagueManager)premierLeagueManager).getClubList());
        } catch (Exception e) {
            Alert loadError = new Alert(Alert.AlertType.ERROR);
            loadError.setTitle("Error loading log file");
            loadError.setHeaderText("We encountered an error in loading the log file");
            loadError.setContentText("The file containing the data of the previous entries has potentially been corrupted.");
            loadError.show();
        }
        startScreen();
    }

    public void startScreen(){
        Stage stageOne = new Stage();
        BorderPane rootOne = new BorderPane();
        Scene sceneOne = new Scene(rootOne,1100,300);
        stageOne.setScene(sceneOne);
        stageOne.setResizable(false);
        stageOne.setTitle("Main Menu");
        stageOne.show();

        Label mainLabel = new Label("Premier League Information View- Main Menu");
        mainLabel.setFont(Font.font("sans-serif", FontWeight.BOLD, FontPosture.REGULAR, 30));
        mainLabel.setPadding(new Insets(40,20,0,20));

        Label subLabel = new Label("Please select the button of the function you wish to perform");
        subLabel.setFont(Font.font("sans-serif", FontPosture.REGULAR, 18));
        subLabel.setPadding(new Insets(0,20,20,20));

        Button displayClub = new Button("View all Clubs");
        displayClub.setMinSize(200,100);
        Button generateMatch = new Button("Generate new match");
        generateMatch.setMinSize(200,100);
        Button displayMatches = new Button("View all Matches Played");
        displayMatches.setMinSize(200,100);
        Button searchMatch = new Button("Search for played match");
        searchMatch.setMinSize(200,100);
        Button exitButton = new Button("Exit Application");
        exitButton.setMinSize(200,100);

        VBox header = new VBox(10);
        HBox buttonBody = new HBox(5);

        header.getChildren().add(mainLabel);
        header.getChildren().add(subLabel);
        header.setAlignment(Pos.CENTER);

        buttonBody.getChildren().addAll(displayClub, generateMatch, displayMatches, searchMatch, exitButton);
        buttonBody.setAlignment(Pos.CENTER);

        rootOne.setTop(header);
        rootOne.setCenter(buttonBody);

        displayClub.setOnAction(event -> displayClub());
        generateMatch.setOnAction(event -> generateMatch());
        displayMatches.setOnAction(event -> viewMatchesPlayed(((PremierLeagueManager) premierLeagueManager).getMatchList()));
        searchMatch.setOnAction(event -> searchMatch());
        exitButton.setOnAction(event -> {
            stageOne.close();
            exit();
        });

        stageOne.setOnCloseRequest(event -> {
            exit();
        });

    }

    public void displayClub() {

        Stage stage = new Stage();
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root,1700,900);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Premier League- Club list");
        stage.show();

        //Header formatting
        VBox headerBox = new VBox(10);
        headerBox.setStyle("-fx-background-color: #e0dede");
        HBox buttonBox = new HBox(30);
        buttonBox.setPadding(new Insets(0,0,20,0));

        Label mainLabel = new Label("Premier League- List of Football Clubs");
        mainLabel.setFont(Font.font("sans-serif", FontWeight.BOLD, FontPosture.REGULAR, 30));
        mainLabel.setPadding(new Insets(50,20,30,20));

        Button pointsSortButton = new Button("Sort according to points (descending order)");
        pointsSortButton.setMinSize(400,50);
        Button goalsSortButton = new Button("Sort according to goals scored (descending order)");
        goalsSortButton.setMinSize(400,50);
        Button winsSortButton = new Button("Sort according to number of Wins (descending order)");
        winsSortButton.setMinSize(400,50);

        root.setTop(headerBox);
        headerBox.setAlignment(Pos.CENTER);
        buttonBox.setAlignment(Pos.CENTER);

        headerBox.getChildren().add(mainLabel);
        headerBox.getChildren().add(buttonBox);

        buttonBox.getChildren().addAll(pointsSortButton,goalsSortButton,winsSortButton);


        //Body Formatting
        TableView<FootballClub> table = new TableView<>();
        table.setSelectionModel(null);

        TableColumn<FootballClub, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameColumn.setMinWidth(627);
        nameColumn.setMaxWidth(627);
        nameColumn.setSortable(false);

        TableColumn<FootballClub, String> locationColumn = new TableColumn<>("Location");
        locationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        locationColumn.setMinWidth(266);
        locationColumn.setMaxWidth(266);
        locationColumn.setSortable(false);

        TableColumn<FootballClub, String> formationColumn = new TableColumn<>("Date of Formation");
        formationColumn.setCellValueFactory(new PropertyValueFactory<>("dateOfFormation"));
        formationColumn.setMinWidth(145);
        formationColumn.setMaxWidth(145);
        formationColumn.setSortable(false);

        TableColumn<FootballClub, String> coachColumn = new TableColumn<>("Coach");
        coachColumn.setCellValueFactory(new PropertyValueFactory<>("coach"));
        coachColumn.setMinWidth(266);
        coachColumn.setMaxWidth(266);
        coachColumn.setSortable(false);

        TableColumn<FootballClub, String> winsColumn = new TableColumn<>("W");
        winsColumn.setCellValueFactory(new PropertyValueFactory<>("numWins"));
        winsColumn.setMinWidth(50);
        winsColumn.setMaxWidth(50);
        winsColumn.setSortable(false);

        TableColumn<FootballClub, String> drawsColumn = new TableColumn<>("D");
        drawsColumn.setCellValueFactory(new PropertyValueFactory<>("numDraws"));
        drawsColumn.setMinWidth(50);
        drawsColumn.setMaxWidth(50);
        drawsColumn.setSortable(false);

        TableColumn<FootballClub, String> losesColumn = new TableColumn<>("L");
        losesColumn.setCellValueFactory(new PropertyValueFactory<>("numDefeats"));
        losesColumn.setMinWidth(50);
        losesColumn.setMaxWidth(50);
        losesColumn.setSortable(false);

        TableColumn<FootballClub, String> goalsScoredColumn = new TableColumn<>("GS");
        goalsScoredColumn.setCellValueFactory(new PropertyValueFactory<>("goalsScored"));
        goalsScoredColumn.setMinWidth(50);
        goalsScoredColumn.setMaxWidth(50);
        goalsScoredColumn.setSortable(false);

        TableColumn<FootballClub, String> goalsConcededColumn = new TableColumn<>("GC");
        goalsConcededColumn.setCellValueFactory(new PropertyValueFactory<>("goalsConceded"));
        goalsConcededColumn.setMinWidth(50);
        goalsConcededColumn.setMaxWidth(50);
        goalsConcededColumn.setSortable(false);

        TableColumn<FootballClub, String> goalsDifferenceColumn = new TableColumn<>("GD");
        goalsDifferenceColumn.setCellValueFactory(new PropertyValueFactory<>("goalDifference"));
        goalsDifferenceColumn.setMinWidth(50);
        goalsDifferenceColumn.setMaxWidth(50);
        nameColumn.setSortable(false);

        TableColumn<FootballClub, String> matchesColumn = new TableColumn<>("MP");
        matchesColumn.setCellValueFactory(new PropertyValueFactory<>("matchesPlayed"));
        matchesColumn.setMinWidth(50);
        matchesColumn.setMaxWidth(50);
        nameColumn.setSortable(false);

        TableColumn<FootballClub, String> pointsColumn = new TableColumn<>("Pts");
        pointsColumn.setCellValueFactory(new PropertyValueFactory<>("points"));
        pointsColumn.setMinWidth(50);
        pointsColumn.setMaxWidth(50);
        nameColumn.setSortable(false);

        table.getColumns().add(nameColumn);
        table.getColumns().add(locationColumn);
        table.getColumns().add(formationColumn);
        table.getColumns().add(coachColumn);
        table.getColumns().add(winsColumn);
        table.getColumns().add(drawsColumn);
        table.getColumns().add(losesColumn);
        table.getColumns().add(goalsScoredColumn);
        table.getColumns().add(goalsConcededColumn);
        table.getColumns().add(goalsDifferenceColumn);
        table.getColumns().add(matchesColumn);
        table.getColumns().add(pointsColumn);

        tempList.sort(Collections.reverseOrder());
        for (SportsClub club : tempList){
            table.getItems().add((FootballClub) club);
        }

        //Footer formatting
        HBox footerBox = new HBox();
        Button backButton = new Button("Back to Menu");
        backButton.setMinSize(180,50);
        footerBox.setPadding(new Insets(20,20,20,20));

        footerBox.getChildren().add(backButton);
        footerBox.setAlignment(Pos.CENTER);
        root.setBottom(footerBox);

        root.setCenter(table);

        backButton.setOnAction(event -> stage.close());

        pointsSortButton.setOnAction(event -> table.getItems().sort(Collections.reverseOrder()));

        goalsSortButton.setOnAction(event -> table.getItems().sort(new CompareGoals().reversed()));

        winsSortButton.setOnAction(event -> table.getItems().sort(new CompareWins().reversed()));

    }

    public void generateMatch() {
        boolean validator = false;
        String finalDate = "";
        String finalClubOne = "";
        String finalClubTwo = "";
        int finalTeamOneScore = 0;
        int finalTeamTwoScore = 0;
        String returnString = "";
        if (premierLeagueManager.getMatchAvailability()){
            do {
                Random random = new Random();
                int year = random.nextInt(3) + ((PremierLeagueManager) premierLeagueManager).getOldestClubYear();
                do {
                    int day = random.nextInt(31) + 1;
                    int month = random.nextInt(12) + 1;
                    if ((((month == 2) | (month == 4) | (month == 6) | (month == 9) | (month == 11)) && (day >= 1) && (day <= 30)) |
                            (((month == 1) | (month == 3) | (month == 5) | (month == 7) | (month == 8) | (month == 10) | (month == 12)) && (day >= 1) && (day <= 31))) {
                        //since months 2,4,6,9 and 12 have only 30 days max and months 1,3,5,7,8,10 and 12 have 31 days
                        finalDate = String.format("%02d/%02d/", day, month) + year; //formatting the date to have 0s
                        break;
                    }
                } while (true);
//                finalClubOne = tempList.get((random.nextInt(tempList.size()))).getName();
                finalClubOne = tempList.get(2).getName();
                finalClubTwo = tempList.get(1).getName();
                finalTeamOneScore = random.nextInt(11) + 1;
                finalTeamTwoScore = random.nextInt(11) + 1;
                try {
                    returnString = premierLeagueManager.playMatch(finalDate, finalClubOne, finalClubTwo, finalTeamOneScore, finalTeamTwoScore);
                    validator = true;
                } catch (Exception ignored) {}
            }while (!validator);
        }

        Stage stage = new Stage();
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root,700,500);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Premier League- Club list");
        stage.show();

        VBox headerBox = new VBox(10);
        headerBox.setStyle("-fx-background-color: #e0dede");
        headerBox.setAlignment(Pos.CENTER);

        VBox bodyBox = new VBox(10);
        bodyBox.setAlignment(Pos.CENTER);

        Label mainLabel = new Label("Match Results");
        mainLabel.setFont(Font.font("sans-serif", FontWeight.BOLD, FontPosture.REGULAR, 25));
        mainLabel.setPadding(new Insets(40,20,20,20));

        Label dateText = new Label();
        Label matchBetween = new Label();
        matchBetween.setPadding(new Insets(0,20,30,20));
        Label winner = new Label();
        winner.setStyle("-fx-font-size: 20; -fx-font-weight: bold");
        winner.setPadding(new Insets(30,0,0,0));
        Label defeat = new Label();
        defeat.setPadding(new Insets(0,0,20,0));
        Label draw = new Label();
        draw.setStyle("-fx-font-size: 20; -fx-font-weight: bold");
        Label statistics1 = new Label();
        Label statistics2 = new Label();

        root.setTop(headerBox);
        root.setCenter(bodyBox);

        headerBox.getChildren().addAll(mainLabel, dateText, matchBetween);

        dateText.setText("Date: "+finalDate);
        matchBetween.setText(finalClubOne+" and "+finalClubTwo);

        if (returnString.equals("one")){
            winner.setText("Winner: "+finalClubOne);
            defeat.setText("Defeat: "+finalClubTwo);
            statistics1.setText("Winners Goals Scored: "+finalTeamOneScore);
            statistics2.setText("Runner-ups' Goals Scored: "+finalTeamTwoScore);
            bodyBox.getChildren().addAll(winner, defeat, statistics1, statistics2);
        }
        if (returnString.equals("two")){
            winner.setText("Winner: "+finalClubTwo);
            defeat.setText("Defeat: "+finalClubOne);
            statistics1.setText("Winners Goals Scored: "+finalTeamOneScore);
            statistics2.setText("Runner-ups' Goals Scored: "+finalTeamTwoScore);
            bodyBox.getChildren().addAll(winner, defeat, statistics1, statistics2);
        }
        if (returnString.equals("draw")){
            draw.setText("Match Draw");
            statistics1.setText("Team One's Goals Scored: "+finalTeamOneScore);
            statistics2.setText("Runner-ups' Goals Scored: "+finalTeamTwoScore);
            bodyBox.getChildren().addAll(draw, statistics1, statistics2);
        }

        //Footer formatting
        HBox footerBox = new HBox(10);
        Button backButton = new Button("Back to Menu");
        backButton.setMinSize(220,70);
        footerBox.setPadding(new Insets(20,20,20,20));
        Button viewMatchesButton = new Button("View All matches played");
        viewMatchesButton.setMinSize(220,70);
        viewMatchesButton.setPadding(new Insets(20,20,20,20));

        footerBox.getChildren().addAll(backButton,viewMatchesButton);
        footerBox.setAlignment(Pos.CENTER);
        root.setBottom(footerBox);

        backButton.setOnAction(event -> stage.close());
        viewMatchesButton.setOnAction(event -> {
            stage.close();
            viewMatchesPlayed(((PremierLeagueManager) premierLeagueManager).getMatchList());
        });

    }

    public void viewMatchesPlayed(List<Match> matchesList) {
        Stage stage = new Stage();
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root,1500,900);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Premier League- All Matches Played");
        stage.show();

        //Header formatting
        VBox headerBox = new VBox(10);
        headerBox.setStyle("-fx-background-color: #e0dede");

        Label mainLabel = new Label("Premier League- Match History");
        mainLabel.setFont(Font.font("sans-serif", FontWeight.BOLD, FontPosture.REGULAR, 30));
        mainLabel.setPadding(new Insets(50,20,30,20));
        Label searchLabel = new Label("Enter date");
        TextField dateSearch = new TextField();
        dateSearch.setMaxSize(250, 20);
        headerBox.setPadding(new Insets(0,0,30,0));

        root.setTop(headerBox);
        headerBox.setAlignment(Pos.CENTER);

        headerBox.getChildren().addAll(mainLabel, searchLabel ,dateSearch);

        //Body Formatting((PremierLeagueManager) premierLeagueManager).getMatchList();
        ObservableList<Match> list = FXCollections.observableArrayList(matchesList);
        TableView<Match> table = new TableView<>();
        table.setSelectionModel(null);

        TableColumn<Match, String> dateColumn = new TableColumn<>("Date Played");
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        dateColumn.setMinWidth(145);
        dateColumn.setMaxWidth(145);
        dateColumn.setSortable(false);

        TableColumn<Match, String> teamOneColumn = new TableColumn<>("Club One Name");
        teamOneColumn.setCellValueFactory(new PropertyValueFactory<>("teamOne"));
        teamOneColumn.setMinWidth(370);
        teamOneColumn.setMaxWidth(370);
        teamOneColumn.setSortable(false);

        TableColumn<Match, String> teamOneGoals = new TableColumn<>("GS- 1");
        teamOneGoals.setCellValueFactory(new PropertyValueFactory<>("scoreTeamOne"));
        teamOneGoals.setMinWidth(50);
        teamOneGoals.setMaxWidth(50);
        teamOneGoals.setSortable(false);

        TableColumn<Match, String> teamTwoColumn = new TableColumn<>("Club Two Name");
        teamTwoColumn.setCellValueFactory(new PropertyValueFactory<>("teamTwo"));
        teamTwoColumn.setMinWidth(370);
        teamTwoColumn.setMaxWidth(370);
        teamTwoColumn.setSortable(false);

        TableColumn<Match, String> teamTwoGoals = new TableColumn<>("GS- 2");
        teamTwoGoals.setCellValueFactory(new PropertyValueFactory<>("scoreTeamTwo"));
        teamTwoGoals.setMinWidth(50);
        teamTwoGoals.setMaxWidth(50);
        teamTwoGoals.setSortable(false);

        TableColumn<Match, String> resultColumn = new TableColumn<>("Result");
        resultColumn.setCellValueFactory(new PropertyValueFactory<>("winTeam"));
        resultColumn.setMinWidth(300);
        resultColumn.setMaxWidth(300);
        resultColumn.setSortable(false);

        table.getColumns().add(dateColumn);
        table.getColumns().add(teamOneColumn);
        table.getColumns().add(teamOneGoals);
        table.getColumns().add(teamTwoColumn);
        table.getColumns().add(teamTwoGoals);
        table.getColumns().add(resultColumn);

        list.sort(Collections.reverseOrder().reversed());

        FilteredList<Match> match = new FilteredList<>(list, p -> true);
        table.setItems(match);

        dateSearch.setPromptText("Enter date to search");
        dateSearch.setOnKeyReleased(keyEvent ->
                match.setPredicate(p -> p.getDate().toLowerCase().contains(dateSearch.getText().toLowerCase().trim()))
        );

        //Footer formatting
        HBox footerBox = new HBox();
        Button backButton = new Button("Back to Menu");
        backButton.setMinSize(180,50);
        footerBox.setPadding(new Insets(20,20,20,20));

        footerBox.getChildren().add(backButton);
        footerBox.setAlignment(Pos.CENTER);
        root.setBottom(footerBox);

        root.setCenter(table);

        backButton.setOnAction(event -> stage.close());

    }

    public void searchMatch() {
        Stage stage = new Stage();
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root,1000,250);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Premier League- Search");
        stage.show();

        //Header formatting
        VBox headerBox = new VBox(10);
        headerBox.setStyle("-fx-background-color: #e0dede");

        Label mainLabel = new Label("Premier League- Search Match on Date");
        mainLabel.setFont(Font.font("sans-serif", FontWeight.BOLD, FontPosture.REGULAR, 30));
        mainLabel.setPadding(new Insets(50,20,30,20));

        root.setTop(headerBox);
        headerBox.setAlignment(Pos.CENTER);

        headerBox.getChildren().add(mainLabel);

        //Body
        HBox searchBox = new HBox(10);

        Label label = new Label("Select the date:   ");

        DatePicker datePicker = new DatePicker();
        datePicker.setPromptText("Date");

        Button searchButton = new Button("Search");
        searchButton.setMaxSize(250, 100);

        root.setCenter(searchBox);
        searchBox.setAlignment(Pos.CENTER);

        searchBox.getChildren().addAll(label, datePicker, searchButton);

        searchButton.setOnAction(event -> {
            String date = "";
            try {
                date = datePicker.getValue().toString();
            }catch (Exception e){
                Alert inputError = new Alert(Alert.AlertType.ERROR);
                inputError.setTitle("No input received");
                inputError.setHeaderText("Please select the date before you search");
                inputError.setContentText("Please check and re-select");
                inputError.show();
            }
            List<Match> match = ((PremierLeagueManager) premierLeagueManager).getMatchesForDate(date);
            if (match==null) {
                Alert findError = new Alert(Alert.AlertType.ERROR);
                findError.setTitle("No matches on day");
                findError.setHeaderText("The date you have entered does not match a match date");
                findError.setContentText("Please check and re-select");
                findError.show();
            }else{
                viewMatchesPlayed(match);
            }
        });

    }

    public void exit(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Exit type");
        alert.setHeaderText("Please select if you would like to save your changes before exiting");
        alert.setContentText("Choose your option.");

        ButtonType exitButton = new ButtonType("Exit");
        ButtonType saveAndExitButton = new ButtonType("Save and Exit");
        ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(exitButton, saveAndExitButton, cancelButton);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == exitButton){
            System.console();
        } else if (result.get() == saveAndExitButton) {
            try {
                premierLeagueManager.saveSeasonStatus();
            } catch (Exception ignored) { }
            System.console();
        } else {
            startScreen();
        }

    }

}
