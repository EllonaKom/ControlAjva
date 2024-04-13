
import java.util.InputMismatchException;

public class Toy {

    private String article;
    private String toy_name;
    private int quantity;
    private int frequency;

    public Toy(String article, String toy_name, Integer quantity, Integer frequency) {
        this.article = article;
        this.toy_name = toy_name;
        this.quantity = quantity;
        this.frequency = frequency;
    }

    public String getArticle() {
        return article;
    }

    public String getToy_name() {
        return toy_name;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getFrequency() {
        return frequency;
    }


    public void setArticle(String article) {
        this.article = article;
    }

    public void setToy_name(String toy_name) {
        this.toy_name = toy_name;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    public void setFrequency(int frequency) throws InputMismatchException {

        if (frequency > 0 & frequency < 100) {
            this.frequency = frequency; // от 0 до 100%
        }
        else {
            throw new InputMismatchException("процент выпадения  от 0 до 100%");
        }

    }


}