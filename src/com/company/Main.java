package com.company;

import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        File textFile = new File("./out/basket.txt");

        try {
            if (textFile.createNewFile())
                System.out.println("Файл был создан");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
//
//        System.out.println("Hell");
//
//        String[] goods = {"Хлеб", "Яблоки", "Молоко"};
//        long[] prices = {100, 200, 300};
//
//        Basket basket = new Basket(goods,prices);
//        basket.addToCart(1,1);
//        basket.addToCart(2,2);
//        basket.addToCart(3,3);
//
//        basket.printCart();
//        basket.saveTxt(textFile);
//        Basket basket2 = Basket.loadFromTxtFile(textFile);


        Basket basket = Basket.loadFromTxtFile(textFile);
        int productNumber = 0;
        int productCount = 0;
//        int currentPrice = prices[productNumber];

        for (int i = 0; i < basket.getGoods().length; i++) {
            System.out.println(i+1 + " " + basket.getGoods()[i] + " " + basket.getPrices()[i] + " руб/шт");
        }



        while (true) {


            Scanner scanner = new Scanner(System.in);
            System.out.println("Выберите товар и количество или введите `end`");
            String input = scanner.nextLine();

            if (input.equals("end")) {
                basket.printCart();
                //todo перебрать корзину и вывести Хлеб 3 шт 100 руб/шт 300 руб в сумме
                //todo Яблоки 1 шт 200 руб/шт 200 руб в сумме
                System.out.println("Всего рублей...");
                break;
            }

            String[] parts = input.split(" ");
            if (parts.length > 2 | parts.length == 1) {
                System.out.println("Введите два значения через пробел и нажмите Enter!");
                System.out.println();
                continue;
            }

            try {
                productNumber = Integer.parseInt(parts[0]);
                if (productNumber <= 0 | productNumber > basket.getGoods().length) {
                    System.out.println();
                    System.out.println("Введите нормальный номер товара");
                    System.out.println();
                    continue;

                }
                productCount = Integer.parseInt(parts[1]);
                if (productCount <= 0 | productCount > 100) {
                    System.out.println();
                    System.out.println("Введите нормальное количесвто товара!");
                    System.out.println();
                    continue;
                }
                basket.addToCart(productNumber, productCount);
                basket.saveTxt(textFile);
//                numOfChosenGoods[productNumber - 1] = productCount;

            } catch (NumberFormatException e) {
                System.out.println("Вы ввели не число!");
            } finally {
                continue;
            }



        }
//        String[] products = {"Хлеб", "Яблоки", "Молоко"};
//        int[] prices = {100, 200, 300};
//        int[] numOfChosenGoods = new int[3];
//
//
//
//        for (int i = 0; i < products.length; i++) {
//            System.out.println(i+1 + " " + products[i] + " " + prices[i] + " руб/шт");
//        }
//
//        while (true) {
//            Scanner scanner = new Scanner(System.in);
//
//            int productNumber = 0;
//            int productCount = 0;
//            int currentPrice = prices[productNumber];
//
//            System.out.println("Выберите товар и количество или введите `end`");
//            String input = scanner.nextLine();
//            if (input.equals("end")) {
//                int total = 0;
//                System.out.println("Ваша корзина:");
//                for (int i = 0; i < numOfChosenGoods.length; i++) {
//                    if (numOfChosenGoods[i] != 0) {
//                        System.out.println(products[i] + " " + numOfChosenGoods[i] + " шт " + prices[i] + " руб/шт " + numOfChosenGoods[i] * prices[i] + " руб в сумме");
//                        total += numOfChosenGoods[i] * prices[i];
//                    }
//                }
//                System.out.println("Всего " + total + " рублей");
//                break;
//            }
//            String[] parts = input.split(" ");
//            if (parts.length > 2 | parts.length == 1) {
//                System.out.println("Введите два значения через пробел и нажмите Enter!");
//                System.out.println();
//                continue;
//            }
//
//            try {
//                productNumber = Integer.parseInt(parts[0]);
//                if (productNumber <= 0 | productNumber > products.length) {
//                    System.out.println();
//                    System.out.println("Введите нормальный номер товара");
//                    System.out.println();
//                    continue;
//
//                }
//                productCount = Integer.parseInt(parts[1]);
//                if (productCount <= 0 | productCount > 100) {
//                    System.out.println();
//                    System.out.println("Введите нормальное количесвто товара!");
//                    System.out.println();
//                    continue;
//                }
//                numOfChosenGoods[productNumber - 1] = productCount;
//
//            } catch (NumberFormatException e) {
//                System.out.println("Вы ввели не число!");
//            } finally {
//                continue;
//            }
//        }
    }
}
