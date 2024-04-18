
import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class StatisticsPane extends SummeryPane {

	static protected TextArea summary;
//	static CircularDoublyLinkedList.Node node;
	static CircularDoublyLinkedList.Node current;

	public StatisticsPane(Driver obj) {
		super(obj);
		this.obj = obj;
		current = obj.cities.getNode(LocationPane.currentCityName);

		// Title
		lblTitle = new Label("Statistics");
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
		Button btuNext = new Button("Next");
		btuNext.setStyle("-fx-min-width:100px;-fx-background-radius:0;-fx-font-size:15");
		Button btuPrevious = new Button("Previous");
		btuPrevious.setStyle("-fx-min-width:100px;-fx-background-radius:0;-fx-font-size:15");
		Button stats = new Button("Stats");
		stats.setStyle("-fx-min-width:100px;-fx-background-radius:0;-fx-font-size:15");
		Button btuAVL1 = new Button("1AVL Traverse");
		btuAVL1.setStyle("-fx-min-width:100px;-fx-background-radius:0;-fx-font-size:15");
		Button btuAVL2 = new Button("2AVL Traverse Backward");
		btuAVL2.setStyle("-fx-min-width:100px;-fx-background-radius:0;-fx-font-size:15");
		HBox traverseBox = new HBox(10);
		traverseBox.getChildren().addAll(stats,btuAVL1,btuAVL2);
		HBox buttonsBox = new HBox(10);
		buttonsBox.getChildren().addAll(btuPrevious, btuNext);
		buttonsBox.setPadding(new Insets(10, 0, 0, 0));
		summary = new TextArea();
		summary.setStyle("-fx-font-size: 20px;");

		boxCenterLayout = new VBox(5, lblTitle, summary,traverseBox, buttonsBox);
		boxCenterLayout.setAlignment(Pos.TOP_CENTER);
		boxCenterLayout.setPadding(new Insets(0, 50, 0, 50));

		setCenter(boxCenterLayout);
		
		stats.setOnAction(e->{
			setStatistics(current.locationRecord);
		});
		
		btuAVL1.setOnAction(e->{
			String summaryData = "(("+current.locationRecord.getLocationName()+"))" + "\n" + current.locationRecord.getNamesTree().levelByLevel();
			summary.setText(summaryData);
		});
		
		btuAVL2.setOnAction(e->{
			String summaryData = "(("+current.locationRecord.getLocationName()+"))" + "\n" +current.locationRecord.getDatesTree().inOrderBackward();
			summary.setText(summaryData);
		});
		
		btuNext.setOnAction(e->{
			if(!obj.isActivated) {
				current = current.next;
				setStatistics(current.locationRecord);
			}else {
				current.locationRecord.getDatesTree().root = null;
				current.locationRecord.getNamesTree().root = null;
				current = current.next;// current is the first city and current.next is the next city
				readCityInfo(current);
				setStatistics(current.locationRecord);
			}
		});
		
		btuPrevious.setOnAction(e->{
			if(!obj.isActivated) {
				current = current.previous;
				setStatistics(current.locationRecord);
			}else {
				current.locationRecord.getDatesTree().root = null;
				current.locationRecord.getNamesTree().root = null;
				current = current.previous;
				readCityInfo(current);
				setStatistics(current.locationRecord);
			}
			
		});
		
		btuIcon.setOnAction(e -> {
			Scene scene = obj.mainScene;
			obj.primaryStage.setScene(scene);
		});

		btuLocation.setOnAction(e -> {
			Scene scene = obj.locationScene;
			obj.primaryStage.setScene(scene);
		});

		btuMartyrs.setOnAction(e -> {
			Scene scene = obj.martyrsScene;
			obj.primaryStage.setScene(scene);
		});

		btuSummary.setOnAction(e -> {
			obj.loadToSummary();
			Scene scene = obj.summaryScene;
			obj.primaryStage.setScene(scene);
		});

		btuSave.setOnAction(e -> {
			Scene scene = obj.saveScene;
			obj.primaryStage.setScene(scene);
		});
		
		
	}
	public void readCityInfo(CircularDoublyLinkedList.Node current) {
		File file = Driver.btselemFile;
		if(obj.isActivated) {
			try {
				Scanner input = new Scanner(file);
				input.nextLine();// skipping the first line of the file so it does'nt read it
				while (input.hasNextLine()) {
					String line = input.nextLine();
					String[] info = line.split(",");// reading the info from the file line by line and
													// dividing the
													// words by
													// the comma
					Date date = new SimpleDateFormat("MM/dd/yyyy").parse(info[3]);
					Martyr martyr;
					if (info[2].equals(current.locationRecord.getLocationName())) {
						if (info[1] != "") {// info[1] represents the age of the martyr
							martyr = new Martyr(info[0], Byte.parseByte(info[1]), info[2], date,
									info[4].charAt(0), info[5].charAt(0));
						} else {
							martyr = new Martyr(info[0], (byte) 0, info[2], date, info[4].charAt(0),
									info[5].charAt(0));// 0 for those who are unknown
						}
						LocationRecord locationRecord = obj.cities.getNode(current.locationRecord.getLocationName()).locationRecord;
						locationRecord.getNamesTree().insert(martyr);
						locationRecord.getDatesTree().insert(martyr);
					}
				}
				input.close();
			} catch (FileNotFoundException | ParseException e2) {
				e2.printStackTrace();
			}
		}
	}

	public void setStatistics(LocationRecord locationRecord) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("M/d/yyyy");
		String formattedDate = dateFormat.format(locationRecord.getDatesTree().findMaximumNumber().getMaxDate());
		String summaryData = "(("+current.locationRecord.getLocationName()+"))" + "\nNumber of Martyrs : "
				+ locationRecord.getNamesTree().numberOfMartyrs()
				+ "\n--------------------------------\n1AVL Tree Height : "
				+ locationRecord.getNamesTree().heihgt()
				+ "\n--------------------------------\nMax Number Of Martyrs : "
				+ formattedDate
				+ "\n--------------------------------\n" + "2AVL Tree Height : "
				+ locationRecord.getDatesTree().heihgt();
		summary.setText(summaryData);
	}
}
