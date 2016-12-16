package ch.hslu.dmg;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.*;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.bson.Document;
import java.util.ArrayList;
 
public class UniDBView extends Application {
 
    private TableView<Professor> table = new TableView<Professor>();
    private static ArrayList<Professor> professoren = new ArrayList<>();
    private final ObservableList<Professor> data = FXCollections.observableArrayList();
    private final HBox hb = new HBox();
    
    public static void main(String[] args) {
        launch(args);
    }
 
    @Override
    public void start(Stage stage) {
        Scene scene = new Scene(new Group());
        stage.setTitle("UniDB - NoSQL Project");
        stage.setWidth(600);
        stage.setHeight(550);
 
        final Label label = new Label("Professoren");
        label.setFont(new Font("Arial", 20));
 
        table.setEditable(true);
 
        TableColumn persNrCol = new TableColumn("PersNr");
        persNrCol.setMinWidth(80);
        persNrCol.setCellValueFactory(
                new PropertyValueFactory<Professor, String>("persNr"));
 
        TableColumn nameCol = new TableColumn("Name");
        nameCol.setMinWidth(100);
        nameCol.setCellValueFactory(
                new PropertyValueFactory<Professor, String>("name"));
 
        TableColumn rangCol = new TableColumn("Rang");
        rangCol.setMinWidth(50);
        rangCol.setCellValueFactory(
                new PropertyValueFactory<Professor, String>("rang"));
        
        TableColumn raumCol = new TableColumn("Raum");
        raumCol.setMinWidth(50);
        raumCol.setCellValueFactory(
                new PropertyValueFactory<Professor, String>("raum"));
        
        TableColumn studCol = new TableColumn("Anz. Studenten");
        studCol.setMinWidth(100);
        studCol.setCellValueFactory(
                new PropertyValueFactory<Professor, String>("anzStud"));
        
        TableColumn swsCol = new TableColumn("Anz. SWS");
        swsCol.setMinWidth(80);
        swsCol.setCellValueFactory(
                new PropertyValueFactory<Professor, String>("anzSWS"));
 
        table.setItems(data);
        getProfs(data);
        
        final Button queryButton = new Button("Search Students");
        queryButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                runProfQuery(data);
            }
        });  
        
        table.getColumns().addAll(persNrCol, nameCol, rangCol, raumCol, studCol, swsCol);
        hb.getChildren().addAll(queryButton);
 
        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, table, hb);
 
        ((Group) scene.getRoot()).getChildren().addAll(vbox);
 
        stage.setScene(scene);
        stage.show();
    }
    
    public static void getProfs(ObservableList<Professor> data){
        MongoClient mongo = new MongoClient( "localhost" , 27017 );
        MongoDatabase database = mongo.getDatabase("unidb");
        MongoCollection<Document> prof = database.getCollection("professoren");
        
        Block<Document> printBlock = new Block<Document>(){
            @Override
            public void apply(Document document) {
                System.out.println(document.toJson());
            }  
        };
        
        Block<Document> newProfBlock = new Block<Document>(){
            @Override
            public void apply(Document document) {
                Professor professor = new Professor(
                        document.get("PersNr").toString(), 
                        document.get("Name").toString(),
                        document.get("Rang").toString(),
                        document.get("Raum").toString()
                );
                professoren.add(professor);
                data.add(professor);
            }  
        };
        prof.find().forEach(newProfBlock);
        mongo.close();
    }
    
    public static void runProfQuery(ObservableList<Professor> data){
        MongoClient mongo = new MongoClient( "localhost" , 27017 );
        MongoDatabase database = mongo.getDatabase("unidb");
        MongoCollection<Document> prof = database.getCollection("professoren");
        MongoCollection<Document> vorl = database.getCollection("vorlesungen");
        MongoCollection<Document> stud = database.getCollection("studenten");
        
        for (Professor p: professoren){
            int swsCounter = 0;
            int studCounter = 0;
            
            for(Document dVorl: vorl.find(eq("GelesenVon", p.getPersNr()))){
                swsCounter += Integer.parseInt(dVorl.get("SWS").toString());
                for(Document dStud: stud.find(eq("Hoeren", dVorl.get("VorlNr")))){
                    studCounter ++;
                }
            }
            p.setAnzSWS(swsCounter);
            p.setAnzStud(studCounter);
        }
        data.removeAll(data);
        for(Professor p: professoren){
            data.add(p);
        }
        mongo.close();
    }
    
    public static class Professor {
 
        private final SimpleStringProperty persNr;
        private final SimpleStringProperty name;
        private final SimpleStringProperty rang;
        private final SimpleStringProperty raum;
        private final SimpleStringProperty anzStud;
        private final SimpleStringProperty anzSWS;
 
        public Professor(String persNr, String name, String rang, String raum){
            this(Integer.parseInt(persNr), name, rang, Integer.parseInt(raum), 0, 0);            
        }
        
        public Professor(int persNr, String name, String rang, int raum) {
            this.persNr = new SimpleStringProperty(Integer.toString(persNr));
            this.name = new SimpleStringProperty(name);
            this.rang = new SimpleStringProperty(rang);
            this.raum = new SimpleStringProperty(Integer.toString(raum));
            this.anzStud = new SimpleStringProperty();
            this.anzSWS = new SimpleStringProperty();
        }
        
        public Professor(int persNr, String name, String rang, int raum, int anzStud, int anzSWS) {
            this.persNr = new SimpleStringProperty(Integer.toString(persNr));
            this.name = new SimpleStringProperty(name);
            this.rang = new SimpleStringProperty(rang);
            this.raum = new SimpleStringProperty(Integer.toString(raum));
            this.anzStud = new SimpleStringProperty(Integer.toString(anzStud));
            this.anzSWS = new SimpleStringProperty(Integer.toString(anzSWS));
        }
 
        public int getPersNr() {
            return Integer.parseInt(persNr.get());
        }
 
        public void setPersNr(int persNr) {
            this.persNr.set(Integer.toString(persNr));
        }
 
        public String getName() {
            return name.get();
        }
 
        public void setName(String name) {
            this.name.set(name);
        }
 
        public String getRang() {
            return rang.get();
        }
 
        public void setRang(String rang) {
            this.rang.set(rang);
        }
        
        public String getRaum() {
            return raum.get();
        }
 
        public void setRaum(int raum) {
            this.raum.set(Integer.toString(raum));
        }
        
        public int getAnzStud() {
            return Integer.parseInt(anzStud.get());
        }
 
        public void setAnzStud(int anzStud) {
            this.anzStud.set(Integer.toString(anzStud));
        }
        
        public String getAnzSWS() {
            return anzSWS.get();
        }
 
        public void setAnzSWS(int anzSWS) {
            this.anzSWS.set(Integer.toString(anzSWS));
        }
    }
} 