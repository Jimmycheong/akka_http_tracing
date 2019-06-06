package jimmy.project.schema

import slick.jdbc.PostgresProfile.api._

/**
  * Represents the schema for query
  */

class UserSchema(tag: Tag) extends Table[(Int, String)](tag, "users") {
  def id = column[Int]("id")
  def username = column[String]("username")
  def * = (id, username)
}
