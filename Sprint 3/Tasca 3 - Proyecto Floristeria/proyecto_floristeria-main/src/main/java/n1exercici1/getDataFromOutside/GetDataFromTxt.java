package n1exercici1.getDataFromOutside;

import n1exercici1.products.Decoration;
import n1exercici1.products.Flower;
import n1exercici1.products.Product;
import n1exercici1.products.Tree;
import n1exercici1.products.enums.MadeOf;

import java.util.ArrayList;
import java.util.List;

public class GetDataFromTxt {
    // ESTE METODO NO CARGA LOS ID DE LOS PRODUCTOS EN LA APP. ESTA ULTIMA LOS AUTOGENERA AL RECIBIR EL STACK DE LA BBDD
    public static List<Product> getProductList(String txtFileName){
        List<Product> productListFromTxt = new ArrayList<>();
        for (String[] s : TxtReader.getTxtLines(txtFileName)) {
//            System.out.println(Arrays.toString(s));
            if (s[1].equalsIgnoreCase("FLOWER")) {
                productListFromTxt.add(new Flower(s[2],Double.valueOf(s[3]),s[4]));
            } else if (s[1].equalsIgnoreCase("TREE")){
                productListFromTxt.add(new Tree(s[2],Double.valueOf(s[3]),Double.valueOf(s[4])));
            } else {    // el producto de "Decoración"
                if (s[4].equalsIgnoreCase("WOOD")){
                    productListFromTxt.add(new Decoration(s[2],Double.valueOf(s[3]), MadeOf.WOOD));
                }else {
                    productListFromTxt.add(new Decoration (s[2],Double.valueOf(s[3]), MadeOf.PLASTIC));
                }
            }
        }
//        System.out.println(productListFromTxt);
        return productListFromTxt;
    }
}
