package db.mongodb;

import java.text.ParseException;

import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.IndexOptions;

public class MongoDBTableCreation {
	public static void main(String[] args) throws ParseException {
		MongoClient mongoClient = MongoClients.create();
		MongoDatabase db = mongoClient.getDatabase(MongoDBUtil.DB_NAME);
		
		
		db.getCollection("users").drop();
		db.getCollection("items").drop();
		
		IndexOptions indexOptions = new IndexOptions().unique(true);
		db.getCollection("users").createIndex(new Document("user_id", 1), indexOptions);
		db.getCollection("items").createIndex(new Document("item_id", 1), indexOptions);
		
		db.getCollection("users").insertOne(
				new Document().append("user_id", "mongotester").append("password", "28080b0ea5f4a6733c6f3b0ef9e8220f")
						.append("first_name", "Josh").append("last_name", "Foer"));
		
		mongoClient.close();
		System.out.println("import done");
		
		/*
		String userId = "1111";
		FindIterable<Document> iterable =
		        db.getCollection("users").find(new Document("user_id", userId));
	    Document document = iterable.first();
	    String firstName = document.getString("first_name");
	    String lastName = document.getString("last_name");
	    System.out.println(firstName + " " + lastName) ;
	    */
	}
}
