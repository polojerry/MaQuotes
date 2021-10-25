package com.pol0.local.typeconverters

import androidx.room.TypeConverter

class TagsConverter {

    @TypeConverter
    fun toString(tags: List<String>): String {
        val tagsString = ""
        return if (tags.isEmpty()) {
            tagsString
        } else {
            tags.forEach { tag ->
                tagsString + "${tag}*"
            }
            tagsString
        }
    }

    @TypeConverter
    fun toList(tagString: String): List<String> {
        val tag = mutableListOf<String>()
        return if (tag.isEmpty()) {
            tag
        } else {
            val tags = tagString.split("*")
            tags.forEach {
                tag.add(it)
            }
            tag
        }
    }
}