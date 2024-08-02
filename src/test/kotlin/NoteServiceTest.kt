import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class NoteServiceTest {
    @Before
    fun clearBeforeTest() {
        NoteService.clear()
    }

    @Test
    fun add() {
        val note = Note(title = "title 1", text = "text 1")
        val result = NoteService.add(note)
        assertEquals(1, result.noteId)
    }

    @Test
    fun createComment() {
        val note = Note(title = "title 1", text = "text 1")
        NoteService.add(note)
        val comment = Comment(message = "comment 1")
        val result = NoteService.createComment(1, comment)
        assertEquals(1, result.commentId)
    }

    @Test(expected = NoteNotFoundException::class)
    fun createComment_expected() {
        val comment = Comment(message = "comment 1")
        NoteService.createComment(2, comment)
    }

    @Test
    fun deleteNote() {
        val note = Note(title = "title 1", text = "text 1")
        NoteService.add(note)
        val result = NoteService.deleteNote(1)
        assertEquals(1, result)
    }

    @Test(expected = NoteNotFoundException::class)
    fun deleteNote_expected() {
        NoteService.deleteNote(2)
    }

    @Test
    fun deleteComment() {
        val note = Note(title = "title 1", text = "text 1")
        val comment = Comment(message = "comment 1")
        NoteService.add(note)
        NoteService.createComment(1, comment)
        val result = NoteService.deleteComment(1, 1)
        assertEquals(1, result)
    }

    @Test(expected = NoteNotFoundException::class)
    fun deleteComment_expected() {
        NoteService.deleteComment(1, 1)
    }

    @Test
    fun editNote() {
        val note = Note(title = "title 1", text = "text 1")
        NoteService.add(note)
        val newNote = Note(1, title = "new title", text = "new text")
        val result = NoteService.editNote(newNote)
        assertEquals(1, result)
    }

    @Test(expected = NoteNotFoundException::class)
    fun editNote_expected() {
        val note = Note(title = "title 1", text = "text 1")
        NoteService.add(note)
        val newNote = Note(2, title = "new title", text = "new text")
        NoteService.editNote(newNote)
    }

    @Test
    fun editComment() {
        val note = Note(title = "title 1", text = "text 1")
        NoteService.add(note)
        val comment = Comment(message = "comment 1")
        NoteService.createComment(1, comment)
        val newComment = Comment(message = "new comment")
        val result = NoteService.editComment(1, 1, newComment)
        assertEquals(1, result)
    }

    @Test(expected = NoteNotFoundException::class)
    fun editComment_expected() {
        val newComment = Comment(message = "new comment")
        NoteService.editComment(1, 1, newComment)
    }

    @Test
    fun get() {
        val note1 = Note(1, title = "title 1", text = "text 1")
        val note2 = Note(2, title = "title 2", text = "text 2")
        NoteService.add(note1)
        NoteService.add(note2)
        val expected = listOf(note1, note2)
        val result = NoteService.get()
        assertEquals(expected, result)
    }

    @Test(expected = NoteNotFoundException::class)
    fun get_expected() {
        NoteService.get()
    }

    @Test
    fun getById() {
        val note = Note(1, title = "title 1", text = "text 1")
        NoteService.add(note)
        val result = NoteService.getById(1)
        assertEquals(note, result)
    }

    @Test(expected = NoteNotFoundException::class)
    fun getById_expected() {
        NoteService.getById(1)
    }

    @Test
    fun getComments() {
        val note = Note(title = "title 1", text = "text 1")
        val comment = Comment(1, message = "comment 1")
        NoteService.add(note)
        NoteService.createComment(1, comment)
        val expected = listOf(comment)
        val result = NoteService.getComments(1)
        assertEquals(expected, result)
    }

    @Test(expected = NoteNotFoundException::class)
    fun getComments_expected() {
        NoteService.getComments(1)
    }

    @Test
    fun restoreComment() {
        val note = Note(title = "title 1", text = "text 1")
        val comment = Comment(message = "comment 1", delete = true)
        NoteService.add(note)
        NoteService.createComment(1, comment)
        val result = NoteService.restoreComment(1, 1)
        assertEquals(1, result)
    }

    @Test(expected = NoteNotFoundException::class)
    fun restoreComment_expected() {
        val note = Note(title = "title 1", text = "text 1")
        val comment = Comment(message = "comment 1")
        NoteService.add(note)
        NoteService.createComment(1, comment)
        NoteService.restoreComment(1, 1)
    }
}