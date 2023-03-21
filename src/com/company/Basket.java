package com.company;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Basket {

    private int[] prices;
    private String[] goods;
    private int amount;
    private int productNum;
    private HashMap <Integer,Integer> cart;

    public  Basket(String[] goods, int[] prices) {
        this.goods = goods;
        this.prices = prices;
        this.cart = new HashMap<>();

    }

    public void addToCart(int productNum, int amount) {
        this.cart.put(productNum, amount);
    }

    public void printCart() {
        System.out.println("Ваша корзина");


        String stringForFile = this.cart.keySet().stream().
                map(key -> key + " " + this.cart.get(key)).
                collect(Collectors.joining(",","{","}"));

        System.out.println(stringForFile);
    }

    public void saveTxt(File textFile){
        System.out.println("Сохраняю корзину в файл");


        try (FileOutputStream fos = new FileOutputStream(textFile.getPath())) {
// перевод строки в массив байтов

            //todo надо вытащить из мапы ключи и значения для дальнейшей записи в файл

            String stringForFile = this.cart.keySet().stream().
                    map(key -> key + " " + this.cart.get(key)).
                    collect(Collectors.joining(",","{","}"));

            byte[] bytes = stringForFile.getBytes();
// запись байтов в файл
            fos.write(bytes, 0, bytes.length);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
    }
//todo доделать выгрузку из файла
//    public static Basket loadFromTxtFile(File textFile) {
//        System.out.println("Выгружаю из фала");
//    }

}}
