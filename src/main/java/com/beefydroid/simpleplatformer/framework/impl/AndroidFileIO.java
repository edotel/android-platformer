package com.beefydroid.simpleplatformer.framework.impl;

/**
 * Created by Leo on 11/06/13.
 *
 * Implementation of file manager for loading files.
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.os.Environment;
import android.preference.PreferenceManager;

import com.beefydroid.simpleplatformer.framework.FileIO;
public class AndroidFileIO implements FileIO{
    Context context;
    AssetManager assets;
    String externalStoragePath;

    // Instantiate with Android Context (gateway to many Android features)
    public AndroidFileIO(Context context){
        this.context = context;
        this.assets = context.getAssets();
        this.externalStoragePath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator;
    }
    public InputStream readAsset(String fileName) throws IOException {
        return assets.open(fileName);
    }
    public InputStream readFile(String fileName) throws IOException {
        return new FileInputStream(externalStoragePath + fileName);
    }
    public OutputStream writeFile(String fileName) throws IOException {
        return new FileOutputStream(externalStoragePath + fileName);
    }
    public SharedPreferences getPreferences(){
        return PreferenceManager.getDefaultSharedPreferences(context);
    }
}
