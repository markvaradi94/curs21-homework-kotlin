package ro.fasttrackit.curs21homeworkkotlin.domain

import org.apache.logging.log4j.util.Strings
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.lang.StringBuilder
import java.util.stream.Collectors.toList
import javax.persistence.*

@Entity
@Table(name = "countries")
data class Country(@Column(name = "name") val name: String, @Column(name = "capital") val capital: String,
                   @Column(name = "population") val population: Long, @Column(name = "area") val area: Long,
                   @Column(name = "continent") val continent: String, @Column(name = "neighbours") val neighbours: String) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0
}

class CountryReader {

    fun readCountries(file: File): List<Country> {
        return BufferedReader(FileReader(file))
                .lines()
                .map(this::readCountry)
                .collect(toList())
    }

    private fun readCountry(line: String): Country {
        val elements = line.split("|")
        return Country(
                elements[0],
                elements[1],
                elements[2].toLong(),
                elements[3].toLong(),
                elements[4],
                if (elements.size > 5) readNeighbours(elements[5]) else Strings.EMPTY
        )
    }

    private fun readNeighbours(neighbours: String): String {
        val elements = neighbours.split("~")
        val result = StringBuilder(elements.toList().toString())
        result.replace(0, 1, "")
        result.replace(result.length - 1, result.length, "")
        return result.toString()
    }
}