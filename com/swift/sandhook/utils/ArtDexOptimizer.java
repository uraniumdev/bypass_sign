package com.swift.sandhook.utils;

import android.os.Build;
import com.swift.sandhook.SandHook;
import com.swift.sandhook.SandHookConfig;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ArtDexOptimizer {
  public static void dexoatAndDisableInline(String paramString1, String paramString2) throws IOException {
    File file = new File(paramString2);
    if (!file.exists())
      file.getParentFile().mkdirs(); 
    ArrayList arrayList = new ArrayList();
    arrayList.add("dex2oat");
    if (SandHookConfig.SDK_INT >= 24) {
      arrayList.add("--runtime-arg");
      arrayList.add("-classpath");
      arrayList.add("--runtime-arg");
      arrayList.add("&");
    } 
    arrayList.add("--dex-file=" + paramString1);
    arrayList.add("--oat-file=" + paramString2);
    StringBuilder stringBuilder = (new StringBuilder()).append("--instruction-set=");
    if (SandHook.is64Bit()) {
      paramString1 = "arm64";
    } else {
      paramString1 = "arm";
    } 
    arrayList.add(stringBuilder.append(paramString1).toString());
    arrayList.add("--compiler-filter=everything");
    if (SandHookConfig.SDK_INT >= 22 && SandHookConfig.SDK_INT < 29)
      arrayList.add("--compile-pic"); 
    if (SandHookConfig.SDK_INT > 25) {
      arrayList.add("--inline-max-code-units=0");
    } else if (Build.VERSION.SDK_INT >= 23) {
      arrayList.add("--inline-depth-limit=0");
    } 
    ProcessBuilder processBuilder = new ProcessBuilder(arrayList);
    processBuilder.redirectErrorStream(true);
    process = processBuilder.start();
    StreamConsumer.consumeInputStream(process.getInputStream());
    StreamConsumer.consumeInputStream(process.getErrorStream());
    try {
      int i = process.waitFor();
      if (i == 0)
        return; 
      IOException iOException = new IOException();
      StringBuilder stringBuilder1 = new StringBuilder();
      this();
      this(stringBuilder1.append("dex2oat works unsuccessfully, exit code: ").append(i).toString());
      throw iOException;
    } catch (InterruptedException process) {
      throw new IOException("dex2oat is interrupted, msg: " + process.getMessage(), process);
    } 
  }
  
  private static class StreamConsumer {
    static final Executor STREAM_CONSUMER = Executors.newSingleThreadExecutor();
    
    static void consumeInputStream(final InputStream is) { STREAM_CONSUMER.execute(new Runnable() {
            final InputStream val$is;
            
            public void run() {
              if (is == null)
                return; 
              arrayOfByte = new byte[256];
              try {
                while (true) {
                  int i = is.read(arrayOfByte);
                  if (i > 0)
                    continue; 
                  break;
                } 
              } catch (IOException arrayOfByte) {
              
              } finally {
                try {
                  is.close();
                } catch (Exception arrayOfByte) {}
              } 
            }
          }); }
  }
  
  class null implements Runnable {
    final InputStream val$is;
    
    public void run() {
      if (is == null)
        return; 
      arrayOfByte = new byte[256];
      try {
        while (true) {
          int i = is.read(arrayOfByte);
          if (i > 0)
            continue; 
          break;
        } 
      } catch (IOException arrayOfByte) {
      
      } finally {
        try {
          is.close();
        } catch (Exception arrayOfByte) {}
      } 
    }
  }
}


/* Location:              D:\Android\APK Tool\APK.Tool.GUI.v3.2.2.0\classes_merge_1.jar!\com\swift\sandhoo\\utils\ArtDexOptimizer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */