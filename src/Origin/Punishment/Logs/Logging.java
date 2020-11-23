package Origin.Punishment.Logs;

import Origin.Punishment.Main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


public class Logging {

    public static void logToFile(String message, String logtype)

    {

        try
        {
            File dataFolder = Main.instance.getDataFolder();
            if(!dataFolder.exists())
            {
                dataFolder.mkdir();

            }

            File saveTo = new File(Main.instance.getDataFolder(), logtype + ".txt");
            if (!saveTo.exists())
            {
                saveTo.createNewFile();
            }


            FileWriter fw = new FileWriter(saveTo, true);

            PrintWriter pw = new PrintWriter(fw);

            pw.println(message);

            pw.flush();

            pw.close();

        } catch (IOException e)
        {

            e.printStackTrace();

        }

    }




}
