import SwiftUI
//import shared
import Shared

struct ContentView: View {
	let greet = Greeting().greeting()
    
    

	var body: some View {
		Text("Ampas kuda")
	}
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
