package kz.mukha.wallet.supabase

import org.jooq.DSLContext
import org.jooq.SQLDialect
import org.jooq.impl.DSL
import org.postgresql.core.ConnectionFactory
import org.postgresql.core.v3.ConnectionFactoryImpl
import org.postgresql.ds.PGSimpleDataSource
import java.sql.Connection
import java.sql.DriverManager


object DatabaseConnector {
    private const val JDBC_URL = ""
    private const val USERNAME = ""
    private const val PASSWORD = ""

    fun connect(): DSLContext {
        val connection: Connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD)

        return DSL.using(connection, SQLDialect.POSTGRES)
    }
}