package org.bcit.comp2522.project;

import static processing.core.PApplet.*;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.*;
import java.io.File;
import org.bson.Document;
import processing.data.JSONObject;



/**
 * The DatabaseHandler class handles the connection to the
 * MongoDB Atlas database and provides methods for inserting
 * and retrieving data from the database.

 * @author Bhavnoor Saroya
 */
public class DatabaseHandler {
  /**
   * The Database.
   */
  static MongoDatabase database;
  /**
   * The Collection.
   */
  MongoCollection<Document> collection;

  /**
   * single instance as the class is a singleton.
   */
  private static DatabaseHandler singleInstance;

  /**
   * Constructs a new DatabaseHandler object with a default collection name.
   */
  private DatabaseHandler() {
    String password = System.getenv("MONGO_KEY");
    System.out.println(password);
    ConnectionString connectionString = new ConnectionString(
            "mongodb+srv://client:"
                    + password
                    + "@javaproj.i875zgv.mongodb.net/?retryWrites=true&w=majority");
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

  }

  /**
   * Gets instance.
   *
   * @return the instance
   */
  public static DatabaseHandler getInstance() {
    if (singleInstance == null) {
      singleInstance = new DatabaseHandler();
    }
    return singleInstance;
  }


  /**
   * Inserts a SaveState object into the database.
   *
   * @param saveState the SaveState object to insert.
   */
  public void put(SaveState saveState) {
    Document player = new Document();
    // also gonna append name but later
    player.append("name", System.getProperty("user.name"));
    player.append("health", saveState.loadPlayerHealth());
    player.append("score", saveState.loadPlayerScore());


    database.getCollection("Players").insertOne(player);
  }



  /**
   * Save to db, puts player data to db.
   */
  public void saveToDb() {
    System.out.println("saveToDB called!");

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
  }

  /**
   * Get top three players method.
   *
   * @return arr of top three players
   *
   */
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


  /**
   * The entry point of application.
   *
   * @param args the input arguments
   */
  public static void main(String[] args) {

    DatabaseHandler q = new DatabaseHandler();

    q.saveToDb();


  }
}
