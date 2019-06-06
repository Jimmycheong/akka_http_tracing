package jimmy.project

import akka.http.scaladsl.model.{ContentTypes, HttpEntity}
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.Directives._
import Models._
import jimmy.project.services.UserManagementService

import scala.concurrent.ExecutionContext
import scala.util.{Failure, Success}

class Routes(umService: UserManagementService)(implicit executionContext: ExecutionContext) extends UserJsonProtocol {

  val allRoutes: Route =
    pathPrefix("api") {
      path("users") {
        pathEnd {
          get {
            onComplete(umService.getUsers) {
              case Success(users: Seq[Users]) => complete(Users(users))
              case Failure(error) => complete(HttpEntity(ContentTypes.`text/html(UTF-8)`, "no users"))
            }
          }
        }
      } ~
      path("addUser") {
        post {
          entity(as[User]){ user =>
            complete("something")
          }
        }
      }
    }

}
