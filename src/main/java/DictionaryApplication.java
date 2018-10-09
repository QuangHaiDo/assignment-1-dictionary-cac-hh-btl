import java.util.Scanner;

public class DictionaryApplication {
    public static void  menuVersion(){
        System.out.println("Choose version:");
        System.out.println("1. Commandline Ver");
        System.out.println("2. GUI ver");
    }
    public static void main(String[] args){
        menuVersion();
        int choice = -1;
        Scanner s = new Scanner(System.in);
        choice = s.nextInt();
        switch (choice){
            case 1: {
                // Start Dictionary CMD
                DictionaryCommandline init = new DictionaryCommandline();
                init.dictionaryBasic();
                break;
            }
            case 2: {
                // Start Dictionay GUI Version
                DictionaryGUIver init = new DictionaryGUIver();
                init.DictionaryGUIVersion();
                break;
            }
            default: break;
        }
    }
}
