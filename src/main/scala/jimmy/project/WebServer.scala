package jimmy.project

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import jimmy.project.repositories.{PsqlDbRepo, SlickDbRepository}
import jimmy.project.services.{PsqlManagementService, UserManagementService}

import scala.io.StdIn

object WebServer {

  def main(args: Array[String]) {

    implicit val system = ActorSystem("my-system")
    implicit val materializer = ActorMaterializer()
    // needed for the future flatMap/onComplete in the end
    implicit val executionContext = system.dispatcher

    val psqlDbRepo: PsqlDbRepo                            = new PsqlDbRepo()
    val psqlManagementService: UserManagementService      = new PsqlManagementService(psqlDbRepo)

    val routes = new Routes(psqlManagementService)

    try {
      val bindingFuture = Http().bindAndHandle(routes.allRoutes, "localhost", 8080)
      println(s"Server online at http://localhost:8080/\nPress RETURN to stop...")
      StdIn.readLine() // let it run until user presses return
      bindingFuture
        .flatMap(_.unbind()) // trigger unbinding from the port
        .onComplete(_ => system.terminate()) // and shutdown when done

    } finally {
      psqlDbRepo.db.close
    }

  }
}
