package n1exercici1;

import n1exercici1.exceptions.NotValidOptionException;
import n1exercici1.exceptions.OnlyYesNoException;
import n1exercici1.exceptions.ProductDoesNotExistsException;
import n1exercici1.products.Product;
import n1exercici1.products.enums.MadeOf;

import java.util.ArrayList;
import java.util.List;

import static n1exercici1.services.InputData.*;

public class App {

    public static void runApp(){

        System.out.println("---FLOWER SHOP MANAGER---");
        FlowerShop flowerShop = askShopName();
        if (flowerShop.seeIfFlowerShopExists()){
            runMainMenu(flowerShop);
        } else {
            System.out.println("""

                    This flower shop is not included in the database,
                     please, consider to create first the database
                     or check if the data introduced is correct.""");
        }
    }

    // LOGIC METHODS
    private static void runMainMenu(FlowerShop flowerShop){
        int menuOption;
        do {
            menuOption = askMenuOption(mainMenu(),4);
            switch (menuOption) {
                case 1 -> runAddProduct(flowerShop);
                case 2 -> runRemoveProduct(flowerShop);
                case 3 -> runStoreInfo(flowerShop);
                case 4 -> runSalesManager(flowerShop);
                case 0 -> runWrapUp(flowerShop);
            }
        } while (menuOption!=0);
    }
    private static void runAddProduct(FlowerShop flowerShop){
        String productName;
        int option = askMenuOption(addProductMenu(),3);
        while (option!=0 && option<4) {
            productName = askProductName();
            if (!returnIfAlreadyStocked(flowerShop, productName, option)){
                switch (option) {
                    case 1 -> flowerShop.addTree(productName, askProductPrice(), askHeight());
                    case 2 -> flowerShop.addFlower(productName, askProductPrice(), askColor());
                    case 3 -> flowerShop.addDecoration(productName, askProductPrice(), askMaterial());
                }
            }
            option = askMenuOption(addProductMenu(),3);
        }
    }
    private static void runRemoveProduct(FlowerShop flowerShop){
        int option;
        do {
            option = askMenuOption(removeProductMenu(),3);
            switch (option) {
                case 1 -> flowerShop.removeTree(askProductName());
                case 2 -> flowerShop.removeFlower(askProductName());
                case 3 -> flowerShop.removeDecoration(askProductName());
            }
        } while (option!=0);
    }
    private static void runStoreInfo(FlowerShop flowerShop){
        int option;
        do {
            option = askMenuOption(storeInfoMenu(),3);
            System.out.println();
            switch (option) {
                case 1 -> runStockType(flowerShop);
                case 2 -> flowerShop.printStock();
                case 3 -> flowerShop.printShopValue();
            }
        } while (option!=0);
    }
    private static void runStockType(FlowerShop flowerShop){
        int option;
        do {
            option = askMenuOption(stockTypeMenu(),3);
            System.out.println();
            switch (option) {
                case 1 -> flowerShop.printTreeStock();
                case 2 -> flowerShop.printFlowerStock();
                case 3 -> flowerShop.printDecorationStock();
            }
            System.out.println();
        } while (option!=0);
    }
    private static void runSalesManager(FlowerShop flowerShop){
        int option;
        do {
            option = askMenuOption(salesMenu(),4);
            switch (option) {
                case 1 -> runSalesMenu(flowerShop);
                case 2 -> flowerShop.printSalesHistory();
                case 3 -> flowerShop.printEarnedMoney();
                case 4 -> flowerShop.printSaleTicket(askSale());
            }
        } while (option!=0);
    }
    private static void runSalesMenu(FlowerShop flowerShop){
        List<Product> cart = new ArrayList<>();
        int option;
        do {
            option = askMenuOption(newSaleMenu(),5);
            switch (option) {
                case 1 -> cart = addToCart(flowerShop, cart, "TREE");
                case 2 -> cart = addToCart(flowerShop, cart, "FLOWER");
                case 3 -> cart = addToCart(flowerShop, cart, "DECORATION");
                case 4 -> cart = removeFromCart(cart);
                case 5 -> flowerShop.processSale(cart);
                case 0 -> option = confirmExiting("Are you sure you want to cancel the sale? (YES/NO): ");
            }
        } while (option!=0 && option!=5);
        if (option==0) returnProductsToStock(flowerShop, cart);
    }
    private static List<Product> addToCart (FlowerShop flowerShop, List<Product> cart, String productType){
        try {
            cart.add(flowerShop.getProduct(askProductName(), productType));
        } catch (ProductDoesNotExistsException e) {
            System.out.println(e.getMessage());
        }
        return cart;
    }
    private static List<Product> removeFromCart (List<Product> cart){
        try {
            cart.remove(getProductFromCart(cart, askProductName()));
        } catch (ProductDoesNotExistsException e) {
            System.out.println(e.getMessage());
        }
        return cart;
    }
    private static Product getProductFromCart(List<Product> cart, String productName) throws ProductDoesNotExistsException {
        return cart.stream().filter(product -> product.getName().equalsIgnoreCase(productName)).findFirst()
                .orElseThrow(() -> new ProductDoesNotExistsException("This product is not in the cart."));
    }
    private static void returnProductsToStock(FlowerShop flowerShop, List<Product> cart){
        flowerShop.returnProduct(cart);
    }
    private static void runWrapUp(FlowerShop flowerShop){
        flowerShop.saveChanges();

    }

