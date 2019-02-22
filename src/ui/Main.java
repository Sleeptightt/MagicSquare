package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * <b> Laboratorio unidad 1 </b>
 * @author César Canales <br>
 * Universidad Icesi
 */
public class Main extends Application{

	/**
	* The main function responsible of initiating the program.
	* @param abc Used to launch the program.
	*/
	public static void main(String[] abc) {
		launch(abc);
	}

	@Override
	/**
	* The start function which initializes the stage and displays it.
	* @param stage The main stage of the graphical interface.
	*/
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("MagicSquare.fxml"));
		
		
		Scene scene = new Scene(root);
		stage.setTitle("Magic square");
		stage.setScene(scene);
		stage.show();
	}

}
