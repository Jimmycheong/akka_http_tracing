package jimmy.project.repositories

import jimmy.project.Models.User
import jimmy.project.schema.UserSchema
import slick.jdbc.PostgresProfile.api._
import slick.jdbc.PostgresProfile.backend

import scala.concurrent.{ExecutionContext, Future}

class PsqlSlickRepository(database: backend.Database) extends DbRepository {

  val users = TableQuery[UserSchema]

  def getAllUsers(implicit executionContext: ExecutionContext): Future[Seq[User]] =
    database.run(users.result).map { // Map future
      _.map { case (id, username) => // Map user
        User(id, username)
      }
    }

  def addUser(user: User)(implicit executionContext: ExecutionContext): Future[Unit] = ???

}
