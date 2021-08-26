package com.larissa.integrativeproject

import org.junit.Test

import org.junit.Assert.*

/**
 * Unit test, which will format data from API requisition.
 */
class MovieDetailsResponseTest {

    @Test
    fun `when the API requisition returns a Double and we want it in percentage `(){
        val result = voteAverageFormatted(8.5)
        assertEquals("85%", result)
    }

    @Test
    fun ` when the API requisition returns a date and we want only the year`(){
        val result = releaseDateFormatted("1991-11-29")
        assertEquals("1991", result)
    }

    @Test
    fun `when the API requisition returns hours in minutes and we want in hours`(){
        val result = runtimeFormatted(210)
        assertEquals("3h 30min", result)
    }

    private fun voteAverageFormatted(voteAverage: Double) : String = "${"%.0f".format((voteAverage* 10.0))}%"

    private fun releaseDateFormatted(releaseDate: String) : String = releaseDate.take(4)

    private fun runtimeFormatted(runtime: Int): String = "%dh %dmin".format(runtime / 60, runtime % 60)
}