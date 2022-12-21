package first_semester.lab1;

import java.util.Scanner;

public class Main {

    public static Universe U = new Universe(new String[]{"Watermelon","Pumpkin","Bean","Lettuce"});
    public static Set intersection(Set a, Set b){
        Set result_set = new Set("Result",new String[0]);
        for (int i = 0; i < a.items.length; i++) {
            for (int j = 0; j < b.items.length; j++) {
                if (a.items[i].equals(b.items[j])) result_set.addItem(a.items[i]);
            }
        }
        return result_set;
    }
    public static Set union(Set a, Set b){
        Set result_set = new Set("Result",a.items);
        for(int i = 0; i < b.items.length; i++) {
            if (!result_set.IsContains(b.items[i])){
                result_set.addItem(b.items[i]);
            }
        }
        return result_set;
    }
    public static Set difference(Set a, Set b){
        Set result_set = new Set("Result",new String[0]);
        for (int i = 0; i < a.items.length; i++) {
            if(!b.IsContains(a.items[i])){
                result_set.addItem(a.items[i]);
            }
        }
        return result_set;
    }

    public static Set complement(Set a){
        Set all_universe = U.getAllItems();
        difference(all_universe,a);
        return difference(all_universe,a);
    }

    public static Set recieveFromUser(String name){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter elements for SET(" + name + ")");
        String input = scanner.nextLine();
        StringBuilder fruits = new StringBuilder();
        while(! input.equals("stop")) {
            fruits.append(input).append(",");
            input = scanner.nextLine();
        }
        String[] elements = fruits.toString().split(",");

        Set result = new Set(name,elements);
        System.out.println(result.toString());
        return result;
    }

    public static void main(String[] args) {

        U.sets.add(recieveFromUser("A"));
        U.sets.add(new Set("B",new String[]{"Apple","Peach","Onion","Cucumber"}));
        U.sets.add(new Set("C",new String[]{"Shallot","Banana","Potato","Melon"}));

        System.out.println(U.getAllItems().toString());
        System.out.println("Intersection: " + intersection(U.getSet("A"),U.getSet("B")).toString());
        System.out.println("Union: " + union(U.getSet("A"),U.getSet("B")).toString());
        System.out.println("Difference: " + difference(U.getSet("A"),U.getSet("B")).toString());
        System.out.println("Complement: " + complement(U.getSet("A")).toString());

    }

}
