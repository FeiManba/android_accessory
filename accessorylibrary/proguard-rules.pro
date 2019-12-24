# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

#第三方包不混淆
-dontwarn com.mrzang.accessorylibrary**
-keep class com.mrzang.accessorylibrary.**{*;}
-keep interface  com.mrzang.accessorylibrary.**{*;}

#easyPermissionGranted
-dontwarn pub.devrel.easypermissions**
-keep class pub.devrel.easypermissions.**{*;}
-keep interface pub.devrel.easypermissions.**{*;}

