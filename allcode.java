import os

def merge_files(directory, output_file):
    with open(output_file, 'w') as outfile:
        for root, _, files in os.walk(directory):
            for file in files:
                file_path = os.path.join(root, file)
                if file_path != output_file:
                    with open(file_path, 'r') as infile:
                        outfile.write(infile.read())
                    outfile.write('\n')  # Add a newline between files

# Usage example
directory_path = r'D:\Android\APK Tool\APK.Tool.GUI.v3.2.2.0\xx'
output_file_path = r'D:\Android\APK Tool\APK.Tool.GUI.v3.2.2.0\xx\al.txt'

merge_files(directory_path, output_file_path)
print(f"All files in '{directory_path}' and its subdirectories have been merged into '{output_file_path}'.")

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
package com.swift.sandhook;

public class ArtMethodSizeTest {
  public static final void method1() {}
  
  public static final void method2() {}
}


/* Location:              D:\Android\APK Tool\APK.Tool.GUI.v3.2.2.0\classes_merge_1.jar!\com\swift\sandhook\ArtMethodSizeTest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */
package com.swift.sandhook;

import android.util.Log;

public class ClassNeverCall {
  private void neverCall() {}
  
  private void neverCall2() { Log.e("ClassNeverCall", "ClassNeverCall2"); }
  
  private native void neverCallNative();
  
  private native void neverCallNative2();
  
  private static void neverCallStatic() {}
}


/* Location:              D:\Android\APK Tool\APK.Tool.GUI.v3.2.2.0\classes_merge_1.jar!\com\swift\sandhook\ClassNeverCall.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */
package com.swift.sandhook.utils;

import com.swift.sandhook.SandHookConfig;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Modifier;

public class ClassStatusUtils {
  static Field fieldStatusOfClass;
  
  static  {
    try {
      Field field = Class.class.getDeclaredField("status");
      fieldStatusOfClass = field;
      field.setAccessible(true);
    } catch (NoSuchFieldException noSuchFieldException) {}
  }
  
  public static int getClassStatus(Class paramClass, boolean paramBoolean) {
    byte b;
    if (paramClass == null)
      return 0; 
    int i = 0;
    try {
      b = fieldStatusOfClass.getInt(paramClass);
    } finally {
      paramClass = null;
    } 
    if (paramBoolean)
      i = (int)(toUnsignedLong(b) >> 28); 
    return i;
  }
  
  public static boolean isInitialized(Class paramClass) {
    Field field = fieldStatusOfClass;
    boolean bool1 = true;
    boolean bool2 = true;
    boolean bool3 = true;
    boolean bool = true;
    if (field == null)
      return true; 
    if (SandHookConfig.SDK_INT >= 30) {
      if (getClassStatus(paramClass, true) < 14)
        bool = false; 
      return bool;
    } 
    if (SandHookConfig.SDK_INT >= 28) {
      if (getClassStatus(paramClass, true) == 14) {
        bool = bool1;
      } else {
        bool = false;
      } 
      return bool;
    } 
    if (SandHookConfig.SDK_INT == 27) {
      if (getClassStatus(paramClass, false) == 11) {
        bool = bool2;
      } else {
        bool = false;
      } 
      return bool;
    } 
    if (getClassStatus(paramClass, false) == 10) {
      bool = bool3;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public static boolean isStaticAndNoInited(Member paramMember) {
    boolean bool1 = paramMember instanceof java.lang.reflect.Method;
    boolean bool2 = false;
    if (!bool1)
      return false; 
    Class clazz = paramMember.getDeclaringClass();
    if (Modifier.isStatic(paramMember.getModifiers()) && !isInitialized(clazz))
      bool2 = true; 
    return bool2;
  }
  
  public static long toUnsignedLong(int paramInt) { return paramInt & 0xFFFFFFFFL; }
}


/* Location:              D:\Android\APK Tool\APK.Tool.GUI.v3.2.2.0\classes_merge_1.jar!\com\swift\sandhoo\\utils\ClassStatusUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */
package com.swift.sandhook.utils;

import android.os.Build;
import android.system.Os;
import android.text.TextUtils;
import com.swift.sandhook.HookLog;
import com.swift.sandhook.SandHookConfig;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileUtils {
  public static final boolean IS_USING_PROTECTED_STORAGE;
  
  static  {
    boolean bool;
    if (Build.VERSION.SDK_INT >= 24) {
      bool = true;
    } else {
      bool = false;
    } 
    IS_USING_PROTECTED_STORAGE = bool;
  }
  
  public static void chmod(String paramString, int paramInt) throws Exception {
    if (SandHookConfig.SDK_INT >= 21)
      try {
        Os.chmod(paramString, paramInt);
        return;
      } catch (Exception exception) {} 
    File file = new File(paramString);
    String str1 = "chmod ";
    if (file.isDirectory())
      str1 = "chmod " + " -R "; 
    String str2 = String.format("%o", new Object[] { Integer.valueOf(paramInt) });
    Runtime.getRuntime().exec(str1 + str2 + " " + paramString).waitFor();
  }
  
  public static void delete(File paramFile) throws IOException {
    for (File file : paramFile.listFiles()) {
      if (file.isDirectory()) {
        delete(file);
      } else if (!file.delete()) {
        throw new IOException();
      } 
    } 
    if (paramFile.delete())
      return; 
    throw new IOException();
  }
  
  public static String getDataPathPrefix() {
    String str;
    if (IS_USING_PROTECTED_STORAGE) {
      str = "/data/user_de/0/";
    } else {
      str = "/data/data/";
    } 
    return str;
  }
  
  public static String getPackageName(String paramString) {
    if (TextUtils.isEmpty(paramString)) {
      HookLog.e("getPackageName using empty dataDir");
      return "";
    } 
    int i = paramString.lastIndexOf("/");
    return (i < 0) ? paramString : paramString.substring(i + 1);
  }
  
  public static String readLine(File paramFile) {
    try {
      bufferedReader = new BufferedReader();
      FileReader fileReader = new FileReader();
      this(paramFile);
      this(fileReader);
    } finally {
      paramFile = null;
    } 
  }
  
  public static void writeLine(File paramFile, String paramString) {
    try {
      paramFile.createNewFile();
    } catch (IOException iOException) {}
    try {
      bufferedWriter = new BufferedWriter();
      FileWriter fileWriter = new FileWriter();
      this(paramFile);
      this(fileWriter);
    } finally {
      paramString = null;
    } 
  }
  
  public static interface FileMode {
    public static final int MODE_755 = 493;
    
    public static final int MODE_IRGRP = 32;
    
    public static final int MODE_IROTH = 4;
    
    public static final int MODE_IRUSR = 256;
    
    public static final int MODE_ISGID = 1024;
    
    public static final int MODE_ISUID = 2048;
    
    public static final int MODE_ISVTX = 512;
    
    public static final int MODE_IWGRP = 16;
    
    public static final int MODE_IWOTH = 2;
    
    public static final int MODE_IWUSR = 128;
    
    public static final int MODE_IXGRP = 8;
    
    public static final int MODE_IXOTH = 1;
    
    public static final int MODE_IXUSR = 64;
  }
}


/* Location:              D:\Android\APK Tool\APK.Tool.GUI.v3.2.2.0\classes_merge_1.jar!\com\swift\sandhoo\\utils\FileUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */
package top.hooksign;

import android.app.Application;
import android.content.Context;
import arm.Loader;
import java.io.InputStream;

public class HookApplication extends Application {
  public static boolean IOHookRedirect;
  
  private static String TAG;
  
  public static String aa;
  
  public static boolean apkPathRedirect;
  
  private static String bb;
  
  private static String cc;
  
  private static Application sOriginalApplication;
  
  static  {
    Loader.registerNativesForClass(0);
    native_special_clinit0();
    TAG = "Application";
    apkPathRedirect = true;
    IOHookRedirect = false;
    aa = "BK7h6WgkoIpbM1JNN1zZBttC/LFIaJGlGFiE+Kd30dVvEzGhFr2wFQnDUl0iv6XzOrkOIzSAnhftU9jp/0lJP9Ciozp4VsEtLeDTg6+SHWLs8/Ih+Ipwhlb/BDWbDoHN20UVvVx8FqLiTT4MwiaYt4sTKC3qOC6JwzEqv4BbwYobLfYjtWiGrKaxrjynnrblsuB/9s5cjd9jCg9Q6AsCpZHAdOMrOYDmsLmyBOR5a5ndPId0jVZDY6XfRaWKl8HbEY5xvKisNVVu+/XmYRSy50k1Bd1g/vnscPoRWHtZ2MsOieuaWuo/fsOcRZeH4oWbe9gXjnR00CgH2OSjxleWlMU+XJpfQKt7TN0cjc94epxxf5Lo6kDan0YJ3XXuaPVnc6mkRBUHXmG5Ecud+HryMCYHkF9lJXqrG3zkKTLaNwAJA+2gRTgxTkxsuKfNkD2Yj8s2cl0pGzjdnGAOKB50Zx2ipGMbVY7wa3ZdCQzL+6HPrW0vbyYGBrFzG56Q7NzRYtTZe+vlgB1aIbgMEmIr0B9wrh2EncXebICBPOKkcQqh9PMevhdfMDKBkjF4bQjKaXXCSHn/oCEaPgBNXth79xTj1JFzZ8PPhzJ9ioqZKKsf+fwZuZDy7YQRqtRWxrqiJJCo6eCSPq/N/OXvbixZD3rfYxlb5c8OqTPMLr36i/DmGbCvdmuplNnlmR1Q3nWDBYSmWoW00Ua+mXKXe5KMyfEFEG8uNLE5af7AsAZKryU/9Z65N82i8r78lSX73babi8sv88mLqmmsgPnIz+iKk+Rwgc5gDAJookesSrulkjAe8RZpE9h00OvIV1pxbN6dKt+HOMo+IyYIGftUodcOhqDtAg9sFbPYcHPMCtYJVVsJH4NULGNcYZQhAREzmCz7mjQwM0tzawjxM8a9PFvcFo5aQhGfX4Qg17OzFWQTnGuLjJrxCL6dN/ryv6WZbZXcqyLJu34vpAh0kGOKBa5/G1jk1VovzG6Ks8wVPN4QfVsFxcIbQ8P2iwRf5B4m7Sx8di8kJCcCtILQkQTHY9BukoiOA0ISuxNlqaW/fCHGiJ+NolFxsHIlMjlNKQ3FljFupkI9uUIi2QVh8c20V2t6/4excpVMEWkingFISGd04DEdVGmBnvja/8em64+0AbvsAihdRWI28ImiZIjecas+fMQQxW3eBfc/Lb8+xi1gaSiqg7jq4tw3U0con2AWzgnVhEGB3Xk194WXeA2kEGTnR5gUvg8MZ2qBg10BxAAIZMWDHjJUXdVmFCKRtEfK00oLXnHazQ2CI+tYCjK/yCCkUSmKA/9O/BmZ6pTDwa1kOMflyCvFPPd+NbEJE97D1aUuuHWrmuXrvjx8FCA5Cik5fu32jYkYj3EUvNIt+8YzWfF+1MAOcJJZBMQgQFJiigVZ";
    bb = "ZkNf8bpkjpqMIUtLYmHCs8lnDIToCG9Fk6KDwjJ0eXM=";
    cc = "EJg8sEKateTnPkjvl5O5Zg==";
  }
  
  public static native void copy(Context paramContext) throws Throwable;
  
  private static native boolean fileEqualsByte(InputStream paramInputStream1, InputStream paramInputStream2);
  
  public static native Application replaceApp(String paramString) throws Throwable;
  
  protected native void attachBaseContext(Context paramContext) throws Throwable;
  
  public native void onCreate();
}


/* Location:              D:\Android\APK Tool\APK.Tool.GUI.v3.2.2.0\classes_merge_1.jar!\top\hooksign\HookApplication.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */
package com.swift.sandhook.blacklist;

import com.swift.sandhook.SandHookConfig;
import java.lang.reflect.Member;
import java.util.HashSet;
import java.util.Set;

public class HookBlackList {
  public static Set<Class> classBlackList;
  
  public static Set<String> methodBlackList = new HashSet();
  
  public static Set<String> methodUseInHookBridge;
  
  public static Set<String> methodUseInHookStub;
  
  static  {
    classBlackList = new HashSet();
    methodUseInHookBridge = new HashSet();
    methodUseInHookStub = new HashSet();
    methodBlackList.add("java.lang.reflect.Method.invoke");
    methodBlackList.add("java.lang.reflect.AccessibleObject.setAccessible");
    methodUseInHookBridge.add("java.lang.Class.getDeclaredField");
    methodUseInHookBridge.add("java.lang.reflect.InvocationTargetException.getCause");
    methodUseInHookStub.add("java.lang.Object.equals");
    methodUseInHookStub.add("java.lang.Class.isPrimitive");
  }
  
  public static final boolean canNotHook(Member paramMember) {
    if (classBlackList.contains(paramMember.getDeclaringClass()))
      return true; 
    String str = paramMember.getDeclaringClass().getName() + "." + paramMember.getName();
    return methodBlackList.contains(str);
  }
  
  public static final boolean canNotHookByBridge(Member paramMember) {
    String str = paramMember.getDeclaringClass().getName() + "." + paramMember.getName();
    return methodUseInHookBridge.contains(str);
  }
  
  public static final boolean canNotHookByStub(Member paramMember) {
    if (SandHookConfig.SDK_INT >= 29 && Thread.class.equals(paramMember.getDeclaringClass()))
      return true; 
    String str = paramMember.getDeclaringClass().getName() + "." + paramMember.getName();
    return methodUseInHookStub.contains(str);
  }
}


/* Location:              D:\Android\APK Tool\APK.Tool.GUI.v3.2.2.0\classes_merge_1.jar!\com\swift\sandhook\blacklist\HookBlackList.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */
package com.swift.sandhook.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface HookClass {
  Class<?> value();
}


/* Location:              D:\Android\APK Tool\APK.Tool.GUI.v3.2.2.0\classes_merge_1.jar!\com\swift\sandhook\annotation\HookClass.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */
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
package com.swift.sandhook.wrapper;

public class HookErrorException extends Exception {
  public HookErrorException(String paramString) { super(paramString); }
  
  public HookErrorException(String paramString, Throwable paramThrowable) { super(paramString, paramThrowable); }
}


/* Location:              D:\Android\APK Tool\APK.Tool.GUI.v3.2.2.0\classes_merge_1.jar!\com\swift\sandhook\wrapper\HookErrorException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */
package com.swift.sandhook;

import android.util.Log;

public class HookLog {
  public static boolean DEBUG = SandHookConfig.DEBUG;
  
  public static final String TAG = "SandHook";
  
  public static int d(String paramString) { return Log.d("SandHook", paramString); }
  
  public static int e(String paramString) { return Log.e("SandHook", paramString); }
  
  public static int e(String paramString, Throwable paramThrowable) { return Log.e("SandHook", paramString, paramThrowable); }
  
  public static int i(String paramString) { return Log.i("SandHook", paramString); }
  
  public static int v(String paramString) { return Log.v("SandHook", paramString); }
  
  public static int w(String paramString) { return Log.w("SandHook", paramString); }
}


/* Location:              D:\Android\APK Tool\APK.Tool.GUI.v3.2.2.0\classes_merge_1.jar!\com\swift\sandhook\HookLog.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */
package com.swift.sandhook.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface HookMethod {
  String value() default "<init>";
}


/* Location:              D:\Android\APK Tool\APK.Tool.GUI.v3.2.2.0\classes_merge_1.jar!\com\swift\sandhook\annotation\HookMethod.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */
package com.swift.sandhook.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD})
public @interface HookMethodBackup {
  String value() default "<init>";
}


