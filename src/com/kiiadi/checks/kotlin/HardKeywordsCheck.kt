package com.kiiadi.checks.kotlin

import com.puppycrawl.tools.checkstyle.StatelessCheck
import com.puppycrawl.tools.checkstyle.api.AbstractCheck
import com.puppycrawl.tools.checkstyle.api.DetailAST
import com.puppycrawl.tools.checkstyle.api.TokenTypes
import com.puppycrawl.tools.checkstyle.utils.AnnotationUtil

/**
 * Checks that public method and member names do not shadow a Kotlin hard keyword.
 * https://kotlinlang.org/docs/reference/keyword-reference.html
 */

@StatelessCheck
class HardKeywordsCheck : RequiredTokenCheck() {
    override fun getRequiredTokens() = intArrayOf(TokenTypes.METHOD_DEF, TokenTypes.VARIABLE_DEF)

    override fun visitToken(ast: DetailAST?) {
        super.visitToken(ast)
        ast ?: return
        val parentClass = ast.parent?.parent ?: return

        if (!parentClass.isExternallyAccessible) {
            return
        }

        if (ast.isExternallyAccessible || ast.parent?.parent?.type == TokenTypes.INTERFACE_DEF) {
            when (ast.type) {
                TokenTypes.METHOD_DEF -> checkMethod(ast)
                TokenTypes.VARIABLE_DEF -> checkVariable(ast)
            }
        }
    }

    private fun checkMethod(method: DetailAST) {
        if (AnnotationUtil.containsAnnotation(method, OVERRIDE) || AnnotationUtil.containsAnnotation(method, CANONICAL_OVERRIDE)) {
            return
        }
        checkName(method)
    }

    private fun checkName(ast: DetailAST) {
        val name = ast.findFirstToken(TokenTypes.IDENT).text
        if (name in HARD_KEYWORDS) {
            log(ast, "Identifier \"{0}\" shadows a Kotlin hard key-word", name)
        }
    }

    private fun checkVariable(variable: DetailAST) {
        checkName(variable)
    }

    private companion object {
        private const val OVERRIDE = "Override"
        private const val CANONICAL_OVERRIDE = "java.lang.$OVERRIDE"
        private val HARD_KEYWORDS = setOf("is", "as", "object", "typealias", "val", "var", "when", "in")

        val DetailAST.isExternallyAccessible: Boolean
            get() {
                val modifiers = findFirstToken(TokenTypes.MODIFIERS)
                return modifiers.findFirstToken(TokenTypes.LITERAL_PUBLIC) != null || modifiers.findFirstToken(TokenTypes.LITERAL_PROTECTED) != null
            }
    }
}

abstract class RequiredTokenCheck : AbstractCheck() {
    final override fun getAcceptableTokens(): IntArray = requiredTokens
    final override fun getDefaultTokens(): IntArray = requiredTokens
}