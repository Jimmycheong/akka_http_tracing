package jimmy.project.services

import jimmy.project.Models.User
import jimmy.project.repositories.DbRepository

import scala.concurrent.{ExecutionContext, Future}

class PsqlManagementService(dbRepo: DbRepository)(implicit executionContext: ExecutionContext) extends UserManagementService {

  override def getUsers: Future[Seq[User]] = dbRepo.getAllUsers

  override def addUsers(user: User): Future[Unit] = dbRepo.addUser(user)
}