/* Location:              D:\Android\APK Tool\APK.Tool.GUI.v3.2.2.0\classes_merge_1.jar!\com\swift\sandhook\annotation\HookMethodBackup.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */
package com.swift.sandhook.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface HookMode {
  public static final int AUTO = 0;
  
  public static final int INLINE = 1;
  
  public static final int REPLACE = 2;
  
  int value() default 0;
}


/* Location:              D:\Android\APK Tool\APK.Tool.GUI.v3.2.2.0\classes_merge_1.jar!\com\swift\sandhook\annotation\HookMode.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */
package com.swift.sandhook.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface HookReflectClass {
  String value();
}


/* Location:              D:\Android\APK Tool\APK.Tool.GUI.v3.2.2.0\classes_merge_1.jar!\com\swift\sandhook\annotation\HookReflectClass.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */
package com.swift.sandhook.wrapper;

import android.text.TextUtils;
import com.swift.sandhook.SandHook;
import com.swift.sandhook.annotation.HookClass;
import com.swift.sandhook.annotation.HookMethodBackup;
import com.swift.sandhook.annotation.HookReflectClass;
import com.swift.sandhook.annotation.MethodParams;
import com.swift.sandhook.annotation.MethodReflectParams;
import com.swift.sandhook.annotation.Param;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class HookWrapper {
  public static void addHookClass(ClassLoader paramClassLoader, Class<?> paramClass) throws HookErrorException {
    Class clazz = getTargetHookClass(paramClassLoader, paramClass);
    if (clazz != null) {
      Map map = getHookMethods(paramClassLoader, clazz, paramClass);
      try {
        fillBackupMethod(paramClassLoader, paramClass, map);
        iterator = map.values().iterator();
        return;
      } finally {
        paramClassLoader = null;
      } 
    } 
    throw new HookErrorException("error hook wrapper class :" + paramClass.getName());
  }
  
  public static void addHookClass(ClassLoader paramClassLoader, Class<?>... paramVarArgs) throws HookErrorException {
    int i = paramVarArgs.length;
    for (byte b = 0; b < i; b++)
      addHookClass(paramClassLoader, paramVarArgs[b]); 
  }
  
  public static void addHookClass(Class<?>... paramVarArgs) throws HookErrorException { addHookClass(null, paramVarArgs); }
  
  public static void checkSignature(Member paramMember, Method paramMethod, Class[] paramArrayOfClass) throws HookErrorException {
    if (Modifier.isStatic(paramMethod.getModifiers())) {
      if (paramMember instanceof java.lang.reflect.Constructor) {
        if (!paramMethod.getReturnType().equals(void.class))
          throw new HookErrorException("error return type! - " + paramMethod.getName()); 
      } else if (paramMember instanceof Method) {
        Class clazz = ((Method)paramMember).getReturnType();
        if (clazz != paramMethod.getReturnType() && !clazz.isAssignableFrom(clazz))
          throw new HookErrorException("error return type! - " + paramMethod.getName()); 
      } 
      Class[] arrayOfClass2 = paramMethod.getParameterTypes();
      Class[] arrayOfClass1 = arrayOfClass2;
      if (arrayOfClass2 == null)
        arrayOfClass1 = new Class[0]; 
      arrayOfClass2 = paramArrayOfClass;
      if (paramArrayOfClass == null)
        arrayOfClass2 = new Class[0]; 
      if (arrayOfClass2.length == 0 && arrayOfClass1.length == 0)
        return; 
      byte b1 = 0;
      if (!Modifier.isStatic(paramMember.getModifiers())) {
        b1 = 1;
        if (arrayOfClass1.length != 0) {
          if (arrayOfClass1[false] == paramMember.getDeclaringClass() || arrayOfClass1[0].isAssignableFrom(paramMember.getDeclaringClass())) {
            if (arrayOfClass1.length != arrayOfClass2.length + 1)
              throw new HookErrorException("hook method pars must match the origin method! " + paramMethod.getName()); 
          } else {
            throw new HookErrorException("first par must be this! " + paramMethod.getName());
          } 
        } else {
          throw new HookErrorException("first par must be this! " + paramMethod.getName());
        } 
      } else if (arrayOfClass1.length != arrayOfClass2.length) {
        throw new HookErrorException("hook method pars must match the origin method! " + paramMethod.getName());
      } 
      byte b2 = 0;
      while (b2 < arrayOfClass2.length) {
        if (arrayOfClass1[b2 + b1] == arrayOfClass2[b2] || arrayOfClass1[b2 + b1].isAssignableFrom(arrayOfClass2[b2])) {
          b2++;
          continue;
        } 
        throw new HookErrorException("hook method pars must match the origin method! " + paramMethod.getName());
      } 
      return;
    } 
    throw new HookErrorException("hook method must static! - " + paramMethod.getName());
  }
  
  private static Class classNameToClass(String paramString, ClassLoader paramClassLoader) throws ClassNotFoundException {
    byte b;
    switch (paramString.hashCode()) {
      default:
        b = -1;
        break;
      case 109413500:
        if (paramString.equals("short")) {
          b = 7;
          break;
        } 
      case 97526364:
        if (paramString.equals("float")) {
          b = 4;
          break;
        } 
      case 64711720:
        if (paramString.equals("boolean")) {
          b = 0;
          break;
        } 
      case 3327612:
        if (paramString.equals("long")) {
          b = 6;
          break;
        } 
      case 3052374:
        if (paramString.equals("char")) {
          b = 2;
          break;
        } 
      case 3039496:
        if (paramString.equals("byte")) {
          b = 1;
          break;
        } 
      case 104431:
        if (paramString.equals("int")) {
          b = 5;
          break;
        } 
      case -1325958191:
        if (paramString.equals("double")) {
          b = 3;
          break;
        } 
    } 
    switch (b) {
      default:
        if (paramClassLoader == null)
          return Class.forName(paramString); 
        break;
      case 7:
        return short.class;
      case 6:
        return long.class;
      case 5:
        return int.class;
      case 4:
        return float.class;
      case 3:
        return double.class;
      case 2:
        return char.class;
      case 1:
        return byte.class;
      case 0:
        return boolean.class;
    } 
    return Class.forName(paramString, true, paramClassLoader);
  }
  
  private static void fillBackupMethod(ClassLoader paramClassLoader, Class<?> paramClass, Map<Member, HookEntity> paramMap) {
    Class<?> clazz = null;
    try {
      Field[] arrayOfField = paramClass.getDeclaredFields();
    } finally {
      paramClass = null;
    } 
    if (paramMap.isEmpty())
      return; 
    int i = paramClass.length;
    for (byte b = 0; b < i; b++) {
      Class<?> clazz1 = paramClass[b];
      if (Modifier.isStatic(clazz1.getModifiers())) {
        HookMethodBackup hookMethodBackup = (HookMethodBackup)clazz1.getAnnotation(HookMethodBackup.class);
        if (hookMethodBackup != null)
          for (HookEntity hookEntity : paramMap.values()) {
            if (hookEntity.isCtor()) {
              str = "<init>";
            } else {
              str = hookEntity.target.getName();
            } 
            if (TextUtils.equals(str, hookMethodBackup.value()) && samePars(paramClassLoader, clazz1, hookEntity.pars)) {
              clazz1.setAccessible(true);
              if (hookEntity.backup == null) {
                hookEntity.backup = StubMethodsFactory.getStubMethod();
                hookEntity.hookIsStub = true;
                hookEntity.resolveDexCache = false;
              } 
              if (hookEntity.backup == null)
                continue; 
              try {
                if (clazz1.getType() == Method.class) {
                  clazz1.set(null, hookEntity.backup);
                  continue;
                } 
                if (clazz1.getType() == HookEntity.class)
                  clazz1.set(null, hookEntity); 
              } catch (IllegalAccessException str) {
                str.printStackTrace();
              } 
            } 
          }  
      } 
    } 
  }
  
  private static Map<Member, HookEntity> getHookMethods(ClassLoader paramClassLoader, Class paramClass1, Class<?> paramClass2) throws HookErrorException {
    HashMap hashMap = new HashMap();
    Class<?> clazz = null;
    try {
      Method[] arrayOfMethod = paramClass2.getDeclaredMethods();
    } finally {
      paramClass2 = null;
    } 
    throw new HookErrorException("error hook wrapper class :" + paramClass1.getName());
  }
  
  private static int getParsCount(Method paramMethod) {
    int i;
    Class[] arrayOfClass = paramMethod.getParameterTypes();
    if (arrayOfClass == null) {
      i = 0;
    } else {
      i = arrayOfClass.length;
    } 
    return i;
  }
  
  private static Class getRealParType(ClassLoader paramClassLoader, Class paramClass, Annotation[] paramArrayOfAnnotation, boolean paramBoolean) throws Exception {
    if (paramArrayOfAnnotation == null || paramArrayOfAnnotation.length == 0)
      return paramClass; 
    int i = paramArrayOfAnnotation.length;
    for (byte b = 0; b < i; b++) {
      Annotation annotation = paramArrayOfAnnotation[b];
      if (annotation instanceof Param) {
        Param param = (Param)annotation;
        if (TextUtils.isEmpty(param.value()))
          return paramClass; 
        Class clazz = classNameToClass(param.value(), paramClassLoader);
        if (paramBoolean || clazz.equals(paramClass) || paramClass.isAssignableFrom(clazz))
          return clazz; 
        throw new ClassCastException("hook method par cast error!");
      } 
    } 
    return paramClass;
  }
  
  private static Class getTargetHookClass(ClassLoader paramClassLoader, Class<?> paramClass) {
    HookClass hookClass = (HookClass)paramClass.getAnnotation(HookClass.class);
    HookReflectClass hookReflectClass = (HookReflectClass)paramClass.getAnnotation(HookReflectClass.class);
    if (hookClass != null)
      return hookClass.value(); 
    if (hookReflectClass != null) {
      if (paramClassLoader == null)
        try {
          return Class.forName(hookReflectClass.value());
        } catch (ClassNotFoundException paramClassLoader) {
          return null;
        }  
      return Class.forName(hookReflectClass.value(), true, paramClassLoader);
    } 
    return null;
  }
  
  private static boolean hasThisObject(Method paramMethod) {
    Annotation[][] arrayOfAnnotation = paramMethod.getParameterAnnotations();
    return (arrayOfAnnotation == null || arrayOfAnnotation.length == 0) ? false : isThisObject(arrayOfAnnotation[0]);
  }
  
  private static boolean isThisObject(Annotation[] paramArrayOfAnnotation) {
    if (paramArrayOfAnnotation == null || paramArrayOfAnnotation.length == 0)
      return false; 
    int i = paramArrayOfAnnotation.length;
    for (byte b = 0; b < i; b++) {
      if (paramArrayOfAnnotation[b] instanceof com.swift.sandhook.annotation.ThisObject)
        return true; 
    } 
    return false;
  }
  
  private static Class[] parseMethodPars(ClassLoader paramClassLoader, Field paramField) throws HookErrorException {
    MethodParams methodParams = (MethodParams)paramField.getAnnotation(MethodParams.class);
    MethodReflectParams methodReflectParams = (MethodReflectParams)paramField.getAnnotation(MethodReflectParams.class);
    if (methodParams != null)
      return methodParams.value(); 
    if (methodReflectParams != null) {
      if (methodReflectParams.value().length == 0)
        return null; 
      Class[] arrayOfClass = new Class[methodReflectParams.value().length];
      byte b = 0;
      while (b < methodReflectParams.value().length) {
        try {
          arrayOfClass[b] = classNameToClass(methodReflectParams.value()[b], paramClassLoader);
          b++;
        } catch (ClassNotFoundException paramClassLoader) {
          throw new HookErrorException("hook method pars error: " + paramField.getName(), paramClassLoader);
        } 
      } 
      return arrayOfClass;
    } 
    return null;
  }
  
  private static Class[] parseMethodPars(ClassLoader paramClassLoader, Method paramMethod) throws HookErrorException {
    MethodParams methodParams = (MethodParams)paramMethod.getAnnotation(MethodParams.class);
    MethodReflectParams methodReflectParams = (MethodReflectParams)paramMethod.getAnnotation(MethodReflectParams.class);
    if (methodParams != null)
      return methodParams.value(); 
    if (methodReflectParams != null) {
      if (methodReflectParams.value().length == 0)
        return null; 
      Class[] arrayOfClass = new Class[methodReflectParams.value().length];
      byte b = 0;
      while (b < methodReflectParams.value().length) {
        try {
          arrayOfClass[b] = classNameToClass(methodReflectParams.value()[b], paramClassLoader);
          b++;
        } catch (ClassNotFoundException paramClassLoader) {
          throw new HookErrorException("hook method pars error: " + paramMethod.getName(), paramClassLoader);
        } 
      } 
      return arrayOfClass;
    } 
    return (getParsCount(paramMethod) > 0) ? ((getParsCount(paramMethod) == 1) ? (hasThisObject(paramMethod) ? parseMethodParsNew(paramClassLoader, paramMethod) : null) : parseMethodParsNew(paramClassLoader, paramMethod)) : null;
  }
  
  private static Class[] parseMethodParsNew(ClassLoader paramClassLoader, Method paramMethod) throws HookErrorException {
    Class[] arrayOfClass1 = paramMethod.getParameterTypes();
    if (arrayOfClass1 == null || arrayOfClass1.length == 0)
      return null; 
    Annotation[][] arrayOfAnnotation = paramMethod.getParameterAnnotations();
    Class[] arrayOfClass2 = null;
    byte b1 = 0;
    for (byte b2 = 0;; b2++) {
      Annotation[] arrayOfAnnotation1;
      Class clazz;
      if (b2 < arrayOfAnnotation.length) {
        clazz = arrayOfClass1[b2];
        arrayOfAnnotation1 = arrayOfAnnotation[b2];
        if (b2 == 0) {
          if (isThisObject(arrayOfAnnotation1)) {
            arrayOfClass2 = new Class[arrayOfAnnotation.length - 1];
          } else {
            arrayOfClass2 = new Class[arrayOfAnnotation.length];
            try {
              arrayOfClass2[b1] = getRealParType(paramClassLoader, clazz, arrayOfAnnotation1, paramMethod.isAnnotationPresent(com.swift.sandhook.annotation.SkipParamCheck.class));
              b1++;
              continue;
            } catch (Exception paramClassLoader) {
              throw new HookErrorException("hook method <" + paramMethod.getName() + "> parser pars error", paramClassLoader);
            } 
          } 
          continue;
        } 
      } else {
        break;
      } 
      try {
        arrayOfClass2[b1] = getRealParType(paramClassLoader, clazz, arrayOfAnnotation1, paramMethod.isAnnotationPresent(com.swift.sandhook.annotation.SkipParamCheck.class));
        b1++;
        continue;
      } catch (Exception paramClassLoader) {
        throw new HookErrorException("hook method <" + paramMethod.getName() + "> parser pars error", paramClassLoader);
      } 
    } 
    return arrayOfClass2;
  }
  
  private static boolean samePars(ClassLoader paramClassLoader, Field paramField, Class[] paramArrayOfClass) {
    try {
      Class[] arrayOfClass3 = parseMethodPars(paramClassLoader, paramField);
      if (arrayOfClass3 == null && paramField.isAnnotationPresent(com.swift.sandhook.annotation.SkipParamCheck.class))
        return true; 
      Class[] arrayOfClass1 = paramArrayOfClass;
      if (paramArrayOfClass == null)
        arrayOfClass1 = new Class[0]; 
      Class[] arrayOfClass2 = arrayOfClass3;
      if (arrayOfClass3 == null)
        arrayOfClass2 = new Class[0]; 
      if (arrayOfClass1.length != arrayOfClass2.length)
        return false; 
      for (byte b = 0; b < arrayOfClass1.length; b++) {
        Class clazz1 = arrayOfClass1[b];
        Class clazz2 = arrayOfClass2[b];
        if (clazz1 != clazz2)
          return false; 
      } 
      return true;
    } catch (HookErrorException paramClassLoader) {
      return false;
    } 
  }
  
  public static class HookEntity {
    public Method backup;
    
    public boolean backupIsStub = true;
    
    public Method hook;
    
    public boolean hookIsStub = false;
    
    public int hookMode;
    
    public boolean initClass = true;
    
    public Class[] pars;
    
    public boolean resolveDexCache = true;
    
    public Member target;
    
    public HookEntity(Member param1Member) { this.target = param1Member; }
    
    public HookEntity(Member param1Member, Method param1Method1, Method param1Method2) {
      this.target = param1Member;
      this.hook = param1Method1;
      this.backup = param1Method2;
    }
    
    public HookEntity(Member param1Member, Method param1Method1, Method param1Method2, boolean param1Boolean) {
      this.target = param1Member;
      this.hook = param1Method1;
      this.backup = param1Method2;
      this.resolveDexCache = param1Boolean;
    }
    
    public Object callOrigin(Object param1Object, Object... param1VarArgs) throws Throwable { return SandHook.callOriginMethod(this.backupIsStub, this.target, this.backup, param1Object, param1VarArgs); }
    
    public boolean isCtor() { return this.target instanceof java.lang.reflect.Constructor; }
  }
}


