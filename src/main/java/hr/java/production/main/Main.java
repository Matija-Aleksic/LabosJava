package hr.java.production.main;

import hr.java.production.Enum.City;
import hr.java.production.exception.DuplicateItemException;
import hr.java.production.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.*;


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


        ArrayList<Category> categories = getCategories(scanner);

        ArrayList<Item> items = getItems(scanner, categories);

        ArrayList<Factory> factories = (ArrayList<Factory>) getFactories(scanner, items);

        ArrayList<Store> stores = getStores(scanner, items);

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


        Factory largestVolumeFactory = factories.get(0);
        Item largestVolumeItem = factories.get(0).getItems()[0];
        for (Factory factory : factories) {
            for (Item item : factory.getItems()) {
                if (item.getVolume().compareTo(largestVolumeItem.getVolume()) > 0) {
                    largestVolumeFactory = factory;
                    largestVolumeItem = item;
                }
            }
        }
        System.out.println("Factory with largest volume item: " + largestVolumeFactory.getName());

        Store cheapestStore = stores.get(0);
        Item cheapestItem = stores.get(0).getItems().get(0);
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

    private static ArrayList<Store> getStores(Scanner scanner, ArrayList<Item> items) {
        ArrayList<Store> stores = new ArrayList<>();

        int i = 0;
        try {
            for (i = 0; i < 2; i++) {
                System.out.println("Unesite podatke za trgovinu " + (i + 1) + ":");
                System.out.println("Naziv:");
                String storeName = scanner.nextLine();

                System.out.println("Web adresa:");
                String webAddress = scanner.nextLine();

                Store store = new Store(storeName, webAddress, items);
                stores.add(store);
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please try again.");
            i--;
        }

        return stores;
    }

    private static ArrayList<Category> getCategories(Scanner scanner) {
        ArrayList<Category> categories = new ArrayList<>();
        ArrayList<String> existingCategoryNames = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            System.out.println("Unesite naziv kategorije " + (i + 1) + ":");
            String categoryName = scanner.nextLine();

            if (existingCategoryNames.contains(categoryName)) {
                System.out.println("Kategorija s istim imenom već postoji. Unesite kategoriju s drugačijim imenom.");
                i--;
            } else {
                Category category = new Category(categoryName);
                categories.add(category);
                existingCategoryNames.add(categoryName);
            }
        }

        return categories;
    }

    private static ArrayList<Item> getItems(Scanner scanner, ArrayList<Category> categories) {
        ArrayList<Item> items = new ArrayList<>();
        try {
            for (int i = 0; i < 5; i++) {
                System.out.println("Unesite podatke za artikal " + (i + 1) + ":");
                System.out.println("Dali je artikal Hrana/jestiv? (y/n) ili laptop");

                String choice = scanner.nextLine();
                if (choice.equals("y")) {
                    System.out.println("Trenutno postoji Bread i Milk");
                    choice = scanner.nextLine();

                    if (choice.equals("bread") || choice.equals("milk")) {
                        int weight;

                        System.out.println("Naziv:");
                        String itemName = scanner.nextLine();

                        Category itemCategory = categories.get(0);

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
                            Bread bread = new Bread(itemName, itemCategory, width, height, length, productionCost, sellingPrice, weight);
                            bread.calculateKilocalories();
                            items.add(bread);
                        } else if (choice.equals("milk")) {
                            Milk milk = new Milk(itemName, itemCategory, width, height, length, productionCost, sellingPrice, weight);
                            milk.calculateKilocalories();
                            items.add(milk);
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

                    Laptop laptop = new Laptop(itemName, categories.get(0), width, height, length, productionCost, sellingPrice, warrantyDuration);
                    items.add(laptop);
                } else {
                    System.out.println("Naziv:");
                    String itemName = scanner.nextLine();

                    System.out.println("Kategorija (unesite broj 2 ili 3):");
                    int categoryIndex = Integer.parseInt(scanner.nextLine()) - 1;
                    Category itemCategory = categories.get(categoryIndex);

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

                    boolean isDuplicate = false;
                    for (Item item : items) {
                        if (item.getName().equals(itemName)) {
                            isDuplicate = true;
                            break;
                        }
                    }

                    if (isDuplicate) {
                        throw new DuplicateItemException(itemName);
                    }

                    Item newItem = new Item(itemName, itemCategory, width, height, length, productionCost, sellingPrice);
                    items.add(newItem);
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please try again.");
        } catch (DuplicateItemException e) {
            System.out.println("unesen duplikat " + e.getMessage());
        }

        return items;
    }


    private static Set<Factory> getFactories(Scanner scanner, ArrayList<Item> items) {
        Set<Factory> factories = new HashSet<>();

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
                        .setCity(City.valueOf(city))
                        .build();

                Factory factory = new Factory(factoryName, factoryAddress, new HashSet<>(items).toArray(new Item[0]));
                factories.add(factory);
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please try again.");
                i--;
            }
        }

        return factories;
    }


}
