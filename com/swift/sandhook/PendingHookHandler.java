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