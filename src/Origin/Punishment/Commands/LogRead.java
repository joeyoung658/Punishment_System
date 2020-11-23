package Origin.Punishment.Commands;


import Origin.Punishment.Main;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.util.ChatPaginator;


import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class LogRead implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        //StringBuffer stringBuffer = new StringBuffer();
        List<String> data;
//// TODO: Make it look pretty
//// TODO: Add sub commands for all type of logs.
//OR ITS LIKE THIS


        if (cmd.getName().equalsIgnoreCase("logread")) {
        try {
            //stringBuffer = log_reading("BanLog.txt");
            data = log_reading("BanLog.txt");

            StringBuilder log = new StringBuilder();
            for (int i = 0; i < data.size() ; i++) {
                log.append(data.get(i));
            }


            String datas = data.toString();

            StringBuilder log2 = new StringBuilder();
            for(String w:datas.split("\n")){

                log2.append(w);

            }

            int pageNumber;
            int pageHeight;
            int pageWidth;
            String type;

            if (args.length == 0) {
                type = "Ban log";
                pageNumber = 1;
            } else {
                type = "Ban log";
                if (isInt(args[0])) {
                    pageNumber = Integer.parseInt(args[0]);
                } else {
                    pageNumber = 1;
                }
            }


            if (sender instanceof ConsoleCommandSender) {
                pageHeight = ChatPaginator.UNBOUNDED_PAGE_HEIGHT;
                pageWidth = ChatPaginator.UNBOUNDED_PAGE_WIDTH;
            } else {
                pageHeight = ChatPaginator.CLOSED_CHAT_PAGE_HEIGHT - 1;
                pageWidth = ChatPaginator.GUARANTEED_NO_WRAP_CHAT_PAGE_WIDTH;
            }

            ChatPaginator.ChatPage page = ChatPaginator.paginate(log2.toString(), pageNumber, pageWidth, pageHeight);

            StringBuilder header = new StringBuilder();
            header.append(ChatColor.AQUA);
            header.append("--------- ");
            header.append(ChatColor.LIGHT_PURPLE);
            header.append("LogRead: ");
            header.append(type);
            header.append(" ");
            if (page.getTotalPages() > 1) {
                header.append("(");
                header.append(page.getPageNumber());
                header.append("/");
                header.append(page.getTotalPages());
                header.append(") ");
            }
            header.append(ChatColor.AQUA);
            for (int i = header.length(); i < ChatPaginator.GUARANTEED_NO_WRAP_CHAT_PAGE_WIDTH; i++) {
                header.append("-");
            }
            sender.sendMessage(header.toString());




//            for(String w:page.getLines().toString().split("\n")){
//
//                sender.sendMessage(w.toString());
//
//            }

                sender.sendMessage(page.getLines());

            return true;




            //stringBuffer.toString();

//            sender.sendMessage(data.get(0));
//
//            if (stringBuffer.length() > 5) {
//                sender.sendMessage("Four");
//            } else {
//                sender.sendMessage("Other");
//            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        }

            return true; //ends commands statement

    }


    public static List log_reading(String a) throws IOException {
        //try {
        File file = new File(Main.instance.getDataFolder(), a);
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        //StringBuffer stringBuffer = new StringBuffer();
        List<String> data = new ArrayList<>();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            //stringBuffer.append(line);
            //stringBuffer.append("\n");
            data.add(line);
        }
        fileReader.close();
        return data;
    }

    public static boolean isInt(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }



}