/* Location:              D:\Android\APK Tool\APK.Tool.GUI.v3.2.2.0\classes_merge_1.jar!\com\swift\sandhook\wrapper\HookWrapper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */
package arm;

public class Loader {
  static  {
    System.loadLibrary("arm");
  }
  
  public static native void registerNativesForClass(int paramInt);
}


/* Location:              D:\Android\APK Tool\APK.Tool.GUI.v3.2.2.0\classes_merge_1.jar!\arm\Loader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */
package com.swift.sandhook.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD})
public @interface MethodParams {
  Class<?>[] value();
}


/* Location:              D:\Android\APK Tool\APK.Tool.GUI.v3.2.2.0\classes_merge_1.jar!\com\swift\sandhook\annotation\MethodParams.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */
package com.swift.sandhook.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD})
public @interface MethodReflectParams {
  public static final String BOOLEAN = "boolean";
  
  public static final String BYTE = "byte";
  
  public static final String CHAR = "char";
  
  public static final String DOUBLE = "double";
  
  public static final String FLOAT = "float";
  
  public static final String INT = "int";
  
  public static final String LONG = "long";
  
  public static final String SHORT = "short";
  
  String[] value();
}


/* Location:              D:\Android\APK Tool\APK.Tool.GUI.v3.2.2.0\classes_merge_1.jar!\com\swift\sandhook\annotation\MethodReflectParams.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */
package com.swift.sandhook.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER})
public @interface Param {
  String value() default "";
}


/* Location:              D:\Android\APK Tool\APK.Tool.GUI.v3.2.2.0\classes_merge_1.jar!\com\swift\sandhook\annotation\Param.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */
package com.swift.sandhook.utils;

import com.swift.sandhook.SandHook;

public class ParamWrapper {
  private static boolean is64Bit = SandHook.is64Bit();
  
  public static Object addressToObject(Class paramClass, long paramLong) { return is64Bit ? addressToObject64(paramClass, paramLong) : addressToObject32(paramClass, (int)paramLong); }
  
