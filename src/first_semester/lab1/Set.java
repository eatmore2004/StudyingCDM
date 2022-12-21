package first_semester.lab1;
import java.util.Arrays;

import static java.util.Arrays.copyOf;

public class Set{
    protected String[] items;
    protected String name;

    public Set(String name,String[] items) {
        this.items = items;
        this.name = name;
    }

    @Override
    public String toString() {
        return Arrays.toString(items);
    }
    protected boolean IsContains(String item){
        for (String item_t : items) {
            if (item_t.equals(item)) return true;
        }
        return false;
    }

    protected void addItem(String item) {
        String[] items_temp = copyOf(items, (items.length + 1));
        items_temp[items.length] = item;
        items = items_temp;
    }
}
