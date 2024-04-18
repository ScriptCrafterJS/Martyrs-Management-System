
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Date;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class MartyrsPane extends SummeryPane {

	private GridPane container;
	private TableView<Martyr> martyrsTable;
	private Label lblName;
	protected TextField tfName;
	private Label lblAge;
	protected TextField tfAge;
	private Label lblDateOfDeath;
	private TextField tfDateOfDeath;
	private DatePicker datePicker;

	private Label lblGender;
	private HBox toggleBoxGender;
	private ToggleGroup groupGender;
	protected RadioButton male;
	protected RadioButton female;

	private Label lblMaridStatus;
	private HBox toggleBoxStatus;
	private ToggleGroup groupStatus;
	protected RadioButton marid;
	protected RadioButton single;

	private HBox buttonsBox;
	private Button btuAdd;
	private Button btuUpdateM;
	private Button btuDelete;

	private Button btuClear;

	private Label lblSearch;
	private HBox searchBox;
	private Label lblSearchName;
	private TextField tfSearchName;
	private Button btuSearch;

	private Date date;

	private SingleLinkedList martyrsSearchedList;// for moving between the searched martyrs

	private SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
	private String dateOfDeath;

	private Martyr martyr;

//	private String oldName;
//	private String searchedName;

	public MartyrsPane(Driver obj) {
		super(obj);
		this.obj = obj;// this.obj is being inherit from the SummeryPane Class

		// Title
		lblTitle = new Label("Martyrs");
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

		container = new GridPane();
		// the input section
		lblName = new Label("Name: ");
		lblName.setStyle("-fx-font-size:15");
		lblAge = new Label("Age: ");
		lblAge.setStyle("-fx-font-size:15");
		lblDateOfDeath = new Label("Date of Death: ");
		lblDateOfDeath.setStyle("-fx-font-size:15");
		lblGender = new Label("Gender: ");
		lblGender.setStyle("-fx-font-size:15");
		lblMaridStatus = new Label("Status");
		lblMaridStatus.setStyle("-fx-font-size:15");
		tfName = new TextField();
		tfName.setStyle("-fx-background-radius:0;-fx-font-size:15");
		tfAge = new TextField();
		tfAge.setStyle("-fx-background-radius:0;-fx-font-size:15");
		// insted of typing then choosing by date picker
		datePicker = new DatePicker();
		datePicker.setStyle("-fx-background-radius:0;-fx-font-size:15");
		tfDateOfDeath = new TextField();
		tfDateOfDeath.setStyle("-fx-background-radius:0;-fx-font-size:15");

		toggleBoxGender = new HBox(10);
		male = new RadioButton("Male");
		female = new RadioButton("Female");
		groupGender = new ToggleGroup();
		male.setToggleGroup(groupGender);
		female.setToggleGroup(groupGender);
		toggleBoxGender.getChildren().addAll(male, female);

		toggleBoxStatus = new HBox(10);
		marid = new RadioButton("Marid");
		single = new RadioButton("Single");
		groupStatus = new ToggleGroup();
		marid.setToggleGroup(groupStatus);
		single.setToggleGroup(groupStatus);
		toggleBoxStatus.getChildren().addAll(marid, single);

		buttonsBox = new HBox(10);
		btuAdd = new Button("Add");
		btuUpdateM = new Button("Update");
		btuUpdateM.setDisable(true);
		btuDelete = new Button("Delete");
		btuAdd.setStyle("-fx-min-width:100px;-fx-background-radius:0;-fx-font-size:15");
		btuUpdateM.setStyle("-fx-min-width:100px;-fx-background-radius:0;-fx-font-size:15");
		btuDelete.setStyle("-fx-min-width:100px;-fx-background-radius:0;-fx-font-size:15");
		buttonsBox.getChildren().addAll(btuAdd, btuUpdateM, btuDelete);

		lblSearch = new Label("Search For Who Has The Name:");
		lblSearch.setStyle("-fx-font-size:20");
		searchBox = new HBox(10);
		lblSearchName = new Label("Name: ");
		lblSearchName.setStyle("-fx-font-size:20");
		tfSearchName = new TextField();
		tfSearchName.setStyle("-fx-background-radius:0;-fx-font-size:15");
		btuSearch = new Button("Search");
		btuSearch.setStyle("-fx-background-radius:0;-fx-font-size:15");
		searchBox.getChildren().addAll(lblSearchName, tfSearchName, btuSearch);

		btuClear = new Button("Clear");
		btuClear.setStyle("-fx-background-radius:0;-fx-cursor:hand;-fx-font-size:15");

		// creating the table view
		martyrsTable = new TableView<Martyr>();

		TableColumn<Martyr, String> martyrName = new TableColumn<>("Name");
		martyrName.setCellValueFactory(new PropertyValueFactory<>("name"));
		martyrName.prefWidthProperty().bind(martyrsTable.widthProperty());

		martyrsTable.getColumns().add(martyrName);

		container.add(lblName, 0, 0);
		container.add(lblAge, 0, 1);
		container.add(lblDateOfDeath, 0, 2);
		container.add(lblGender, 0, 3);
		container.add(lblMaridStatus, 0, 4);

		container.add(tfName, 1, 0);
		container.add(tfAge, 1, 1);
		container.add(datePicker, 1, 2);
		container.add(toggleBoxGender, 1, 3);
		container.add(toggleBoxStatus, 1, 4);

		container.add(btuClear, 0, 5);
		container.add(buttonsBox, 0, 6);
		GridPane.setColumnSpan(buttonsBox, 3);
		container.add(lblSearch, 2, 5);
		container.add(searchBox, 2, 6);
		container.add(martyrsTable, 2, 0);
		GridPane.setRowSpan(martyrsTable, 5);
		GridPane.setColumnSpan(martyrsTable, 4);

		container.setVgap(20);
		container.setHgap(20);

		boxCenterLayout = new VBox(10, lblTitle, container);
		boxCenterLayout.setAlignment(Pos.TOP_CENTER);
		boxCenterLayout.setPadding(new Insets(20));

		setCenter(boxCenterLayout);

		btuIcon.setOnAction(e -> {
			Scene scene = obj.mainScene;
			obj.primaryStage.setScene(scene);
		});

		btuLocation.setOnAction(e -> {
			Scene scene = obj.locationScene;
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

		btuSave.setOnAction(e -> {
			Scene scene = obj.saveScene;
			obj.primaryStage.setScene(scene);
		});

		btuAdd.setOnAction(e -> {
			if (tfName.getText().equals("") || tfAge.getText().equals("") || datePicker.getValue().equals(null)) {
				alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Oppss");
				alert.setContentText("Please fill up the fields");
				alert.setHeaderText(null);
				alert.show();
			} else {
				try {
					SimpleDateFormat dateFormat = new SimpleDateFormat("M/d/yyyy");
					Date dateOfDeath = Date
							.from(datePicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
					RadioButton selectedGender = (RadioButton) groupGender.getSelectedToggle();
					RadioButton selectedStatus = (RadioButton) groupStatus.getSelectedToggle();
					Martyr martyr = new Martyr(tfName.getText(), Byte.parseByte(tfAge.getText()),
							LocationPane.currentCityName, dateOfDeath, selectedGender.getText().charAt(0),
							selectedStatus.getText().charAt(0));
					LocationRecord locationRecord = obj.cities.getNode(LocationPane.currentCityName).locationRecord;
					locationRecord.getNamesTree().insert(martyr);// to link the martyr with his/her own node
					locationRecord.getDatesTree().insert(martyr);
					alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Success");
					alert.setContentText("Martyr added successfully!");
					alert.setHeaderText(null);
					alert.show();
				} catch (NumberFormatException e1) {
					e1.printStackTrace();
				}
			}
		});

		btuUpdateM.setOnAction(e -> {
			if (tfName.getText().isBlank() || tfAge.getText().isBlank() || datePicker.getValue().equals(null)) {
				alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Oppss");
				alert.setContentText("Please fill up the fields");
				alert.setHeaderText(null);
				alert.show();
				System.out.println("out");
			} else {
				try {

					date = Date.from(datePicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
					NameNode treeNode = obj.cities.getNode(LocationPane.currentCityName).locationRecord.getNamesTree()
							.findNode(martyr.getName());
					treeNode.martyr.setName(tfName.getText());
					treeNode.martyr.setAge(Byte.parseByte(tfAge.getText()));
					treeNode.martyr.setDataOfDeath(date);
					RadioButton selectedGender = (RadioButton) groupGender.getSelectedToggle();
					RadioButton selectedStatus = (RadioButton) groupStatus.getSelectedToggle();
					treeNode.martyr.setGender(selectedGender.getText().charAt(0));
					treeNode.martyr.setMaridStatus(selectedStatus.getText().charAt(0));
					alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Success");
					alert.setContentText("Martyr Updated successfully!");
					alert.setHeaderText(null);
					alert.show();
				} catch (NumberFormatException e1) {
					e1.printStackTrace();
				}
			}
		});

		btuDelete.setOnAction(e -> {
			if (tfName.getText().equals("")) {
				alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Oppss");
				alert.setContentText("Please write the Name of the martyre to be deleted");
				alert.setHeaderText(null);
				alert.show();
			} else {
				LocationRecord locationRecord = obj.cities.getNode(LocationPane.currentCityName).locationRecord;
				if (locationRecord.getNamesTree().findNode(tfName.getText()) != null) {
					locationRecord.getNamesTree().delete(martyr);// here we keep maintain of the martyr we selected in
																	// the table
					locationRecord.getDatesTree().delete(martyr);
					martyrsTable.getItems().remove(martyrsTable.getSelectionModel().getSelectedItem());
					martyrsTable.refresh();
					alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Success");
					alert.setContentText("Martyr deleted successfully!");
					alert.setHeaderText(null);
					alert.show();
					tfName.clear();
					tfAge.clear();
					datePicker.setValue(null);
					male.setSelected(false);
					female.setSelected(false);
					marid.setSelected(false);
					single.setSelected(false);
				} else {
					alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Oppss");
					alert.setContentText("Martyr is not found :(");
					alert.setHeaderText(null);
					alert.show();
				}
			}
		});

		btuSearch.setOnAction(e -> {
			martyrsTable.getItems().clear();
			martyrsTable.refresh();
			if (tfSearchName.getText().equals("")) {
				alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Oppss");
				alert.setContentText("Please write the Name of the martyre");
				alert.setHeaderText(null);
				alert.show();
			} else {
				martyrsSearchedList = obj.cities.getNode(LocationPane.currentCityName).locationRecord.getNamesTree()
						.findNodes(tfSearchName.getText().substring(0, 1).toUpperCase()
								+ tfSearchName.getText().substring(1));
				SingleLinkedList.Node current = martyrsSearchedList.getFirst();
				while (current != null) {
					martyrsTable.getItems().add(current.getObj());
					current = current.getNext();
				}

				alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Success");
				alert.setContentText("All Martyrs That contains \"" + tfSearchName.getText() + "\" are ready");
				alert.setHeaderText(null);
				alert.show();
			}

		});

		martyrsTable.setOnMouseClicked(e -> {
			martyr = martyrsTable.getSelectionModel().getSelectedItem();
			if(martyr != null) {
				tfName.setText(martyr.getName());
				tfAge.setText(String.valueOf(martyr.getAge()));
				datePicker.setValue(martyr.getDataOfDeath().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
				char genderS = martyr.getGender();
				char statusS = martyr.getMaridStatud();
				if (genderS == 'M') {
					male.setSelected(true);
					female.setSelected(false);
				} else {
					male.setSelected(false);
					female.setSelected(true);
				}
				if (statusS == 'M') {
					marid.setSelected(true);
					single.setSelected(false);
				} else {
					marid.setSelected(false);
					single.setSelected(true);
				}
				btuUpdateM.setDisable(false);
			}
		});

		btuClear.setOnAction(e -> {
			tfName.clear();
			tfAge.clear();
			datePicker.setValue(null);
			male.setSelected(false);
			female.setSelected(false);
			marid.setSelected(false);
			single.setSelected(false);
		});

	}

}