    // DATA INPUT METHODS
    private static FlowerShop askShopName (){
        return FlowerShop.openFlowerShop(askString("Enter the shop's name: "));
    }
    private static String askProductName(){
        return askString("Enter the product's name: ");
    }
    private static double askProductPrice(){
        return askDouble("Enter the product's price: ");
    }
    private static double askHeight(){
        return askDouble("Enter the tree's height: ");
    }
    private static String askColor(){
        return askString("Enter the flower's color: ");
    }
    private static MadeOf askMaterial(){
        int option;
        do {
            option = askMenuOption(materialTypeMenu(),1);
        } while (option!=0 && option!=1);
        return (option==0 ? MadeOf.WOOD : MadeOf.PLASTIC);
    }
    private static int askSale(){
        return askInt("Enter the sale's ID: ");
    }

    // ASK MENU OPTION AND VALIDATOR METHODS
    private static int askMenuOption(String menu, int totalOptions) {
        System.out.println(menu);
        int menuOption = askInt("Enter an option: ");
        try{
            optionValidator(menuOption, totalOptions);
        } catch (NotValidOptionException e){
            System.out.println(e.getMessage());
        }
        return menuOption;
    }
    private static void optionValidator (int menuOption, int max) throws NotValidOptionException {
        if (menuOption<0 || menuOption>max) {
            throw new NotValidOptionException("This option does not exist.");
        }
    }
    private static boolean returnIfAlreadyStocked(FlowerShop flowerShop, String productName, int option) {
        boolean exists = flowerShop.findProduct(productName, returnProductType(option))!=null;
        if (exists) System.out.println("This product is already stocked");
        return exists;
    }
    private static String returnProductType (int option){
        String type = "";
        switch (option){
            case 1 -> type = "TREE";
            case 2 -> type = "FLOWER";
            case 3 -> type = "DECORATION";
        }
        return type;
    }
    private static int confirmExiting(String message){
        int option = 0;
        boolean correct = false;
        do {
            try {
                option = yesNoValidator(askString(message));
                correct = true;
            } catch (OnlyYesNoException e){
                System.out.println(e.getMessage());
            }
        } while (!correct);
        return option;
    }
    private static int yesNoValidator(String option) throws OnlyYesNoException {
        return switch (option.toUpperCase()){
            case "YES" -> 0;
            case "NO" -> 1;
            default -> throw new OnlyYesNoException("Format error. You need to answer Yes or No.");
        };
    }

    // SHOW A MENU METHODS
    private static String mainMenu(){
        return """

                 MAIN MENU:
                (1). Add a product
                (2). Remove a product
                (3). Enter to store's info menu
                (4). Enter to sale's menu
                (0). Close app and save changes""";
    }
    private static String addProductMenu(){
        return """

                 ADD PRODUCT MENU:
                (1). Add a tree
                (2). Add a flower
                (3). Add a decoration
                (0). Return to main menu""";
    }
    private static String materialTypeMenu(){
        return """

                 DECORATION'S MATERIAL
                (0). Wood
                (1). Plastic""";
    }
    private static String removeProductMenu(){
        return """

                 REMOVE PRODUCT MENU:
                (1). Remove a tree
                (2). Remove a flower
                (3). Remove a decoration
                (0). Return to main menu""";
    }
    private static String storeInfoMenu(){
        return """
                 
                 STORE INFO MENU:
                (1). Show the actual stock of the store
                (2). Show the actual stock quantities
                (3). Show the actual value of the store
                (0). Return to main menu""";
    }
    private static String stockTypeMenu(){
        return """
                 STOCK TYPE INFO MENU:
                (1). Show the stock of Tree's
                (2). Show the stock of Flower's
                (3). Show the stock of Decoration's
                (0). Return to the store's info menu""";
    }
    private static String salesMenu(){
        return """

                 SALES MANAGER MENU:
                (1). Register a new sale
                (2). Show sale's history
                (3). Show store's earned money
                (4). Print a sale's ticket
                (0). Return to main menu""";
    }
    private static String newSaleMenu(){
        return """

                 NEW SALE MENU:
                (1). Add a tree to cart
                (2). Add a flower to cart
                (3). Add a decoration to cart
                (4). Remove a product from cart
                (5). Register the sale
                (0). Cancel the new sale""";
    }
}
