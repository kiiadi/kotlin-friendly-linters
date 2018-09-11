package com.kiiadi.checks.kotlin

import org.junit.Test

class HardKeywordsCheckTest : AbstractTest() {
    @Test
    fun invalidMethodName() {
        val config = createModuleConfig(HardKeywordsCheck::class.java)

        val expected = arrayOf(
            expectation("4:5", "val"),
            expectation("6:5", "in"),
            expectation("10:5", "var"),
            expectation("13:9", "as"),
            expectation("16:5", "typealias"),
            expectation("33:9", "in"),
            expectation("35:9", "var")
        )

        verify(config, getFile("HardKeywordsInIdentifiers.java"), *expected)
    }

    private fun expectation(position: String, identifier: String) = "$position: Identifier \"$identifier\" shadows a Kotlin hard key-word"
}