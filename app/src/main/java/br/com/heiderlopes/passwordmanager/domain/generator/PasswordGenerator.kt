package br.com.heiderlopes.passwordmanager.domain.generator

interface PasswordGenerator {
    fun generate(length: Int): String
}