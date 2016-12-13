/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.dmg;

import com.mongodb.*;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.client.ListCollectionsIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import static com.mongodb.client.model.Filters.eq;
import java.util.List;
import java.util.Set;
import org.bson.Document;


public class UniDB {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        new InitDB().initDB();
        
        
        
        
        /*MongoClient mongo = new MongoClient( "localhost" , 27017 );
        //DB db = mongo.getDB("unidb");
        
        MongoDatabase database = mongo.getDatabase("unidb");
        
        MongoCollection<Document> collection = database.getCollection("prof");
        
        Document doc = new Document("name", "MongoDB")
               .append("type", "database")
               .append("count", 1)
               .append("info", new Document("x", 203).append("y", 102));
        
        //collection.insertOne(doc);
        collection.deleteOne(eq("name", "Test"));
        Document myDoc = collection.find().first();
        System.out.println(myDoc.toJson());*/
        
    }
    
}
