package com.kiiadi.ktfriendly.checkstyle

import com.puppycrawl.tools.checkstyle.api.AbstractCheck
import com.puppycrawl.tools.checkstyle.api.DetailAST
import com.puppycrawl.tools.checkstyle.api.TokenTypes
import com.puppycrawl.tools.checkstyle.utils.AnnotationUtil

abstract class AbstractInvalidIdentifiersCheck(private val invalidIdentifiers: Set<String>, private val message: String) : AbstractCheck() {
    override fun getRequiredTokens(): IntArray = intArrayOf(TokenTypes.METHOD_DEF, TokenTypes.VARIABLE_DEF)
    final override fun getAcceptableTokens(): IntArray = requiredTokens
    final override fun getDefaultTokens(): IntArray = requiredTokens

    override fun visitToken(ast: DetailAST?) {
        super.visitToken(ast)
        val parentClass = ast?.parent?.parent ?: return

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
        if (AnnotationUtil.containsAnnotation(method, OVERRIDE) || AnnotationUtil.containsAnnotation(
                method,
                CANONICAL_OVERRIDE
            )) {
            return
        }
        checkName(method)
    }

    private fun checkName(ast: DetailAST) {
        val name = ast.findFirstToken(TokenTypes.IDENT).text
        if (name in invalidIdentifiers) {
            log(ast, message, name)
        }
    }

    private fun checkVariable(variable: DetailAST) {
        checkName(variable)
    }

    private companion object {
        private const val OVERRIDE = "Override"
        private const val CANONICAL_OVERRIDE = "java.lang.$OVERRIDE"

        val DetailAST.isExternallyAccessible: Boolean
            get() {
                val modifiers = findFirstToken(TokenTypes.MODIFIERS)
                return modifiers.findFirstToken(TokenTypes.LITERAL_PUBLIC) != null || modifiers.findFirstToken(TokenTypes.LITERAL_PROTECTED) != null
            }
    }
}