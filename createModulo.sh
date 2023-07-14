#!/bin/bash
#USO PROPIEDAD TATHO
# Verificar que se haya proporcionado un nombre de carpeta como argumento
if [ -z "$1" ]; then
    echo "Debe proporcionar un nombre de carpeta como argumento"
    exit 1
fi

# Obtener el nombre de la carpeta del primer argumento en minúsculas
nombre_carpeta=$(echo "$1" )
nombre_carpeta_lower=$(echo "$1" | tr '[:upper:]' '[:lower:]')


# Obtener la ruta absoluta del directorio actual
directorio_actual=$(pwd)

# Crear la carpeta un nivel arriba de "app/"
ruta_carpeta="$directorio_actual/app/$nombre_carpeta"

# Verificar si la carpeta ya existe
if [ -d "$ruta_carpeta" ]; then
    echo "La carpeta \"$nombre_carpeta\" ya existe"
    exit 1
fi

# Crear la carpeta principal
mkdir -p "$ruta_carpeta/libs"

# Crear la estructura de carpetas dentro de la carpeta
mkdir -p "$ruta_carpeta/${nombre_carpeta_lower}_data/libs"
mkdir -p "$ruta_carpeta/${nombre_carpeta_lower}_data/src/androidTest/java/com/tatho/${nombre_carpeta_lower}_data"
mkdir -p "$ruta_carpeta/${nombre_carpeta_lower}_data/src/main/java/com/tatho/${nombre_carpeta_lower}_data/di"
mkdir -p "$ruta_carpeta/${nombre_carpeta_lower}_data/src/main/java/com/tatho/${nombre_carpeta_lower}_data/repository"
mkdir -p "$ruta_carpeta/${nombre_carpeta_lower}_data/src/test/java/com/tatho/${nombre_carpeta_lower}_data"

mkdir -p "$ruta_carpeta/${nombre_carpeta_lower}_domain/libs"
mkdir -p "$ruta_carpeta/${nombre_carpeta_lower}_domain/src/androidTest/java/com/tatho/${nombre_carpeta_lower}_domain"
mkdir -p "$ruta_carpeta/${nombre_carpeta_lower}_domain/src/main/java/com/tatho/${nombre_carpeta_lower}_domain/repository"
mkdir -p "$ruta_carpeta/${nombre_carpeta_lower}_domain/src/main/java/com/tatho/${nombre_carpeta_lower}_domain/usercase"
mkdir -p "$ruta_carpeta/${nombre_carpeta_lower}_domain/src/test/java/com/tatho/${nombre_carpeta_lower}_domain"

mkdir -p "$ruta_carpeta/${nombre_carpeta_lower}_presentation/libs"
mkdir -p "$ruta_carpeta/${nombre_carpeta_lower}_presentation/src/androidTest/java/com/tatho/${nombre_carpeta_lower}_presentation"
mkdir -p "$ruta_carpeta/${nombre_carpeta_lower}_presentation/src/main/java/com/tatho/${nombre_carpeta_lower}_presentation"
mkdir -p "$ruta_carpeta/${nombre_carpeta_lower}_presentation/src/test/java/com/tatho/${nombre_carpeta_lower}_presentation"

mkdir -p "$ruta_carpeta/src/androidTest/java/com/tatho/$nombre_carpeta"
mkdir -p "$ruta_carpeta/src/main/java/com/tatho/$nombre_carpeta"
mkdir -p "$ruta_carpeta/src/test/java/com/tatho/$nombre_carpeta"

# Crear archivo .gitignore
contenido_gitignore="/build"
contenido_build_gradle_root="plugins {
    id 'com.android.library'
    id 'org.jetbrains.kotlin.android'
}

android {
    compileSdk 33

    defaultConfig {
        minSdk 21
        targetSdk 33

        testInstrumentationRunner \"androidx.test.runner.AndroidJUnitRunner\"
        consumerProguardFiles \"consumer-rules.pro\"
    }

    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
        }
    }
    compileOptions {
        sourceCompatibility JavaVersion.VERSION_1_8
        targetCompatibility JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = '1.8'
    }
}

