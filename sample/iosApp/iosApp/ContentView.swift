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
       
        let formatter = KDateTimeFormatter(format: Format.dateTime, style: DateTimeStyle.full)
        
        
        return dateTime.format(formatter: formatter, withLocale: NSLocale.current.languageCode!)
        
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