  public static Object addressToObject32(Class paramClass, int paramInt) {
    if (paramClass == null)
      return null; 
    if (paramClass.isPrimitive()) {
      if (paramClass == int.class)
        return Integer.valueOf(paramInt); 
      if (paramClass == short.class)
        return Short.valueOf((short)paramInt); 
      if (paramClass == byte.class)
        return Byte.valueOf((byte)paramInt); 
      if (paramClass == char.class)
        return Character.valueOf((char)paramInt); 
      if (paramClass == boolean.class) {
        boolean bool;
        if (paramInt != 0) {
          bool = true;
        } else {
          bool = false;
        } 
        return Boolean.valueOf(bool);
      } 
      throw new RuntimeException("unknown type: " + paramClass.toString());
    } 
    return SandHook.getObject(paramInt);
  }
  
  public static Object addressToObject64(Class paramClass, long paramLong) {
    if (paramClass == null)
      return null; 
    if (paramClass.isPrimitive()) {
      if (paramClass == int.class)
        return Integer.valueOf((int)paramLong); 
      if (paramClass == long.class)
        return Long.valueOf(paramLong); 
      if (paramClass == short.class)
        return Short.valueOf((short)(int)paramLong); 
      if (paramClass == byte.class)
        return Byte.valueOf((byte)(int)paramLong); 
      if (paramClass == char.class)
        return Character.valueOf((char)(int)paramLong); 
      if (paramClass == boolean.class) {
        boolean bool;
        if (paramLong != 0L) {
          bool = true;
        } else {
          bool = false;
        } 
        return Boolean.valueOf(bool);
      } 
      throw new RuntimeException("unknown type: " + paramClass.toString());
    } 
    return SandHook.getObject(paramLong);
  }
  
  public static long objectToAddress(Class paramClass, Object paramObject) { return is64Bit ? objectToAddress64(paramClass, paramObject) : objectToAddress32(paramClass, paramObject); }
  
  public static int objectToAddress32(Class paramClass, Object paramObject) {
    if (paramObject == null)
      return 0; 
    if (paramClass.isPrimitive()) {
      if (paramClass == int.class)
        return ((Integer)paramObject).intValue(); 
      if (paramClass == short.class)
        return ((Short)paramObject).shortValue(); 
      if (paramClass == byte.class)
        return ((Byte)paramObject).byteValue(); 
      if (paramClass == char.class)
        return ((Character)paramObject).charValue(); 
      if (paramClass == boolean.class)
        return Boolean.TRUE.equals(paramObject); 
      throw new RuntimeException("unknown type: " + paramClass.toString());
    } 
    return (int)SandHook.getObjectAddress(paramObject);
  }
  
  public static long objectToAddress64(Class paramClass, Object paramObject) {
    long l = 0L;
    if (paramObject == null)
      return 0L; 
    if (paramClass.isPrimitive()) {
      if (paramClass == int.class)
        return ((Integer)paramObject).intValue(); 
      if (paramClass == long.class)
        return ((Long)paramObject).longValue(); 
      if (paramClass == short.class)
        return ((Short)paramObject).shortValue(); 
      if (paramClass == byte.class)
        return ((Byte)paramObject).byteValue(); 
      if (paramClass == char.class)
        return ((Character)paramObject).charValue(); 
      if (paramClass == boolean.class) {
        if (Boolean.TRUE.equals(paramObject))
          l = 1L; 
        return l;
      } 
      throw new RuntimeException("unknown type: " + paramClass.toString());
    } 
    return SandHook.getObjectAddress(paramObject);
  }
  
  public static boolean support(Class paramClass) {
    boolean bool1 = is64Bit;
    boolean bool = true;
    boolean bool2 = true;
    if (bool1) {
      if (paramClass == float.class || paramClass == double.class)
        bool2 = false; 
      return bool2;
    } 
    if (paramClass != float.class && paramClass != double.class && paramClass != long.class) {
      bool2 = bool;
    } else {
      bool2 = false;
    } 
    return bool2;
  }
}


/* Location:              D:\Android\APK Tool\APK.Tool.GUI.v3.2.2.0\classes_merge_1.jar!\com\swift\sandhoo\\utils\ParamWrapper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */
package com.swift.sandhook;

import com.swift.sandhook.wrapper.HookErrorException;
import com.swift.sandhook.wrapper.HookWrapper;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

public class PendingHookHandler {
  private static boolean canUsePendingHook;
  
  private static Map<Class, Vector<HookWrapper.HookEntity>> pendingHooks = new ConcurrentHashMap();
  
  static  {
    if (SandHookConfig.delayHook)
      canUsePendingHook = SandHook.initForPendingHook(); 
  }
  
