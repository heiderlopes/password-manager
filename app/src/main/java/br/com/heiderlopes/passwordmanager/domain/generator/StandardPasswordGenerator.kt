package br.com.heiderlopes.passwordmanager.domain.generator

class StandardPasswordGenerator(
    private val includeUppercase: Boolean,
    private val includeLowercase: Boolean,
    private val includeNumbers: Boolean,
    private val includeSymbols: Boolean
) : PasswordGenerator {
    override fun generate(length: Int): String {
        val chars = buildList<Char> {
            if (includeUppercase) addAll('A'..'Z')
            if (includeLowercase) addAll('a'..'z')
            if (includeNumbers) addAll('0'..'9')
            if (includeSymbols)
                addAll("!@#\$%&*()_-+=<>?".toList())

        }
        if (chars.isEmpty()) return ""
        return (1..length)
            .map { chars.random() }
            .joinToString("")
    }
}