
package UI;

import Validation.Util;
import java.util.ArrayList;

public class Menu {
    private String title;
    private ArrayList<String> List = new ArrayList();

    public Menu(String title) {
        this.title = title;
    }

    public void addOption(String Option) {
        List.add(Option);
    }

    public void printMenu() {
        System.out.println("" + title); 
        for (int i = 0; i < List.size(); i++) {
            System.out.println(List.get(i));
        }
    }

    public int getChoice() {
        int max = List.size();
        String input = "Enter your choice: ";
        String error = "Invalid option";
        return Util.getInteger(input, error, 1, max);
    }
}
