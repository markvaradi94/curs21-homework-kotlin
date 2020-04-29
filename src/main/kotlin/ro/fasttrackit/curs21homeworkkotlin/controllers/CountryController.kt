package ro.fasttrackit.curs21homeworkkotlin.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import ro.fasttrackit.curs21homeworkkotlin.domain.Country
import ro.fasttrackit.curs21homeworkkotlin.services.CountryService

@RestController
class CountryController(val countryService: CountryService) {

    @GetMapping("/countries")
    fun countriesByNeighbours(@RequestParam(required = false) neighbouring: String?,
                              @RequestParam(required = false) avoiding: String?): List<Country> {
        return if (neighbouring == null || avoiding == null) {
            countryService.allCountries()
        } else {
            countryService.countriesByNeighbours(neighbouring, avoiding)
        }
    }

    @GetMapping("/countries/names")
    fun countryNames() = countryService.countryNames()

    @GetMapping("/countries/{id}/capital")
    fun countryCapital(@PathVariable id: Long) = countryService.countryCapital(countryService.findById(id).name)

    @GetMapping("/countries/{id}/population")
    fun countryPopulation(@PathVariable id: Long) = countryService.countryPopulation(countryService.findById(id).name)

    @GetMapping("/continents/{continentName}/countries")
    fun continentCountriesWithMinimumPopulation(@PathVariable continentName: String,
                                                @RequestParam(required = false) minPopulation: Long?): List<Country> {
        return if (minPopulation == null) {
            countryService.countriesFromContinent(continentName)
        } else {
            countryService.countriesFromContinentWithMinimumPopulation(continentName, minPopulation)
        }
    }

    @GetMapping("/countries/{id}/neighbours")
    fun countryNeighbours(@PathVariable id: Long) = countryService.countryNeighbours(countryService.findById(id).name)

    @GetMapping("/countries/population")
    fun countryNamesToPopulation() = countryService.countryNamesToPopulation()

    @GetMapping("/continents/countries")
    fun continentsToCountries() = countryService.continentsToCountries()
}