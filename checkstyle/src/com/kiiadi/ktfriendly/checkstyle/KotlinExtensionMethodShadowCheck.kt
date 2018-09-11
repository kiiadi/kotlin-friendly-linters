package com.kiiadi.ktfriendly.checkstyle

import com.puppycrawl.tools.checkstyle.StatelessCheck
import com.puppycrawl.tools.checkstyle.api.DetailAST
import com.puppycrawl.tools.checkstyle.api.TokenTypes

/**
 * Checks that single parameter methods don't shadow Kotlin receiver-based extension methods (e.g. apply).
 */
@StatelessCheck
class KotlinExtensionMethodShadowCheck : AbstractInvalidIdentifiersCheck(setOf("apply", "run"), "kotlin.shadows.extension.method") {
    override fun getRequiredTokens() = intArrayOf(TokenTypes.METHOD_DEF)

    override fun visitToken(ast: DetailAST?) {
        val parameterCount = ast?.findFirstToken(TokenTypes.PARAMETERS)?.getChildCount(TokenTypes.PARAMETER_DEF) ?: return
        if (parameterCount > 1) return
        super.visitToken(ast)
    }
}