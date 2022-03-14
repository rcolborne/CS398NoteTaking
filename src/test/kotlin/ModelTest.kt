 import kotlinx.serialization.encodeToString
 import kotlinx.serialization.json.Json
 import org.junit.Assert.assertEquals
 import java.io.File
 //import org.junit.Test
 import kotlin.test.Test
 import kotlin.test.assertEquals

  internal class ModelTest {
     
      @Test
       fun testModel() {
          val testModel : Model = Model()
          val note1 = Note()
          testModel.save(note1)
          //note1.content.add(TextContent(12, "Test", "Test"))
          //assertEquals(expected,  notebook.title)
       }

      @Test
      fun testModel2() {
          val testModel : Model = Model()
          val expected = ""
          val note1 = Note()
          testModel.save(note1)
          testModel.loadNotes("./")
          assertEquals(expected, testModel.notes[0].title)
      }

      @Test
      fun testModel3() {
          val testModel : Model = Model()
          val expected = "Note Title"
          val note1 = Note("Note Title", mutableListOf(),  mutableListOf())
          testModel.save(note1)
          testModel.loadNotes("./")
          //note1.content.add(TextContent(12, "Test", "Test"))
          assertEquals(expected, testModel.notes[0].title)
      }

      @Test
      fun testModel4() {
          val testModel : Model = Model()
          val expected = "Note Title"
          val expectedtag1 = "tag1"
          val expectedtag2 = "tag2"
          val note1 = Note("Note Title", mutableListOf("tag1", "tag2"),  mutableListOf())
          testModel.save(note1)
          testModel.loadNotes("./")
          //note1.content.add(TextContent(12, "Test", "Test"))
          assertEquals(expectedtag1, testModel.notes[0].tags?.get(0))
          assertEquals(expectedtag2, testModel.notes[0].tags?.get(1))
      }

      @Test
      fun testModel5() {
          val testModel : Model = Model()
          val expectedFontSize = 12
          val expectedFont = "Arial"
          val expectedText = "This is a Test Note"
          var textContent = TextContent(12, "Arial", "This is a Test Note")
          val note1 = Note("Note Title", mutableListOf("tag1", "tag2"),  mutableListOf(textContent))
          testModel.save(note1)
          testModel.loadNotes("./")
          //note1.content.add(TextContent(12, "Test", "Test"))
          assertEquals(expectedFontSize, testModel.notes[0].content?.get(0)?.fontSize)
          assertEquals(expectedFont, testModel.notes[0].content?.get(0)?.font)
          assertEquals(expectedText, testModel.notes[0].content?.get(0)?.text)
      }


      @Test
      fun testModel6() {
          val testModel : Model = Model()
          val expected = true
          val note1 = Note("Note Title", mutableListOf("tag1", "tag2"),  mutableListOf())
          testModel.save(note1)
          testModel.loadNotes("./")
          val result = testModel.search("tag1").get(0).tags?.contains("tag1")
          assertEquals(expected, result)
      }

 }
