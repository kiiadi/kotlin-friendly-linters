package com.kiiadi.ktfriendly.checkstyle

import com.puppycrawl.tools.checkstyle.StatelessCheck

/**
 * Checks that public method and member names do not shadow a Kotlin hard keyword.
 * https://kotlinlang.org/docs/reference/keyword-reference.html
 */

@StatelessCheck
class KotlinHardKeywordsCheck : AbstractInvalidIdentifiersCheck(HARD_KEYWORDS, "kotlin.hard.keyword") {
    private companion object {
        private val HARD_KEYWORDS = setOf("is", "as", "object", "typealias", "val", "var", "when", "in")
    }
}