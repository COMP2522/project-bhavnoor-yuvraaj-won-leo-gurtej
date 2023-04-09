package org.bcit.comp2522.project;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import processing.data.JSONArray;
import processing.data.JSONObject;

import java.io.File;

import static processing.core.PApplet.loadJSONArray;
import static processing.core.PApplet.loadJSONObject;

/**
 * The DatabaseHandler class handles the connection to the
 * MongoDB Atlas database and provides methods for inserting
 * and retrieving data from the database.
 */
public class DatabaseHandler {
    static MongoDatabase database;
    MongoCollection<Document> collection;

//  run below stuff only once: this is just for set up
    //create document
//  database.createCollection("placeholder");
//  document.append(key, value);
//  document.append(otherkey, otherval);


    //insert to db
//  database.getColletion("placeholder").insertOne(document);

//  Document find = database.getCollection("placeholder").find(key, value).first();

//  System.out.println(find);

    /**
     * Constructs a new DatabaseHandler object with a specified collection name.
     *
     * @param collection the name of the collection in the database.
     */
    public DatabaseHandler(MongoCollection collection){
        //
        String password = "${MONGO_KEY}";//hopefully nobody is able to find the supersecretpassoword
        ConnectionString connectionString = new ConnectionString(
        "mongodb+srv://Client:" + password + "@javaproj.i875zgv.mongodb.net/?retryWrites=true&w=majority");
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .serverApi(ServerApi.builder()
                        .version(ServerApiVersion.V1)
                        .build())
                .build();
        MongoClient mongoClient = MongoClients.create(settings);
        database = mongoClient.getDatabase("javaProj");
        this.collection = collection;
    }

    /**
     * Constructs a new DatabaseHandler object with a default collection name.
     */
    public DatabaseHandler(){
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

//        check if user exists

//    create document
//    database.createCollection(System.getProperty("user.dir"));

//    add stuff to document
    Document document = new Document();
//    document.append("name", "Ram");
//    document.append("age", 26);
//    document.append("city", "Hyderabad");

//    insert document to database
//    database.getCollection("students").insertOne(document);


//    Document find = database.getCollection("students").find(eq("name", "Ram")).first();

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


    public void saveToDB() {
        new Thread(() -> {
            Document document = new Document();
            JSONObject playerStats = new JSONObject();

            File file = new File("player-stats.json");
            playerStats = loadJSONObject(file);
            System.out.println(playerStats);

            String jsonString = playerStats.toString();

            document.append("PlayerStats", jsonString);
            database.getCollection("Players").insertOne(document);
            System.out.println("Uploaded Player data to database!");
        }).start();
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


//    System.getProperty("user.name"); how to get user name;
    public static void main(String[] args){

        DatabaseHandler q = new DatabaseHandler();

        q.database.createCollection("Players");
        q.saveToDB();
    }
}
