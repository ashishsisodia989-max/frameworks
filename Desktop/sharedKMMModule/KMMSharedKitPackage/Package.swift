// swift-tools-version:6.1
import PackageDescription

let package = Package(
    name: "KMMSharedKit",
    platforms: [
        .iOS(.v13)
    ],
    products: [
        .library(
            name: "KMMSharedKit",
            targets: ["KMMSharedKit"]
        )
    ],
    targets: [
        .binaryTarget(
            name: "KMMSharedKit",
            url: "https://github.com/ashishsisodia989-max/KMMSharedKit/releases/download/1.0.3/KMMSharedKit.xcframework.zip",
            checksum: "1624840d1bdbfead48e79d56ac6cfb08bc1de0b38ceec4e427b3760352e91eb8"
        )
    ]
)
