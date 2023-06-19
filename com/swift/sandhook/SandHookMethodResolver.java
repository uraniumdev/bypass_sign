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