package jimmy.project.repositories

import jimmy.project.Models.User
import jimmy.project.schema.UserSchema
import slick.jdbc.PostgresProfile.api._

import scala.concurrent.{ExecutionContext, Future}


abstract class SlickDbRepository {
  val db: Database
}

class PsqlDbRepo extends SlickDbRepository {

  override val db = Database.forConfig("psqldb")
  val users = TableQuery[UserSchema]

  def getAllUsers(implicit executionContext: ExecutionContext): Future[Seq[User]] =
    db.run(users.result).map { // Map future
      _.map { case (id, username) => // Map user
        User(id, username)
      }
    }

}
