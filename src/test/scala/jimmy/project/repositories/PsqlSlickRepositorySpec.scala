package jimmy.project.repositories

import com.typesafe.config.ConfigFactory
import com.typesafe.scalalogging.StrictLogging
import jimmy.project.DbMigrations
import jimmy.project.Models.User
import org.scalatest.concurrent.{PatienceConfiguration, ScalaFutures}
import org.scalatest.time._
import org.scalatest.{BeforeAndAfterAll, Matchers, WordSpec}

import scala.concurrent.ExecutionContext.Implicits._

class PsqlSlickRepositorySpec extends WordSpec with Matchers
  with ScalaFutures
  with PsqlSlickRepositoryFixture
  with BeforeAndAfterAll
  with PatienceConfiguration
  with StrictLogging
{
  implicit override val patienceConfig =
    PatienceConfig(timeout = scaled(Span(2, Seconds)), interval = scaled(Span(5, Millis)))

  override def beforeAll {
    val migrationsDone =
      DbMigrations.performMigrations(config, Seq("/db/migrations", migrationTestScripts))
    println(s"No. of total db migrations: $migrationsDone")
  }

  val psqlSlickRepository = new PsqlSlickRepository(db)

  "The repository" should {
    "get a list of users" in {
      psqlSlickRepository.getAllUsers.futureValue shouldBe Seq(
        User(1, "john"),
        User(2, "mary")
      )
    }
  }

}

trait PsqlSlickRepositoryFixture {
  import slick.jdbc.PostgresProfile.api._
  val config = ConfigFactory.load()

  val db = Database.forURL(
    url = config.getString("psqldb.jdbcUrl"),
    user = config.getString("psqldb.properties.user"),
    password = config.getString("psqldb.properties.password")
  )

  val migrationTestScripts = config.getString("psqldb.migrationsFolder")
}
