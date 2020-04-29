package ro.fasttrackit.curs21homeworkkotlin.repositories

import org.springframework.data.repository.CrudRepository
import ro.fasttrackit.curs21homeworkkotlin.domain.Country

interface CountryRepository : CrudRepository<Country, Long> {

    fun findByName(countryName: String): Country
}