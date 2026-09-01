package br.com.heiderlopes.passwordmanager.domain.generator

class PinPasswordGenerator : PasswordGenerator {
    override fun generate(length: Int): String {
        val digits = ('0'..'9')
        return (1..length)
            .map { digits.random() }
            .joinToString("")
    }
}