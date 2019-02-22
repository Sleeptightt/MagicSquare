package ui;

import model.*;
import customExceptions.OutOfRangeSizeException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;

/**
 * <b> Laboratorio unidad 1 </b>
 * @author César Canales <br>
 * Universidad Icesi
 */
public class MagicSquareController {

	
    @FXML
    /**
	* The ComboBox of the different possible starting positions.
	*/
    private ComboBox<String> dirBox;

    @FXML
    /**
	* The northern starting position.
	*/
    private String dir1;

    @FXML
    /**
	* The eastern starting position.
	*/
    private String dir2;

    @FXML
    /**
	* The western starting position.
	*/
    private String dir3;

    @FXML
    /**
	* The southern starting position.
	*/
    private String dir4;
    
    @FXML
    /**
	* The textField which represents the size of the magic square matrix.
	*/
    private TextField textField1;

    @FXML
    /**
	* The ComboBox of the different possible orientations for the fill algorithm.
	*/
    private ComboBox<String> box;
    
    @FXML
    /**
	* The FlowPane which houses the GridPane that represents the magic square.
	*/
    private FlowPane gridBackGround;
    
    /**
	* The association with the model from the interface.
	*/
    private MagicSquare mS;
    
    /**
     * This function initializes the MagicSquare and creates a changeListener for each of the dirBox text cells.
     */
    public void initialize() {
    	mS = new MagicSquare(MagicSquare.NORTH, 5, MagicSquare.NORTHEAST, 0, 2);
    	dirBox.getSelectionModel().selectedItemProperty().addListener(
    		new ChangeListener<String>() {

				@Override
				public void changed(ObservableValue<? extends String> obv, String oldValue, String newValue) {
					ObservableList<String> obA  = FXCollections.observableArrayList();
					box.setPromptText("Select the orientation of the algorithm");
					if(newValue.equals(MagicSquare.NORTH)) {
						obA.add(MagicSquare.NORTHEAST);
						obA.add(MagicSquare.NORTHWEST);
					}
					else if(newValue.equals(MagicSquare.EAST)) {
						obA.add(MagicSquare.NORTHEAST);
						obA.add(MagicSquare.SOUTHEAST);
					}
					else if(newValue.equals(MagicSquare.WEST)) {
						obA.add(MagicSquare.NORTHWEST);
						obA.add(MagicSquare.SOUTHWEST);
					}
					else {
						obA.add(MagicSquare.SOUTHEAST);
						obA.add(MagicSquare.SOUTHWEST);
					}
					box.setItems(obA);
					box.setVisible(true);
				}
    			
    		}
    	);
    }
    
    @FXML
    /**
	* This functions fills the matrix once the generate button is pressed.<br>
	* It also creates an eventhandler for every textfield with a number in order to change the color of the row and column and show the magic constant at the end.
	*/
    void buttonPressed(ActionEvent event){
    	try {
    		if(gridBackGround.getChildren().size() == 1)
    			gridBackGround.getChildren().remove(0);
    		mS.setSize(Integer.parseInt(textField1.getText()));
    		mS.setOrientation(box.getValue());
    		mS.setSquare(new int[mS.getSize()][mS.getSize()]);
    		mS.setDirection(dirBox.getValue());
    		
    		GridPane grid = new GridPane();
    		grid.setAlignment(Pos.CENTER);
        	grid.setHgap(5);
        	grid.setVgap(5);
        	grid.setPadding(new Insets(25, 25, 25, 25));
        	for(int i = 0; i < mS.getSize(); i++) {
        		for(int j = 0; j < mS.getSize(); j++) {
        			TextField tF = new TextField();
        			tF.setPrefSize(50, 25);
        			tF.setAlignment(Pos.CENTER);
        			tF.setEditable(false);
        			tF.setPadding(new Insets(8, 8, 8, 8));;
        			grid.add(tF, j, i);
        		}
        	}
        	
        	gridBackGround.getChildren().add(grid);
        	int[] values = mS.solve();
        	for(int i = 0; i < values.length; i++) {
        		TextField w = ((TextField) grid.getChildren().get(values[i]));
        		w.setText(String.valueOf(i+1));
        		w.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
        			
					@Override
					public void handle(MouseEvent event) {
						if(grid.getChildren().size() > mS.getSize() * mS.getSize()) {
							grid.getChildren().remove(grid.getChildren().size()-1);
							grid.getChildren().remove(grid.getChildren().size()-1);
						}
						for(int i = 0; i < grid.getChildren().size(); i++) {
							((TextField)grid.getChildren().get(i)).setStyle("Style.css");
						}
						
						int[] array = mS.calculatePos(GridPane.getRowIndex(w), GridPane.getColumnIndex(w));
						for(int i = 0; i < array.length-2; i++) {
							TextField y = (TextField)grid.getChildren().get(array[i]);
							y.setStyle("-fx-background-color: red");
						}
						TextField horCons = new TextField();
						horCons.setPrefSize(50, 25);
	        			horCons.setAlignment(Pos.CENTER);
	        			horCons.setEditable(false);
	        			horCons.setPadding(new Insets(8, 8, 8, 8));;
						horCons.setText("" + mS.calculateMagicConstant());
						horCons.setStyle("-fx-background-color: white");
						TextField verCons = new TextField();
						verCons.setPrefSize(50, 25);
	        			verCons.setAlignment(Pos.CENTER);
	        			verCons.setEditable(false);
	        			verCons.setPadding(new Insets(8, 8, 8, 8));;
						verCons.setText("" + mS.calculateMagicConstant());
						verCons.setStyle("-fx-background-color: white");
						grid.add(horCons, mS.getSize() + 1, array[array.length-2]);
						grid.add(verCons, array[array.length-1], mS.getSize()+1);
					}
        			
        		});
        	}
    	}catch(NumberFormatException e) {
    		textField1.clear();
    		textField1.setPromptText("Please enter a number");
    	}catch(NullPointerException e) {
    		gridBackGround.getChildren().clear();
    		dirBox.setPromptText("You have to select a direction!");
    		box.setPromptText("You have to select an orientation!");
    	}catch(OutOfRangeSizeException e) {
    		textField1.clear();
    		String o = e.getOut();
    		if(o.equals(OutOfRangeSizeException.UNDER))
    			textField1.setPromptText("Please enter a positive number greater than 0");
    		else if(o.equals(OutOfRangeSizeException.OVER))
    			textField1.setPromptText("Please enter a positive number smaller than 201");
    		else
    			textField1.setPromptText("Please enter an odd number");
    	}
    }

}