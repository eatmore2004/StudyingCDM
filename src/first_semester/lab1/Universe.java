package first_semester.lab1;

import java.util.ArrayList;
import static java.util.Arrays.copyOf;

public class Universe {
    protected ArrayList<Set> sets = new ArrayList<>();
    protected String[] free_items;

    public Universe(String[] items) {
        this.free_items = items;
    }

    protected Set getSet(String name){
        for (Set set : sets) {
            if (set.name.equals(name)) {
                return set;
            }
        }
        return sets.get(0);
    }
    protected Set getAllItems(){
        Set result = new Set("Result",new String[0]);

        for (Set set : sets) {
            for (int j = 0; j < set.items.length; j++) {
                if (!result.IsContains(set.items[j])) result.addItem(set.items[j]);
            }
        }
        for (String free_item : free_items) {
            if (!result.IsContains(free_item)) result.addItem(free_item);
        }

        return result;
    }

    protected void addItem(String item) {
        String[] items_temp = copyOf(free_items, (free_items.length + 1));
        items_temp[free_items.length] = item;
        free_items = items_temp;
    }

}
