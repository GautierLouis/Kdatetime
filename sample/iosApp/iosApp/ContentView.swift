//
//  ContentView.swift
//  iosApp
//
//  Created by Louis on 01/01/2022.
//

import SwiftUI
import Kdatetime

struct ContentView: View {
    func getCurrentDate() -> String {
        let dateTime = KDateTimeBuilderKt.now(zone: NSTimeZone.default)
       
        
        return DateTimeFormatterAppleKt.format(instant: dateTime.instant, pattern: "EEEE DD MMMM YYY HH:mm:ss", zone: dateTime.zone(), localeString: "fr-FR")
    }
    
    var body: some View {
        Text(getCurrentDate())
            .padding()
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}
