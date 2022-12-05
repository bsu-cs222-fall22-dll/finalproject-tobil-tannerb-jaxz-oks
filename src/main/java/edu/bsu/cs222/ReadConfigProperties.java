package edu.bsu.cs222;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadConfigProperties {

    private static String guildID;
    private static String botToken;
    private static String imgflipUser;
    private static String imgflipPassword;

    public static void main() {
        Properties properties = new Properties();
        InputStream InputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties");
        try {
            properties.load(InputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        guildID = properties.getProperty("GUILD_ID");
        botToken = properties.getProperty("TOKEN");
        imgflipUser = properties.getProperty("USER");
        imgflipPassword = properties.getProperty("PASSWORD");

    }

    public static String getGuildID(){
        main();
        return guildID;
    }

    public static String getBotToken(){
        main();
        return botToken;
    }

    public static String getImgflipUser() {
        main();
        return imgflipUser;
    }

    public static String getImgflipPassword() {
        main();
        return imgflipPassword;
    }
}
