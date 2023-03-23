package com.company;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import static java.util.Arrays.stream;

public class Basket {

    private long[] prices;
    private String[] goods;
    private long amount;
    private long productNum;
    private HashMap<Long, Long> cart;



    public Basket() {
        goods = new String[]{"Хлеб", "Яблоки", "Молоко"};
        prices = new long[]{100, 200, 300};
        this.cart = new HashMap<>();
    }
    public Basket(String[] goods, long[] prices) {
        this.goods = goods;
        this.prices = prices;
        this.cart = new HashMap<>();

    }

    public String[] getGoods() {
        return this.goods;
    }

    public long[] getPrices() {
        return this.prices;
    }

    public void addToCart(long productNum, long amount) {
        this.cart.put(productNum, amount);
    }

    public void printCart() {
        System.out.println("Ваша корзина");


        String stringForFile = this.cart.keySet().stream().
                map(key -> key + " " + this.cart.get(key)).
                collect(Collectors.joining(" "));

        System.out.println(stringForFile);
    }

    public void saveTxt(File textFile) {
        System.out.println("Сохраняю корзину в файл");
        try (FileOutputStream fos = new FileOutputStream(textFile.getPath())) {
// перевод строки в массив байтов
            String stringForFile = this.cart.keySet().stream().
                    map(key -> key + " " + this.cart.get(key)).
                    collect(Collectors.joining(" "));

            byte[] bytes = stringForFile.getBytes();
// запись байтов в файл
            fos.write(bytes, 0, bytes.length);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

// выгрузка из файла
    public static Basket loadFromTxtFile(File textFile) {
        System.out.println("Выгружаю из фала");
        FileInputStream fos = null;
        Basket basket = null;
        try {
            fos = new FileInputStream(textFile.getPath());
            int i;
            StringBuilder fileContents = new StringBuilder();
            while ((i = fos.read()) != -1) {
                if (i == 32) continue;
                fileContents.append(i);
            }
            basket = new Basket();
            long prodNum = 0;
            long prodAm = 0;
            long counter = 0;
            for (int idx = 0; idx < fileContents.length(); idx++) {
                if (idx % 2 == 0) {
                    prodNum = fileContents.charAt(idx);
                } else {
                    prodAm = fileContents.charAt(idx);
                }
                counter++;
                if (counter > 0 & counter % 2 == 0) {
                    basket.addToCart(Character.getNumericValue((char) prodNum), Character.getNumericValue((char) prodAm));
                }
            }


        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return basket;
    }

//todo геттеры и сеттеры?


//     public String toString() {
//
//
//        StringBuilder sb = new StringBuilder();
//        for (String elem: this.goods) {
//            sb.append(elem + " ");
//        }
//        sb.append("\n");
//        for (long elem: this.prices) {
//            sb.append(elem + " ");
//        }
//
//        return sb.toString();
//    }


}
