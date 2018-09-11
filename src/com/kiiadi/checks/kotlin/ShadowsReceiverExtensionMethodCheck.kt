package com.kiiadi.checks.kotlin

import com.puppycrawl.tools.checkstyle.api.DetailAST
import com.puppycrawl.tools.checkstyle.api.TokenTypes

class ShadowsReceiverExtensionMethodCheck : AbstractInvalidIdentifiersCheck(setOf("apply", "run"), "kotlin.shadows.extension.method") {
    override fun getRequiredTokens() = intArrayOf(TokenTypes.METHOD_DEF)

    override fun visitToken(ast: DetailAST?) {
        val parameterCount = ast?.findFirstToken(TokenTypes.PARAMETERS)?.getChildCount(TokenTypes.PARAMETER_DEF) ?: return
        if (parameterCount > 1) return
        super.visitToken(ast)
    }
}