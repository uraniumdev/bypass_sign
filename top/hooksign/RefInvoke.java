package top.hooksign;

import arm.Loader;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class RefInvoke {
  private static final String TAG = "RefInvoke";
  
  static  {
    Loader.registerNativesForClass(2);
    native_special_clinit8();
  }
  
  public static native Object currentThread() throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException;
  
  public static native Field findField(Object paramObject, String paramString) throws NoSuchFieldException;
  
  public static native Method findMethod(Object paramObject, String paramString, Class... paramVarArgs) throws NoSuchMethodException;
  
  public static native Object getFieldObject(Object paramObject, String paramString);
  
  public static native void setFieldObject(Object paramObject1, String paramString, Object paramObject2);
  
  public native Object getFieldObject(String paramString1, Object paramObject, String paramString2);
  
  public native void setFieldObject(String paramString1, String paramString2, Object paramObject1, Object paramObject2);
}


/* Location:              D:\Android\APK Tool\APK.Tool.GUI.v3.2.2.0\classes_merge_1.jar!\top\hooksign\RefInvoke.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */