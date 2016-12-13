/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.dmg;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;
import java.util.ArrayList;
import java.util.List;
import org.bson.Document;

/**
 *
 * @author Lukas
 */
public class InitDB {
    
    public void initDB(){
        initProfs();
        initStuds();
        initVorlesungen();
        initAssistenten();
        
        //MongoCollection<Document> prof = database.getCollection("professoren");

        
        //collection.insertOne(doc);
        //collection.deleteOne(eq("name", "Test"));
        //Document myDoc = collection.find().first();
        //System.out.println(myDoc.toJson());
    }
    
    public void initProfs(){
        MongoClient mongo = new MongoClient( "localhost" , 27017 );
        MongoDatabase database = mongo.getDatabase("unidb");
        MongoCollection<Document> prof = database.getCollection("professoren");
        
        ArrayList<Document> profs = new ArrayList<>();
        profs.add(new Document("PersNr", 2125)
               .append("Name", "Sokrates")
               .append("Rang", "FP")
               .append("Raum", 226));
        profs.add(new Document("PersNr", 2126)
               .append("Name", "Russel")
               .append("Rang", "FP")
               .append("Raum", 232));
        profs.add(new Document("PersNr", 2127)
               .append("Name", "Kopernikus")
               .append("Rang", "AP")
               .append("Raum", 310));
        profs.add(new Document("PersNr", 2133)
               .append("Name", "Popper")
               .append("Rang", "AP")
               .append("Raum", 52));
        profs.add(new Document("PersNr", 2134)
               .append("Name", "Augustinus")
               .append("Rang", "AP")
               .append("Raum", 309));
        profs.add(new Document("PersNr", 2136)
               .append("Name", "Curie")
               .append("Rang", "FP")
               .append("Raum", 36));
        profs.add(new Document("PersNr", 2137)
               .append("Name", "Kant")
               .append("Rang", "FP")
               .append("Raum", 7));
        prof.insertMany(profs);
        
        mongo.close();
    }
    
    public void initStuds(){
        MongoClient mongo = new MongoClient( "localhost" , 27017 );
        MongoDatabase database = mongo.getDatabase("unidb");
        MongoCollection<Document> stud = database.getCollection("studenten");
        
        ArrayList<Document> studs = new ArrayList<>();
        studs.add(new Document("Legi", 24002)
               .append("Name", "Xenokrates")
               .append("Semester", 18));
        studs.add(new Document("Legi", 24002)
               .append("Name", "Jonas")
               .append("Semester", 12));
        studs.add(new Document("Legi", 24002)
               .append("Name", "Fichte")
               .append("Semester", 10));
        studs.add(new Document("Legi", 24002)
               .append("Name", "Aristoxenos")
               .append("Semester", 8));
        studs.add(new Document("Legi", 24002)
               .append("Name", "Schopenhauer")
               .append("Semester", 6));
        studs.add(new Document("Legi", 24002)
               .append("Name", "Carnap")
               .append("Semester", 3));
        studs.add(new Document("Legi", 24002)
               .append("Name", "Theophrastos")
               .append("Semester", 2));
        studs.add(new Document("Legi", 24002)
               .append("Name", "Feuerbach")
               .append("Semester", 2));
        stud.insertMany(studs);
        
        mongo.close();
    }
    
    public void initVorlesungen(){
        MongoClient mongo = new MongoClient( "localhost" , 27017 );
        MongoDatabase database = mongo.getDatabase("unidb");
        MongoCollection<Document> vorlesung = database.getCollection("vorlesungen");
        
        ArrayList<Document> vorlesungen = new ArrayList<>();
        vorlesungen.add(new Document("VorlNr", 5001)
               .append("Titel", "Grundzüge")
               .append("KP", 4)
               .append("gelsenVon", 2137));
        vorlesungen.add(new Document("VorlNr", 5041)
               .append("Titel", "Ethik")
               .append("KP", 4)
               .append("gelsenVon", 2125));
        vorlesungen.add(new Document("VorlNr", 5043)
               .append("Titel", "Erkentnistheorie")
               .append("KP", 3)
               .append("gelsenVon", 2126));
        vorlesungen.add(new Document("VorlNr", 5049)
               .append("Titel", "Mäeutik")
               .append("KP", 2)
               .append("gelsenVon", 2125));
        vorlesungen.add(new Document("VorlNr", 4052)
               .append("Titel", "Logik")
               .append("KP", 4)
               .append("gelsenVon", 2125));
        vorlesungen.add(new Document("VorlNr", 5216)
               .append("Titel", "Wissenschaftstheorie")
               .append("KP", 3)
               .append("gelsenVon", 2126));
        vorlesungen.add(new Document("VorlNr", 5259)
               .append("Titel", "Bioethik")
               .append("KP", 2)
               .append("gelsenVon", 2126));
        vorlesungen.add(new Document("VorlNr", 5022)
               .append("Titel", "Der Wiener Kreis")
               .append("KP", 2)
               .append("gelsenVon", 2133));
        vorlesungen.add(new Document("VorlNr", 4630)
               .append("Titel", "Glaube und Wissen")
               .append("KP", 2)
               .append("gelsenVon", 2134));
        vorlesungen.add(new Document("VorlNr", 5001)
               .append("Titel", "Die 3 Kritiken")
               .append("KP", 4)
               .append("gelsenVon", 2137));
        
        vorlesung.insertMany(vorlesungen);
        
        mongo.close();
    }
    
    public void initAssistenten(){
        MongoClient mongo = new MongoClient( "localhost" , 27017 );
        MongoDatabase database = mongo.getDatabase("unidb");
        MongoCollection<Document> assistent = database.getCollection("assistenten");
        
        ArrayList<Document> assistenten = new ArrayList<>();
        assistenten.add(new Document("PersNr", 3002)
               .append("Name", "Platon")
               .append("Fachgebiet", "Ideenlehre")
               .append("Boss", 2125));
        assistenten.add(new Document("PersNr", 3003)
               .append("Name", "Aristoteles")
               .append("Fachgebiet", "Syllogistik")
               .append("Boss", 2125));
        assistenten.add(new Document("PersNr", 3004)
               .append("Name", "Wittgenstein")
               .append("Fachgebiet", "Sprachtheorie")
               .append("Boss", 2126));
        assistenten.add(new Document("PersNr", 3005)
               .append("Name", "Rhetikus")
               .append("Fachgebiet", "Planetenbewegung")
               .append("Boss", 2127));
        assistenten.add(new Document("PersNr", 3006)
               .append("Name", "Newton")
               .append("Fachgebiet", "Keplersche Gesetze")
               .append("Boss", 2127));
        assistenten.add(new Document("PersNr", 3007)
               .append("Name", "Spinoza")
               .append("Fachgebiet", "Gott und Natur")
               .append("Boss", 2126));
        
        assistent.insertMany(assistenten);
        
        mongo.close();
    }
}
