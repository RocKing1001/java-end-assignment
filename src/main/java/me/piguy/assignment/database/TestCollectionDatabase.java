package me.piguy.assignment.database;

import me.piguy.assignment.models.Item;
import me.piguy.assignment.models.Role;
import me.piguy.assignment.models.User;

/**
 * CollectionDatabase but with test data which gets set upon initialisation
 */
public class TestCollectionDatabase extends CollectionDatabase {
    public TestCollectionDatabase() {
        super();
    }

    // this is a separate function because I don't want
    // the config manager to ever create new classes within its classes
    // this is to prevent recursion
    public void initTestData() {
        // users
        KVDatabase<String,User> users = (KVDatabase<String, User>) getCollection(DBCollections.Users);
        users.setValue("joey_nonsensejp", new User("Joey Bizinger", Role.Sales, "nonsense"));
        users.setValue("cdawgva", new User("Connor Colquhoun", Role.Sales, "jumpking"));
        users.setValue("notgrant", new User("Garnt \"Grant\" Maneetapho", Role.IT, "isekai"));
        users.setValue("1", new User("Garnt \"Grant\" Maneetapho", Role.IT, "1"));

        // products
        KVDatabase<String, Item> products = (KVDatabase<String, Item>) getCollection(DBCollections.Products);
        products.setValue("Fender Stratocaster", new Item(10, "Fender Stratocaster", "Guitar", 2199.99));

    }
}
