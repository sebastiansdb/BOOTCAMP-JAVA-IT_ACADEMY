package n1exercici1;

import n1exercici1.exceptions.NotValidOptionException;
import n1exercici1.exceptions.OnlyYesNoException;
import n1exercici1.exceptions.ProductAlreadyExistsException;
import n1exercici1.products.Flower;
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
            System.out.println("\nThis flower shop is not included in the database,\n" +
                    " please, consider to create first the database\n " +
                    "or check if the flowershop name is correct.");
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
        int option;
        String productName;
        do {
            option = askMenuOption(addProductMenu(),3);
            try {
                switch (option) {
                    case 1 -> {
                        productName = askName();
                        seeIfItExists(flowerShop, productName, "tree");
                        flowerShop.addTree(productName, askPrice(), askHeight());
                    }
                    case 2 -> {
                        productName = askName();
                        seeIfItExists(flowerShop, productName, "flower");
                        flowerShop.addFlower(productName, askPrice(), askColor());
                    }
                    case 3 -> {
                        productName = askName();
                        seeIfItExists(flowerShop, productName, "decoration");
                        flowerShop.addDecoration(productName, askPrice(), askMaterial());
                    }
                }
            } catch (ProductAlreadyExistsException e){
                System.out.println(e.getMessage());
            }
        } while (option!=0);
    }
    private static void runRemoveProduct(FlowerShop flowerShop){
        int option;
        String productName;
        do {
            option = askMenuOption(removeProductMenu(),3);
            switch (option) {
                case 1 -> {
                    flowerShop.removeTree(askName());
                }
                case 2 -> {
                    flowerShop.removeFlower(askName());
                }
                case 3 -> {
                    flowerShop.removeDecoration(askName());
                }
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
                case 2 -> System.out.println("OOOOOOOOOOOOOOOOO");
                case 3 -> System.out.println("OOOOOOOOOOOOOOOOO");
                case 4 -> System.out.println("OOOOOOOOOOOOOOOOO");
            }
        } while (option!=0);
    }
    private static void runSalesMenu(FlowerShop flowerShop){
        int option;
        List<Product> cart = new ArrayList<Product>();
        do {
            option = askMenuOption(newSaleMenu(),3);
            switch (option) {
                case 1 -> System.out.println("OOOOOOOOOOOOOOOOO");
                case 2 -> System.out.println("OOOOOOOOOOOOOOOOO");
                case 3 -> System.out.println("OOOOOOOOOOOOOOOOO");
                case 0 -> option = confirmExiting();
            }
        } while (option!=0 && option!=3);
    }
    private static void runWrapUp(FlowerShop flowerShop){
        flowerShop.saveProductList();
    }

    // DATA INPUT METHODS
    private static FlowerShop askShopName (){
        return FlowerShop.openFlowerShop(askString("Enter the shop's name: "));
    }
    private static String askName(){
        return askString("Enter the product's name: ");
    }
    private static double askPrice(){
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
    private static void seeIfItExists(FlowerShop flowerShop, String productName, String productType) throws ProductAlreadyExistsException{
        boolean exists = false;
        switch (productType.toLowerCase()){
            case "tree" -> exists = flowerShop.findTree(productName)!=null;
            case "flower" -> exists = flowerShop.findFlower(productName)!=null;
            case "decoration" -> exists = flowerShop.findDecoration(productName)!=null;
        }
        if (exists) throw new ProductAlreadyExistsException("This " + productType + " is already stocked");
    }
    private static int confirmExiting(){
        int option = 0;
        boolean correct = false;
        do {
            try {
                option = yesNoValidator(askString("Are you sure you want to cancel the sale? (YES/NO): "));
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
                (0). Close app""";
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
                (1). Add a product to cart
                (2). Remove a product from cart
                (3). Register the sale
                (0). Cancel the new sale""";
    }
}
