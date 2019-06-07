package jimmy.project.services

import jimmy.project.Models.User

import scala.concurrent.Future

trait UserManagementService {

  def getUsers: Future[Seq[User]]

  def addUsers(user: User): Future[Unit]

}