  public static void addPendingHook(HookWrapper.HookEntity paramHookEntity) { // Byte code:
    //   0: ldc com/swift/sandhook/PendingHookHandler
    //   2: monitorenter
    //   3: getstatic com/swift/sandhook/PendingHookHandler.pendingHooks : Ljava/util/Map;
    //   6: aload_0
    //   7: getfield target : Ljava/lang/reflect/Member;
    //   10: invokeinterface getDeclaringClass : ()Ljava/lang/Class;
    //   15: invokeinterface get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   20: checkcast java/util/Vector
    //   23: astore_1
    //   24: aload_1
    //   25: astore_2
    //   26: aload_1
    //   27: ifnonnull -> 57
    //   30: new java/util/Vector
    //   33: astore_2
    //   34: aload_2
    //   35: invokespecial <init> : ()V
    //   38: getstatic com/swift/sandhook/PendingHookHandler.pendingHooks : Ljava/util/Map;
    //   41: aload_0
    //   42: getfield target : Ljava/lang/reflect/Member;
    //   45: invokeinterface getDeclaringClass : ()Ljava/lang/Class;
    //   50: aload_2
    //   51: invokeinterface put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   56: pop
    //   57: aload_2
    //   58: aload_0
    //   59: invokevirtual add : (Ljava/lang/Object;)Z
    //   62: pop
    //   63: ldc com/swift/sandhook/PendingHookHandler
    //   65: monitorexit
    //   66: return
    //   67: astore_0
    //   68: ldc com/swift/sandhook/PendingHookHandler
    //   70: monitorexit
    //   71: aload_0
    //   72: athrow
    // Exception table:
    //   from	to	target	type
    //   3	24	67	finally
    //   30	38	67	finally
    //   38	57	67	finally
    //   57	63	67	finally }
  
  public static boolean canWork() {
    boolean bool;
    if (canUsePendingHook && SandHook.canGetObject() && !SandHookConfig.DEBUG) {
      bool = true;
    } else {
      bool = false;
    } 
    return bool;
  }
  
  public static void onClassInit(long paramLong) {
    if (paramLong == 0L)
      return; 
    Class clazz = (Class)SandHook.getObject(paramLong);
    if (clazz == null)
      return; 
    Vector vector = (Vector)pendingHooks.get(clazz);
    if (vector == null)
      return; 
    for (HookWrapper.HookEntity hookEntity : vector) {
      HookLog.w("do pending hook for method: " + hookEntity.target.toString());
      try {
        hookEntity.initClass = false;
        SandHook.hook(hookEntity);
      } catch (HookErrorException hookEntity) {
        HookLog.e("Pending Hook Error!", hookEntity);
      } 
    } 
    pendingHooks.remove(clazz);
  }
}


/* Location:              D:\Android\APK Tool\APK.Tool.GUI.v3.2.2.0\classes_merge_1.jar!\com\swift\sandhook\PendingHookHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */
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
package com.swift.sandhook;

import com.swift.sandhook.utils.Unsafe;
import com.swift.sandhook.wrapper.HookErrorException;
import com.swift.sandhook.wrapper.HookWrapper;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SandHook {
  public static Class artMethodClass;
  
  static Map<Method, HookWrapper.HookEntity> globalBackupMap;
  
  static Map<Member, HookWrapper.HookEntity> globalHookEntityMap = new ConcurrentHashMap();
  
  private static HookModeCallBack hookModeCallBack;
  
  private static HookResultCallBack hookResultCallBack;
  
  public static Field nativePeerField;
  
  public static int testAccessFlag;
  
  public static Object testOffsetArtMethod1;
  
  public static Object testOffsetArtMethod2;
  
  public static Method testOffsetMethod1;
  
  public static Method testOffsetMethod2;
  
  static  {
    globalBackupMap = new ConcurrentHashMap();
    SandHookConfig.libLoader.loadLib();
    init();
  }
  
  public static native void MakeInitializedClassVisibilyInitialized(long paramLong);
  
  public static void addHookClass(ClassLoader paramClassLoader, Class... paramVarArgs) throws HookErrorException { HookWrapper.addHookClass(paramClassLoader, paramVarArgs); }
  
  public static void addHookClass(Class... paramVarArgs) throws HookErrorException { HookWrapper.addHookClass(paramVarArgs); }
  
  public static final Object callOriginByBackup(Method paramMethod, Object paramObject, Object... paramVarArgs) throws Throwable {
    HookWrapper.HookEntity hookEntity = (HookWrapper.HookEntity)globalBackupMap.get(paramMethod);
    return (hookEntity == null) ? null : callOriginMethod(hookEntity.backupIsStub, hookEntity.target, paramMethod, paramObject, paramVarArgs);
  }
  
  public static final Object callOriginMethod(Member paramMember, Object paramObject, Object... paramVarArgs) throws Throwable {
    HookWrapper.HookEntity hookEntity = (HookWrapper.HookEntity)globalHookEntityMap.get(paramMember);
    return (hookEntity == null || hookEntity.backup == null) ? null : callOriginMethod(hookEntity.backupIsStub, paramMember, hookEntity.backup, paramObject, paramVarArgs);
  }
  
  public static final Object callOriginMethod(Member paramMember, Method paramMethod, Object paramObject, Object[] paramArrayOfObject) throws Throwable { return callOriginMethod(true, paramMember, paramMethod, paramObject, paramArrayOfObject); }
  
  public static final Object callOriginMethod(boolean paramBoolean, Member paramMember, Method paramMethod, Object paramObject, Object[] paramArrayOfObject) throws Throwable {
    if (!paramBoolean && SandHookConfig.SDK_INT >= 24) {
      paramMember.getDeclaringClass();
      ensureDeclareClass(paramMember, paramMethod);
    } 
    if (Modifier.isStatic(paramMember.getModifiers()))
      try {
        return paramMethod.invoke(null, paramArrayOfObject);
      } catch (InvocationTargetException paramMember) {
        if (paramMember.getCause() != null)
          throw paramMember.getCause(); 
        throw paramMember;
      }  
    try {
      return paramMethod.invoke(paramObject, paramArrayOfObject);
    } catch (InvocationTargetException paramMember) {
      if (paramMember.getCause() != null)
        throw paramMember.getCause(); 
      throw paramMember;
    } 
  }
  
  public static native boolean canGetObject();
  
  public static boolean canGetObjectAddress() { return Unsafe.support(); }
  
  public static native boolean compileMethod(Member paramMember);
  
  public static native boolean deCompileMethod(Member paramMember, boolean paramBoolean);
  
  public static native boolean disableDex2oatInline(boolean paramBoolean);
  
  public static native boolean disableVMInline();
  
  public static final void ensureBackupMethod(Method paramMethod) {
    if (SandHookConfig.SDK_INT < 24)
      return; 
    HookWrapper.HookEntity hookEntity = (HookWrapper.HookEntity)globalBackupMap.get(paramMethod);
    if (hookEntity != null)
      ensureDeclareClass(hookEntity.target, paramMethod); 
  }
  
  public static native void ensureDeclareClass(Member paramMember, Method paramMethod);
  
  public static native void ensureMethodCached(Method paramMethod1, Method paramMethod2);
  
  public static long getArtMethod(Member paramMember) { return SandHookMethodResolver.getArtMethod(paramMember); }
  
  private static Object[] getFakeArgs(Method paramMethod) {
    Class[] arrayOfClass = paramMethod.getParameterTypes();
    return (arrayOfClass == null || arrayOfClass.length == 0) ? new Object[] { new Object() } : null;
  }
  
  public static Field getField(Class paramClass, String paramString) throws NoSuchFieldException {
    while (paramClass != null && paramClass != Object.class) {
      try {
        Field field = paramClass.getDeclaredField(paramString);
        field.setAccessible(true);
        return field;
      } catch (Exception exception) {
        paramClass = paramClass.getSuperclass();
      } 
    } 
    throw new NoSuchFieldException(paramString);
  }
  
  public static Object getJavaMethod(String paramString1, String paramString2) {
    if (paramString1 == null)
      return null; 
    try {
      clazz = Class.forName(paramString1);
      try {
        return clazz.getDeclaredMethod(paramString2, new Class[0]);
      } catch (NoSuchMethodException clazz) {
        return null;
      } 
    } catch (ClassNotFoundException paramString1) {
      return null;
    } 
  }
  
  public static Object getObject(long paramLong) { return (paramLong == 0L) ? null : getObjectNative(getThreadId(), paramLong); }
  
  public static long getObjectAddress(Object paramObject) { return Unsafe.getObjectAddress(paramObject); }
  
  public static native Object getObjectNative(long paramLong1, long paramLong2);
  
  public static long getThreadId() {
    field = nativePeerField;
    if (field == null)
      return 0L; 
    try {
      return (field.getType() == int.class) ? nativePeerField.getInt(Thread.currentThread()) : nativePeerField.getLong(Thread.currentThread());
    } catch (IllegalAccessException field) {
      return 0L;
    } 
  }
  
  public static boolean hasJavaArtMethod() {
    if (SandHookConfig.SDK_INT >= 26)
      return false; 
    if (artMethodClass != null)
      return true; 
    try {
      ClassLoader classLoader = SandHookConfig.initClassLoader;
      if (classLoader == null) {
        artMethodClass = Class.forName("java.lang.reflect.ArtMethod");
      } else {
        artMethodClass = Class.forName("java.lang.reflect.ArtMethod", true, SandHookConfig.initClassLoader);
      } 
      return true;
    } catch (ClassNotFoundException classNotFoundException) {
      return false;
    } 
  }
  
  public static void hook(HookWrapper.HookEntity paramHookEntity) throws HookErrorException { // Byte code:
    //   0: ldc com/swift/sandhook/SandHook
    //   2: monitorenter
    //   3: aload_0
    //   4: ifnull -> 540
    //   7: aload_0
    //   8: getfield target : Ljava/lang/reflect/Member;
    //   11: astore_1
    //   12: aload_0
    //   13: getfield hook : Ljava/lang/reflect/Method;
    //   16: astore_2
    //   17: aload_0
    //   18: getfield backup : Ljava/lang/reflect/Method;
    //   21: astore_3
    //   22: aload_1
    //   23: ifnull -> 523
    //   26: aload_2
    //   27: ifnull -> 523
    //   30: getstatic com/swift/sandhook/SandHook.globalHookEntityMap : Ljava/util/Map;
    //   33: aload_0
    //   34: getfield target : Ljava/lang/reflect/Member;
    //   37: invokeinterface containsKey : (Ljava/lang/Object;)Z
    //   42: ifne -> 479
    //   45: aload_1
    //   46: invokestatic canNotHook : (Ljava/lang/reflect/Member;)Z
    //   49: ifne -> 435
    //   52: getstatic com/swift/sandhook/SandHookConfig.delayHook : Z
    //   55: ifeq -> 82
    //   58: invokestatic canWork : ()Z
    //   61: ifeq -> 82
    //   64: aload_0
    //   65: getfield target : Ljava/lang/reflect/Member;
    //   68: invokestatic isStaticAndNoInited : (Ljava/lang/reflect/Member;)Z
    //   71: ifeq -> 82
    //   74: aload_0
    //   75: invokestatic addPendingHook : (Lcom/swift/sandhook/wrapper/HookWrapper$HookEntity;)V
    //   78: ldc com/swift/sandhook/SandHook
    //   80: monitorexit
    //   81: return
    //   82: aload_0
    //   83: getfield initClass : Z
    //   86: ifeq -> 100
    //   89: aload_1
    //   90: invokestatic resolveStaticMethod : (Ljava/lang/reflect/Member;)Z
    //   93: pop
    //   94: invokestatic getThreadId : ()J
    //   97: invokestatic MakeInitializedClassVisibilyInitialized : (J)V
    //   100: aload_3
    //   101: invokestatic resolveStaticMethod : (Ljava/lang/reflect/Member;)Z
    //   104: pop
    //   105: aload_3
    //   106: ifnull -> 121
    //   109: aload_0
    //   110: getfield resolveDexCache : Z
    //   113: ifeq -> 121
    //   116: aload_2
    //   117: aload_3
    //   118: invokestatic resolveMethod : (Ljava/lang/reflect/Method;Ljava/lang/reflect/Method;)V
    //   121: aload_1
    //   122: instanceof java/lang/reflect/Method
    //   125: ifeq -> 136
    //   128: aload_1
    //   129: checkcast java/lang/reflect/Method
    //   132: iconst_1
    //   133: invokevirtual setAccessible : (Z)V
    //   136: iconst_0
    //   137: istore #4
    //   139: getstatic com/swift/sandhook/SandHook.hookModeCallBack : Lcom/swift/sandhook/SandHook$HookModeCallBack;
    //   142: astore #5
    //   144: aload #5
    //   146: ifnull -> 159
    //   149: aload #5
    //   151: aload_1
    //   152: invokeinterface hookMode : (Ljava/lang/reflect/Member;)I
    //   157: istore #4
    //   159: getstatic com/swift/sandhook/SandHook.globalHookEntityMap : Ljava/util/Map;
    //   162: aload_0
    //   163: getfield target : Ljava/lang/reflect/Member;
    //   166: aload_0
    //   167: invokeinterface put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   172: pop
    //   173: iconst_0
    //   174: istore #6
    //   176: iload #4
    //   178: ifeq -> 194
    //   181: aload_1
    //   182: aload_2
    //   183: aload_3
    //   184: iload #4
    //   186: invokestatic hookMethod : (Ljava/lang/reflect/Member;Ljava/lang/reflect/Method;Ljava/lang/reflect/Method;I)I
    //   189: istore #4
    //   191: goto -> 236
    //   194: aload_2
    //   195: ldc_w com/swift/sandhook/annotation/HookMode
    //   198: invokevirtual getAnnotation : (Ljava/lang/Class;)Ljava/lang/annotation/Annotation;
    //   201: checkcast com/swift/sandhook/annotation/HookMode
    //   204: astore #5
    //   206: aload #5
    //   208: ifnonnull -> 217
    //   211: iconst_0
    //   212: istore #4
    //   214: goto -> 226
    //   217: aload #5
    //   219: invokeinterface value : ()I
    //   224: istore #4
    //   226: aload_1
    //   227: aload_2
    //   228: aload_3
    //   229: iload #4
    //   231: invokestatic hookMethod : (Ljava/lang/reflect/Member;Ljava/lang/reflect/Method;Ljava/lang/reflect/Method;I)I
    //   234: istore #4
    //   236: iload #4
    //   238: ifle -> 250
    //   241: aload_3
    //   242: ifnull -> 250
    //   245: aload_3
    //   246: iconst_1
    //   247: invokevirtual setAccessible : (Z)V
    //   250: aload_0
    //   251: iload #4
    //   253: putfield hookMode : I
    //   256: getstatic com/swift/sandhook/SandHook.hookResultCallBack : Lcom/swift/sandhook/SandHook$HookResultCallBack;
    //   259: astore_1
    //   260: aload_1
    //   261: ifnull -> 281
    //   264: iload #4
    //   266: ifle -> 272
    //   269: iconst_1
    //   270: istore #6
    //   272: aload_1
    //   273: iload #6
    //   275: aload_0
    //   276: invokeinterface hookResult : (ZLcom/swift/sandhook/wrapper/HookWrapper$HookEntity;)V
    //   281: iload #4
    //   283: iflt -> 378
    //   286: aload_0
    //   287: getfield backup : Ljava/lang/reflect/Method;
    //   290: ifnull -> 307
    //   293: getstatic com/swift/sandhook/SandHook.globalBackupMap : Ljava/util/Map;
    //   296: aload_0
    //   297: getfield backup : Ljava/lang/reflect/Method;
    //   300: aload_0
    //   301: invokeinterface put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   306: pop
    //   307: new java/lang/StringBuilder
    //   310: astore_1
    //   311: aload_1
    //   312: invokespecial <init> : ()V
    //   315: aload_1
    //   316: ldc_w 'method <'
    //   319: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   322: aload_0
    //   323: getfield target : Ljava/lang/reflect/Member;
    //   326: invokevirtual toString : ()Ljava/lang/String;
    //   329: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   332: ldc_w '> hook <'
    //   335: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   338: astore_1
    //   339: iload #4
    //   341: iconst_1
    //   342: if_icmpne -> 352
    //   345: ldc_w 'inline'
    //   348: astore_0
    //   349: goto -> 356
    //   352: ldc_w 'replacement'
    //   355: astore_0
    //   356: aload_1
    //   357: aload_0
    //   358: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   361: ldc_w '> success!'
    //   364: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   367: invokevirtual toString : ()Ljava/lang/String;
    //   370: invokestatic d : (Ljava/lang/String;)I
    //   373: pop
    //   374: ldc com/swift/sandhook/SandHook
    //   376: monitorexit
    //   377: return
    //   378: getstatic com/swift/sandhook/SandHook.globalHookEntityMap : Ljava/util/Map;
    //   381: aload_0
    //   382: getfield target : Ljava/lang/reflect/Member;
    //   385: invokeinterface remove : (Ljava/lang/Object;)Ljava/lang/Object;
    //   390: pop
    //   391: new com/swift/sandhook/wrapper/HookErrorException
    //   394: astore_1
    //   395: new java/lang/StringBuilder
    //   398: astore_2
    //   399: aload_2
    //   400: invokespecial <init> : ()V
    //   403: aload_1
    //   404: aload_2
    //   405: ldc_w 'hook method <'
    //   408: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   411: aload_0
    //   412: getfield target : Ljava/lang/reflect/Member;
    //   415: invokevirtual toString : ()Ljava/lang/String;
    //   418: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   421: ldc_w '> error in native!'
    //   424: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   427: invokevirtual toString : ()Ljava/lang/String;
    //   430: invokespecial <init> : (Ljava/lang/String;)V
    //   433: aload_1
    //   434: athrow
    //   435: new com/swift/sandhook/wrapper/HookErrorException
    //   438: astore_2
    //   439: new java/lang/StringBuilder
    //   442: astore_1
    //   443: aload_1
    //   444: invokespecial <init> : ()V
    //   447: aload_2
    //   448: aload_1
    //   449: ldc_w 'method <'
    //   452: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   455: aload_0
    //   456: getfield target : Ljava/lang/reflect/Member;
    //   459: invokevirtual toString : ()Ljava/lang/String;
    //   462: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   465: ldc_w '> can not hook, because of in blacklist!'
    //   468: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   471: invokevirtual toString : ()Ljava/lang/String;
    //   474: invokespecial <init> : (Ljava/lang/String;)V
    //   477: aload_2
    //   478: athrow
    //   479: new com/swift/sandhook/wrapper/HookErrorException
    //   482: astore_2
    //   483: new java/lang/StringBuilder
    //   486: astore_1
    //   487: aload_1
    //   488: invokespecial <init> : ()V
    //   491: aload_2
    //   492: aload_1
    //   493: ldc_w 'method <'
    //   496: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   499: aload_0
    //   500: getfield target : Ljava/lang/reflect/Member;
    //   503: invokevirtual toString : ()Ljava/lang/String;
    //   506: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   509: ldc_w '> has been hooked!'
    //   512: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   515: invokevirtual toString : ()Ljava/lang/String;
    //   518: invokespecial <init> : (Ljava/lang/String;)V
    //   521: aload_2
    //   522: athrow
    //   523: new com/swift/sandhook/wrapper/HookErrorException
    //   526: astore_0
    //   527: aload_0
    //   528: ldc_w 'null input'
    //   531: invokespecial <init> : (Ljava/lang/String;)V
    //   534: aload_0
    //   535: athrow
    //   536: astore_0
    //   537: goto -> 553
    //   540: new com/swift/sandhook/wrapper/HookErrorException
    //   543: astore_0
    //   544: aload_0
    //   545: ldc_w 'null hook entity'
    //   548: invokespecial <init> : (Ljava/lang/String;)V
    //   551: aload_0
    //   552: athrow
    //   553: ldc com/swift/sandhook/SandHook
    //   555: monitorexit
    //   556: aload_0
    //   557: athrow
    // Exception table:
    //   from	to	target	type
    //   7	22	536	finally
    //   30	78	536	finally
    //   82	100	536	finally
    //   100	105	536	finally
    //   109	121	536	finally
    //   121	136	536	finally
    //   139	144	536	finally
    //   149	159	536	finally
    //   159	173	536	finally
    //   181	191	536	finally
    //   194	206	536	finally
    //   217	226	536	finally
    //   226	236	536	finally
    //   245	250	536	finally
    //   250	260	536	finally
    //   272	281	536	finally
    //   286	307	536	finally
    //   307	339	536	finally
    //   356	374	536	finally
    //   378	435	536	finally
    //   435	479	536	finally
    //   479	523	536	finally
    //   523	536	536	finally
    //   540	553	536	finally }
  
  private static native int hookMethod(Member paramMember, Method paramMethod1, Method paramMethod2, int paramInt);
  
  private static boolean init() {
    initTestOffset();
    initThreadPeer();
    SandHookMethodResolver.init();
    return initNative(SandHookConfig.SDK_INT, SandHookConfig.DEBUG);
  }
  
  public static native boolean initForPendingHook();
  
  private static native boolean initNative(int paramInt, boolean paramBoolean);
  
  private static void initTestAccessFlag() {
    if (hasJavaArtMethod()) {
      try {
        loadArtMethod();
        testAccessFlag = ((Integer)getField(artMethodClass, "accessFlags").get(testOffsetArtMethod1)).intValue();
      } catch (Exception exception) {}
    } else {
      try {
        testAccessFlag = ((Integer)getField(Method.class, "accessFlags").get(testOffsetMethod1)).intValue();
      } catch (Exception exception) {}
    } 
  }
  
  private static void initTestOffset() {
    ArtMethodSizeTest.method1();
    ArtMethodSizeTest.method2();
    try {
      testOffsetMethod1 = ArtMethodSizeTest.class.getDeclaredMethod("method1", new Class[0]);
      testOffsetMethod2 = ArtMethodSizeTest.class.getDeclaredMethod("method2", new Class[0]);
      initTestAccessFlag();
      return;
    } catch (NoSuchMethodException noSuchMethodException) {
      throw new RuntimeException("SandHook init error", noSuchMethodException);
    } 
  }
  
  private static void initThreadPeer() {
    try {
      nativePeerField = getField(Thread.class, "nativePeer");
    } catch (NoSuchFieldException noSuchFieldException) {}
  }
  
  public static native boolean is64Bit();
  
  private static void loadArtMethod() {
    try {
      Field field = getField(Method.class, "artMethod");
      testOffsetArtMethod1 = field.get(testOffsetMethod1);
      testOffsetArtMethod2 = field.get(testOffsetMethod2);
    } catch (IllegalAccessException illegalAccessException) {
      illegalAccessException.printStackTrace();
    } catch (NoSuchFieldException noSuchFieldException) {
      noSuchFieldException.printStackTrace();
    } 
  }
  
  public static boolean resolveStaticMethod(Member paramMember) {
    if (paramMember == null)
      return true; 
  }
  
  public static native void setHookMode(int paramInt);
  
  public static void setHookModeCallBack(HookModeCallBack paramHookModeCallBack) { hookModeCallBack = paramHookModeCallBack; }
  
  public static void setHookResultCallBack(HookResultCallBack paramHookResultCallBack) { hookResultCallBack = paramHookResultCallBack; }
  
  public static native void setInlineSafeCheck(boolean paramBoolean);
  
  public static native boolean setNativeEntry(Member paramMember1, Member paramMember2, long paramLong);
  
  public static native void skipAllSafeCheck(boolean paramBoolean);
  
  public static boolean tryDisableProfile(String paramString) {
    if (SandHookConfig.SDK_INT < 24)
      return false; 
    try {
      file = new File();
      StringBuilder stringBuilder = new StringBuilder();
      this();
      this(stringBuilder.append("/data/misc/profiles/cur/").append(SandHookConfig.curUser).append("/").append(paramString).append("/primary.prof").toString());
      boolean bool = file.getParentFile().exists();
      if (!bool)
        return false; 
      try {
        file.delete();
        file.createNewFile();
      } finally {}
      return true;
    } finally {
      paramString = null;
    } 
  }
  
  @FunctionalInterface
  public static interface HookModeCallBack {
    int hookMode(Member param1Member);
  }
  
  @FunctionalInterface
  public static interface HookResultCallBack {
    void hookResult(boolean param1Boolean, HookWrapper.HookEntity param1HookEntity);
  }
}


/* Location:              D:\Android\APK Tool\APK.Tool.GUI.v3.2.2.0\classes_merge_1.jar!\com\swift\sandhook\SandHook.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */
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
package com.swift.sandhook;

import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;

public class SandHookMethodResolver {
  public static Field artMethodField;
  
  public static boolean canResolvedInJava = false;
  
  public static Field dexCacheField;
  
  public static int dexMethodIndex;
  
  public static Field dexMethodIndexField;
  
  public static long entryPointFromCompiledCode;
  
  public static long entryPointFromInterpreter;
  
  public static Field fieldEntryPointFromCompiledCode;
  
  public static Field fieldEntryPointFromInterpreter;
  
  public static boolean isArtMethod = false;
  
  public static long resolvedMethodsAddress = 0L;
  
  public static Field resolvedMethodsField;
  
  public static Object testArtMethod;
  
  public static Method testMethod;
  
  static  {
    dexMethodIndex = 0;
    entryPointFromCompiledCode = 0L;
    entryPointFromInterpreter = 0L;
  }
  
  private static void checkSupport() {
    try {
      Field field = SandHook.getField(Method.class, "artMethod");
      artMethodField = field;
      testArtMethod = field.get(testMethod);
      if (SandHook.hasJavaArtMethod() && testArtMethod.getClass() == SandHook.artMethodClass) {
        checkSupportForArtMethod();
        isArtMethod = true;
      } else if (testArtMethod instanceof Long) {
        checkSupportForArtMethodId();
        isArtMethod = false;
      } else {
        canResolvedInJava = false;
      } 
    } catch (Exception exception) {}
  }
  
  private static void checkSupportForArtMethod() { // Byte code:
    //   0: getstatic com/swift/sandhook/SandHook.artMethodClass : Ljava/lang/Class;
    //   3: ldc 'dexMethodIndex'
    //   5: invokestatic getField : (Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/reflect/Field;
    //   8: putstatic com/swift/sandhook/SandHookMethodResolver.dexMethodIndexField : Ljava/lang/reflect/Field;
    //   11: goto -> 26
    //   14: astore_0
    //   15: getstatic com/swift/sandhook/SandHook.artMethodClass : Ljava/lang/Class;
    //   18: ldc 'methodDexIndex'
    //   20: invokestatic getField : (Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/reflect/Field;
    //   23: putstatic com/swift/sandhook/SandHookMethodResolver.dexMethodIndexField : Ljava/lang/reflect/Field;
    //   26: ldc java/lang/Class
    //   28: ldc 'dexCache'
    //   30: invokestatic getField : (Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/reflect/Field;
    //   33: astore_0
    //   34: aload_0
    //   35: putstatic com/swift/sandhook/SandHookMethodResolver.dexCacheField : Ljava/lang/reflect/Field;
    //   38: aload_0
    //   39: getstatic com/swift/sandhook/SandHookMethodResolver.testMethod : Ljava/lang/reflect/Method;
    //   42: invokevirtual getDeclaringClass : ()Ljava/lang/Class;
    //   45: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   48: astore_0
    //   49: aload_0
    //   50: invokevirtual getClass : ()Ljava/lang/Class;
    //   53: ldc 'resolvedMethods'
    //   55: invokestatic getField : (Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/reflect/Field;
    //   58: astore_1
    //   59: aload_1
    //   60: putstatic com/swift/sandhook/SandHookMethodResolver.resolvedMethodsField : Ljava/lang/reflect/Field;
    //   63: aload_1
    //   64: aload_0
    //   65: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   68: instanceof [Ljava/lang/Object;
    //   71: ifeq -> 78
    //   74: iconst_1
    //   75: putstatic com/swift/sandhook/SandHookMethodResolver.canResolvedInJava : Z
    //   78: getstatic com/swift/sandhook/SandHookMethodResolver.dexMethodIndexField : Ljava/lang/reflect/Field;
    //   81: getstatic com/swift/sandhook/SandHookMethodResolver.testArtMethod : Ljava/lang/Object;
    //   84: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   87: checkcast java/lang/Integer
    //   90: invokevirtual intValue : ()I
    //   93: putstatic com/swift/sandhook/SandHookMethodResolver.dexMethodIndex : I
    //   96: goto -> 100
    //   99: astore_0
    //   100: getstatic com/swift/sandhook/SandHook.artMethodClass : Ljava/lang/Class;
    //   103: ldc 'entryPointFromQuickCompiledCode'
    //   105: invokestatic getField : (Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/reflect/Field;
    //   108: putstatic com/swift/sandhook/SandHookMethodResolver.fieldEntryPointFromCompiledCode : Ljava/lang/reflect/Field;
    //   111: goto -> 126
    //   114: astore_0
    //   115: getstatic com/swift/sandhook/SandHook.artMethodClass : Ljava/lang/Class;
    //   118: ldc 'entryPointFromCompiledCode'
    //   120: invokestatic getField : (Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/reflect/Field;
    //   123: putstatic com/swift/sandhook/SandHookMethodResolver.fieldEntryPointFromCompiledCode : Ljava/lang/reflect/Field;
    //   126: getstatic com/swift/sandhook/SandHookMethodResolver.fieldEntryPointFromCompiledCode : Ljava/lang/reflect/Field;
    //   129: invokevirtual getType : ()Ljava/lang/Class;
    //   132: getstatic java/lang/Integer.TYPE : Ljava/lang/Class;
    //   135: if_acmpne -> 154
    //   138: getstatic com/swift/sandhook/SandHookMethodResolver.fieldEntryPointFromCompiledCode : Ljava/lang/reflect/Field;
    //   141: getstatic com/swift/sandhook/SandHookMethodResolver.testArtMethod : Ljava/lang/Object;
    //   144: invokevirtual getInt : (Ljava/lang/Object;)I
    //   147: i2l
    //   148: putstatic com/swift/sandhook/SandHookMethodResolver.entryPointFromCompiledCode : J
    //   151: goto -> 178
    //   154: getstatic com/swift/sandhook/SandHookMethodResolver.fieldEntryPointFromCompiledCode : Ljava/lang/reflect/Field;
    //   157: invokevirtual getType : ()Ljava/lang/Class;
    //   160: getstatic java/lang/Long.TYPE : Ljava/lang/Class;
    //   163: if_acmpne -> 178
    //   166: getstatic com/swift/sandhook/SandHookMethodResolver.fieldEntryPointFromCompiledCode : Ljava/lang/reflect/Field;
    //   169: getstatic com/swift/sandhook/SandHookMethodResolver.testArtMethod : Ljava/lang/Object;
    //   172: invokevirtual getLong : (Ljava/lang/Object;)J
    //   175: putstatic com/swift/sandhook/SandHookMethodResolver.entryPointFromCompiledCode : J
    //   178: getstatic com/swift/sandhook/SandHook.artMethodClass : Ljava/lang/Class;
    //   181: ldc 'entryPointFromInterpreter'
    //   183: invokestatic getField : (Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/reflect/Field;
    //   186: astore_0
    //   187: aload_0
    //   188: putstatic com/swift/sandhook/SandHookMethodResolver.fieldEntryPointFromInterpreter : Ljava/lang/reflect/Field;
    //   191: aload_0
    //   192: invokevirtual getType : ()Ljava/lang/Class;
    //   195: getstatic java/lang/Integer.TYPE : Ljava/lang/Class;
    //   198: if_acmpne -> 217
    //   201: getstatic com/swift/sandhook/SandHookMethodResolver.fieldEntryPointFromInterpreter : Ljava/lang/reflect/Field;
    //   204: getstatic com/swift/sandhook/SandHookMethodResolver.testArtMethod : Ljava/lang/Object;
    //   207: invokevirtual getInt : (Ljava/lang/Object;)I
    //   210: i2l
    //   211: putstatic com/swift/sandhook/SandHookMethodResolver.entryPointFromInterpreter : J
    //   214: goto -> 241
    //   217: getstatic com/swift/sandhook/SandHookMethodResolver.fieldEntryPointFromCompiledCode : Ljava/lang/reflect/Field;
    //   220: invokevirtual getType : ()Ljava/lang/Class;
    //   223: getstatic java/lang/Long.TYPE : Ljava/lang/Class;
    //   226: if_acmpne -> 241
    //   229: getstatic com/swift/sandhook/SandHookMethodResolver.fieldEntryPointFromInterpreter : Ljava/lang/reflect/Field;
    //   232: getstatic com/swift/sandhook/SandHookMethodResolver.testArtMethod : Ljava/lang/Object;
    //   235: invokevirtual getLong : (Ljava/lang/Object;)J
    //   238: putstatic com/swift/sandhook/SandHookMethodResolver.entryPointFromInterpreter : J
    //   241: goto -> 245
    //   244: astore_0
    //   245: return
    // Exception table:
    //   from	to	target	type
    //   0	11	14	java/lang/NoSuchFieldException
    //   78	96	99	finally
    //   100	111	114	finally
    //   115	126	244	finally
    //   126	151	244	finally
    //   154	178	244	finally
    //   178	214	244	finally
    //   217	241	244	finally }
  
  private static void checkSupportForArtMethodId() {
    Field field = SandHook.getField(Method.class, "dexMethodIndex");
    dexMethodIndexField = field;
    dexMethodIndex = ((Integer)field.get(testMethod)).intValue();
    field = SandHook.getField(Class.class, "dexCache");
    dexCacheField = field;
    Object object2 = field.get(testMethod.getDeclaringClass());
    field = SandHook.getField(object2.getClass(), "resolvedMethods");
    resolvedMethodsField = field;
    Object object1 = field.get(object2);
    if (object1 instanceof Long) {
      canResolvedInJava = false;
      resolvedMethodsAddress = ((Long)object1).longValue();
    } else if (object1 instanceof long[]) {
      canResolvedInJava = true;
    } else if (object1 instanceof int[]) {
      canResolvedInJava = true;
    } 
  }
  
  public static long getArtMethod(Member paramMember) {
    Field field = artMethodField;
    if (field == null)
      return 0L; 
    try {
      return ((Long)field.get(paramMember)).longValue();
    } catch (IllegalAccessException paramMember) {
      return 0L;
    } 
  }
  
  public static void init() {
    testMethod = SandHook.testOffsetMethod1;
    checkSupport();
  }
  
  private static void resolveInJava(Method paramMethod1, Method paramMethod2) throws Exception {
    Object object2;
    Object object1 = dexCacheField.get(paramMethod1.getDeclaringClass());
    if (isArtMethod) {
      object2 = artMethodField.get(paramMethod2);
      int i = ((Integer)dexMethodIndexField.get(object2)).intValue();
      (Object[])resolvedMethodsField.get(object1)[i] = object2;
    } else {
      int i = ((Integer)dexMethodIndexField.get(object2)).intValue();
      object1 = resolvedMethodsField.get(object1);
      if (object1 instanceof long[]) {
        long l = ((Long)artMethodField.get(object2)).longValue();
        (long[])object1[i] = l;
      } else {
        if (object1 instanceof int[]) {
          int j = Long.valueOf(((Long)artMethodField.get(object2)).longValue()).intValue();
          (int[])object1[i] = j;
          return;
        } 
        throw new UnsupportedOperationException("un support");
      } 
    } 
  }
  
  private static void resolveInNative(Method paramMethod1, Method paramMethod2) throws Exception { SandHook.ensureMethodCached(paramMethod1, paramMethod2); }
  
  public static void resolveMethod(Method paramMethod1, Method paramMethod2) throws Exception {
    if (canResolvedInJava && artMethodField != null) {
      try {
        resolveInJava(paramMethod1, paramMethod2);
      } catch (Exception exception) {
        resolveInNative(paramMethod1, paramMethod2);
      } 
    } else {
      resolveInNative(paramMethod1, paramMethod2);
    } 
  }
}


/* Location:              D:\Android\APK Tool\APK.Tool.GUI.v3.2.2.0\classes_merge_1.jar!\com\swift\sandhook\SandHookMethodResolver.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */
package com.swift.sandhook.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD})
public @interface SkipParamCheck {}


/* Location:              D:\Android\APK Tool\APK.Tool.GUI.v3.2.2.0\classes_merge_1.jar!\com\swift\sandhook\annotation\SkipParamCheck.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */
package com.swift.sandhook.wrapper;

import java.lang.reflect.Method;

public class StubMethodsFactory {
  static final int maxStub = 300;
  
