import java.util.Map;
import java.lang.Math;
import java.util.Scanner;
import java.io.*;

public class Main {

    public static void main(String[] args) {

        Toy toy1 = new Toy("123999", "Мишка   ", 50, 10);
        Toy toy2 = new Toy("123998", "Лягушка ", 50, 20);
        Toy toy3 = new Toy("123997", "Зайчик  ", 50, 30);
        Toy toy4 = new Toy("123996", "Щенок   ", 50, 40);
        Toy toy5 = new Toy("123995", "Котик   ", 50, 50);
        Toy toy6 = new Toy("123994", "Слоник  ", 50, 60);
        Toy toy7 = new Toy("123993", "Утенок  ", 50, 70);
        Toy toy8 = new Toy("123992", "Пингвин ", 50, 1);
        Toy toy9 = new Toy("123991", "Тигренок", 50, 0);

        ToysStorage toysStorage = new ToysStorage();
        ToysStorage prizeStorage = new ToysStorage();

        toysStorage.addToy(toy1.getArticle(), toy1);
        toysStorage.addToy(toy2.getArticle(), toy2);
        toysStorage.addToy(toy3.getArticle(), toy3);
        toysStorage.addToy(toy4.getArticle(), toy4);
        toysStorage.addToy(toy5.getArticle(), toy5);
        toysStorage.addToy(toy6.getArticle(), toy6);
        toysStorage.addToy(toy7.getArticle(), toy7);
        toysStorage.addToy(toy8.getArticle(), toy8);
        toysStorage.addToy(toy9.getArticle(), toy9);

        System.out.println("------- Игрушки в магазине --------------");
        System.out.println("toys in storage");
        toysStorage.printToys();
        System.out.println("prize toys");
        prizeStorage.printToys();

        System.out.println("---------- меняем частоту выпадения 9 на 5 и уменьшим количество  -----------");
        String article = "123994";
        for (Map.Entry<String, Toy> item : toysStorage.getStorage().entrySet()) {
            if (article.equals(item.getValue().getArticle())) {
                item.getValue().setFrequency(5);
                toysStorage.getToy(item.getValue().getArticle()).setQuantity(item.getValue().getQuantity() - 1);
            }
        }

        System.out.println("toys in storage");
        toysStorage.printToys();

        System.out.println("старт лотереи 1");
        start_lottery(toysStorage, prizeStorage);
        System.out.println("старт лотереи 2");
        start_lottery(toysStorage, prizeStorage);
        System.out.println("старт лотереи 3");
        start_lottery(toysStorage, prizeStorage);
        System.out.println("старт лотереи 4");
        start_lottery(toysStorage, prizeStorage);

        System.out.println("---------- Лотерея разыграна -----------");
        System.out.println("toys in storage");
        toysStorage.printToys();
        System.out.println("prize toys");
        prizeStorage.printToys();

        while (!prizeStorage.getStorage().isEmpty()) {
            Scanner in = new Scanner(System.in);
            System.out.println("введите артикул приза для выдачи");
            String prize = in.nextLine();
            getPrize(prizeStorage, prize);

            System.out.println("prize toys");
            prizeStorage.printToys();
            if (prizeStorage.getStorage().isEmpty()){
                System.out.println("Все призы разыграны, отчет смотри в файле:prizeToys.txt \n Cпасибо!");
            }
        }

    }

    
    public static void start_lottery(ToysStorage toysStorage, ToysStorage prizeStorage) {

        boolean result_lottery = true;
        while (result_lottery) {
//            System.out.println("начало тиража");
            int random = (int) (1 + (Math.random() * 100));
            int i = 1; // счетчик
            int toy_number = (int) (Math.random() * toysStorage.getStorage().size() + 1);
            for (Map.Entry<String, Toy> item : toysStorage.getStorage().entrySet()) {
                if (i == toy_number) {
                    if (random <= (item.getValue().getFrequency())) {
                        System.out.printf("выпала игрушка %s кол во игрушек на складе %d \n", item.getValue().getToy_name(), item.getValue().getQuantity());
                        if (prizeStorage.getStorage().containsKey(item.getKey())) {
                            System.out.println("уже есть такой приз, увеличиваем количество +1");
                            prizeStorage.getStorage().get(item.getKey()).setQuantity(prizeStorage.getStorage().get(item.getKey()).getQuantity() + 1);
                        } else {
                            System.out.println("приза нет в коробке, добавляем +1");
                           prizeStorage.copyToy(item.getKey(), item.getValue());
                            prizeStorage.getToy(item.getValue().getArticle()).setQuantity(1);
                        }
                        toysStorage.getToy(item.getValue().getArticle()).setQuantity(item.getValue().getQuantity() - 1);
                        System.out.printf("количество игрушки после уменьшения %s = %d \n", item.getValue().getToy_name(), item.getValue().getQuantity());
                        if ((item.getValue().getQuantity()) == 0) {
                            System.out.printf("артикул %s закончился, удаляем из базы \n", item.getValue().getArticle());
                            toysStorage.removeToy(item.getKey());
                        }
                        result_lottery = false;
                        break;
                    }
                }
                i += 1;
            }
        }
    }

    public static void getPrize(ToysStorage prizeStorage, String article) {
        if (prizeStorage.getStorage().containsKey(article)) {

            System.out.println("выдаем приз - игрушка " + prizeStorage.getStorage().get(article).getToy_name());
            String text = "Выдан приз - " + "Артикул: " + prizeStorage.getStorage().get(article).getArticle() +
                    ", Наименование: " + prizeStorage.getStorage().get(article).getToy_name() +
                    ", Количество: 1" +"\n";
            try(FileWriter writer = new FileWriter("prizeToys.txt", true))
            {
                writer.write(text);
                System.out.println("Записываем выданный приз в файл - prizeToys.txt ");
                writer.flush();
            }
            catch(IOException ex){
                System.out.println(ex.getMessage());
            }
            prizeStorage.getStorage().get(article).setQuantity(prizeStorage.getStorage().get(article).getQuantity() - 1);

            if (prizeStorage.getStorage().get(article).getQuantity() == 0) {
                System.out.printf("призы %s закончились, удаляем из базы призов \n", prizeStorage.getStorage().get(article).getToy_name());
                prizeStorage.removeToy(article);
            }
        }
    }

}