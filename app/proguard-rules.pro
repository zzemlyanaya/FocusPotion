-printconfiguration ~/tmp/full-r8-config.txt
-optimizationpasses 3
-allowaccessmodification

-keep @javax.inject.Qualifier public class *
-dontwarn **.R$*
-keep class * {
 @com.google.gson.annotations.SerializedName <fields>;
}
-keep class kotlin.coroutines.Continuation

#Preserve annotations, line numbers, and source file names
-keepattributes Signature,Exceptions,InnerClasses,EnclosingMethod,*Annotation*,LineNumberTable,SourceFile

-keepclassmembers enum * {
    <fields>;
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

# Retrolambda
-dontwarn java.lang.invoke.*
-dontwarn **$$Lambda$*

# Gson specific classes
-keep class sun.misc.Unsafe { *; }
-dontnote com.google.gson.**

-keepnames class * implements java.io.Serializable
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}

-dontwarn com.google.common.annotations.**
-dontnote com.google.common.annotations.**
-keep class **.proto.** { *; }


#Glide
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep class * extends com.bumptech.glide.module.AppGlideModule {
 <init>(...);
}
-keep public enum com.bumptech.glide.load.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}
-keep class com.bumptech.glide.load.data.ParcelFileDescriptorRewinder$InternalRewinder {
  *** rewind();
}


-dontwarn com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool
-dontwarn com.bumptech.glide.load.resource.bitmap.Downsampler
-dontwarn com.bumptech.glide.load.resource.bitmap.HardwareConfigState

-dontwarn com.bumptech.glide.load.model.stream.*

# кастомные сериализаторы
-keep public class * implements com.google.gson.JsonSerializer

#kotlin
-dontnote kotlin.internal.**
-dontwarn kotlinx.coroutines.**

# org.slf4j
-dontwarn org.slf4j.**

# Proguard config for project using GMS
-keep class * implements com.google.android.gms { *; }
-keep class com.google.android.gms.** { *; }
-keep interface com.google.android.gms.** { *; }