dependencies {
    implementation 'androidx.core:core-ktx:1.7.0'
    implementation 'androidx.appcompat:appcompat:1.6.1'
    implementation 'com.google.android.material:material:1.9.0'
    testImplementation 'junit:junit:4.13.2'
    androidTestImplementation 'androidx.test.ext:junit:1.1.5'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.5.1'

    // Import the BoM for the Firebase platform
    implementation(platform(\"com.google.firebase:firebase-bom:32.1.1\"))

    // Add the dependency for the Firebase Authentication library
    // When using the BoM, you don't specify versions in Firebase library dependencies
    implementation(\"com.google.firebase:firebase-auth-ktx\")
}"
contenido_build_gradle_data="plugins {
    id 'com.android.library'
    id 'org.jetbrains.kotlin.android'
    id 'kotlin-kapt'
    id 'com.google.dagger.hilt.android'
}

android {
    compileSdk 33

    defaultConfig {
        minSdk 21
        targetSdk 33

        testInstrumentationRunner \"androidx.test.runner.AndroidJUnitRunner\"
        consumerProguardFiles \"consumer-rules.pro\"
    }

    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
        }
    }
    compileOptions {
        sourceCompatibility JavaVersion.VERSION_1_8
        targetCompatibility JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = '1.8'
    }
}

