package com.kiiadi.ktfriendly.checkstyle

import org.junit.Test

class KotlinHardKeywordsCheckTest : AbstractTest() {

    @Test
    fun invalidExtensionMethodShadowing() {
        val config = createModuleConfig(KotlinHardKeywordsCheck::class.java)

        val expected = arrayOf(
            expectation("7:5", "when"),
            expectation("8:5", "in")
        )

        verify(config, getFile("HasKotlinHardKeyWords.java"), *expected)
    }

    private fun expectation(position: String, identifier: String) = "$position: Identifier \"$identifier\" shadows a Kotlin hard key-word"

}