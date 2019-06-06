package jimmy.project.services

import jimmy.project.Models.User

import scala.concurrent.Future

trait UserManagementService {
//  def addUsers: Future[Unit]

  def getUsers: Future[Seq[User]]
}
