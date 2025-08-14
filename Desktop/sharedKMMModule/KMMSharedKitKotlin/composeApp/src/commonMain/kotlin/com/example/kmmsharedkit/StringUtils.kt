package com.example.kmmsharedkit

class StringUtils {
    fun capitalizeWords(input: String): String {
        return input.split(" ").joinToString(" ") { word ->
            word.replaceFirstChar {
                if (it.isLowerCase()) it.titlecase() else it.toString()
            }
        }
    }

    fun greet(name: String): String {
        return "Hello, ${capitalizeWords(name)}!"
    }

    fun repeatText(text: String, times: Int): String {
        return text.repeat(times)
    }

}