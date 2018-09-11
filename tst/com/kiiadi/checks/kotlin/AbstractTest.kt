package com.kiiadi.checks.kotlin

import com.puppycrawl.tools.checkstyle.AbstractModuleTestSupport
import java.io.File

abstract class AbstractTest : AbstractModuleTestSupport() {
    override fun getPackageLocation() = null

    fun getFile(name: String): String = File(javaClass.getResource(name).toURI()).canonicalPath
}