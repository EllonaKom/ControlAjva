import java.util.HashMap;
import java.util.Map;

public class ToysStorage {



    public Map<String, Toy> getStorage() {
        return storage;
    }

    public void setStorage(HashMap<String, Toy> storage) {
        this.storage = storage;
    }

    public void addToy(String article, Toy item){
        this.storage.put(article, item );
    }

    public void copyToy(String article, Toy item){
        Toy tempToy = new Toy(item.getArticle(), item.getToy_name(), item.getQuantity(), item.getFrequency());
        this.storage.put(article, tempToy);
    }

    public void removeToy(String article){
        this.storage.remove(article);
    }

    public Toy getToy(String article){
        return this.storage.get(article);
    }

    public void printToys(){
        for (Map.Entry<String, Toy> item: storage.entrySet()){
            System.out.printf("article is: %s, Toy: %s, Quantity: %d, Frequency: %d \n", item.getKey(), item.getValue().getToy_name(), item.getValue().getQuantity(), item.getValue().getFrequency());
        }
    }

    public ToysStorage() {
        this.storage = storage;
    }


    private Map<String, Toy> storage = new HashMap<String, Toy>();



}