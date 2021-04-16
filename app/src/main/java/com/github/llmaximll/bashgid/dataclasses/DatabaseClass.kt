package com.github.llmaximll.bashgid.dataclasses

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DatabaseClass(@PrimaryKey var titleObject: String = "Null",
                         var favorites: Boolean = false)