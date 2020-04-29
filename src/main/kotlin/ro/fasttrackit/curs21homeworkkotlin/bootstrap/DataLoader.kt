package ro.fasttrackit.curs21homeworkkotlin.bootstrap

import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import ro.fasttrackit.curs21homeworkkotlin.domain.CountryReader
import ro.fasttrackit.curs21homeworkkotlin.services.CountryService
import java.io.File

@Component
class DataLoader(val countryService: CountryService) : CommandLineRunner {

    override fun run(vararg args: String?) {
        val count = countryService.findAll().size
        if (count == 0) loadData()
    }

    fun loadData() {
        val countryFile = File("src\\main\\resources\\countries2.txt")
        val reader = CountryReader()
        val countries = reader.readCountries(countryFile)
        countries.forEach { country -> countryService.save(country) }

        println("Loaded Countries ...")
    }
}