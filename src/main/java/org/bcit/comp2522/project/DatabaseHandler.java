package org.bcit.comp2522.project;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.*;
import org.bson.Document;
import processing.data.JSONArray;
import processing.data.JSONObject;

import javax.print.Doc;
import javax.xml.crypto.Data;
import java.io.File;

import static processing.core.PApplet.*;

/**
 * The DatabaseHandler class handles the connection to the
 * MongoDB Atlas database and provides methods for inserting
 * and retrieving data from the database.
 */
public class DatabaseHandler {
    static MongoDatabase database;
    MongoCollection<Document> collection;

    private static DatabaseHandler singleInstance;

    /**
     * Constructs a new DatabaseHandler object with a default collection name.
     */
    private DatabaseHandler(){
        String password = System.getenv("MONGO_KEY");//hopefully nobody is able to find the supersecretpassoword
        System.out.println(password);
        ConnectionString connectionString = new ConnectionString(
                "mongodb+srv://client:" + password + "@javaproj.i875zgv.mongodb.net/?retryWrites=true&w=majority");
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .serverApi(ServerApi.builder()
                        .version(ServerApiVersion.V1)
                        .build())
                .build();
        MongoClient mongoClient = MongoClients.create(settings);
        database = mongoClient.getDatabase("JavaProj");
        collection = database.getCollection("Players");
        System.out.println(database);
//    Document document = new Document();

    }
    public static DatabaseHandler getInstance(){
        if (singleInstance == null){
            singleInstance = new DatabaseHandler();
        }
        return singleInstance;
    }

//    public void put(String key, String val){
//        Document doc = new Document();
//        doc.append(key, val);
//
//        database.getCollection(collection).insertOne(doc);
//        //did with Thread as well, however may just call the method in a thread
////        new Thread(()-> database.getCollection(collection).insertOne(doc)).start();
//    }

    /**
     * Inserts a SaveState object into the database.
     *
     * @param saveState the SaveState object to insert.
     */
    public void put(SaveState saveState){
        Document player = new Document();
        // also gonna append name but later
        player.append("name", System.getProperty("user.name"));
        player.append("health", saveState.loadPlayerHealth());
        player.append("score", saveState.loadPlayerScore());


        database.getCollection("Players").insertOne(player);
        //did with Thread as well, however may just call the method in a thread
//        new Thread(()-> database.getCollection(collection).insertOne(doc)).start();
    }


//    public void saveToDB() {
//        new Thread(() -> {
//            Document document = new Document();
//            JSONObject playerStats = new JSONObject();
//
//            File file = new File("player-stats.json");
//            playerStats = loadJSONObject(file);
//            System.out.println(playerStats);
//
//            String jsonString = playerStats.toString();
//
//            Document doc = Document.parse(jsonString);
//
//            document.append("PlayerStats", doc);
//            database.getCollection("Players").insertOne(document);
//            System.out.println("Uploaded Player data to database!");
//        }).start();
//    }

    public void saveToDB() {
        System.out.println("saveToDB called!");
//        new Thread(() -> {
            String username = System.getProperty("user.name");
            Document query = new Document("name", "PlayerStats-" + username);
            FindIterable<Document> result = database.getCollection("Players").find(query);

            JSONObject playerStats = loadJSONObject(new File("player-stats.json"));

            Document doc = Document.parse(playerStats.toString());

            if (result.first() != null) {
                // If a document with the specified name exists, update it
                Document update = new Document("$set", new Document("PlayerStats", doc));
                database.getCollection("Players").updateOne(query, update);
                System.out.println("Updated Player data in database!");
            } else {
                // If a document with the specified name doesn't exist, create it
                Document document = new Document("name", "PlayerStats-" + username)
                        .append("PlayerStats", doc);
                database.getCollection("Players").insertOne(document);
                System.out.println("Uploaded Player data to database!");
            }
//        }).start();
    }



    /** we'll prolly use this method to retrieve all player stats stored in DB
     * DON'T DELETE, WILL COMPLETE IMPLEMENTATION LATER
     * REQUIRED: PLAYER NAME
    */
//    public SaveState get(SaveState ss) {
//        SaveState saveState = new SaveState();
//        FindIterable<Document> res = database.getCollection("players").find();
//        res.forEach((d) -> {
//            if (!d.get("name").toString().equals(gs.me.name)) {
//                Player p = new Player(
//                  Float.parseFloat(d.get("x").toString()),
//                  Float.parseFloat(d.get("y").toString()),
//                  Color.decode(d.get("color").toString()),
//                  d.get("name").toString()
//                );
//                newGs.players.add(p);
//            }
//        });
//        return newGs;
//    }

    public Document[] getTopThreePlayersByScore() {
        Document[] topThreePlayers = new Document[3];
        int i = 0;

        FindIterable<Document> results = database.getCollection("Players")
                .find()
                .sort(new Document("PlayerStats.Score", -1))
                .limit(3);

        for (Document doc : results) {
            topThreePlayers[i++] = doc;
        }

        return topThreePlayers;
    }



    //    System.getProperty("user.name"); how to get user name;
    public static void main(String[] args){

        DatabaseHandler q = new DatabaseHandler();

//        q.database.createCollection("Players");
        q.saveToDB();


    }
}
