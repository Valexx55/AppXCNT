// Top-level build file where you can add configuration options common to all sub-projects/modules.
/**
 * | Gradle                       | Mundo real               |
 * | ---------------------------- | ------------------------ |
 * | `id(...)`                    | Conozco esta herramienta |
 * | `version`                    | Sé qué versión usar      |
 * | `apply false`                | La guardo en el armario  |
 * | `plugins { id(...) }` en app | La uso aquí              |
 *
 */
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.jetbrains.kotlin.jvm) apply false
    id("com.google.gms.google-services") version "4.4.4" apply false
}