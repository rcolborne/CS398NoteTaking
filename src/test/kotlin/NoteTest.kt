import org.junit.Test
//import kotlin.test.Test
//import kotlin.test.assertEquals

 internal class NoteTest {
     private var testNote : Note = Note()
     
      @Test
       fun testNote1() {
            val expected = "{}"
            //assertEquals(expected, Json.encodeToString(testNote))
       }

     @Test
       fun testNote2() {
         //testNote = Note("Test Model.Note", ["tag1", "tag2"], [], "30/05/1978 02:34:56")
         val expected = "{\"title\": \"example glossary\", \"tags\": [\"tag1\", \"tag2\"], \"content\": [], \"lastModified \": \"30/05/1978 02:34:56\"}"
         //assertEquals(expected, Json.encodeToString(testNote))
       }



     @Test
       fun testNote3() {
            //testNote = Note("Test Model.Note", ["tag1", "tag2"], [Content()], "30/05/1978 02:34:56")
            val expected = "{\"title\": \"example glossary\", \"tags\": [\"tag1\", \"tag2\"],\"content\": [\"content\": \"\"], \"lastModified \": \"30/05/1978 02:34:56\"}"
            //assertEquals(expected, Json.encodeToString(testNote))
       }

 }
