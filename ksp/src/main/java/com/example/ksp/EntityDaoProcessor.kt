package com.example.ksp

import com.google.devtools.ksp.processing.Dependencies
import com.google.devtools.ksp.processing.Resolver
import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSFile
import com.google.devtools.ksp.validate

internal class EntityDaoProcessor(
    private val environment: SymbolProcessorEnvironment,
) : SymbolProcessor {

    override fun process(resolver: Resolver): List<KSAnnotated> {

        val entities = resolver
            .getSymbolsWithAnnotation("androidx.room.Entity")
            .filterIsInstance<KSClassDeclaration>()

        if(!entities.iterator().hasNext()) return emptyList()

        val imports = entities.mapNotNull { it.qualifiedName?.asString() }.toSet()
        val entityList = entities.map { it.simpleName.asString() }
        val sourceFiles = entities.mapNotNull { it.containingFile }

        val fileText = buildString {
            append("package com.example.scalecaching.common")

            newLine(2)
            imports.forEach {
                appendNewLine("import $it")
            }
            appendNewLine("import androidx.room.*")
            appendNewLine("import kotlinx.coroutines.flow.Flow")
            newLine()

            entityList.forEach {
                appendNewLine("@Dao")
                appendNewLine("interface ${it}Dao: BaseDao<$it> {")
                appendNewLine("\t@Query(\"SELECT * FROM $it\")")
                appendNewLine("\toverride fun getAll(): Flow<List<$it>>", 2)
                appendNewLine("\t@Query(\"DELETE FROM $it\")")
                appendNewLine("\toverride fun nukeTable()")
                appendNewLine("}", 2)
            }
        }

        createFileWithText(sourceFiles, fileText)
        return (entities).filterNot { it.validate() }.toList()
    }

    private fun createFileWithText(
        sourceFiles: Sequence<KSFile>,
        fileText: String,
    ) {
        val file = environment.codeGenerator.createNewFile(
            Dependencies(
                false,
                *sourceFiles.toList().toTypedArray(),
            ),
            "your.generated.file.package",
            "GeneratedLists"
        )

        file.write(fileText.toByteArray())
    }

    private fun StringBuilder.appendNewLine(txt: String, count: Int = 1) {
        append(txt)
        repeat(count){
            append("\n")
        }
    }
    private fun StringBuilder.newLine(count: Int = 1) {
        repeat(count){
            append("\n")
        }
    }
}
