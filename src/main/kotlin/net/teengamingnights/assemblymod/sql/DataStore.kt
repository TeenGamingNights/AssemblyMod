package net.teengamingnights.assemblymod.sql

import org.jetbrains.exposed.sql.Table

interface DataStore {
    val instance: Table
}