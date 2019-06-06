package jimmy.project.services

import jimmy.project.Models
import jimmy.project.Models.{User, Users}
import jimmy.project.repositories.PsqlDbRepo

import scala.concurrent.{ExecutionContext, Future}

class PsqlManagementService(psqlDbRepo: PsqlDbRepo)(implicit executionContext: ExecutionContext) extends UserManagementService {
//  override def addUsers: Future[Unit] = ???

  override def getUsers: Future[Seq[User]] = psqlDbRepo.getAllUsers
}
