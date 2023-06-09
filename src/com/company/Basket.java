package com.company;

import java.io.*;
import java.util.Arrays;
public class Basket implements Serializable {

    private long[] prices;
    private String[] goods;
    private long[] quantity;

    public static final long serialVersionUID = 1L;


    public Basket() {
        goods = new String[]{"Хлеб", "Яблоки", "Молоко"};
        prices = new long[]{100, 200, 300};
        quantity = new long[]{0,0,0};
    }
    public Basket(String[] goods, long[] prices) {
        this.goods = goods;
        this.prices = prices;
    }

    public String[] getGoods() {
        return this.goods;
    }

    public long[] getPrices() {
        return this.prices;
    }

    public void addToCart(long productNum, long amount) {
        this.quantity[(int) (productNum-1)] = amount;
    }

    public void printCart() {
        System.out.println("Ваша корзина");

        long sum = 0;
        for (int i = 0; i < this.goods.length; i++) {
            sum = sum + this.prices[i] * this.quantity[i];
            if (this.quantity[i] != 0) {
                System.out.println(this.goods[i] + " цена: "
                        + this.prices[i]
                        +" рублей, всего " + this.quantity[i] + " шт., итого: "
                        + this.quantity[i]*this.prices[i] + " рублей");
            }

        }
        System.out.println();
        System.out.println("Общая стоимость " + sum + " рублей");
    }

    public void saveTxt(File textFile) throws RuntimeException {
        System.out.println("Сохраняю корзину в файл");

        try(PrintWriter out = new PrintWriter(textFile.getPath())) {

            for (String good: this.goods) {
                out.print(good + " ");
            }
            out.println();

            for (long price: this.prices) {
                out.print(price + " ");
            }
            out.println();

            for (long amount: this.quantity) {
                out.print(amount + " ");
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    // выгрузка из файла
    public static Basket loadFromTxtFile(File textFile) throws RuntimeException {
        FileInputStream fos = null;

        Basket basket = new Basket();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(textFile))) {

             String goodsStr = bufferedReader.readLine();
             String pricesStr = bufferedReader.readLine();
             String quantitiesStr = bufferedReader.readLine();

             basket.goods = goodsStr.split(" ");
             String[] arrPrices = pricesStr.split( " ");
             basket.prices = Arrays.stream(arrPrices).
                     map(Long::parseLong).
                     mapToLong(Long::longValue).
                     toArray();
             String[] arrQuantities = quantitiesStr.split(" ");
             basket.quantity = Arrays.stream(arrQuantities).
                    map(Long::parseLong).
                    mapToLong(Long::longValue).
                    toArray();


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return basket;
    }

    public void saveBin(File file) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(this);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static Basket loadFromBinFile(File file) throws ClassNotFoundException {
        Basket basket = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            basket = (Basket) ois.readObject();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return basket;
    }
}
