package top.hooksign;

import android.content.Context;
import android.content.pm.PackageInfo;
import arm.Loader;

public class Hooker {
  public static Context HookContext;
  
  public static Object a;
  
  public static Object b;
  
  static  {
    Loader.registerNativesForClass(1);
    native_special_clinit0();
  }
  
  public static native PackageInfo hook(Object paramObject, String paramString, int paramInt) throws Throwable;
  
  public static native PackageInfo hook(Object paramObject, String paramString, int paramInt1, int paramInt2) throws Throwable;
  
  public static native void hook(Context paramContext);
  
  public static native String hookz(String paramString);
}


/* Location:              D:\Android\APK Tool\APK.Tool.GUI.v3.2.2.0\classes_merge_1.jar!\top\hooksign\Hooker.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */