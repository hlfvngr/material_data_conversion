package com.hgits.hotc.utils;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ZipUtils {

    public static void unzip(String parent, InputStream inputStream) throws IOException {
        ZipInputStream zis = new ZipInputStream(inputStream);

        ZipEntry nextEntry = null;
        while (null != (nextEntry = zis.getNextEntry())){
            if(!nextEntry.isDirectory()){
                File f = new File(parent, nextEntry.getName());
                File parentFile = f.getParentFile();
                if (!parentFile.exists()) {
                    parentFile.mkdirs();
                }

                FileOutputStream fos = new FileOutputStream(f);
                BufferedOutputStream bos = new BufferedOutputStream(fos);

                byte[] bytes = new byte[1024];
                int len;
                while(-1 != (len = zis.read(bytes))){
                    bos.write(bytes, 0, len);
                }
                bos.flush();
                bos.close();
                fos.close();
            }
        }
    }
}
