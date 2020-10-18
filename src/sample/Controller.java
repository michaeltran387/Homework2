package sample;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.math.RoundingMode;
import java.net.URL;
import java.sql.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class Controller implements Initializable {
    final String hostname = "employee-db.cnzc6lffeqyr.us-east-1.rds.amazonaws.com";
    final String dbname = "testdb";
    final String port = "3306";
    final String username = "michaeltran387";
    final String password = "tri1999!Mi";

    final String AWS_URL = "jdbc:mysql://" + hostname + ":" + port + "/" + dbname + "?user=" + username + "&password=" + password;

    //@FXML
    //private JFXButton createDBB;

    @FXML
    private JFXListView<Patient> patientListLV;

    @FXML
    private JFXButton createTableB;

    @FXML
    private JFXButton fillTableB;

    @FXML
    private JFXButton deleteTableB;

    @FXML
    private JFXButton nameSortB;

    @FXML
    private JFXButton ageSortB;

    @FXML
    private JFXButton bloodTypeSortB;

    private ObservableList<Patient> patientListOL;
    //private ArrayList<Patient> patientListAL;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        patientListOL = patientListLV.getItems();
        //patientListAL = new ArrayList<Patient>();
        try
        {
            Connection conn = DriverManager.getConnection(AWS_URL);
            Statement stmt = conn.createStatement();
            System.out.println("Connection to database established.");

            System.out.println("Populating table with current database.");
            String sql = "SELECT name, id, age, gender, bloodType, weight, height FROM Patient";
            ResultSet result = stmt.executeQuery(sql);
            while (result.next())
            {
                Patient dummy = new Patient(result.getString("name"),UUID.fromString(result.getString("id")),
                        Integer.parseInt(result.getString("age")),result.getString("gender").charAt(0),
                        result.getString("bloodType"),Float.parseFloat(result.getString("weight")),
                        result.getString("height"));
                //patientListAL.add(dummy);
                patientListOL.add(dummy);
            }
        }
        catch(Exception a)
        {
            System.out.println("Error: " + a.getMessage());
        }

        createTableB.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                GeneratePatientTable();
            }
        });

        fillTableB.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                FillPatientTable();
            }
        });

        deleteTableB.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                DeletePatientTable();
            }
        });

        nameSortB.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    Connection conn = DriverManager.getConnection(AWS_URL);
                    Statement stmt = conn.createStatement();
                    System.out.println("Connection to database established.");

                    try {
                        System.out.println("Filtering mySQL database by name.");

                        System.out.println("Clearing Patient table.");
                        patientListLV.getItems().clear();

                        System.out.println("Populating table with filtered database.");

                        ResultSet result = stmt.executeQuery("SELECT * FROM Patient ORDER BY name");
                        while (result.next())
                        {
                            Patient dummy = new Patient(result.getString("name"),UUID.fromString(result.getString("id")),
                                    Integer.parseInt(result.getString("age")),result.getString("gender").charAt(0),
                                    result.getString("bloodType"),Float.parseFloat(result.getString("weight")),
                                    result.getString("height"));
                            //patientListAL.add(dummy);
                            patientListOL.add(dummy);
                        }

                    }
                    catch (Exception a) {
                        System.out.println("Error: " + a.getMessage());
                    }

                } catch (Exception a) {
                    System.out.println("Error: " + a.getMessage());
                }
            }
        });

        ageSortB.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    Connection conn = DriverManager.getConnection(AWS_URL);
                    Statement stmt = conn.createStatement();
                    System.out.println("Connection to database established.");

                    try {
                        System.out.println("Filtering mySQL database by age.");

                        System.out.println("Clearing Patient table.");
                        patientListLV.getItems().clear();

                        System.out.println("Populating table with filtered database.");

                        ResultSet result = stmt.executeQuery("SELECT * FROM Patient ORDER BY age");
                        while (result.next())
                        {
                            Patient dummy = new Patient(result.getString("name"),UUID.fromString(result.getString("id")),
                                    Integer.parseInt(result.getString("age")),result.getString("gender").charAt(0),
                                    result.getString("bloodType"),Float.parseFloat(result.getString("weight")),
                                    result.getString("height"));
                            //patientListAL.add(dummy);
                            patientListOL.add(dummy);
                        }

                    }
                    catch (Exception a) {
                        System.out.println("Error: " + a.getMessage());
                    }

                } catch (Exception a) {
                    System.out.println("Error: " + a.getMessage());
                }
            }
        });

        bloodTypeSortB.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    Connection conn = DriverManager.getConnection(AWS_URL);
                    Statement stmt = conn.createStatement();
                    System.out.println("Connection to database established.");

                    try {
                        System.out.println("Filtering mySQL database by blood type.");

                        System.out.println("Clearing Patient table.");
                        patientListLV.getItems().clear();

                        System.out.println("Populating table with filtered database.");

                        ResultSet result = stmt.executeQuery("SELECT * FROM Patient ORDER BY bloodType");
                        while (result.next())
                        {
                            Patient dummy = new Patient(result.getString("name"),UUID.fromString(result.getString("id")),
                                    Integer.parseInt(result.getString("age")),result.getString("gender").charAt(0),
                                    result.getString("bloodType"),Float.parseFloat(result.getString("weight")),
                                    result.getString("height"));
                            //patientListAL.add(dummy);
                            patientListOL.add(dummy);
                        }

                    }
                    catch (Exception a) {
                        System.out.println("Error: " + a.getMessage());
                    }

                } catch (Exception a) {
                    System.out.println("Error: " + a.getMessage());
                }
            }
        });

    }

    private void GeneratePatientTable()
    {
        try {
            Connection conn = DriverManager.getConnection(AWS_URL);
            Statement stmt = conn.createStatement();
            System.out.println("Connection to database established.");

            try {
                stmt.execute("CREATE TABLE Patient (" +
                        "name VARCHAR(25), " +
                        "id VARCHAR(36), " +
                        "age int, " +
                        "gender CHAR(1), " +
                        "bloodType VARCHAR(3), " +
                        "weight float, " +
                        "height VARCHAR(4))");
                System.out.println("Table created with fields 'name', 'id', 'age', 'gender', 'bloodType', 'weight', and 'height'");
            }
            catch (Exception a) {
                System.out.println("Error: " + a.getMessage());
            }

        } catch (Exception a) {
            System.out.println("Error: " + a.getMessage());
        }
    }

    private void FillPatientTable(){
        try
        {
            Connection conn = DriverManager.getConnection(AWS_URL);
            Statement stmt = conn.createStatement();
            System.out.println("Connection to database established.");

            for(int i=0;i<10;i++) {
                System.out.println("Adding an employee to the table!");
                Patient dummy = new Patient(randomName(), randomID(), randomAge(), randomGender(), randomBloodType(), randomWeight(), randomHeight());

                String sql = "INSERT into Patient VALUES" + "('" + dummy.getName() + "', '" + dummy.getId() + "', '" + dummy.getAge() + "', '"
                        + dummy.getGender() + "', '" + dummy.getBloodType() + "', '" + dummy.getWeight() + "', '" + dummy.getHeight() + "')";
                stmt.executeUpdate(sql);

                //patientListAL.add(dummy);
                patientListOL.add(dummy);

                System.out.println("Employee added to Patient table.");

            }
        }
        catch (Exception a)
        {
            System.out.println("Error: " + a.getMessage());
        }

    }

    private void DeletePatientTable(){
        try
        {
            Connection conn = DriverManager.getConnection(AWS_URL);
            Statement stmt = conn.createStatement();
            System.out.println("Connection to database established.");

            String sql = "DROP TABLE Patient ";
            stmt.executeUpdate(sql);
            System.out.println("Table 'Patient' deleted.");

            //patientListAL.clear();
            patientListLV.getItems().clear();

            System.out.println("Patient list cleared.");

        }
        catch (Exception a)
        {
            System.out.println("Error: " + a.getMessage());
        }
    }

    private String randomName(){
        Random r = new Random();
        String alphabetUC = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String alphabetLC = "abcdefghijklmnopqrstuvwxyz";
        String dummy = String.valueOf(alphabetUC.charAt(r.nextInt(alphabetUC.length())));
        for(int i=0;i<9;i++){
            dummy += String.valueOf(alphabetLC.charAt(r.nextInt(alphabetUC.length())));
        }
        System.out.println(dummy);
        return dummy;
    }

    private UUID randomID(){
        UUID dummy = UUID.randomUUID();
        System.out.println(dummy);
        return dummy;
    }

    private int randomAge(){
        int dummy = ThreadLocalRandom.current().nextInt(1, 100);
        System.out.println(dummy);
        return dummy;
    }

    private char randomGender(){
        Random r = new Random();
        String alphabet = "MF";
        char dummy = (alphabet.charAt(r.nextInt(alphabet.length())));
        System.out.println(dummy);
        return dummy;
    }
    private String randomBloodType(){
        String[] bloodTypes  = {"A","B","AB","O"};
        int randomNum = ThreadLocalRandom.current().nextInt(0, 4);
        Random r = new Random();
        String alphabet = "+-";
        String dummy = bloodTypes[randomNum] + String.valueOf(alphabet.charAt(r.nextInt(alphabet.length())));
        System.out.println(dummy);
        return dummy;
    }

    private float randomWeight(){
        float dummy = (float) (Math.random() * (250));
        DecimalFormat df = new DecimalFormat("##.#");
        df.setRoundingMode(RoundingMode.CEILING);
        System.out.println(df.format(dummy));
        return Float.parseFloat(df.format(dummy));
    }

    private String randomHeight(){
        String[] feet  = {"4","5","6"};
        String[] inches = {"1","2","3","4","5","6","7","8","9","10","11",};
        int randomNum = ThreadLocalRandom.current().nextInt(0, 3);
        int randomNum2 = ThreadLocalRandom.current().nextInt(0, 10);
        String alphabet = "+-";
        String dummy = feet[randomNum] + "-" + inches[randomNum2];
        System.out.println(dummy);
        return dummy;
    }

}
