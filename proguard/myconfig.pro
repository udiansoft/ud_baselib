#
# This ProGuard configuration file illustrates how to process applications.
# Usage:
#     java -jar proguard.jar @applications.pro
#

# Specify the input jars, output jars, and library jars.

-injars  <user.dir>/dist/ud-base-1.0_raw.jar
-outjars <user.dir>/dist/ud-base-1.0.jar
-dontskipnonpubliclibraryclasses
-dontskipnonpubliclibraryclassmembers

-libraryjars <java.home>/lib/rt.jar
-libraryjars <java.home>/lib/jce.jar
-libraryjars <user.dir>/lib/javaee.jar
-libraryjars <user.dir>/lib/lib1/commons-beanutils-1.5.jar
-libraryjars <user.dir>/lib/lib1/commons-codec-1.3.jar
-libraryjars <user.dir>/lib/lib1/commons-codec-1.6.jar
-libraryjars <user.dir>/lib/lib1/commons-collections-2.1.1.jar
-libraryjars <user.dir>/lib/lib1/commons-dbcp-1.2.1.jar
-libraryjars <user.dir>/lib/lib1/commons-digester-1.7.jar
-libraryjars <user.dir>/lib/lib1/commons-fileupload-1.2.jar
-libraryjars <user.dir>/lib/lib1/commons-httpclient-3.0-rc3.jar
-libraryjars <user.dir>/lib/lib1/commons-io-1.3.2.jar
-libraryjars <user.dir>/lib/lib1/commons-lang-2.0.jar
-libraryjars <user.dir>/lib/lib1/commons-logging-1.0.4.jar
-libraryjars <user.dir>/lib/lib1/commons-logging-api-1.0.2.jar
-libraryjars <user.dir>/lib/lib1/commons-net-1.4.1.jar
-libraryjars <user.dir>/lib/lib1/commons-pool-1.2.jar
-libraryjars <user.dir>/lib/lib1/freemarker-2.3.jar
-libraryjars <user.dir>/lib/lib1/json-lib-2.4-jdk15.jar
-libraryjars <user.dir>/lib/lib1/spring.jar

#-dontshrink
-dontoptimize

-keep public class * {
   public protected private *;
}

# Print out a list of what we're preserving.
-printseeds


# Preserve all native method names and the names of their classes.
-keepclasseswithmembernames class * {
    native <methods>;
}

# Preserve the special static methods that are required in all enumeration
# classes.
-keepclassmembers class * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

# Explicitly preserve all serialization members. The Serializable interface
# is only a marker interface, so it wouldn't save them.
# You can comment this out if your library doesn't use serialization.
# If your code contains serializable classes that have to be backward
# compatible, please refer to the manual.

-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}
