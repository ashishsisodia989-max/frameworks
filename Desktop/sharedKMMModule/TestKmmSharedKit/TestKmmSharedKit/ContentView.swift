//
//  ContentView.swift
//  TestKmmSharedKit
//
//  Created by Ashish Sisodia on 13/08/25.
//

import SwiftUI
import KMMSharedKit

struct ContentView: View {
    var body: some View {
        VStack {
            Image(systemName: "globe")
                .imageScale(.large)
                .foregroundStyle(.tint)
            Text("Hello, world!")
            Text("KMMSharedKit:- \(Greeting().greet())")
        }
        .padding()
    }
}

#Preview {
    ContentView()
}
