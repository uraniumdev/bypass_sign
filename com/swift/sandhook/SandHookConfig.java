package com.swift.sandhook;

import android.os.Build;

public class SandHookConfig {
  static  {
    boolean bool;
    SDK_INT = Build.VERSION.SDK_INT;
    DEBUG = true;
    if (SDK_INT < 29) {
      bool = true;
    } else {
      bool = false;
    } 
    compiler = bool;
    curUser = 0;
    delayHook = true;
    libLoader = new LibLoader() {
        public void loadLib() {
          if (SandHookConfig.libSandHookPath == null) {
            System.loadLibrary("sandhook");
          } else {
            System.load(SandHookConfig.libSandHookPath);
          } 
        }
      };
  }
  
  public static interface LibLoader {
    void loadLib();
  }
}


/* Location:              D:\Android\APK Tool\APK.Tool.GUI.v3.2.2.0\classes_merge_1.jar!\com\swift\sandhook\SandHookConfig.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */