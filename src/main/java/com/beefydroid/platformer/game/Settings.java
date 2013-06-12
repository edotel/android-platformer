package com.beefydroid.platformer.game;

/**
 * Created by Leo on 12/06/13.
 */
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import com.beefydroid.platformer.framework.FileIO;

public class Settings {
    public static boolean soundEnabled = true;
    public static String settings_file = ".platformer";
    private static String debug_tag = "Platformer Settings";

    public static void load(FileIO files){
        BufferedReader in = null;
        try{
            in = new BufferedReader(new InputStreamReader(files.readFile(settings_file)));
            soundEnabled = Boolean.parseBoolean(in.readLine());

        } catch (IOException e) {
            Log.d(debug_tag, "Failed to open file.. not found?");

        } catch (NumberFormatException e) {
            Log.d(debug_tag, "Failed to parse settings.. badly encoded?");
        } finally {
            try{
                if (in != null){
                    in.close();
                }
            } catch (IOException e){
                Log.d(debug_tag, "Failed to close settings file.. Time to panic.");
            }
        }
    }
}
