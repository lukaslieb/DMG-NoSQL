/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.dmg;

import com.mongodb.BasicDBList;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.util.ArrayList;
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
        initPruefen();
    }
    
    public static void main(String[] args) {
        new InitDB().initDB();
    }
    
    public void initProfs(){
        MongoClient mongo = new MongoClient( "localhost" , 27017 );
        MongoDatabase database = mongo.getDatabase("unidb");
        MongoCollection<Document> prof = database.getCollection("professoren");
        prof.drop();
        
        ArrayList<Document> profs = new ArrayList<>();
        profs.add(new Document("PersNr", 2125)
               .append("Name", "Sokrates")
               .append("Rang", "C4")
               .append("Raum", 226));
        profs.add(new Document("PersNr", 2126)
               .append("Name", "Russel")
               .append("Rang", "C4")
               .append("Raum", 232));
        profs.add(new Document("PersNr", 2127)
               .append("Name", "Kopernikus")
               .append("Rang", "C3")
               .append("Raum", 310));
        profs.add(new Document("PersNr", 2133)
               .append("Name", "Popper")
               .append("Rang", "C3")
               .append("Raum", 52));
        profs.add(new Document("PersNr", 2134)
               .append("Name", "Augustinus")
               .append("Rang", "C3")
               .append("Raum", 309));
        profs.add(new Document("PersNr", 2136)
               .append("Name", "Curie")
               .append("Rang", "C4")
               .append("Raum", 36));
        profs.add(new Document("PersNr", 2137)
               .append("Name", "Kant")
               .append("Rang", "C4")
               .append("Raum", 7));
        prof.insertMany(profs);
        
        mongo.close();
    }
    
    public void initStuds(){
        MongoClient mongo = new MongoClient( "localhost" , 27017 );
        MongoDatabase database = mongo.getDatabase("unidb");
        MongoCollection<Document> stud = database.getCollection("studenten");
        stud.drop();
        
        ArrayList<Document> studs = new ArrayList<>();
        studs.add(new Document("Legi", 24002)
                .append("Name", "Xenokrates")
                .append("Semester", 18));
        studs.add(new Document("Legi", 25403)
                .append("Name", "Jonas")
                .append("Semester", 12)
                .append("Hoeren", 5022));
        studs.add(new Document("Legi", 26120)
                .append("Name", "Fichte")
                .append("Semester", 10)
                .append("Hoeren", 5001));
        studs.add(new Document("Legi", 26830)
                .append("Name", "Aristoxenos")
                .append("Semester", 8));
        BasicDBList hoeren = new BasicDBList();
        hoeren.add(5001);
        hoeren.add(4052);
        studs.add(new Document("Legi", 27550)
                .append("Name", "Schopenhauer")
                .append("Semester", 6)
                .append("Hoeren", hoeren));
        BasicDBList hoeren2 = new BasicDBList();
        hoeren2.add(5041);
        hoeren2.add(5052);
        hoeren2.add(5216);
        hoeren2.add(5259);
        studs.add(new Document("Legi", 28106)
                .append("Name", "Carnap")
                .append("Semester", 3)
                .append("Hoeren", hoeren2));
        BasicDBList hoeren3 = new BasicDBList();
        hoeren3.add(5001);
        hoeren3.add(5041);
        hoeren3.add(5049);
        studs.add(new Document("Legi", 29120)
                .append("Name", "Theophrastos")
                .append("Semester", 2)
                .append("Hoeren", hoeren3));
        BasicDBList hoeren4 = new BasicDBList();
        hoeren4.add(5022);
        hoeren4.add(5001);
        studs.add(new Document("Legi", 29555)
                .append("Name", "Feuerbach")
                .append("Semester", 2)
                .append("Hoeren", hoeren4));
        stud.insertMany(studs);
        
        mongo.close();
    }
    
    public void initVorlesungen(){
        MongoClient mongo = new MongoClient( "localhost" , 27017 );
        MongoDatabase database = mongo.getDatabase("unidb");
        MongoCollection<Document> vorlesung = database.getCollection("vorlesungen");
        vorlesung.drop();
        
        ArrayList<Document> vorlesungen = new ArrayList<>();
        vorlesungen.add(new Document("VorlNr", 5001)
                .append("Titel", "Grundzüge")
                .append("SWS", 4)
                .append("GelesenVon", 2137));
        vorlesungen.add(new Document("VorlNr", 5041)
                .append("Titel", "Ethik")
                .append("SWS", 4)
                .append("GelesenVon", 2125)
                .append("Voraussetzung", 5001));
        vorlesungen.add(new Document("VorlNr", 5043)
                .append("Titel", "Erkentnistheorie")
                .append("SWS", 3)
                .append("GelesenVon", 2126)
                .append("Voraussetzung", 5001));
        vorlesungen.add(new Document("VorlNr", 5049)
                .append("Titel", "Maeeutik")
                .append("SWS", 2)
                .append("GelesenVon", 2125)
                .append("Voraussetzung", 5001));
        vorlesungen.add(new Document("VorlNr", 4052)
                .append("Titel", "Logik")
                .append("SWS", 4)
                .append("GelesenVon", 2125));
        BasicDBList voraussetzungen = new BasicDBList();
        voraussetzungen.add(5043);
        voraussetzungen.add(5041);
        vorlesungen.add(new Document("VorlNr", 5052)
                .append("Titel", "Wissenschaftstheorie")
                .append("SWS", 3)
                .append("GelesenVon", 2126)
                .append("Voraussetzung", voraussetzungen)); 
        vorlesungen.add(new Document("VorlNr", 5216)
                .append("Titel", "Bioethik")
                .append("SWS", 2)
                .append("GelesenVon", 2126)
                .append("Voraussetzung", 5041));
        vorlesungen.add(new Document("VorlNr", 5259)
                .append("Titel", "Der Wiener Kreis")
                .append("SWS", 2)
                .append("GelesenVon", 2133)
                .append("Voraussetzung", 5052));
        vorlesungen.add(new Document("VorlNr", 5022)
                .append("Titel", "Glaube und Wissen")
                .append("SWS", 2)
                .append("GelesenVon", 2134));
        vorlesungen.add(new Document("VorlNr", 4630)
                .append("Titel", "Die 3 Kritiken")
                .append("SWS", 4)
                .append("GelesenVon", 2137));
        
        vorlesung.insertMany(vorlesungen);
        
        mongo.close();
    }
    
    public void initAssistenten(){
        MongoClient mongo = new MongoClient( "localhost" , 27017 );
        MongoDatabase database = mongo.getDatabase("unidb");
        MongoCollection<Document> assistent = database.getCollection("assistenten");
        assistent.drop();
        
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
               .append("Boss", 2134));
        
        assistent.insertMany(assistenten);
        
        mongo.close();
    }
    
    public void initPruefen(){
        MongoClient mongo = new MongoClient( "localhost" , 27017 );
        MongoDatabase database = mongo.getDatabase("unidb");
        MongoCollection<Document> pruefungen = database.getCollection("pruefungen");
        pruefungen.drop();
        
        ArrayList<Document> pruefung = new ArrayList<>();
        pruefung.add(new Document("Legi", 28106)
               .append("VorlNr", 5001)
               .append("PersNr", 2126)
               .append("Note", 1));
        pruefung.add(new Document("Legi", 25403)
               .append("VorlNr", 5041)
               .append("PersNr", 2125)
               .append("Note", 2));
        pruefung.add(new Document("Legi", 27550)
               .append("VorlNr", 4630)
               .append("PersNr", 2137)
               .append("Note", 2));
        
        pruefungen.insertMany(pruefung);
        
        mongo.close();
    }
}
