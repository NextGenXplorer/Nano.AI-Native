-keep class com.nano.plugins.api.** { *; }
-keep class com.nano.plugins.model.** { *; }
-keep class com.nano.plugins.manager.PluginManager { *; }
-keepclassmembers class ** {
    @androidx.compose.runtime.Composable *;
}
