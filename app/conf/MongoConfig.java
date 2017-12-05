package conf;

import com.mongodb.rx.client.MongoClient;
import com.mongodb.rx.client.MongoClients;
import com.mongodb.rx.client.MongoDatabase;

public class MongoConfig {

    // TODO find a way to declare these as application properties and use it as a datastore
    private static final String MONGODB_URI = "mongodb://192.168.99.100:27017";
    public static final MongoClient client = MongoClients.create(MONGODB_URI);
    private static final String FAILUREMODE_DB_NAME = "failuremodecatalog";
    public static final MongoDatabase db = client.getDatabase(FAILUREMODE_DB_NAME);

}
