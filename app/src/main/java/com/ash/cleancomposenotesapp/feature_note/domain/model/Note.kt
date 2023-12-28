package com.ash.cleancomposenotesapp.feature_note.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ash.cleancomposenotesapp.ui.theme.BabyBlue
import com.ash.cleancomposenotesapp.ui.theme.LightGreen
import com.ash.cleancomposenotesapp.ui.theme.RedOrange
import com.ash.cleancomposenotesapp.ui.theme.RedPink
import com.ash.cleancomposenotesapp.ui.theme.Violet

@Entity
data class Note (
    val title: String,
    val content: String,
    val timeStamp: Long,
    val color: Int,
    @PrimaryKey val id: Int? = null
) {
    companion object {
        val noteColors = listOf(RedOrange, LightGreen, Violet, BabyBlue, RedPink)
    }
}

class InvalidNoteException(message: String): Exception(message) {

}