package test.ktor

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.locations.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.gson.*
import java.text.*
import org.slf4j.event.Level

@Location("/test/{name}")
data class TestLocation(val name: String)
data class TestLocationGsonBody(val say: String, val times: Int)

fun Application.main() {
  install(DefaultHeaders)
  install(CallLogging) {
    level = Level.INFO
  }
  install(Locations)
	install(ContentNegotiation) {
		gson {
			setDateFormat(DateFormat.LONG)
			setPrettyPrinting()
		}
	}
  routing {
    post<TestLocation> { withLocationParameters ->
      val says = call.receive<TestLocationGsonBody>()
      var saysText = " says:\n"
      for (i in 0 until says.times) {
        saysText += says.say + "\n"
      }
      call.respondText(withLocationParameters.name + saysText)
    }
  }

}

