package dataLayer;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import businessLayer.MenuItem;


public class FileWriter {
    public FileWriter(String nume, ArrayList<MenuItem> m, Date date) throws IOException {
        String numeFisier=nume;
        numeFisier+=".txt";
        File myObj = new File(numeFisier);
        if (myObj.createNewFile()) {
            System.out.println("File created: " + myObj.getName());
        } else {
            System.out.println("File already exists.");
        }
        PrintWriter writer = new PrintWriter(numeFisier, "UTF-8");
        int pret=0;
        for(MenuItem i:m)
        {
            writer.println("produs: "+i.getTitle());
            pret+=i.getPrice();
        }
        writer.println("The total price is "+pret);
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        writer.println(formatter.format(date));
        writer.close();

    }

}
