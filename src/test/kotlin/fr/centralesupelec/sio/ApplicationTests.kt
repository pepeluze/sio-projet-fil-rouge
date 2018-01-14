package fr.centralesupelec.sio

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner

@RunWith(SpringJUnit4ClassRunner::class)
@SpringBootTest
class ApplicationTests {

    @Test
    fun contextLoads() {
    }

//    @Test
//    fun findAll() {
//        val content = """[{"title":"Lord of the Rings: The Return of the King","genres":"FANTASY","id":1},{"title":"Star Wars VIII: The Last Jedi","genres":"SCIENCE_FICTION","id":2},{"title":"Kingsman 2: The Golden Circle","genres":"ACTION","id":3}]"""
//        Assertions.assertEquals(content, restTemplate.getForObject<String>("/movies"))
//    }
}
