package hr.java.production.main;

import hr.java.production.genericsi.FoodStore;
import hr.java.production.genericsi.TechnicalStore;
import hr.java.production.model.*;
import hr.java.production.sort.ProductionSorter;
import hr.java.production.sort.VolumeComparator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

import static hr.java.production.model.Address.Builder.loadAddressesFromFile;
import static hr.java.production.model.Category.loadCategoriesFromFile;
import static hr.java.production.model.Factory.loadFactoriesFromFile;
import static hr.java.production.model.Item.loadItemsFromFile;
import static hr.java.production.model.Store.loadStoresFromFile;


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

        List<Address> addressList = loadAddressesFromFile("E:\\Projects\\javaLabos\\Aleksic-1\\dat\\addresses.txt");

        ArrayList<Category> categories = loadCategoriesFromFile("E:\\Projects\\javaLabos\\Aleksic-1\\dat\\categories.txt");

        ArrayList<Item> items = loadItemsFromFile("E:\\Projects\\javaLabos\\Aleksic-1\\dat\\items.txt");

        ArrayList<Factory> factories = loadFactoriesFromFile("E:\\Projects\\javaLabos\\Aleksic-1\\dat\\factories.txt");

        ArrayList<Store> stores = loadStoresFromFile("E:\\Projects\\javaLabos\\Aleksic-1\\dat\\stores.txt");

        ArrayList<Item> tehnika = getTehnical(items);

        ArrayList<Item> hrana = getEdible(items);


        Factory largestVolumeFactory = null;
        Item largestVolumeItem = null;

        for (Factory factory : factories) {
            for (Item item : factory.getItems()) {
                if (largestVolumeFactory == null || item.getVolume().compareTo(largestVolumeItem.getVolume()) > 0) {
                    largestVolumeFactory = factory;
                    largestVolumeItem = item;
                }
            }
        }

        if (largestVolumeFactory != null) {
            System.out.println("Factory with largest volume item: " + largestVolumeFactory.getName());
            System.out.println("Largest volume item: " + largestVolumeItem.getName());
        } else {
            System.out.println("No factories or items found.");
        }
        System.out.println("Factory with largest volume item: " + largestVolumeFactory.getName());

        Store cheapestStore = null;
        Item cheapestItem = null;

        for (Store store : stores) {
            for (Item item : store.getItems()) {
                if (cheapestStore == null || item.getSellingPrice().compareTo(cheapestItem.getSellingPrice()) < 0) {
                    cheapestStore = store;
                    cheapestItem = item;
                }
            }
        }

        if (cheapestStore != null) {
            System.out.println("Store with the cheapest item: " + cheapestStore.getName());
            System.out.println("Cheapest item: " + cheapestItem.getName());
        } else {
            System.out.println("No stores or items found.");
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
            if (item instanceof Edible edibleItem) {

                int kilocalories = edibleItem.calculateKilocalories();
                BigDecimal totalPrice = BigDecimal.valueOf(0);

                if (item instanceof Bread bread) {
                    totalPrice = edibleItem.calculatePrice(bread.getWeight());
                } else if (item instanceof Milk milk) {
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
            if (item instanceof Laptop laptop) {
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


//        Map<Category, List<Item>> categoryItemMap = new HashMap<>();
//
//        ProductionSorter ascendingSorter = new ProductionSorter(true);
//        ProductionSorter descendingSorter = new ProductionSorter(false);
//
//        for (Category category : categories) {
//            categoryItemMap.put(category, new ArrayList<>());
//        }
//
//        for (Item item : items) {
//            Category itemCategory = item.getCategory();
//            categoryItemMap.get(itemCategory).add(item);
//        }
//        for (Category category : categoryItemMap.keySet()) {
//            List<Item> itemList = categoryItemMap.get(category);
//
//            itemList.sort(ascendingSorter);
//            Item cheapestItem2 = itemList.get(0);
//            itemList.sort(descendingSorter);
//            Item mostExpensiveItem = itemList.get(0);
//
//            System.out.println("Najjeftiniji artikal u kategoriji " + category.getName() + ": " + cheapestItem2.getName());
//            System.out.println("Najskuplji artikal u kategoriji " + category.getName() + ": " + mostExpensiveItem.getName());
//        }


        Item mostExpensiveEdible = null;
        Item cheapestEdible = null;
        Item mostExpensiveTechnical = null;
        Item cheapestTechnical = null;

        for (Item item : items) {
            if (item instanceof Edible) {
                if (mostExpensiveEdible == null || item.getSellingPrice().compareTo(mostExpensiveEdible.getSellingPrice()) > 0) {
                    mostExpensiveEdible = item;
                }
                if (cheapestEdible == null || item.getSellingPrice().compareTo(cheapestEdible.getSellingPrice()) < 0) {
                    cheapestEdible = item;
                }
            } else if (item instanceof Technical) {
                if (mostExpensiveTechnical == null || item.getSellingPrice().compareTo(mostExpensiveTechnical.getSellingPrice()) > 0) {
                    mostExpensiveTechnical = item;
                }
                if (cheapestTechnical == null || item.getSellingPrice().compareTo(cheapestTechnical.getSellingPrice()) < 0) {
                    cheapestTechnical = item;
                }
            }
        }

        if (mostExpensiveEdible != null) {
            System.out.println("Most Expensive Edible Item: " + mostExpensiveEdible.getName());
        }
        if (cheapestEdible != null) {
            System.out.println("Cheapest Edible Item: " + cheapestEdible.getName());
        }
        if (mostExpensiveTechnical != null) {
            System.out.println("Most Expensive Technical Item: " + mostExpensiveTechnical.getName());
        }
        if (cheapestTechnical != null) {
            System.out.println("Cheapest Technical Item: " + cheapestTechnical.getName());
        }

        TechnicalStore<Technical> technicalStore = new TechnicalStore<>("tehStore", "www.tehstore.hr", tehnika, 1L);
        FoodStore<Edible> foodStore = new FoodStore<>("tehStore", "www.tehstore.hr", hrana, 1L);

        //sortItemsByVolume((ArrayList<? extends Item>) technicalStore.getTechnicalItems());
        //sortItemsByVolume((ArrayList<? extends Item>) foodStore.getEdibleItems());
        //calculateAveragePriceOfItemsWithAboveAverageVolume(technicalStore.getItems());

        findStoresWithAboveAverageNumberOfItems(technicalStore, foodStore);

        Instant startNonLambdaSort = Instant.now();
        sortItemsByVolumeWithoutLambda(technicalStore.getItems());
        Instant endNonLambdaSort = Instant.now();
        Duration nonLambdaSortDuration = Duration.between(startNonLambdaSort, endNonLambdaSort);
        System.out.println("Vrijeme sortiranja bez lambda izraza: " + nonLambdaSortDuration.toMillis() + " ms");
        Optional<Item> itemWithDiscount = findItemWithDiscountGreaterThanZero(technicalStore.getItems());


        itemWithDiscount.ifPresent(item -> System.out.println("Pronađen artikl s popustom: " + item.getName()));
        if (itemWithDiscount.isEmpty()) {
            System.out.println("Nije pronađen artikl s popustom većim od 0.");
        }


        Map<String, Long> storeItemCount = stores.stream().collect(Collectors.toMap(Store::getName, store -> (long) store.getItems().size()));

        storeItemCount.forEach((store, count) -> System.out.println(store + ": " + count + " items"));

    }


    private static Optional<Item> findItemWithDiscountGreaterThanZero(List<? extends Item> items) {
        return (Optional<Item>) items.stream().filter(item -> item.getDiscount().getDiscountAmount() > 0).findFirst();
    }


    private static void sortItemsByVolume(List<? extends Item> items) {
        Collections.sort(items, (item1, item2) -> item1.getVolume().compareTo(item2.getVolume()));
        System.out.println("Sortirani artikli po volumenu:");
        for (Item item : items) {
            System.out.println(item.getName() + ": " + item.getVolume());
        }
    }

    private static void sortItemsByVolumeLambda(List<? extends Item> items) {
        Collections.sort(items, (item1, item2) -> item1.getVolume().compareTo(item2.getVolume()));
    }

    // Metoda za sortiranje bez lambda izraza
    private static void sortItemsByVolumeWithoutLambda(List<? extends Item> items) {
        Collections.sort(items, new VolumeComparator(Boolean.TRUE));
    }


    private static ArrayList<Item> getTehnical(ArrayList<Item> items) {
        ArrayList<Item> laps = new ArrayList<>();
        for (Item item : items) {
            if (item instanceof Technical) {
                laps.add(item);
            }
        }
        return laps;
    }

    private static ArrayList<Item> getEdible(ArrayList<Item> items) {
        ArrayList<Item> food = new ArrayList<>();
        for (Item item : items) {
            if (item instanceof Edible) {
                food.add(item);
            }
        }
        return food;
    }

    private static void calculateAveragePriceOfItemsWithAboveAverageVolume(List<? extends Item> items) {
        BigDecimal averageVolume = calculateAverageVolume(items);

        double averagePrice = items.stream().filter(item -> item.getVolume().compareTo(averageVolume) > 0).mapToDouble(Item::getSellingPriceinDouble).average().orElse(0.0);

        System.out.println("Srednja cijena artikala s natprosječnim volumenom: " + averagePrice);
    }

    private static BigDecimal calculateAverageVolume(List<? extends Item> items) {
        BigDecimal totalVolume = items.stream().map(Item::getVolume).reduce(BigDecimal.ZERO, BigDecimal::add);

        return totalVolume.divide(BigDecimal.valueOf(items.size()), RoundingMode.HALF_UP);
    }

    private static void findStoresWithAboveAverageNumberOfItems(Store... stores) {
        double averageNumberOfItems = calculateAverageNumberOfItems(stores);

        List<Store> storesWithAboveAverageItems = Arrays.stream(stores).filter(store -> store.getItems().size() > averageNumberOfItems).collect(Collectors.toList());

        System.out.println("Trgovine s natprosječnim brojem artikala:");
        storesWithAboveAverageItems.forEach(store -> System.out.println(store.getName()));
    }

    private static double calculateAverageNumberOfItems(Store... stores) {
        return Arrays.stream(stores).mapToDouble(Store::getNumberOfItems).average().orElse(0.0);
    }


}




