package ozaii.managers;

import org.json.JSONObject;

import ozaii.infos;
import ozaii.utils.logger.OLogger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
//made by ozaii in 2022
public class PlayerStatusManager {
    private static final String API_BASE_URL = "http://localhost:3000/playerstatus";
    infos i = new infos();
    String playername = i.Player_Name;

    public boolean getPlayerCapeStatus(String playerName) {
        String apiUrl = API_BASE_URL + "?name=" + playerName;
        String jsonResponse = sendGetRequest(apiUrl);

        try {
            JSONObject jsonObject = new JSONObject(jsonResponse);
            int c = jsonObject.getInt("cape_status");
            if (c == 1) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean getPlayerWingStatus(String playerName) {
        String apiUrl = API_BASE_URL + "?name=" + playerName;
        String jsonResponse = sendGetRequest(apiUrl);

        try {
            JSONObject jsonObject = new JSONObject(jsonResponse);
            int c = jsonObject.getInt("wing_status");
            if (c == 1) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean getPlayerSkinStatus(String playerName) {
        String apiUrl = API_BASE_URL + "?name=" + playerName;
        String jsonResponse = sendGetRequest(apiUrl);

        try {
            JSONObject jsonObject = new JSONObject(jsonResponse);
            int c = jsonObject.getInt("skin_status");
            if (c == 1) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean getPlayerHdSkinStatus(String playerName) {
        String apiUrl = API_BASE_URL + "?name=" + playerName;
        String jsonResponse = sendGetRequest(apiUrl);

        try {
            JSONObject jsonObject = new JSONObject(jsonResponse);
            int c = jsonObject.getInt("hdskin_status");
            if (c == 1) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    public boolean getPlayerBanStatus(String playerName) {
        String apiUrl = API_BASE_URL + "?name=" + playerName;
        String jsonResponse = sendGetRequest(apiUrl);

        try {
            JSONObject jsonObject = new JSONObject(jsonResponse);
            int c = jsonObject.getInt("ban_status");
            if (c == 1) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean getPlayerPremiumStatus(String playerName) {
        String apiUrl = API_BASE_URL + "?name=" + playerName;
        String jsonResponse = sendGetRequest(apiUrl);

        try {
            JSONObject jsonObject = new JSONObject(jsonResponse);
            int c = jsonObject.getInt("premium_status");
            if (c == 1) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean getPlayerStaffStatus(String playerName) {
        String apiUrl = API_BASE_URL + "?name=" + playerName;
        String jsonResponse = sendGetRequest(apiUrl);

        try {
            JSONObject jsonObject = new JSONObject(jsonResponse);
            int c = jsonObject.getInt("staff_status");
            if (c == 1) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private String sendGetRequest(String apiUrl) {
        StringBuilder response = new StringBuilder();

        try {
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return response.toString();
    }

    public void logAllStatus() {
        boolean wingStatus = getPlayerWingStatus(i.Player_Name);
        boolean capeStatus = getPlayerCapeStatus(i.Player_Name);
        boolean skinStatus = getPlayerSkinStatus(i.Player_Name);
        boolean hdSkinStatus = getPlayerHdSkinStatus(i.Player_Name);
        boolean banStatus = getPlayerBanStatus(i.Player_Name);
        boolean premiumStatus = getPlayerPremiumStatus(i.Player_Name);
        boolean staffStatus = getPlayerStaffStatus(i.Player_Name);

        OLogger.info("Wing Status: " + wingStatus);
        OLogger.info("Cape Status: " + capeStatus);
        OLogger.info("Skin Status: " + skinStatus);
        OLogger.info("HD Skin Status: " + hdSkinStatus);
        OLogger.info("Ban Status: " + banStatus);
        OLogger.info("Premium Status: " + premiumStatus);
        OLogger.info("Staff Status: " + staffStatus);
    }

}