  private static Method proxyGenClass;
  
  static  {
    try {
      Method method = java.lang.reflect.Proxy.class.getDeclaredMethod("generateProxy", new Class[] { String.class, Class[].class, ClassLoader.class, Method[].class, Class[][].class });
      proxyGenClass = method;
      method.setAccessible(true);
    } finally {
      Class<?> clazz = null;
    } 
  }
  
  public static Method getStubMethod() { // Byte code:
    //   0: ldc com/swift/sandhook/wrapper/StubMethodsFactory
    //   2: monitorenter
    //   3: getstatic com/swift/sandhook/wrapper/StubMethodsFactory.curStub : I
    //   6: istore_0
    //   7: iload_0
    //   8: sipush #300
    //   11: if_icmpgt -> 66
    //   14: new java/lang/StringBuilder
    //   17: astore_1
    //   18: aload_1
    //   19: invokespecial <init> : ()V
    //   22: aload_1
    //   23: ldc 'stub'
    //   25: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   28: astore_1
    //   29: getstatic com/swift/sandhook/wrapper/StubMethodsFactory.curStub : I
    //   32: istore_0
    //   33: iload_0
    //   34: iconst_1
    //   35: iadd
    //   36: putstatic com/swift/sandhook/wrapper/StubMethodsFactory.curStub : I
    //   39: ldc com/swift/sandhook/wrapper/StubMethodsFactory
    //   41: aload_1
    //   42: iload_0
    //   43: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   46: invokevirtual toString : ()Ljava/lang/String;
    //   49: iconst_0
    //   50: anewarray java/lang/Class
    //   53: invokevirtual getDeclaredMethod : (Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   56: astore_1
    //   57: ldc com/swift/sandhook/wrapper/StubMethodsFactory
    //   59: monitorexit
    //   60: aload_1
    //   61: areturn
    //   62: astore_1
    //   63: goto -> 3
    //   66: getstatic com/swift/sandhook/wrapper/StubMethodsFactory.curStub : I
    //   69: iconst_1
    //   70: iadd
    //   71: putstatic com/swift/sandhook/wrapper/StubMethodsFactory.curStub : I
    //   74: getstatic com/swift/sandhook/wrapper/StubMethodsFactory.proxyGenClass : Ljava/lang/reflect/Method;
    //   77: astore_1
    //   78: new java/lang/StringBuilder
    //   81: astore_2
    //   82: aload_2
    //   83: invokespecial <init> : ()V
    //   86: aload_1
    //   87: aconst_null
    //   88: iconst_5
    //   89: anewarray java/lang/Object
    //   92: dup
    //   93: iconst_0
    //   94: aload_2
    //   95: ldc 'SandHookerStubClass_'
    //   97: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   100: getstatic com/swift/sandhook/wrapper/StubMethodsFactory.curStub : I
    //   103: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   106: invokevirtual toString : ()Ljava/lang/String;
    //   109: aastore
    //   110: dup
    //   111: iconst_1
    //   112: aconst_null
    //   113: aastore
    //   114: dup
    //   115: iconst_2
    //   116: ldc com/swift/sandhook/wrapper/StubMethodsFactory
    //   118: invokevirtual getClassLoader : ()Ljava/lang/ClassLoader;
    //   121: aastore
    //   122: dup
    //   123: iconst_3
    //   124: iconst_1
    //   125: anewarray java/lang/reflect/Method
    //   128: dup
    //   129: iconst_0
    //   130: getstatic com/swift/sandhook/wrapper/StubMethodsFactory.proxyGenClass : Ljava/lang/reflect/Method;
    //   133: aastore
    //   134: aastore
    //   135: dup
    //   136: iconst_4
    //   137: aconst_null
    //   138: aastore
    //   139: invokevirtual invoke : (Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   142: checkcast java/lang/Class
    //   145: invokevirtual getDeclaredMethods : ()[Ljava/lang/reflect/Method;
    //   148: iconst_0
    //   149: aaload
    //   150: astore_1
    //   151: ldc com/swift/sandhook/wrapper/StubMethodsFactory
    //   153: monitorexit
    //   154: aload_1
    //   155: areturn
    //   156: astore_1
    //   157: aload_1
    //   158: invokevirtual printStackTrace : ()V
    //   161: ldc com/swift/sandhook/wrapper/StubMethodsFactory
    //   163: monitorexit
    //   164: aconst_null
    //   165: areturn
    //   166: astore_1
    //   167: ldc com/swift/sandhook/wrapper/StubMethodsFactory
    //   169: monitorexit
    //   170: aload_1
    //   171: athrow
    // Exception table:
    //   from	to	target	type
    //   3	7	166	finally
    //   14	57	62	java/lang/NoSuchMethodException
    //   14	57	166	finally
    //   66	151	156	finally
    //   157	161	166	finally }
  
  public void stub0() {}
  
  public void stub1() {}
  
  public void stub10() {}
  
  public void stub100() {}
  
  public void stub101() {}
  
  public void stub102() {}
  
  public void stub103() {}
  
  public void stub104() {}
  
  public void stub105() {}
  
  public void stub106() {}
  
  public void stub107() {}
  
  public void stub108() {}
  
  public void stub109() {}
  
  public void stub11() {}
  
  public void stub110() {}
  
  public void stub111() {}
  
  public void stub112() {}
  
  public void stub113() {}
  
  public void stub114() {}
  
  public void stub115() {}
  
  public void stub116() {}
  
  public void stub117() {}
  
  public void stub118() {}
  
  public void stub119() {}
  
  public void stub12() {}
  
  public void stub120() {}
  
  public void stub121() {}
  
  public void stub122() {}
  
  public void stub123() {}
  
  public void stub124() {}
  
  public void stub125() {}
  
  public void stub126() {}
  
  public void stub127() {}
  
  public void stub128() {}
  
  public void stub129() {}
  
  public void stub13() {}
  
  public void stub130() {}
  
  public void stub131() {}
  
  public void stub132() {}
  
  public void stub133() {}
  
  public void stub134() {}
  
  public void stub135() {}
  
  public void stub136() {}
  
  public void stub137() {}
  
  public void stub138() {}
  
  public void stub139() {}
  
  public void stub14() {}
  
  public void stub140() {}
  
  public void stub141() {}
  
  public void stub142() {}
  
  public void stub143() {}
  
  public void stub144() {}
  
  public void stub145() {}
  
  public void stub146() {}
  
  public void stub147() {}
  
  public void stub148() {}
  
  public void stub149() {}
  
  public void stub15() {}
  
  public void stub150() {}
  
  public void stub151() {}
  
  public void stub152() {}
  
  public void stub153() {}
  
  public void stub154() {}
  
  public void stub155() {}
  
  public void stub156() {}
  
  public void stub157() {}
  
  public void stub158() {}
  
  public void stub159() {}
  
  public void stub16() {}
  
  public void stub160() {}
  
  public void stub161() {}
  
  public void stub162() {}
  
  public void stub163() {}
  
  public void stub164() {}
  
  public void stub165() {}
  
  public void stub166() {}
  
  public void stub167() {}
  
  public void stub168() {}
  
  public void stub169() {}
  
  public void stub17() {}
  
  public void stub170() {}
  
  public void stub171() {}
  
  public void stub172() {}
  
  public void stub173() {}
  
  public void stub174() {}
  
  public void stub175() {}
  
  public void stub176() {}
  
  public void stub177() {}
  
  public void stub178() {}
  
  public void stub179() {}
  
  public void stub18() {}
  
  public void stub180() {}
  
  public void stub181() {}
  
  public void stub182() {}
  
  public void stub183() {}
  
  public void stub184() {}
  
  public void stub185() {}
  
  public void stub186() {}
  
  public void stub187() {}
  
  public void stub188() {}
  
  public void stub189() {}
  
  public void stub19() {}
  
  public void stub190() {}
  
  public void stub191() {}
  
  public void stub192() {}
  
  public void stub193() {}
  
  public void stub194() {}
  
  public void stub195() {}
  
  public void stub196() {}
  
  public void stub197() {}
  
  public void stub198() {}
  
  public void stub199() {}
  
  public void stub2() {}
  
  public void stub20() {}
  
  public void stub200() {}
  
  public void stub201() {}
  
  public void stub202() {}
  
  public void stub203() {}
  
  public void stub204() {}
  
  public void stub205() {}
  
  public void stub206() {}
  
  public void stub207() {}
  
  public void stub208() {}
  
  public void stub209() {}
  
  public void stub21() {}
  
  public void stub210() {}
  
  public void stub211() {}
  
  public void stub212() {}
  
  public void stub213() {}
  
  public void stub214() {}
  
  public void stub215() {}
  
  public void stub216() {}
  
  public void stub217() {}
  
  public void stub218() {}
  
  public void stub219() {}
  
  public void stub22() {}
  
  public void stub220() {}
  
  public void stub221() {}
  
  public void stub222() {}
  
  public void stub223() {}
  
  public void stub224() {}
  
  public void stub225() {}
  
  public void stub226() {}
  
  public void stub227() {}
  
  public void stub228() {}
  
  public void stub229() {}
  
  public void stub23() {}
  
  public void stub230() {}
  
  public void stub231() {}
  
  public void stub232() {}
  
  public void stub233() {}
  
  public void stub234() {}
  
  public void stub235() {}
  
  public void stub236() {}
  
  public void stub237() {}
  
  public void stub238() {}
  
  public void stub239() {}
  
  public void stub24() {}
  
  public void stub240() {}
  
  public void stub241() {}
  
  public void stub242() {}
  
  public void stub243() {}
  
  public void stub244() {}
  
  public void stub245() {}
  
  public void stub246() {}
  
  public void stub247() {}
  
  public void stub248() {}
  
  public void stub249() {}
  
  public void stub25() {}
  
  public void stub250() {}
  
  public void stub251() {}
  
  public void stub252() {}
  
  public void stub253() {}
  
  public void stub254() {}
  
  public void stub255() {}
  
  public void stub256() {}
  
  public void stub257() {}
  
  public void stub258() {}
  
  public void stub259() {}
  
  public void stub26() {}
  
  public void stub260() {}
  
  public void stub261() {}
  
  public void stub262() {}
  
  public void stub263() {}
  
  public void stub264() {}
  
  public void stub265() {}
  
  public void stub266() {}
  
  public void stub267() {}
  
  public void stub268() {}
  
  public void stub269() {}
  
  public void stub27() {}
  
  public void stub270() {}
  
  public void stub271() {}
  
  public void stub272() {}
  
  public void stub273() {}
  
  public void stub274() {}
  
  public void stub275() {}
  
  public void stub276() {}
  
  public void stub277() {}
  
  public void stub278() {}
  
  public void stub279() {}
  
  public void stub28() {}
  
  public void stub280() {}
  
  public void stub281() {}
  
  public void stub282() {}
  
  public void stub283() {}
  
  public void stub284() {}
  
  public void stub285() {}
  
  public void stub286() {}
  
  public void stub287() {}
  
  public void stub288() {}
  
  public void stub289() {}
  
  public void stub29() {}
  
  public void stub290() {}
  
  public void stub291() {}
  
  public void stub292() {}
  
  public void stub293() {}
  
  public void stub294() {}
  
  public void stub295() {}
  
  public void stub296() {}
  
  public void stub297() {}
  
  public void stub298() {}
  
  public void stub299() {}
  
  public void stub3() {}
  
  public void stub30() {}
  
  public void stub300() {}
  
  public void stub31() {}
  
  public void stub32() {}
  
  public void stub33() {}
  
  public void stub34() {}
  
  public void stub35() {}
  
  public void stub36() {}
  
  public void stub37() {}
  
  public void stub38() {}
  
  public void stub39() {}
  
  public void stub4() {}
  
  public void stub40() {}
  
  public void stub41() {}
  
  public void stub42() {}
  
  public void stub43() {}
  
  public void stub44() {}
  
  public void stub45() {}
  
  public void stub46() {}
  
  public void stub47() {}
  
  public void stub48() {}
  
  public void stub49() {}
  
  public void stub5() {}
  
  public void stub50() {}
  
  public void stub51() {}
  
  public void stub52() {}
  
  public void stub53() {}
  
  public void stub54() {}
  
  public void stub55() {}
  
  public void stub56() {}
  
  public void stub57() {}
  
  public void stub58() {}
  
  public void stub59() {}
  
  public void stub6() {}
  
  public void stub60() {}
  
  public void stub61() {}
  
  public void stub62() {}
  
  public void stub63() {}
  
  public void stub64() {}
  
  public void stub65() {}
  
  public void stub66() {}
  
  public void stub67() {}
  
  public void stub68() {}
  
  public void stub69() {}
  
  public void stub7() {}
  
  public void stub70() {}
  
  public void stub71() {}
  
  public void stub72() {}
  
  public void stub73() {}
  
  public void stub74() {}
  
  public void stub75() {}
  
  public void stub76() {}
  
  public void stub77() {}
  
  public void stub78() {}
  
  public void stub79() {}
  
  public void stub8() {}
  
  public void stub80() {}
  
  public void stub81() {}
  
  public void stub82() {}
  
  public void stub83() {}
  
  public void stub84() {}
  
  public void stub85() {}
  
  public void stub86() {}
  
  public void stub87() {}
  
  public void stub88() {}
  
  public void stub89() {}
  
  public void stub9() {}
  
  public void stub90() {}
  
  public void stub91() {}
  
  public void stub92() {}
  
  public void stub93() {}
  
  public void stub94() {}
  
  public void stub95() {}
  
  public void stub96() {}
  
  public void stub97() {}
  
  public void stub98() {}
  
  public void stub99() {}
}


/* Location:              D:\Android\APK Tool\APK.Tool.GUI.v3.2.2.0\classes_merge_1.jar!\com\swift\sandhook\wrapper\StubMethodsFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */
package com.swift.sandhook.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER})
public @interface ThisObject {}


