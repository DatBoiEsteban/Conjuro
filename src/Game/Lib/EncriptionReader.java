package Lib;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;

public class EncriptionReader {
    private ArrayList<String> keylist;

    public EncriptionReader(String filePath) {
        try {
            BufferedReader BR = new BufferedReader(new FileReader(filePath));
            this.keylist = new ArrayList<>();
            String line = BR.readLine();
            while (!line.equals("")) {
                this.keylist.add(line);
                line = BR.readLine();
            }
        } catch (Exception e) {
            Logger.Log(e.getLocalizedMessage());
        }
    }

    public String getRandomKey() {
        int rand = (int)Math.round(Math.random() * this.keylist.size());
        return this.keylist.get(rand);
    }
}
