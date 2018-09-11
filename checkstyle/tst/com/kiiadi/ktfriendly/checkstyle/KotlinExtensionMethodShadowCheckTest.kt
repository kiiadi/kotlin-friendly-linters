package com.kiiadi.ktfriendly.checkstyle

import org.junit.Test

class KotlinExtensionMethodShadowCheckTest : AbstractTest() {

    @Test
    fun invalidExtensionMethodShadowing() {
        val config = createModuleConfig(KotlinExtensionMethodShadowCheck::class.java)

        val expected = arrayOf(
            expectation("7:5", "apply"),
            expectation("11:5", "run")
        )

        verify(config, getFile("ShadowsReceiverExtensionMethods.java"), *expected)
    }

    private fun expectation(position: String, method: String) = "$position: Method \"$method\" shadows a Kotlin receiver-based extension method"

}