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