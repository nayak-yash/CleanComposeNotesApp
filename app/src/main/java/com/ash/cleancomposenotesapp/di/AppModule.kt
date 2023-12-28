package com.ash.cleancomposenotesapp.di

import android.app.Application
import androidx.room.Room
import com.ash.cleancomposenotesapp.feature_note.data.data_source.NoteDatabase
import com.ash.cleancomposenotesapp.feature_note.data.repository.NoteRepositoryImpl
import com.ash.cleancomposenotesapp.feature_note.domain.repository.NoteRepository
import com.ash.cleancomposenotesapp.feature_note.domain.use_case.AddNote
import com.ash.cleancomposenotesapp.feature_note.domain.use_case.DeleteNote
import com.ash.cleancomposenotesapp.feature_note.domain.use_case.GetNote
import com.ash.cleancomposenotesapp.feature_note.domain.use_case.GetNotes
import com.ash.cleancomposenotesapp.feature_note.domain.use_case.NoteUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNoteDatabase(app: Application): NoteDatabase {
        return Room.databaseBuilder(
            app,
            NoteDatabase::class.java,
            NoteDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideNoteRepository(noteDatabase: NoteDatabase): NoteRepository {
        return NoteRepositoryImpl(noteDatabase.noteDao)
    }

    @Provides
    @Singleton
    fun provideNoteUseCase(noteRepository: NoteRepository): NoteUseCase {
        return NoteUseCase(
            getNotes = GetNotes(noteRepository),
            deleteNote = DeleteNote(noteRepository),
            addNote = AddNote(noteRepository),
            getNote = GetNote(noteRepository)
        )
    }
}