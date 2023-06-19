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