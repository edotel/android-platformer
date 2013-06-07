package com.beefydroid.platformer.framework;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Leo on 7/06/13.
 */
public interface FileIO {
    public InputStream readAsset(String fileName) throws IOException;

    public InputStream readFile(String fileName) throws IOException;

    public OutputStream writeFile(String fileName) throws IOException;
}
