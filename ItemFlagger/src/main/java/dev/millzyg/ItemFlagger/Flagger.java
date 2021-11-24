package dev.millzyg.ItemFlagger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static dev.millzyg.ItemFlagger.GlobalFields.config;

public class Flagger {
    private boolean logToFile;
    private boolean alertOperators;
    private boolean printToConsole;
    private Location location;
    private Player player;
    private Material material;

    public Flagger setLogToFile(boolean logToFile) {
        this.logToFile = logToFile;
        return this;
    }

    public Flagger setAlertOperators(boolean alertOperators) {
        this.alertOperators = alertOperators;
        return this;
    }

    public Flagger setPrintToConsole(boolean printToConsole) {
        this.printToConsole = printToConsole;
        return this;
    }

    public Flagger(Player player, Location location, Material material) {
        this.player = player;
        this.location = location;
        this.material = material;
    }

    private String formatMessage(String f) {
        f = f.replace("{player}", player.getName());
        f = f.replace("{location}", String.format("%d, %d, %d", location.getBlockX(), location.getBlockY(), location.getBlockZ()));
        f = f.replace("{item}", material.name());
        f = f.replace("{world}", location.getWorld().getName());

        return f;
    }

    private static ArrayList<Player> getOperators() {
        ArrayList<Player> operators = new ArrayList<>();
        for(Player onlineOperators : Bukkit.getOnlinePlayers()) {
            if(onlineOperators.isOp()) {
                operators.add(onlineOperators);
            }
        }
        return operators;
    }

    public void sendFlagMessage() {
        String message = formatMessage(config.getFormat());

        if (logToFile) {
            DateTimeFormatter titleFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            DateTimeFormatter logFormat = DateTimeFormatter.ofPattern("[dd/MM/yyyy HH:mm:ss]");
            LocalDateTime localDate = LocalDateTime.now();

            try {
                File logsDir = new File(ItemFlagger.instance.getDataFolder(), "Logs");
                logsDir.mkdirs();

                FileWriter fw = new FileWriter(new File(logsDir, localDate.format(titleFormat) + ".log"), true);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter pw = new PrintWriter(bw);

                pw.println(localDate.format(logFormat) + " " + message);

                pw.close();
                bw.close();
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (alertOperators) {
            for (Player operator : getOperators()) {
                operator.sendMessage(ChatColor.YELLOW + "[ItemFlagger] " + message);
            }
        }

        if (printToConsole) {
            Bukkit.getServer().getConsoleSender().sendMessage(message);
        }
    }
}
