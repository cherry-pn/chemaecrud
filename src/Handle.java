
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author balugoma_sd2023
 */
public class Handle {

    private String file = "account.txt";
    FileWriter write = null;
    FileReader read = null;

    public Handle() throws IOException {

    }

    public void register(ArrayList<String> information) throws IOException {

        try {
            write = new FileWriter(file, true);
            write.write(information.get(0) + "\t" + information.get(1) + "\t" + information.get(2) + "\t" + information.get(3) + "\t" + information.get(4));
            write.write("\r\n");
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } finally {
            write.close();
            System.out.println("connection closed");
        }

    }

    public void retrieve() throws FileNotFoundException {
        read = new FileReader(file);
        String line;
        ArrayList<String[]>templine=new ArrayList();
        try {

            BufferedReader bufferreader = new BufferedReader(new FileReader(file));

            while ((line = bufferreader.readLine()) != null) {
                System.out.println(line);
                String[] temp=line.split(" ");
                System.out.println("temporary "+line.split("\t"));
                templine.add(temp);

            }

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        System.out.println(templine.toString());
        for(String[] item :templine){
            System.out.println(item);
        }
    }

    public static void main(String[] args) throws IOException {
        Handle hand = new Handle();
        ArrayList<String> x = new ArrayList<>(Arrays.asList("fname", "mname", "lname", "email", "password"));
        hand.retrieve();
//        hand.register(x);
    }

}
