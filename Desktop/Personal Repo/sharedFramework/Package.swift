// swift-tools-version:6.1
import PackageDescription

let package = Package(
    name: "sharedFramework",
    platforms: [
        .iOS(.v13)
    ],
    products: [
        .library(
            name: "ComposeApp",
            targets: ["ComposeApp"]
        )
    ],
    targets: [
        .binaryTarget(
            name: "ComposeApp",
            path: "./xcframework/RELEASE/ComposeApp.xcframework"
        )
    ]
)

