package ro.fasttrackit.curs21homeworkkotlin.services

interface CrudService<T, ID> {

    fun findAll(): Set<T>

    fun save(obj: T): T

    fun findById(id: ID): T

    fun delete(obj: T)

    fun deleteById(id: ID)
}