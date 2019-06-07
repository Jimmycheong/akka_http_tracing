package jimmy.project

import java.util.Properties

import com.typesafe.config.Config
import com.typesafe.scalalogging.LazyLogging
import org.flywaydb.core.Flyway
import org.flywaydb.core.api.{MigrationInfo, MigrationInfoService}
import slick.jdbc.DriverDataSource

object DbMigrations extends LazyLogging {

  def performMigrations(dbConfig: Config): Int = {
    logger.info("Performing dbmigration")

    // MAKE SURE TO ACTUALLY CREATE TWO FOLDERS INSTEAD OF ONE called db.migration

    val flyway: Flyway =
      Flyway
        .configure
//          .dataSource("jdbc:postgresql://localhost/testdb", "tom", "tom")
          .dataSource(new DriverDataSource(
        url = "jdbc:postgresql://localhost/testdb",
        user = "tom",
        password = "tom",
        driverClassName = "org.postgresql.Driver"
      ))
//        .dataSource("jdbc:h2:~/testdb;DB_CLOSE_DELAY=-1;DATABASE_TO_LOWER=TRUE;MODE=PostgreSQL", "","")
//        .dataSource(dataSource)/
//        .locations("/src/main/resources/db/migration")
        .load

    val infoService: MigrationInfoService = flyway.info()

    println(s"Number applied: ${infoService.applied().length}")
    println(s"Number pending: ${infoService.pending().length}")
    println(s"Number all: ${infoService.all().length}")

    infoService.all().foreach { migration: MigrationInfo =>
      println(s"Migration: ${migration.getDescription}")
    }


    val numberOfMigrations = flyway.migrate() // perform dbmigration

    println(s"Number applied: ${infoService.applied().length}")
    println(s"Number all: ${infoService.all().length}")

    logger.info(s"Completed dbmigration")
    numberOfMigrations
  }

}