/* Location:              D:\Android\APK Tool\APK.Tool.GUI.v3.2.2.0\classes_merge_1.jar!\com\swift\sandhook\annotation\ThisObject.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */
package com.swift.sandhook.utils;

import android.util.Log;
import com.swift.sandhook.HookLog;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public final class Unsafe {
  private static final String TAG = "Unsafe";
  
  private static Method arrayBaseOffsetMethod;
  
  private static Method arrayIndexScaleMethod;
  
  private static Method getIntMethod;
  
  private static Method getLongMethod;
  
  private static Class objectArrayClass;
  
  private static Object unsafe;
  
  private static Class unsafeClass;
  
  static  {
    objectArrayClass = Object[].class;
    try {
      Class clazz = Class.forName("sun.misc.Unsafe");
      unsafeClass = clazz;
      Field field = clazz.getDeclaredField("theUnsafe");
      field.setAccessible(true);
      unsafe = field.get(null);
    } catch (Exception exception) {
      try {
        Field field = unsafeClass.getDeclaredField("THE_ONE");
        field.setAccessible(true);
        unsafe = field.get(null);
      } catch (Exception exception) {
        Log.w("Unsafe", "Unsafe not found o.O");
      } 
    } 
    if (unsafe != null)
      try {
        arrayBaseOffsetMethod = unsafeClass.getDeclaredMethod("arrayBaseOffset", new Class[] { Class.class });
        arrayIndexScaleMethod = unsafeClass.getDeclaredMethod("arrayIndexScale", new Class[] { Class.class });
        getIntMethod = unsafeClass.getDeclaredMethod("getInt", new Class[] { Object.class, long.class });
        getLongMethod = unsafeClass.getDeclaredMethod("getLong", new Class[] { Object.class, long.class });
        supported = true;
      } catch (Exception exception) {} 
  }
  
  public static int arrayBaseOffset(Class paramClass) {
    try {
      return ((Integer)arrayBaseOffsetMethod.invoke(unsafe, new Object[] { paramClass })).intValue();
    } catch (Exception paramClass) {
      return 0;
    } 
  }
  
  public static int arrayIndexScale(Class paramClass) {
    try {
      return ((Integer)arrayIndexScaleMethod.invoke(unsafe, new Object[] { paramClass })).intValue();
    } catch (Exception paramClass) {
      return 0;
    } 
  }
  
  public static int getInt(Object paramObject, long paramLong) {
    try {
      return ((Integer)getIntMethod.invoke(unsafe, new Object[] { paramObject, Long.valueOf(paramLong) })).intValue();
    } catch (Exception paramObject) {
      return 0;
    } 
  }
  
  public static long getLong(Object paramObject, long paramLong) {
    try {
      return ((Long)getLongMethod.invoke(unsafe, new Object[] { paramObject, Long.valueOf(paramLong) })).longValue();
    } catch (Exception paramObject) {
      return 0L;
    } 
  }
  
  public static long getObjectAddress(Object paramObject) {
    try {
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = paramObject;
      if (arrayIndexScale(objectArrayClass) == 8)
        return getLong(arrayOfObject, arrayBaseOffset(objectArrayClass)); 
      int i = getInt(arrayOfObject, arrayBaseOffset(objectArrayClass));
      return 0xFFFFFFFFL & i;
    } catch (Exception paramObject) {
      HookLog.e("get object address error", paramObject);
      return -1L;
    } 
  }
  
  public static boolean support() { return supported; }
}


/* Location:              D:\Android\APK Tool\APK.Tool.GUI.v3.2.2.0\classes_merge_1.jar!\com\swift\sandhoo\\utils\Unsafe.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */
