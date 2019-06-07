package jimmy.project.repositories

import com.typesafe.config.ConfigFactory
import jimmy.project.DbMigrations
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.{BeforeAndAfterAll, Matchers, WordSpec}

import scala.concurrent.ExecutionContext.Implicits._

class PsqlSlickRepositorySpec extends WordSpec with Matchers
  with ScalaFutures with PsqlSlickRepositoryFixture with BeforeAndAfterAll {

  override def beforeAll {
    val migrationsDone = DbMigrations.performMigrations(config)
    println(s"No. of dbmigration: $migrationsDone")
  }

  val psqlSlickRepository = new PsqlSlickRepository(db)

  "The repository" should {
    "get a list of users" in {
      psqlSlickRepository.getAllUsers.futureValue shouldBe Seq.empty
    }
  }

}

trait PsqlSlickRepositoryFixture {
  import slick.jdbc.PostgresProfile.api._
  val config = ConfigFactory.load()
  val db = Database.forURL(config.getString("h2mem1.jdbcUrl"))

}
