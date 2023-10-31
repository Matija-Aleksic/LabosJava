package hr.java.production.main;

import hr.java.production.exception.DuplicateItemException;
import hr.java.production.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.nio.channels.ScatteringByteChannel;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;


/**
 * The type Main.
 */
public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        logger.info("pokrenut main");
        Scanner scanner = new Scanner(System.in);

        Category[] categories = getCategories(scanner);

        Item[] items = getItems(scanner, categories);

        Factory[] factories = getFactories(scanner, items);

        Store[] stores = getStores(scanner, items);

        System.out.println("Uneseni podaci:");
        System.out.println("Kategorije:");
        for (Category category : categories) {
            System.out.println(category.getName());
        }

        System.out.println("Artikli:");
        for (Item item : items) {
            System.out.println(item.getName());
        }

        System.out.println("Tvornice:");
        for (Factory factory : factories) {
            System.out.println(factory.getName());
        }

        System.out.println("Trgovine:");
        for (Store store : stores) {
            System.out.println(store.getName());
        }


        Factory largestVolumeFactory = factories[0];
        Item largestVolumeItem = factories[0].getItems()[0];
        for (Factory factory : factories) {
            for (Item item : factory.getItems()) {
                if (item.getVolume().compareTo(largestVolumeItem.getVolume()) > 0) {
                    largestVolumeFactory = factory;
                    largestVolumeItem = item;
                }
            }
        }
        System.out.println("Factory with largest volume item: " + largestVolumeFactory.getName());

        Store cheapestStore = stores[0];
        Item cheapestItem = stores[0].getItems()[0];
        for (Store store : stores) {
            for (Item item : store.getItems()) {
                if (item.getSellingPrice().compareTo(cheapestItem.getSellingPrice()) < 0) {
                    cheapestStore = store;
                    cheapestItem = item;
                }
            }
        }
        System.out.println("Store with the cheapest item: " + cheapestStore.getName());
        System.out.println("Cheapest item: " + cheapestItem.getName());

        Item foodWithMaxKilocalories = null;
        double maxKilocalories = 0;
        Item foodWithMaxTotalPrice = null;
        BigDecimal maxTotalPrice = new BigDecimal(0);
        Laptop laptopWithShortestWarranty = null;
        int shortestWarrantyDuration = Integer.MAX_VALUE;

        for (Item item : items) {
            if (item instanceof Edible) {

                Edible edibleItem = (Edible) item;
                int kilocalories = edibleItem.calculateKilocalories();
                BigDecimal totalPrice = BigDecimal.valueOf(0);

                if (item instanceof Bread) {
                    Bread bread = (Bread) item;
                    totalPrice = edibleItem.calculatePrice(bread.getWeight());
                } else if (item instanceof Milk) {
                    Milk milk = (Milk) item;
                    totalPrice = edibleItem.calculatePrice(milk.getWeight());
                }

                if (kilocalories > maxKilocalories) {
                    maxKilocalories = kilocalories;
                    foodWithMaxKilocalories = item;
                }

                if (totalPrice.compareTo(maxTotalPrice) > 0) {
                    maxTotalPrice = totalPrice;
                    foodWithMaxTotalPrice = item;
                }
            }
            if (item instanceof Laptop) {
                Laptop laptop = (Laptop) item;
                int warrantyDuration = laptop.getWarrantyDurationInMonths();
                if (warrantyDuration < shortestWarrantyDuration) {
                    shortestWarrantyDuration = warrantyDuration;
                    laptopWithShortestWarranty = laptop;
                }
            }
        }

        if (foodWithMaxKilocalories != null) {
            System.out.println("Namirnica s najvećim brojem kilokalorija:");
            System.out.println("Naziv: " + foodWithMaxKilocalories.getName());
            System.out.println("Broj kilokalorija: " + maxKilocalories);
        }

        if (foodWithMaxTotalPrice != null) {
            System.out.println("Namirnica s najvećom ukupnom cijenom s obzirom na težinu:");
            System.out.println("Naziv: " + foodWithMaxTotalPrice.getName());
            System.out.println("Ukupna cijena: " + maxTotalPrice);
        }

        if (laptopWithShortestWarranty != null) {
            System.out.println("Laptop s najkraćim garantnim rokom:");
            System.out.println("Naziv: " + laptopWithShortestWarranty.getName());
            System.out.println("Trajanje garantnog roka (u mjesecima): " + shortestWarrantyDuration);
        }

    }

    private static Store[] getStores(Scanner scanner, Item[] items) {
        Store[] stores = new Store[2];
        int i = 0;
        try {
            for (i = 0; i < 2; i++) {
                System.out.println("Unesite podatke za trgovinu " + (i + 1) + ":");
                System.out.println("Naziv:");
                String storeName = scanner.nextLine();

                System.out.println("Web adresa:");
                String webAddress = scanner.nextLine();

                stores[i] = new Store(storeName, webAddress, items);
            }

        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please try again.");
            i--;
        }

        return stores;
    }

    private static Category[] getCategories(Scanner scanner) {
        Category[] categories = new Category[3];
        List<String> existingCategoryNames = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            System.out.println("Unesite naziv kategorije " + (i + 1) + ":");
            String categoryName = scanner.nextLine();

            if (existingCategoryNames.contains(categoryName)) {

                System.out.println("Kategorija s istim imenom već postoji. Unesite kategoriju s drugačijim imenom.");
                i--;
            } else {
                categories[i] = new Category(categoryName);
                existingCategoryNames.add(categoryName);
            }
        }

        return categories;
    }

    private static Item[] getItems(Scanner scanner, Category[] categories) {
        Item[] items = new Item[5];
        int i = 0;
        try {
            for (i = 0; i < 5; i++) {
                System.out.println("Unesite podatke za artikal " + (i + 1) + ":");
                System.out.println("Dali je artikal Hrana/jestiv? (y/n) ili laptop");

                String choice = scanner.nextLine();
                if (choice.equals("y")) {
                    System.out.println("Trenutno postoji Bread i Milk");
                    choice = scanner.nextLine();

                    if (choice.equals("bread") || choice.equals("milk")) {
                        int weight;
                        int j = 0;
                        int n = 0;

                        System.out.println("Naziv:");
                        String itemName = scanner.nextLine();

                        Category itemCategory = categories[0];

                        System.out.println("Širina:");
                        BigDecimal width = new BigDecimal(scanner.nextLine());

                        System.out.println("Visina:");
                        BigDecimal height = new BigDecimal(scanner.nextLine());

                        System.out.println("Dužina:");
                        BigDecimal length = new BigDecimal(scanner.nextLine());

                        System.out.println("Težina:");
                        weight = Integer.parseInt(scanner.nextLine());

                        System.out.println("Trošak proizvodnje:");
                        BigDecimal productionCost = new BigDecimal(scanner.nextLine());

                        System.out.println("Prodajna cijena:");
                        BigDecimal sellingPrice = new BigDecimal(scanner.nextLine());

                        if (choice.equals("bread")) {
                            Bread[] breads = new Bread[1];
                            breads[j] = new Bread(itemName, itemCategory, width, height, length, productionCost, sellingPrice, weight);
                            items[i] = breads[j];
                            breads[j].calculateKilocalories();
                            j++;

                        } else if (choice.equals("milk")) {
                            Milk[] milks = new Milk[1];
                            milks[n] = new Milk(itemName, itemCategory, width, height, length, productionCost, sellingPrice, weight);
                            items[i] = milks[n];
                            milks[n].calculateKilocalories();
                            n++;
                        }
                    }
                } else if (choice.equals("laptop")) {
                    System.out.println("Naziv:");
                    String itemName = scanner.nextLine();

                    System.out.println("Širina:");
                    BigDecimal width = new BigDecimal(scanner.nextLine());

                    System.out.println("Visina:");
                    BigDecimal height = new BigDecimal(scanner.nextLine());

                    System.out.println("Dužina:");
                    BigDecimal length = new BigDecimal(scanner.nextLine());

                    System.out.println("Trošak proizvodnje:");
                    BigDecimal productionCost = new BigDecimal(scanner.nextLine());

                    System.out.println("Prodajna cijena:");
                    BigDecimal sellingPrice = new BigDecimal(scanner.nextLine());

                    System.out.println("Trajanje garantnog roka (u mjesecima):");
                    int warrantyDuration = Integer.parseInt(scanner.nextLine());

                    Laptop laptop = new Laptop(itemName, categories[0], width, height, length, productionCost, sellingPrice, warrantyDuration);
                    items[i] = laptop;
                } else {
                    System.out.println("Naziv:");
                    String itemName = scanner.nextLine();

                    System.out.println("Kategorija (unesite broj 2 ili 3):");
                    int categoryIndex = Integer.parseInt(scanner.nextLine()) - 1;
                    Category itemCategory = categories[categoryIndex];

                    System.out.println("Širina:");
                    BigDecimal width = new BigDecimal(scanner.nextLine());

                    System.out.println("Visina:");
                    BigDecimal height = new BigDecimal(scanner.nextLine());

                    System.out.println("Dužina:");
                    BigDecimal length = new BigDecimal(scanner.nextLine());

                    System.out.println("Trošak proizvodnje:");
                    BigDecimal productionCost = new BigDecimal(scanner.nextLine());

                    System.out.println("Prodajna cijena:");
                    BigDecimal sellingPrice = new BigDecimal(scanner.nextLine());

                    for (int a = 0; a< items.length; a++){
                        if (items[a].getName()==itemName){
                            throw new DuplicateItemException(itemName);
                        }
                    }

                    items[i] = new Item(itemName, itemCategory, width, height, length, productionCost, sellingPrice);
                }
            }

        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please try again.");
            i--;
        } catch (DuplicateItemException e) {
            System.out.println("unesen duplikat"+e.getMessage() );
        }


        return items;
    }


    private static Factory[] getFactories(Scanner scanner, Item[] items) {
        Factory[] factories = new Factory[2];
        for (int i = 0; i < 2; i++) {
            try {
                System.out.println("Unesite podatke za tvornicu " + (i + 1) + ":");
                System.out.println("Naziv:");
                String factoryName = scanner.nextLine();

                System.out.println("Ulica:");
                String street = scanner.nextLine();

                System.out.println("Broj kuće:");
                String houseNumber = scanner.nextLine();

                System.out.println("Grad:");
                String city = scanner.nextLine();

                System.out.println("Poštanski broj:");
                String postalCode = scanner.nextLine();

                Address factoryAddress = new Address.Builder()
                        .setStreet(street)
                        .setHouseNumber(houseNumber)
                        .setCity(city)
                        .setPostalCode(postalCode)
                        .build();

                factories[i] = new Factory(factoryName, factoryAddress, items);
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please try again.");
                i--;
            }
        }
        return factories;
    }


}
