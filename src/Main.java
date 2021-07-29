import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;
/**
 * @author Aditya Gupta
 * @version 1.0
 */
public class Main {
    static boolean flag = false;
    static boolean flag2 = false;
    static Scanner scan = new Scanner(System.in);

    /**
     * This is the main method that houses the menu and calls all relevant methods
     * @param args For main method
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        System.out.println("Welcome to WasteMaster");
        System.out.println("What do you want to do?");
        System.out.println("1. Create a new category of waste with corresponding colour bin");
        System.out.println("2. Update our records with a new waste");
        System.out.println("3. Find out what your waste will be worth when you sell it");
        int ch = scan.nextInt();
        switch (ch)
        {
            case 1:collectCategory();
            break;
            case 2:WasteMaster();
            break;
            case 3:NewTransaction();
            break;
        }
    }

    /**
     * This method is used to collect the category information
     * @throws IOException
     */
    public static void collectCategory() throws IOException {
        System.out.println("Enter the category");
        String category = scan.next();
        System.out.println("Enter bin colour");
        String bin = scan.next();
        addCategory(category,bin);
    }

    /**
     * This is used to write to the category txt file
     * @param category Name of the category
     * @param bin Colour of corresponding bin
     * @throws IOException
     */
    public static void addCategory(String category, String bin) throws IOException {
        FileWriter fileWriter = new FileWriter("/Users/adityagupta/Desktop/Coding/JavaImp/WasteManagement/src/Category.txt",true);
        fileWriter.write(category+":"+bin+"\n");
        fileWriter.close();
        System.out.println("Done with adding Category");
    }

    /**
     * This is to collect the waste information from the user
     * @throws IOException
     */
    public static void WasteMaster() throws IOException{
        System.out.println("Enter Name of Waste");
        String name = scan.next();
        System.out.println("Enter category");
        String category = scan.next();
        System.out.println("Enter the price");
        double cost = scan.nextDouble();
        UpdateWasteMasterFile(name,category,cost);
    }

    /**
     * This is used to write to the master file containing list of wastes
     * @param name Name of the waste
     * @param category Category of the waste
     * @param cost Cost of the waste
     * @throws IOException
     */
    public static void UpdateWasteMasterFile(String name, String category, double cost) throws IOException{
        FileWriter fileWriter = new FileWriter("/Users/adityagupta/Desktop/Coding/JavaImp/WasteManagement/src/master.txt",true);
        fileWriter.write(name+":"+category+"_"+cost+"\n");
        fileWriter.close();
        System.out.println("Done with updating Master");
    }

    /**
     * This is used to create a new transaction of waste
     * @throws IOException
     */
    public static void NewTransaction() throws IOException {
        String date = LocalDate.now().toString();
        String waste = waste();
        int quantity = quantity();
        double value = seek_value(waste,quantity);
        String category = seek_category(waste);
        String bin = seek_bin(category);
        if(flag){
            System.out.println("Hey! We found that waste in our records, here it is:");
            System.out.println("1.Name: "+waste);
            System.out.println("2.Price: "+Math.round(value));
            System.out.println("3.Quantity: "+quantity);
            System.out.println("4.Category: "+ category);
            System.out.println("4.Bin: "+bin);
            writeTransaction(date,waste,quantity,value);
        }
        else
        {
            System.out.println("This waste was not found in our records, would you like to help us add this to our records?(yes/no)");
            String answer = scan.nextLine();
            if(answer.equalsIgnoreCase("yes"))
            {
                System.out.println("\fSure!");
            }
            else{
                System.out.println("Ok :(");
                scan.close();
                System.exit(0);
            }
        }
    }

    /**
     * This is used to collect the quantity of waste
     * @return Quantity of waste
     */
    public static int quantity(){
        System.out.println("Enter quantity");
        int qty = scan.nextInt();
        if(qty<=0) {
            System.out.println("Quantity can not be less than 0");
            quantity();
        }
        return qty;
    }

    /**
     * This method is to collect the name of the waste
     * @return Name of waste
     */
    public static String waste(){
        /**
         * This is to collect waste name
         */
        System.out.println("Enter name of waste");
        return scan.next();
    }

    /**
     * This function is used to open a txt file in the program and loop through it to find its value
     * @param waste
     * @param quantity
     * @return Value of waste
     * @throws IOException
     */
    public static double seek_value(String waste,int quantity) throws IOException {
        Path path = Paths.get("/Users/adityagupta/Desktop/Coding/JavaImp/WasteManagement/src/master.txt");
        String all = new String(Files.readAllBytes(path));
        String price = "";
        String allWastes[] = all.split("\n");
        for (int i = 0; i < allWastes.length; i++) {
            if(allWastes[i].startsWith(waste))
            {
                int pos = allWastes[i].indexOf("_");
                price = allWastes[i].substring(pos+1);
                flag = true;
                break;
            }
        }
        if (flag)
        {
            double value = Double.parseDouble(price) * quantity;
            return value;
        }
        else return 0;
    }

    /**
     * This function is used to open a txt file in the program and loop through it to find its category
     * @param waste Name of the waste
     * @return Name of the category
     * @throws IOException
     */
    public static String seek_category(String waste) throws IOException {

        Path path = Paths.get("/Users/adityagupta/Desktop/Coding/JavaImp/WasteManagement/src/master.txt");
        String all = new String(Files.readAllBytes(path));
        String category = "",attribute = "";
        String allWastes[] = all.split("\n");
        for (int i = 0; i < allWastes.length; i++) {
            if(allWastes[i].startsWith(waste))
            {
                String nowaste = allWastes[i].replace(waste,"");
                category = nowaste.replaceAll("[^a-zA-Z]","");
                break;
            }
        }
        return category;
    }

    /**
     * This function is used to open a txt file in the program and loop through it to find the corresponding bin
     * @param category
     * @return Type of bin
     * @throws IOException
     */
    public static String seek_bin(String category) throws IOException {
        Path path = Paths.get("/Users/adityagupta/Desktop/Coding/JavaImp/WasteManagement/src/Category.txt");
        String all = new String(Files.readAllBytes(path));
        String []allCategories = all.split("\n");
        String bin = "";
        for (int i = 0; i < allCategories.length; i++) {
            if(allCategories[i].startsWith(category)){
                String noCategory = allCategories[i].replace(category,"");
                bin = noCategory.replace(":","");
                flag2 = true;
                break;
            }
        }
        if(flag2)
            return bin;
        else
            return "Bin for existing category not found";
    }

    /**
     * This function is used to write a log of the transaction to the transactionlist.txt file
     * @param date Date of the transaction
     * @param waste Name of waste
     * @param quantity Quantity of waste
     * @param value Value of the waste
     * @throws IOException
     */
    public static void writeTransaction(String date,String waste, int quantity, double value) throws IOException {
        FileWriter fileWriter = new FileWriter("/Users/adityagupta/Desktop/Coding/JavaImp/WasteManagement/src/transactionlist.txt",true);
        fileWriter.write(date + ", Name:" + waste + ", Quantity: " + quantity + ", Price: "+value+"\n");
        fileWriter.close();
        System.out.println("Finished writing to transaction");
    }
}
