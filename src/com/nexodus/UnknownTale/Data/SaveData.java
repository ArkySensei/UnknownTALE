package com.nexodus.UnknownTale.Data;

import com.nexodus.UnknownTale.Login.LoginSession;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

//This is the function which save or load the object value into a file call .sav inside UnknownTaleFolder
public class SaveData {
    public static final String filename = "/"+LoginSession.Username+"UnkTale001.sav";
    public static final String root = "/UnknownTaleFolder";

    //This is a serialize function which enable the value to be save inside the .sav
    public static void save(Serializable objectToSerialize) {

        FileOutputStream fos = null;

        try{
            fos = new FileOutputStream(createDataFolder() + filename);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(objectToSerialize);
            oos.flush();
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //This is a load function which read the .sav and get the value inside it.
    public static WorldSave load() {

        if (checkFileExists()) {

            FileInputStream fis = null;
            WorldSave loadedObject;

            try {
                fis = new FileInputStream(createDataFolder() + filename);
                ObjectInputStream ois = new ObjectInputStream(fis);
               loadedObject = (WorldSave) ois.readObject();
                ois.close();
                return loadedObject;
            } catch (ClassNotFoundException | IOException e) {
                e.printStackTrace();
            }

        }
        return null;
    }

    //Checking whether the folder and the .sav file is exist or not
    public static boolean checkFileExists() {
        return new File(createDataFolder() + filename).isFile();
    }

    //Creating a data Folder
    private static String createDataFolder() {

        String home = System.getProperty("user.home");
        String os = System.getProperty("os.name").toLowerCase();

        if (os.contains("win")) {
            home = System.getenv("appdata");
        }
        else
            if (os.contains("mac")) {
                //filepath
                home += "~/Library/Application Support";
            }
            else
                 if (os.contains("nix") || os.contains("nux") || os.contains("aix")) {
                     home += "~/.";
                 }
        File dir = new File(home);
        dir = new File(dir, root);

        if (!dir.exists()) {
            dir.mkdir();
        }
        return dir.getAbsolutePath();

    }

}
