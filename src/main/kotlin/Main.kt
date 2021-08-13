import com.livia.brainFuck.brainfuckInterpreter
import java.io.File

const val VALID_CHARS = "+-<>[],."

fun main(args: Array<String>) {
    if (args.isEmpty()) {
        throw ExceptionInInitializerError("Please provide a program or file name as the first parameter")
    }

    var brainInstruction = args[0]

    val file = File(brainInstruction)
    if (file.isFile && file.canRead()) {
        brainInstruction = String(file.readBytes())
    }

    brainfuckInterpreter(brainInstruction.filter {
        when {
            VALID_CHARS.contains(it) -> {
                true
            }
            else -> {
                throw ExceptionInInitializerError("Please provide a program or file name as the first parameter")
            }
        }
    })
}