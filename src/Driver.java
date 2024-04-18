
import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Driver extends Application {
	
	private StackPane container;
	private Button btuIcon;
	private Image iconImage;
	private ImageView iconImageView;
	private Button btuSummary;
	private Button btuLocation;
	private Button btuMartyrs;
	private Button btuLocationStatistics;
	private Button btuSave;
	private HBox boxNavBar;
	private Text txtMain;
	private Button btuLoadCities;
	private HBox boxMain;
	private Image backgroundImage;
	private ImageView background;
	private Pane balckShade;
	private BorderPane mainLayout;
	private VBox centerInfo;
	private Alert alert;
	private MartyrsPane martyrs;// so when hit the search button the information of the first martyr of his list
	// will appear first

	Scene mainScene, summaryScene, locationScene, martyrsScene, locationStatisticsScene, saveScene, statisticsScene;
	CircularDoublyLinkedList cities = new CircularDoublyLinkedList();
	Stage primaryStage;

	static File btselemFile;
	boolean isActivated = true;// to handle when to read one city for statistics or read all of them for the summary
	SummeryPane summeryPane;
	
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		
		container = new StackPane();
		btuIcon = new Button();
		iconImage = new Image("palestine-logo.png");
		iconImageView = new ImageView(iconImage);
		iconImageView.setFitWidth(45);
		iconImageView.setFitHeight(45);
		btuIcon.setGraphic(iconImageView);
		btuIcon.setStyle("-fx-background-color:transparent;-fx-cursor:hand;");
		btuIcon.setPadding(new Insets(0, 325, 0, 0));
		btuSummary = new Button("Summary");
		btuLocation = new Button("Location");
		btuMartyrs = new Button("Martyrs");
		btuLocationStatistics = new Button("Location Statistics");
		btuMartyrs.setDisable(true);
		btuLocationStatistics.setDisable(true);
		btuSave = new Button("Save");
		btuSummary.setStyle(
				"-fx-background-color:transparent;-fx-text-fill:white;-fx-border-color:white;-fx-border-width:1;-fx-cursor:hand;-fx-background-radius: 0;-fx-border-radius:0;");
		btuSummary.setOnMouseEntered(e -> {
			btuSummary.setStyle(
					"-fx-background-color:white;-fx-text-fill:black;-fx-border-color:white;-fx-border-width:1;-fx-cursor:hand;-fx-background-radius: 0;-fx-border-radius:0;");
		});
		btuSummary.setOnMouseExited(e -> {
			btuSummary.setStyle(
					"-fx-background-color:transparent;-fx-text-fill:white;-fx-border-color:white;-fx-border-width:1;-fx-cursor:hand;-fx-background-radius: 0;-fx-border-radius:0;");
		});
		btuLocation.setStyle(
				"-fx-background-color:transparent;-fx-text-fill:white;-fx-border-color:white;-fx-border-width:1;-fx-cursor:hand;-fx-background-radius: 0;-fx-border-radius:0;");
		btuLocation.setOnMouseEntered(e -> {
			btuLocation.setStyle(
					"-fx-background-color:white;-fx-text-fill:black;-fx-border-color:white;-fx-border-width:1;-fx-cursor:hand;-fx-background-radius: 0;-fx-border-radius:0;");
		});
		btuLocation.setOnMouseExited(e -> {
			btuLocation.setStyle(
					"-fx-background-color:transparent;-fx-text-fill:white;-fx-border-color:white;-fx-border-width:1;-fx-cursor:hand;-fx-background-radius: 0;-fx-border-radius:0;");
		});

		btuMartyrs.setStyle(
				"-fx-background-color:transparent;-fx-text-fill:white;-fx-border-color:white;-fx-border-width:1;-fx-cursor:hand;-fx-background-radius: 0;-fx-border-radius:0;");
		btuMartyrs.setOnMouseEntered(e -> {
			btuMartyrs.setStyle(
					"-fx-background-color:white;-fx-text-fill:black;-fx-border-color:white;-fx-border-width:1;-fx-cursor:hand;-fx-background-radius: 0;-fx-border-radius:0;");
		});
		btuMartyrs.setOnMouseExited(e -> {
			btuMartyrs.setStyle(
					"-fx-background-color:transparent;-fx-text-fill:white;-fx-border-color:white;-fx-border-width:1;-fx-cursor:hand;-fx-background-radius: 0;-fx-border-radius:0;");
		});
		btuLocationStatistics.setStyle(
				"-fx-background-color:transparent;-fx-text-fill:white;-fx-border-color:white;-fx-border-width:1;-fx-cursor:hand;-fx-background-radius: 0;-fx-border-radius:0;");
		btuLocationStatistics.setOnMouseEntered(e -> {
			btuLocationStatistics.setStyle(
					"-fx-background-color:white;-fx-text-fill:black;-fx-border-color:white;-fx-border-width:1;-fx-cursor:hand;-fx-background-radius: 0;-fx-border-radius:0;");
		});
		btuLocationStatistics.setOnMouseExited(e -> {
			btuLocationStatistics.setStyle(
					"-fx-background-color:transparent;-fx-text-fill:white;-fx-border-color:white;-fx-border-width:1;-fx-cursor:hand;-fx-background-radius: 0;-fx-border-radius:0;");
		});
		btuSave.setStyle(
				"-fx-background-color:white;-fx-text-fill:black;-fx-border-color:white;-fx-border-width:1;-fx-border-radius:0;-fx-background-radius:0;-fx-cursor:hand;");
		boxNavBar = new HBox(10);
		boxNavBar.getChildren().addAll(btuIcon, btuSummary, btuLocation, btuMartyrs, btuLocationStatistics, btuSave);
		boxNavBar.setAlignment(Pos.CENTER_LEFT);
		boxNavBar.setPadding(new Insets(10));
		// Center Text and Button
		txtMain = new Text("Palestine Martyrs\nBlood Till The Freedom");
		txtMain.setTextAlignment(TextAlignment.CENTER);
		txtMain.setFont(Font.font("Arial", FontWeight.BOLD, 30));
		txtMain.setFill(Color.WHITE);
		btuLoadCities = new Button("load Cities");
		btuLoadCities.setTextFill(Color.WHITE);
		btuLoadCities.setStyle(
				"-fx-background-color: transparent; -fx-background-radius: 0;-fx-border-radius:0;-fx-border-color:white; -fx-font-size: 20px;-fx-cursor:hand;");
		boxMain = new HBox(btuLoadCities);
		boxMain.setAlignment(Pos.CENTER);
		boxMain.setSpacing(20);
		// Background Image
		backgroundImage = new Image("background1.jpg");
		background = new ImageView(backgroundImage);
		background.setFitWidth(800);
		background.setFitHeight(600);
		// creating the shady background
		balckShade = new Pane();
		balckShade.setStyle("-fx-background-color: rgba(0, 0, 0, 0.5);");
		// Main Layout
		mainLayout = new BorderPane();
		mainLayout.setTop(boxNavBar);
		centerInfo = new VBox(10);
		centerInfo.getChildren().addAll(txtMain, btuLoadCities);
		centerInfo.setAlignment(Pos.CENTER);
		mainLayout.setCenter(centerInfo);
		container.getChildren().addAll(background, balckShade, mainLayout);
		// creating handlers for the buttons
		summeryPane = new SummeryPane(this);
		btuLoadCities.setOnAction(e -> {
			FileChooser fileChooser = new FileChooser();
			btselemFile = fileChooser.showOpenDialog(null);
			if (btselemFile != null) {
				try {
					Scanner input = new Scanner(btselemFile);
					input.nextLine();// skipping the first line of the file so it starts to read martyrs information
					while (input.hasNextLine()) {
						String line = input.nextLine();
						String[] info = line.split(",");
						if (!cities.contains(info[2])) {// info[2] represents the city name
							LocationRecord locationRecord = new LocationRecord(info[2]);
							LocationPane.locations.getItems().add(info[2]);// to let the user see the available cites on the combo box
							cities.add(locationRecord);
						}

					}
					input.close();
				} catch (FileNotFoundException e2) {
					e2.printStackTrace();
				}
				alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Success");
				alert.setContentText("All the Cities has been set");
				alert.setHeaderText(null);
				alert.show();
			}
			isActivated = true;
		});

		btuLoadCities.setOnMouseEntered(e -> {
			btuLoadCities.setStyle(
					"-fx-background-color:white;-fx-text-fill:black;-fx-background-radius: 0;-fx-border-radius:0;-fx-border-color:white; -fx-font-size: 20px;-fx-cursor:hand;");
		});

		btuLoadCities.setOnMouseExited(e -> {
			btuLoadCities.setStyle(
					"-fx-background-color: transparent; -fx-background-radius: 0;-fx-border-radius:0;-fx-border-color:white; -fx-font-size: 20px;-fx-cursor:hand;");
		});

		martyrs = new MartyrsPane(this);
		martyrsScene = new Scene(martyrs, 800, 500);
		summaryScene = new Scene(summeryPane, 800, 500);
		locationScene = new Scene(new LocationPane(this), 800, 500);
		locationStatisticsScene = new Scene(new StatisticsPane(this), 800, 500);
		saveScene = new Scene(new SavePane(this), 800, 500);
		mainScene = new Scene(container, 800, 500);

		btuSummary.setOnAction(e -> {
			loadToSummary();
			primaryStage.setScene(summaryScene);
		});

		btuLocation.setOnAction(e -> {
			primaryStage.setScene(locationScene);
		});

		btuMartyrs.setOnAction(e -> {
			primaryStage.setScene(martyrsScene);
		});

		btuLocationStatistics.setOnAction(e -> {
			Scene scene = locationStatisticsScene;
			primaryStage.setScene(scene);
		});

		btuSave.setOnAction(e -> {
			Scene scene = saveScene;
			primaryStage.setScene(scene);
		});

		primaryStage.setScene(mainScene);
		primaryStage.show();
	}

	public void loadToSummary() {
		if (isActivated) {
			cities = new CircularDoublyLinkedList();
			if (btselemFile != null) {
				try {
					Scanner input = new Scanner(btselemFile);
					input.nextLine();
					while (input.hasNextLine()) {
						String line = input.nextLine();
						String[] info = line.split(",");
						Date date = new SimpleDateFormat("MM/dd/yyyy").parse(info[3]);
						if (!cities.contains(info[2])) {// info[2] represents the city name
							LocationRecord locationRecord = new LocationRecord(info[2]);
							cities.add(locationRecord);
							Martyr martyr = createMartyr(info,date);
							locationRecord.getNamesTree().insert(martyr);
							locationRecord.getDatesTree().insert(martyr);

						} else {
							Martyr martyr = createMartyr(info,date);
							cities.getNode(info[2]).locationRecord.getNamesTree().insert(martyr);
							cities.getNode(info[2]).locationRecord.getDatesTree().insert(martyr);
							// to link the martyr with his/her own node
						}

					}
					input.close();
					ObservableList<SummaryTable> list = FXCollections.observableArrayList();
					LocationRecord locationRecord;
					int sumHeight1 = 0, sumHeight2 = 0, sumNumberOfMartyrs = 0;
					for (int i = 1; i <=cities.length(); i++) {
						locationRecord = cities.getNode(i).locationRecord;
						SummaryTable summary = new SummaryTable();
						summary.setCityName(locationRecord.getLocationName());
						summary.setHeightAVL1(locationRecord.getNamesTree().heihgt());
						sumHeight1+= locationRecord.getNamesTree().heihgt();
						summary.setHeightAVL2(locationRecord.getDatesTree().heihgt());
						sumHeight2 += locationRecord.getDatesTree().heihgt();
						MaxMartyrsInfo maxDate = locationRecord.getDatesTree().findMaximumNumber();
						SimpleDateFormat dateFormat = new SimpleDateFormat("M/d/yyyy");
						String formattedDate = dateFormat.format(maxDate.getMaxDate());
						summary.setMaxDeathsDate(formattedDate);
						summary.setNumberOfMartyrs(locationRecord.getNamesTree().numberOfMartyrs());
						sumNumberOfMartyrs += locationRecord.getNamesTree().numberOfMartyrs();
						list.add(summary);
					}
					SummaryTable summary = new SummaryTable();
					summary.setCityName("\t(TOTAL)\t");
					summary.setNumberOfMartyrs(sumNumberOfMartyrs);
					summary.setHeightAVL1(sumHeight1);
					summary.setHeightAVL2(sumHeight2);
					summary.setMaxDeathsDate(null);
					list.add(summary);
					summeryPane.table.setItems(list);
					
				} catch (FileNotFoundException e2) {
					e2.printStackTrace();
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
			}
			primaryStage.setScene(summaryScene);
			isActivated = false;//if you download all the info of all the martyrs  
		}

	}
	
	//creating the martyr from the information in the file
	public Martyr createMartyr(String[] info, Date date) {
		Martyr martyr;
		if (info[1] != "") {// info[1] represents the age of the martyr
			martyr = new Martyr(info[0], Byte.parseByte(info[1]), info[2], date, info[4].charAt(0),
					info[5].charAt(0));
		} else {
			martyr = new Martyr(info[0], (byte) 0, info[2], date, info[4].charAt(0),
					info[5].charAt(0));// 0 for those who are unknown
		}
		return martyr;
	}

	public static void main(String[] args) {
		launch(args);
	}

}
