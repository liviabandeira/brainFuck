package com.livia.brainFuck

import org.junit.Test
import java.io.File


const val VALID_CHARS = "+-<>[],."

class InterpreterTest {

    @Test
    fun `Given an valid instruction brain fuck, should doing matchInstruction without error`() {

        var brainInstruction = "+-<>[],."

        val f = File(brainInstruction)
        if (f.isFile && f.canRead()) {
            brainInstruction = String(f.readBytes())
        }

        brainfuckInterpreter(brainInstruction)

    }

    @Test(expected = ExceptionInInitializerError::class)
    fun `Given an non valid instruction brain fuck, should doing matchInstruction with error`() {

        var brainInstruction = "123"

        val f = File(brainInstruction)
        if (f.isFile && f.canRead()) {
            brainInstruction = String(f.readBytes())
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
}