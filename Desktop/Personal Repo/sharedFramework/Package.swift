
import PackageDescription

let package = Package(
    name: "sharedFramework",
    platforms: [
        .iOS(.v13) // Minimum iOS version supported by your framework
    ],
    products: [
        .library(
            name: "ComposeApp",  // Matches the Kotlin framework baseName
            targets: ["ComposeApp"]
        )
    ],
    targets: [
        // This is the binary XCFramework built by your Gradle KMM project
        .binaryTarget(
            name: "ComposeApp",
            path: "./xcframework/RELEASE/ComposeApp.xcframework" // Adjust RELEASE or DEBUG here based on buildType
        )
    ]
)

