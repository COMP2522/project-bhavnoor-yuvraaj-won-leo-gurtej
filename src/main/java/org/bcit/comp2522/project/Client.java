package org.bcit.comp2522.project;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.ParseException;

public class Client {
  // bunch of instance variables
  private static final String GET = "GET";
  private static final String POST = "POST";
  private String host;
  private final int port;

  /**
   * Constructs new client object.
   * @param port int
   */
  public Client(int port) {
    this.port = port;
    try {
      host = InetAddress.getLocalHost().getHostName();
    } catch (UnknownHostException e) {
      System.err.println("Local host name could not be resolved into an address.");
    }
  }

  public JSONObject sendRequest(String req) throws ParseException, IOException {
    // create setup objects
    Socket socket = null;
    ObjectOutputStream outputStream = null;
    ObjectInputStream inputStream = null;

    // establish socket connection to server
    try {
      socket = new Socket(this.host, this.port);
    } catch (IOException e) {
      System.err.println("Error creating the socket.");
    }

    // open output stream
    try {
      assert socket != null;
      outputStream = new ObjectOutputStream(socket.getOutputStream());
    } catch (Exception e) {
      System.err.println("Error connecting to ouput stream socket.");
    }
    // if runs successfully
    System.out.println("Output stream connecting successfull.");

    // write to socket output stream
    try {
      assert outputStream != null;
      outputStream.writeObject(req);
    } catch (Exception e) {
      System.err.println("Error writing to socket output stream");
    }

    // read server response message
    try {
      inputStream = new ObjectInputStream(socket.getInputStream());
    } catch (Exception e) {
      System.err.println("Error connecting to input stream socket.");
    }

    // create JSON string from server response
    String jsonString = null;
    try {
      assert inputStream != null;
      jsonString = (String) inputStream.readObject();
    } catch (Exception e) {
      System.err.println("Error reading server response.");
    }

    // Parse jsonString to jsonObject
    JSONObject jsonRes = null;
    try {
      JSONParser parser = new JSONParser();
      jsonRes = (JSONObject) parser.parse(jsonString);
    } catch (org.json.simple.parser.ParseException e) {
      System.err.println("Error parsing JSON string.");
    }

    // close resources
    try {
      outputStream.close();
      inputStream.close();
    } catch (Exception e) {
      System.err.println("Error closing stream resources.");
    }

    return jsonRes;
  }

  public String createJSON(String reqType, SaveState saveState) {
    // create JSON object to be sent to the server
    JSONObject req = new JSONObject();

    // add KV pairs
    req.put("reqType", reqType);

    // POST request
    if (reqType.equals(POST)) {
      System.out.println("Creating POST request JSONObject");
      req.put("health", saveState.loadPlayerHealth());
      req.put("score", saveState.loadPlayerScore());
    }
    else if (reqType.equals(GET)) {
      System.out.println("Creating GET request JSONObject");
      // to be completed yet
    }
    else {
      System.err.println("Invalid request type");
    }

    // return JSONObject as jsonString
    return req.toJSONString();
  }

  public static void main(String[] args) throws ParseException, IOException {
    Client client = new Client(8000);
    SaveState saveState = new SaveState();
    String request = client.createJSON("GET", saveState);

    //testing multiple requests
    while (true) {
      JSONObject jsonRes = client.sendRequest(request);
      System.out.println("In-loop response: " + jsonRes.toJSONString());
    }
  }
  }
