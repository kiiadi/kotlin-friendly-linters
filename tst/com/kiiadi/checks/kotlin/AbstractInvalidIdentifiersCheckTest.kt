package com.kiiadi.checks.kotlin

import org.junit.Test

class AbstractInvalidIdentifiersCheckTest : AbstractTest() {
    @Test
    fun invalidMethodName() {
        val config = createModuleConfig(TestInvalidKeywordCheck::class.java)

        val expected = arrayOf(
            expectation("4:5"),
            expectation("6:5"),
            expectation("10:5"),
            expectation("13:9"),
            expectation("16:5"),
            expectation("33:9"),
            expectation("35:9")
        )

        verify(config, getFile("HasInvalidIdentifiers.java"), *expected)
    }

    class TestInvalidKeywordCheck : AbstractInvalidIdentifiersCheck(setOf("invalid"), "Identifier \"{0}\" is invalid")

    private fun expectation(position: String) = "$position: Identifier \"invalid\" is invalid"
}