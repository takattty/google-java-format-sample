plugins {
    id("java")
    id("application")
    alias(libs.plugins.spotless)
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.google.java.format)

    testImplementation(platform(libs.junit.bom))
    testImplementation(libs.junit.jupiter)
}

application {
    mainClass.set("org.example.Main")
}

tasks.test {
    useJUnitPlatform()
}

spotless {
    // オプション: Java以外のファイル（Gradle、設定ファイルなど）のフォーマット
    // 必要な場合のみ使用
    format("misc") {
        target(
            "*.gradle",
            "*.gradle.kts",
            ".gitattributes",
            ".gitignore",
            "**/*.yml",
            "**/*.yaml",
            "**/*.md"
        )

        // 行末の空白を削除
        trimTrailingWhitespace()

        // ファイル末尾に改行を追加
        endWithNewline()
    }
    java {
        // google-java-formatの設定
        googleJavaFormat("1.27.0")

        // 型アノテーションのフォーマット修正
        formatAnnotations()

        // デフォルトのGoogle java Formatの内部エンジンを利用
        removeUnusedImports()

        // インポートの順番を整理
        importOrder()

        // Sonarqubeのルールでコードを自動修正（リファクタリング）をしたい時に、有効化する
        // cleanthat()
    }
}
