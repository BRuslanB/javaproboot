package kz.bitlab.javapro.javaproboot.db;

import java.util.ArrayList;

public class DBManager {
    private static ArrayList<Item> items = new ArrayList<>();
    private static Long id = 5L;

    static {
        items.add(new Item(1L, "Iphone 13 pro", 40, 45000));
        items.add(new Item(2L, "Iphone 12 pro", 30, 40000));
        items.add(new Item(3L, "Iphone 11 pro", 20, 35000));
        items.add(new Item(4L, "Iphone 10 pro", 10, 300000));
    }

    public static ArrayList<Item> getAllItems() {
        return items;
    }

    public static void addItem(Item item){
        item.setId(id);
        items.add(item);
        id++;
    }

    public static Item getItem(Long id){
        return items.stream()
                .filter(item -> item.getId() == id)
                .findFirst().orElse(null);
    }
}