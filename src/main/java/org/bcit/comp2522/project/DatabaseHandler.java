package org.bcit.comp2522.project;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;




public class DatabaseHandler {
    MongoDatabase database;

    String collection;

//  run below stuff only once: this is just for set up
    //create document
//  database.createCollection("placeholder");
//  document.append(key, value);
//  document.append(otherkey, otherval);


    //insert to db
//  database.getColletion("placeholder").insertOne(document);

//  Document find = database.getCollection("placeholder").find(key, value).first();

//  System.out.println(find);


    public DatabaseHandler(String collection){
        String password = "${MONGO_KEY}";//hopefully nobody is able to find the supersecretpassoword
        ConnectionString connectionString = new ConnectionString(
                "mongodb+srv://Noooooooor:" + password + "@2522.yczfyxd.mongodb.net/?retryWrites=true&w=majority");
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

    public DatabaseHandler(){
        String password = "SuperSecretPassword";//hopefully nobody is able to find the supersecretpassoword
        ConnectionString connectionString = new ConnectionString(
                "mongodb+srv://Noooooooor:" + password + "@2522.yczfyxd.mongodb.net/?retryWrites=true&w=majority");
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .serverApi(ServerApi.builder()
                        .version(ServerApiVersion.V1)
                        .build())
                .build();
        MongoClient mongoClient = MongoClients.create(settings);
        database = mongoClient.getDatabase("javaProj");
    }

    public void put(String key, String val){
        Document doc = new Document();
        doc.append(key, val);

        database.getCollection(collection).insertOne(doc);
        //did with Thread as well, however may just call the method in a thread
//        new Thread(()-> database.getCollection(collection).insertOne(doc)).start();
    }
}
