

import java.util.Date;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class SummeryPane extends BorderPane {

	protected TableView<SummaryTable> table;
	protected Image iconImage;
	protected ImageView iconImageView;
	protected Button btuIcon;
	protected Button btuSummary;
	protected Button btuLocation;
	protected Button btuMartyrs;
	protected Button btuLocationStatistics;
	protected Button btuSave;
	protected HBox boxNavBar;
	protected Label lblTitle;
	protected VBox boxCenterLayout;
	protected Scene locationScene;
	protected Alert alert;
	protected Driver obj;
	
	public SummeryPane(Driver obj) {
		this.obj = obj;
		// Navigation Bar
		iconImage = new Image("palestine-logo.png");
		iconImageView = new ImageView(iconImage);
		iconImageView.setFitWidth(45);
		iconImageView.setFitHeight(45);
		btuIcon = new Button();
		btuIcon.setGraphic(iconImageView);
		btuIcon.setStyle("-fx-background-color:transparent;-fx-cursor:hand;");
		btuIcon.setPadding(new Insets(0,335,0,0));

		btuSummary = new Button("Summary");
		btuLocation = new Button("Location");
		btuMartyrs = new Button("Martyrs");
		btuLocationStatistics = new Button("Location Statistics");
//		btuMartyrs.setDisable(true);
//		btuLocationStatistics.setDisable(true);
		btuSave = new Button("Save");
		btuSummary.setStyle("-fx-background-radius:0;-fx-cursor:hand;border-radius:0;-fx-background-color:transparent;-fx-border-color:white;-fx-text-fill:white");
		btuLocation.setStyle("-fx-background-radius:0;-fx-cursor:hand;border-radius:0;-fx-background-color:transparent;-fx-border-color:white;-fx-text-fill:white");
		btuMartyrs.setStyle("-fx-background-radius:0;-fx-cursor:hand;border-radius:0;-fx-background-color:transparent;-fx-border-color:white;-fx-text-fill:white");
		btuLocationStatistics.setStyle("-fx-background-radius:0;-fx-cursor:hand;border-radius:0;-fx-background-color:transparent;-fx-border-color:white;-fx-text-fill:white");
		btuSave.setStyle("-fx-background-radius:0;-fx-cursor:hand;border-radius:0;-fx-background-color:transparent;-fx-border-color:white;-fx-text-fill:white");

		boxNavBar = new HBox(10);
		boxNavBar.getChildren().addAll(btuIcon, btuSummary, btuLocation, btuMartyrs, btuLocationStatistics,
				btuSave);
		boxNavBar.setAlignment(Pos.CENTER_LEFT);
		boxNavBar.setPadding(new Insets(10));
		boxNavBar.setStyle("-fx-background-color: #333333;");

		// Title
		lblTitle = new Label("Summary");
		lblTitle.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
		
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


		// Table
		table = new TableView<>();
		table.setEditable(true);

		TableColumn<SummaryTable, String> locationCol = new TableColumn<>("Location");
		locationCol.setCellValueFactory(new PropertyValueFactory<>("cityName"));

		TableColumn<SummaryTable, Integer> martyrsCol = new TableColumn<>("Number Of Martyrs");
		martyrsCol.setCellValueFactory(new PropertyValueFactory<>("numberOfMartyrs"));

		TableColumn<SummaryTable, Integer> AVLNamesHeight = new TableColumn<>("AVL Names Height");
		AVLNamesHeight.setCellValueFactory(new PropertyValueFactory<>("heightAVL1"));

		TableColumn<SummaryTable, Integer> AVLDatesHeight = new TableColumn<>("AVL Dates Height");
		AVLDatesHeight.setCellValueFactory(new PropertyValueFactory<>("heightAVL2"));
		
		TableColumn<SummaryTable, Date> dateCol = new TableColumn<>("max number of deaths");
		dateCol.setCellValueFactory(new PropertyValueFactory<>("maxDeathsDate"));
		
		table.getColumns().addAll(locationCol, martyrsCol, AVLNamesHeight, AVLDatesHeight,dateCol);

		boxCenterLayout = new VBox(10, lblTitle, table);
		boxCenterLayout.setAlignment(Pos.CENTER);
		boxCenterLayout.setPadding(new Insets(20));
		
		setTop(boxNavBar);
		setCenter(boxCenterLayout);
		
		btuIcon.setOnAction(e->{
			Scene scene = obj.mainScene;
			obj.primaryStage.setScene(scene);
		});
		
		btuLocation.setOnAction(e->{
			Scene scene = obj.locationScene;
			obj.primaryStage.setScene(scene);
		});
		
		btuMartyrs.setOnAction(e->{
			Scene scene = obj.martyrsScene;
			obj.primaryStage.setScene(scene);
		});
		
		btuLocationStatistics.setOnAction(e->{
			Scene scene = obj.locationStatisticsScene;
			obj.primaryStage.setScene(scene);
		});
		
		btuSave.setOnAction(e->{
			Scene scene = obj.saveScene;
			obj.primaryStage.setScene(scene);
		});
		
	}
}
