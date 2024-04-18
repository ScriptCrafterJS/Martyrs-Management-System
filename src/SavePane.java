
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

public class SavePane extends SummeryPane {

	private Text txtNameOfFile;
	private TextField tfNameOfFile;
	private TextField tfHidden;
	private Text txtFilePath;
	private Text txtDirectory;
	private Button btuChoose;
	private VBox container;

	public SavePane(Driver obj) {
		super(obj);
		this.obj = obj;
		
		btuMartyrs.setDisable(true);
		btuLocationStatistics.setDisable(true);
		// Title
		lblTitle = new Label("Save");
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

//		container = new HBox(50);

		container = new VBox(10);
		txtNameOfFile = new Text("Please Write The Name Of The File: ");
		txtNameOfFile.setStyle("-fx-font-size: 20px;");
		tfNameOfFile = new TextField();
		// tfNameOfFile.setPrefWidth(100);
		tfNameOfFile.setStyle("-fx-background-radius:0;-fx-font-size:15");
		txtFilePath = new Text("");
		txtFilePath.setStyle("-fx-font-size: 20px;");
		tfHidden = new TextField();
		tfHidden.setVisible(false);
		txtDirectory = new Text("Please Choose Directory To Save The File In: ");
		txtDirectory.setStyle("-fx-font-size: 20px;");
		btuChoose = new Button("Choose");
		btuChoose.setStyle("-fx-background-radius:0;-fx-cursor:hand;-fx-font-size:15");
		container.getChildren().addAll(txtNameOfFile, tfNameOfFile, txtFilePath, tfHidden, txtDirectory, btuChoose);
		container.setAlignment(Pos.CENTER);
		container.setMaxWidth(100);

		btuChoose.setOnAction(e -> {
			if (!tfNameOfFile.getText().equals("")) {
				String fileName = tfNameOfFile.getText() + ".txt";
				DirectoryChooser chooser = new DirectoryChooser();
				File selectedDirectory = chooser.showDialog(new Stage());
				File file = new File(selectedDirectory, fileName);
				try {
					PrintWriter writer = new PrintWriter(file);
					writer.append("Name,Age,Event location - District,Date of death,Gender\n");
					writer.append(obj.cities.getNode(LocationPane.currentCityName).locationRecord.getNamesTree().inOrder());
					alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Succsess");
					alert.setContentText("All Information Has Been Saved To\nThe File \"" + fileName
							+ "\" Inside The Following Directory:\n" + selectedDirectory.getAbsolutePath());
					alert.setHeaderText(null);
					alert.show();
					writer.close();

				} catch (FileNotFoundException ef) {
					ef.printStackTrace();
				}
			} else {
				alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Oppss");
				alert.setContentText("Please Write The Name Of The File!");
				alert.setHeaderText(null);
				alert.show();
			}
		});

		boxCenterLayout = new VBox(10, lblTitle, container);
		boxCenterLayout.setAlignment(Pos.TOP_CENTER);
		boxCenterLayout.setPadding(new Insets(0, 50, 0, 50));

		setCenter(boxCenterLayout);

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

		btuLocationStatistics.setOnAction(e -> {
			Scene scene = obj.locationStatisticsScene;
			obj.primaryStage.setScene(scene);
		});
	}

}
