package org.maxmcold.models;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class OutputWriter {
    public enum Action {cool,freeze}
    public boolean write(Action action){
        try {
            BufferedWriter bf = new BufferedWriter(new FileWriter("/test.txt"));
            switch(action){
                case cool -> bf.write("cool");
                case freeze -> bf.write("freeze");
            }
            bf.flush();
            bf.close();

        }catch(IOException e){
            e.printStackTrace();
        }

        return true;
    }
}
