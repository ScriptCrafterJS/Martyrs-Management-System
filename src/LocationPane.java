
import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class LocationPane extends SummeryPane {
	protected HBox box;
	protected Label lblCityName;
	protected static ComboBox<String> locations;
	protected Button btuSearch;
	protected HBox buttonsBox;
	protected Button btuAdd;
	protected Button btuUpdate;
	protected Button btuDelete;
	private TextField tfHidden;

	static String currentCityName;// when search is done the name of the searched city will be stored here so i
	// can edit its name in the update section
	LocationRecord locationRecord;

	public LocationPane(Driver obj) {
		super(obj);
		this.obj = obj;// this.obj is being inherit from the SummeryPane Class
		
//		btuMartyrs.setDisable(true);
//		btuLocationStatistics.setDisable(true);
		// Title
		lblTitle = new Label("Location");
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

		box = new HBox(10);
		lblCityName = new Label("City Name: ");
		lblCityName.setStyle("-fx-font-size:20");
		
		
		
		locations = new ComboBox<String>();
		locations.setStyle("-fx-background-radius:0;-fx-font-size:15");
		locations.getItems().add(null);
		CircularDoublyLinkedList.Node currentNode = obj.cities.getFirst();
		while(currentNode != null) {
			locations.getItems().add(currentNode.locationRecord.getLocationName());
			currentNode = currentNode.next;
		}
		
		
		btuSearch = new Button("Search");
		btuSearch.setStyle("-fx-background-radius:0;-fx-cursor:hand;-fx-font-size:15");
		box.getChildren().addAll(lblCityName, locations, btuSearch);
		box.setAlignment(Pos.CENTER);

		buttonsBox = new HBox(10);
		btuAdd = new Button("Add");
		btuUpdate = new Button("Update");
		btuUpdate.setDisable(true);
		btuDelete = new Button("Delete");
		btuAdd.setStyle("-fx-background-radius:0;-fx-cursor:hand;-fx-font-size:15");
		btuUpdate.setStyle("-fx-background-radius:0;-fx-cursor:hand;-fx-font-size:15");
		btuDelete.setStyle("-fx-background-radius:0;-fx-cursor:hand;-fx-font-size:15");
		buttonsBox.getChildren().addAll(btuAdd, btuUpdate, btuDelete);
		buttonsBox.setAlignment(Pos.CENTER);
		buttonsBox.setPadding(new Insets(0, 0, 0, 50));

		boxCenterLayout = new VBox(10, lblTitle, box, buttonsBox);
		boxCenterLayout.setAlignment(Pos.TOP_CENTER);

		setCenter(boxCenterLayout);
		setBottom(tfHidden);

		btuIcon.setOnAction(e -> {
			Scene scene = obj.mainScene;
			obj.primaryStage.setScene(scene);
		});

		btuSummary.setOnAction(e -> {
			obj.loadToSummary();
			Scene scene = obj.summaryScene;
			obj.primaryStage.setScene(scene);
		});

		btuMartyrs.setOnAction(e -> {
			Scene scene = obj.martyrsScene;
			obj.primaryStage.setScene(scene);
		});

		btuLocationStatistics.setOnAction(e -> {
			Scene scene = obj.locationStatisticsScene;
			obj.primaryStage.setScene(scene);
		});

		btuSave.setOnAction(e -> {
			Scene scene = obj.saveScene;
			obj.primaryStage.setScene(scene);
		});

		// location handlers
		btuAdd.setOnAction(e -> {
			if (locations.getItems() != null) {
				String cityName = locations.getSelectionModel().getSelectedItem();
				if (!obj.cities.contains(cityName)) {
					obj.cities.add(new LocationRecord(cityName));
					alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Success");
					alert.setContentText("City " + cityName + " added successfully !");
					alert.setHeaderText(null);
					alert.show();
				} else {
					alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Oppss");
					alert.setContentText("City Already exist !");
					alert.setHeaderText(null);
					alert.show();
				}
			} else {
				alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Oppss");
				alert.setContentText("Please enter the name of the City !");
				alert.setHeaderText(null);
				alert.show();
			}
		});

		btuUpdate.setOnAction(e -> {
			if (locations.getItems() != null) {
				String newCityName = locations.getSelectionModel().getSelectedItem();// get the new name entered
				if (!newCityName.equals(currentCityName)) {
					LocationRecord locationRecord = new LocationRecord(newCityName);
					obj.cities.add(locationRecord);
					obj.cities.remove(currentCityName);
					currentCityName = newCityName;// after changing to the new name am butting back the old one to the
													// new one so in the other classes it will take the change
					alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Success");
					alert.setContentText("City new name has been set successfully !");
					alert.setHeaderText(null);
					alert.show();
				}
			} else {
				alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Oppss");
				alert.setContentText("Please enter the name of the City !");
				alert.setHeaderText(null);
				alert.show();
			}
		});

		btuDelete.setOnAction(e -> {
			if (locations.getItems() != null) {
				String cityName = locations.getSelectionModel().getSelectedItem();
				if (obj.cities.contains(cityName)) {
					obj.cities.remove(cityName);
					alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Success");
					alert.setContentText("City " + cityName + " removed successfully!");
					alert.setHeaderText(null);
					alert.show();
				} else {
					alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Oppss");
					alert.setContentText("City does not found !");
					alert.setHeaderText(null);
					alert.show();
				}
			} else {
				alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Oppss");
				alert.setContentText("Please enter the name of the City");
				alert.setHeaderText(null);
				alert.show();
			}
		});

		btuMartyrs.setDisable(true);
		btuLocationStatistics.setDisable(true);
		btuSearch.setOnAction(e -> {
			currentCityName = locations.getSelectionModel().getSelectedItem();
			if (locations.getItems() != null) {
				String cityName = locations.getSelectionModel().getSelectedItem();
				if (obj.cities.contains(cityName)) {
					btuUpdate.setDisable(false);
					File file = Driver.btselemFile;
					if (file != null) {
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
									if (info[2].equals(currentCityName)) {
										if (info[1] != "") {// info[1] represents the age of the martyr
											martyr = new Martyr(info[0], Byte.parseByte(info[1]), info[2], date,
													info[4].charAt(0), info[5].charAt(0));
										} else {
											martyr = new Martyr(info[0], (byte) 0, info[2], date, info[4].charAt(0),
													info[5].charAt(0));// 0 for those who are unknown
										}
										LocationRecord locationRecord = obj.cities.getNode(currentCityName).locationRecord;
										locationRecord.getNamesTree().insert(martyr);
										locationRecord.getDatesTree().insert(martyr);
									}
								}
								input.close();
							} catch (FileNotFoundException | ParseException e2) {
								e2.printStackTrace();
							}
						}
						alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("Success");
						alert.setContentText("All the Informations has been set");
						alert.setHeaderText(null);
						alert.show();
						SimpleDateFormat dateFormat = new SimpleDateFormat("M/d/yyyy");
						locationRecord = obj.cities.getNode(currentCityName).locationRecord;
						String formattedDate = dateFormat.format(locationRecord.getDatesTree().findMaximumNumber().getMaxDate());
						String summaryData = "(("+currentCityName+"))" + "\nNumber of Martyrs : "
								+ locationRecord.getNamesTree().numberOfMartyrs()
								+ "\n--------------------------------\n1AVL Tree Height : "
								+ locationRecord.getNamesTree().heihgt()
								+ "\n--------------------------------\nMax Number Of Martyrs : "
								+ formattedDate
								+ "\n--------------------------------\n" + "2AVL Tree Height : "
								+ locationRecord.getDatesTree().heihgt();
						StatisticsPane.summary.setText(summaryData);
						StatisticsPane.current = obj.cities.getNode(currentCityName);
						btuMartyrs.setDisable(false);
						btuLocationStatistics.setDisable(false);
					}
				} else {
					alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Oppss!");
					alert.setContentText("City is not found :(");
					alert.setHeaderText(null);
					alert.show();
				}
			} else {
				alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Oppss");
				alert.setContentText("Please enter the name of the City");
				alert.setHeaderText(null);
				alert.show();
			}
		});
	}

}