dependencies {

    implementation project(path: ':app:$nombre_carpeta:${nombre_carpeta_lower}_domain')

    implementation 'androidx.core:core-ktx:1.7.0'
    implementation 'androidx.appcompat:appcompat:1.6.1'
    implementation 'com.google.android.material:material:1.9.0'
    implementation \"com.google.dagger:hilt-android:2.44\"
    kapt \"com.google.dagger:hilt-compiler:2.44\"
    //Firebase
    implementation 'com.google.firebase:firebase-firestore-ktx:24.4.1'
    implementation platform('com.google.firebase:firebase-bom:32.1.1')
    implementation 'com.google.firebase:firebase-analytics-ktx'
    // Import the BoM for the Firebase platform
    implementation(platform(\"com.google.firebase:firebase-bom:32.1.1\"))
    // Add the dependency for the Firebase Authentication library
    // When using the BoM, you don't specify versions in Firebase library dependencies
    implementation(\"com.google.firebase:firebase-auth-ktx\")
}

kapt {
    correctErrorTypes = true
}"

contenido_andromanifest_data="<?xml version=\"1.0\" encoding=\"utf-8\"?><manifest package=\"com.tatho.${nombre_carpeta_lower}_data\"></manifest>"
contenido_andromanifest_domain="<?xml version=\"1.0\" encoding=\"utf-8\"?><manifest package=\"com.tatho.${nombre_carpeta_lower}_domain\"></manifest>"
contenido_andromanifest_presentation="<?xml version=\"1.0\" encoding=\"utf-8\"?><manifest package=\"com.tatho.${nombre_carpeta_lower}_presentation\"></manifest>"
contenido_build_gradle_domain="
plugins {
    id 'com.android.library'
    id 'org.jetbrains.kotlin.android'
    id 'kotlin-kapt'
    id 'com.google.dagger.hilt.android'
}

android {
    compileSdk 33

    defaultConfig {
        minSdk 21
        targetSdk 33
        testInstrumentationRunner \"androidx.test.runner.AndroidJUnitRunner\"
        consumerProguardFiles \"consumer-rules.pro\"
    }

    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
        }
    }
    compileOptions {
        sourceCompatibility JavaVersion.VERSION_1_8
        targetCompatibility JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = '1.8'
    }
}

dependencies {

    implementation 'androidx.core:core-ktx:1.10.1'
    implementation 'androidx.appcompat:appcompat:1.6.1'

    //Room
    implementation 'androidx.room:room-ktx:2.5.2'
    implementation \"com.google.dagger:hilt-android:2.44\"
    implementation project(path: ':app:Common')
    kapt \"com.google.dagger:hilt-compiler:2.44\"
}

kapt {
    correctErrorTypes = true
}
"
contenido_build_gradle_presentation="
plugins {
   id 'com.android.library'
   id 'org.jetbrains.kotlin.android'
   id 'kotlin-kapt'
   id 'com.google.dagger.hilt.android'
}

android {
   compileSdk 33

   defaultConfig {
       minSdk 21
       targetSdk 33

       testInstrumentationRunner \"androidx.test.runner.AndroidJUnitRunner\"
       consumerProguardFiles \"consumer-rules.pro\"
   }

   buildTypes {
       release {
           minifyEnabled false
           proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
       }
   }
   compileOptions {
       sourceCompatibility JavaVersion.VERSION_1_8
       targetCompatibility JavaVersion.VERSION_1_8
   }
   kotlinOptions {
       jvmTarget = '1.8'
   }

   buildFeatures {
       compose true
   }

   composeOptions {
       kotlinCompilerExtensionVersion = \"1.4.0\"
   }
}

dependencies {

   implementation 'androidx.core:core-ktx:1.7.0'

   implementation project(path: ':app:Common')
   implementation project(path: ':app:Menu:menu_domain')
   implementation project(path: ':app:Menu:menu_data')
   implementation project(path: ':app:$nombre_carpeta:${nombre_carpeta_lower}_domain')

   def composeBom = platform('androidx.compose:compose-bom:2023.05.01')
   implementation composeBom
   androidTestImplementation composeBom

   implementation 'androidx.compose.material:material'

   // Android Studio Preview support
   implementation 'androidx.compose.ui:ui-tooling-preview'
   debugImplementation 'androidx.compose.ui:ui-tooling'

   // UI Tests
   androidTestImplementation 'androidx.compose.ui:ui-test-junit4'
   debugImplementation 'androidx.compose.ui:ui-test-manifest'

   def nav_version = \"2.6.0\"

   implementation \"androidx.navigation:navigation-compose:$nav_version\"

   implementation 'com.google.android.material:material:1.9.0'

   implementation \"androidx.compose.material:material-icons-extended:1.4.3\"

   implementation \"androidx.constraintlayout:constraintlayout-compose:1.0.1\"
   //Hilt
   implementation \"com.google.dagger:hilt-android:2.44\"
   kapt \"com.google.dagger:hilt-compiler:2.44\"

   kapt \"androidx.hilt:hilt-compiler:1.0.0\"
}
kapt {
   correctErrorTypes = true
}
"
# Crear el archivo build.gradle con el contenido
#Files lib root
echo "$contenido_gitignore" > "$ruta_carpeta/.gitignore"
echo "$contenido_build_gradle_root" > "$ruta_carpeta/build.gradle"
echo "" > "$ruta_carpeta/proguard-rules.pro"
echo "" > "$ruta_carpeta/consumer-rules.pro"

# Files nombre_carpeta_lower_data
echo "$contenido_gitignore" > "$ruta_carpeta/${nombre_carpeta_lower}_data/.gitignore"
echo "$contenido_build_gradle_data" > "$ruta_carpeta/${nombre_carpeta_lower}_data/build.gradle"
echo "" > "$ruta_carpeta/${nombre_carpeta_lower}_data/proguard-rules.pro"
echo "" > "$ruta_carpeta/${nombre_carpeta_lower}_data/consumer-rules.pro"

# Files android manifest data
echo "$contenido_andromanifest_data" > "$ruta_carpeta/${nombre_carpeta_lower}_data/src/main/AndroidManifest.xml"

# Files nombre_carpeta_lower_domain
echo "$contenido_gitignore" > "$ruta_carpeta/${nombre_carpeta_lower}_domain/.gitignore"
echo "$contenido_build_gradle_domain" > "$ruta_carpeta/${nombre_carpeta_lower}_domain/build.gradle"
echo "" > "$ruta_carpeta/${nombre_carpeta_lower}_domain/proguard-rules.pro"
echo "" > "$ruta_carpeta/${nombre_carpeta_lower}_domain/consumer-rules.pro"

# Files android manifest domain
echo "$contenido_andromanifest_domain" > "$ruta_carpeta/${nombre_carpeta_lower}_domain/src/main/AndroidManifest.xml"

# Files nombre_carpeta_lower_presentation
echo "$contenido_gitignore" > "$ruta_carpeta/${nombre_carpeta_lower}_presentation/.gitignore"
echo "$contenido_build_gradle_presentation" > "$ruta_carpeta/${nombre_carpeta_lower}_presentation/build.gradle"
echo "" > "$ruta_carpeta/${nombre_carpeta_lower}_presentation/proguard-rules.pro"
echo "" > "$ruta_carpeta/${nombre_carpeta_lower}_presentation/consumer-rules.pro"

# Files android manifest presentation
echo "$contenido_andromanifest_presentation" > "$ruta_carpeta/${nombre_carpeta_lower}_presentation/src/main/AndroidManifest.xml"

# Agregar las líneas include al archivo settings.gradle
echo "include ':app:$nombre_carpeta'" >> "$directorio_actual/settings.gradle"
echo "include ':app:$nombre_carpeta:${nombre_carpeta_lower}_data'" >> "$directorio_actual/settings.gradle"
echo "include ':app:$nombre_carpeta:${nombre_carpeta_lower}_domain'" >> "$directorio_actual/settings.gradle"
echo "include ':app:$nombre_carpeta:${nombre_carpeta_lower}_presentation'" >> "$directorio_actual/settings.gradle"

# Imprimir mensaje de éxito
echo "Se ha creado la estructura de carpetas para \"$nombre_carpeta\" en $ruta_carpeta"
echo "Se ha creado el archivo build.gradle en $ruta_carpeta"
echo "Se ha agregado la línea include ':app:$nombre_carpeta' en settings.gradle"

