package first_semester.lab2;

import java.util.ArrayList;
import java.util.Scanner;
public class Main {
    protected static ArrayList<String> operators = new ArrayList<>(2);
    protected static ArrayList<String> priority_list = new ArrayList<>(2);
    protected static ArrayList<Set> sets = new ArrayList<>();
    protected static ArrayList<Set> stack = new ArrayList<>();
    private static int numInSet;
    public static void main(String[] args) {
        readExpression();
        getAllSets();
        logic();
        printTruthTable();
    }
    private static void printTruthTable() {
        System.out.println("TRUTH TABLE --->");
        for (Set set : sets) {
            System.out.print("(" + set.name + ") ");
        }
        System.out.println(" ");
        for (int i = 0; i < numInSet; i++) {
            for (Set set : sets) {
                String space = new String(new
                        char[set.name.length()]).replace('\0', ' ');
                System.out.print(" " +set.values[i] + " " + space);
            }
            System.out.println(" ");
        }
        System.out.println("<--- END");
    }
    private static void logic() {
        int op_num = 0;
        addSetsToStack();
        for (int i = 0; i < operators.size(); i++) {
            if (operators.get(i).equals("`")){
                Set set = stack.get(i);
                operators.remove(i);
                Set result_set = function_NOT(set);
                stack.set(i,result_set);
                sets.add(new Set("f"+op_num++,result_set.values));
                i--;
            }
        }
        for (int i = 0; i < operators.size(); i++) {
            String operator = operators.get(i);
            if (operator.equals("*") || operator.equals("=>") ||
                    operator.equals("~")){
                Set set1 = stack.get(i);
                Set set2 = stack.get(i + 1);
                Set result_set = switch (operator) {
                    case "*" -> function_AND(set1, set2);
                    case "~" -> function_EKV(set1, set2);
                    case "=>" -> function_IMP(set1, set2);
                    default -> null;
                };
                operators.remove(i);
                stack.remove(i+1);
                stack.set(i,result_set);
                sets.add(new Set("f"+op_num++,result_set.values));
                i--;
            }
        }
        for (int i = 0; i < operators.size(); i++) {
            if (operators.get(i).equals("+")){
                Set set = stack.get(i);
                Set set1 = stack.get(i + 1);
                operators.remove(i);
                stack.remove(i+1);
                Set result_set = function_OR(set,set1);
                stack.set(i,result_set);
                sets.add(new Set("f"+op_num++,result_set.values));
                i--;
            }
        }
        sets.add(sets.size(),new Set("Output",stack.get(0).values));
    }
    private static void addSetsToStack() {
        for (String s : priority_list) {
            stack.add(getSetByName(s));
        }
    }
    private static Set function_NOT(Set set) {
        int[] _values = new int[numInSet];
        for (int i = 0; i < numInSet; i++) {
            _values[i] = (set.values[i] == 1) ? 0 : 1;
        }
        return new Set(set.name,_values);
    }
    private static Set function_AND(Set set1,Set set2) {
        int[] _values = new int[numInSet];
        for (int i = 0; i < numInSet; i++) {
            _values[i] = set1.values[i] * set2.values[i];
        }
        return new Set(set1.name,_values);
    }
    private static Set function_IMP(Set set1,Set set2) {
        int[] _values = new int[numInSet];
        for (int i = 0; i < numInSet; i++) {
            _values[i] = Math.max(((set1.values[i] == 1) ? 0 :
                    1),set2.values[i]);
        }
        return new Set(set1.name,_values);
    }
    private static Set function_EKV(Set set1,Set set2) {
        int[] _values = new int[numInSet];
        for (int i = 0; i < numInSet; i++) {
            _values[i] = (set1.values[i] == set2.values[i])? 1 : 0 ;
        }
        return new Set(set1.name,_values);
    }
    private static Set function_OR(Set set1,Set set2) {
        int[] _values = new int[numInSet];
        for (int i = 0; i < numInSet; i++) {
            _values[i] = Math.max(set1.values[i],set2.values[i]);
        }
        return new Set(set1.name,_values);
    }
    private static Set getSetByName(String set_name) {
        for (Set set : sets) {
            if (set.name.equals(set_name)) return set;
        }
        return null;
    }
    private static void getAllSets() {
        System.out.println("Write count of elements for each set : ");
        Scanner scan = new Scanner(System.in);
        numInSet = scan.nextInt();
        ArrayList<String> sets_names = getAllUnicNames();
        for (String sets_name : sets_names) {
            if (sets_name == null) break;
            System.out.println("Please write " + numInSet + " elements to set " + sets_name);
            int[] values = new int[numInSet];
            for (int j = 0; j < numInSet; j++) {
                int value = scan.nextInt();
                values[j] = (value > 0) ? 1 : 0;
            }
            sets.add(new Set(sets_name, values));
        }
    }
    private static ArrayList<String> getAllUnicNames() {
        ArrayList<String> result = new ArrayList<>();
        for (String element : priority_list) {
            if (!result.contains(element)) {
                result.add(element);
            }
        }
        return result;
    }
    private static void readExpression() {
        System.out.println("Write an expression : ");
        Scanner scanner = new Scanner(System.in);
        String expression = scanner.nextLine();
        String[] splited = expression.split("\\s+");
        for (String s : splited) {
            if (s.equals("*") || s.equals("+") || s.equals("~") ||
                    s.equals("=>") || s.equals("`")) {
                operators.add(s);
            } else {
                priority_list.add(s);
            }
        }
        System.out.println("Accepted: ");
    }
}
