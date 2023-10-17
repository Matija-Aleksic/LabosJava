package hr.java.production.main;

import hr.java.production.model.*;

import java.math.BigDecimal;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Unos podataka za tri kategorije artikala
        Category[] categories = new Category[3];
        for (int i = 0; i < 3; i++) {
            System.out.println("Unesite naziv kategorije " + (i + 1) + ":");
            String categoryName = scanner.nextLine();
            categories[i] = new Category(categoryName);
        }

        // Unos podataka za pet artikala
        Item[] items = new Item[5];
        for (int i = 0; i < 5; i++) {
            System.out.println("Unesite podatke za artikal " + (i + 1) + ":");
            System.out.println("Naziv:");
            String itemName = scanner.nextLine();

            System.out.println("Kategorija (unesite broj od 1 do 3):");
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

            items[i] = new Item(itemName, itemCategory, width, height, length, productionCost, sellingPrice);
        }

        // Unos podataka za dvije tvornice
        Factory[] factories = new Factory[2];
        for (int i = 0; i < 2; i++) {
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

            Address factoryAddress = new Address(street, houseNumber, city, postalCode);

            factories[i] = new Factory(factoryName, factoryAddress, items);
        }

        // Unos podataka za dvije trgovine
        Store[] stores = new Store[2];
        for (int i = 0; i < 2; i++) {
            System.out.println("Unesite podatke za trgovinu " + (i + 1) + ":");
            System.out.println("Naziv:");
            String storeName = scanner.nextLine();

            System.out.println("Web adresa:");
            String webAddress = scanner.nextLine();

            stores[i] = new Store(storeName, webAddress, items);
        }

        // Ispis unesenih podataka
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

    }
}
