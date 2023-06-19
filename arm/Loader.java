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