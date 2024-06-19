/* Matric: 211276

Name: Kerena Natalie

Course code/Group: CSC3104(G7)

Lab 6
*/
package LAB6;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StudentServerInterfaceClient extends Application {
  // Declare a Student instance
  private StudentServerInterface student;
  private Statement stmt;
  private Label lblStatus = new Label();
  

  private Button btGetScore = new Button("Get Score");
  private TextField tfName = new TextField();
  private TextField tfScore = new TextField();

  public void start(Stage primaryStage) {
    GridPane gridPane = new GridPane();
    gridPane.setHgap(5);
    gridPane.add(new Label("Name"), 0, 0);
    
    gridPane.add(tfName, 1, 0);
   
    gridPane.add(btGetScore, 1, 2);
    gridPane.add(lblStatus, 1, 3);
    gridPane.setAlignment(Pos.CENTER);
    gridPane.setVgap(5);
    gridPane.setHgap(5);
    // Create a scene and place the pane in the stage
    Scene scene = new Scene(gridPane,350 , 300);
    primaryStage.setTitle("StudentServerInterfaceClient"); 
    primaryStage.setScene(scene); // Place the scene in the stage
    primaryStage.show(); // Display the stage
    
    initializeRMI();
    initializeDB();
    btGetScore.setOnAction(e -> showGrade());
  }

 
  

  /** Initialize RMI */
  protected void initializeRMI() {
    String host = "";

    try {
      Registry registry = LocateRegistry.getRegistry(host);
      student = (StudentServerInterface)
        registry.lookup("StudentServerInterfaceImpl");
      System.out.println("Server object " + student + " found");
    }
    catch(Exception ex) {
      System.out.println(ex);
    }
  }

  private void initializeDB() {
	  Connection con = null;
	  try {
			con = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/student","root","Natalie@78");
			
			if(con!=null) {
				System.out.println("database is connected");
			}
			 // Create a statement
		      stmt = con.createStatement();
		}
		catch(Exception e) {
			System.out.println("not connected");
		}
    
  }
  //show grade
  private void showGrade() {
	  double score;
	  boolean permiss;
	    String name = tfName.getText();
	    
	    try {
	    	String queryString = "select score,permission from Scores where name = '" + name+ "'";

	      ResultSet rset = stmt.executeQuery(queryString);

	      if (rset.next()) {
	          
	         score = rset.getDouble(1);
	          permiss=rset.getBoolean(2);

	          if(permiss==true) {
	          // Display result in a label
	          lblStatus.setText("The score is " +
	            score+"\nPermission: "+permiss);
	          }
	          if(permiss==false) {
	        	  lblStatus.setText("The student has not given permission\n"
	        	  		+ "to publish his/her score");
	          }
	        }
	      
	       else {
	          lblStatus.setText("Not found");
	        }
	    }
	    catch (SQLException ex) {
	      ex.printStackTrace();
	    }
	  }
  /**
   * The main method is only needed for the IDE with limited
   * JavaFX support. Not needed for running from the command line.
   */
  public static void main(String[] args) {
    launch(args);
  }
}