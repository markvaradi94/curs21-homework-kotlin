package ro.fasttrackit.curs21homeworkkotlin.services

import org.springframework.stereotype.Service
import ro.fasttrackit.curs21homeworkkotlin.domain.Country
import ro.fasttrackit.curs21homeworkkotlin.repositories.CountryRepository

@Service
class CountryService(val countryRepository: CountryRepository) : CrudService<Country, Long> {

    fun allCountries(): List<Country> {
        val countries = mutableListOf<Country>()
        countryRepository.findAll().forEach(countries::add)
        return countries.toList()
    }

    fun countryNames(): List<String> = allCountries().map { it.name }

    fun countryCapital(countryName: String) = allCountries()
            .find { it == countryRepository.findByName(countryName) }?.capital

    fun countryPopulation(countryName: String) = allCountries()
            .find { it == countryRepository.findByName(countryName) }?.population

    fun countriesFromContinent(continent: String) = allCountries().filter { isFromContinent(it, continent) }

    fun countryNeighbours(countryName: String) = allCountries()
            .find { it == countryRepository.findByName(countryName) }?.neighbours

    fun countriesFromContinentWithMinimumPopulation(continent: String, minPopulation: Long) = allCountries()
            .filter { isFromContinent(it, continent) }
            .filter { it.population >= minPopulation }

    fun countriesByNeighbours(neighbouring: String, avoiding: String) = allCountries()
            .filter { it.neighbours.contains(neighbouring, ignoreCase = true) }
            .filter { !it.neighbours.contains(avoiding, ignoreCase = true) }

    fun countryNamesToPopulation() = allCountries().associate { it.name to it.population }

    fun continentsToCountries() = allCountries().groupBy { it.continent }

    override fun findAll(): Set<Country> {
        val countries = mutableListOf<Country>()
        countryRepository.findAll().forEach(countries::add)
        return countries.toSet()
    }

    override fun save(obj: Country) = countryRepository.save(obj)

    override fun findById(id: Long): Country = countryRepository.findById(id).orElse(null)

    override fun delete(obj: Country) = countryRepository.delete(obj)

    override fun deleteById(id: Long) = countryRepository.deleteById(id)

    private fun isFromContinent(country: Country, continent: String) = country.continent.equals(continent, ignoreCase = true)
}