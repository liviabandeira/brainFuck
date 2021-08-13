package com.livia.brainFuck

import java.util.Stack
import java.util.EmptyStackException
import kotlin.collections.HashMap
import kotlin.system.exitProcess

fun brainfuckInterpreter(brainInstruction: String) {
    val jumps = jumps(brainInstruction)
    val mem = MutableList(30000, init = { 0 })
    var pc = 0
    var cursor = 0

    while (pc < brainInstruction.length) {
        when (brainInstruction[pc]) {
            '+' -> mem[cursor] = (mem[cursor] + 1) % 255
            '-' -> mem[cursor] = (mem[cursor] - 1) % 255
            '>' -> cursor++
            '<' -> cursor--
            '[' -> {
                if (mem[cursor] == 0) {
                    pc = jumps[pc] as Int
                }
            }
            ']' -> {
                pc = jumps[pc] as Int
                pc--
            }
            '.' -> print("${mem[cursor].toChar()}")
            ',' -> mem[cursor] = System.`in`.read()
        }

        pc++
    }
}

private fun jumps(brainInstruction: String): Map<Int, Int> {
    val stack = Stack<Int>()
    val jumpsMap = HashMap<Int, Int>()
    for ((i, c) in brainInstruction.withIndex()) {
        when (c) {
            '[' -> stack.push(i)
            ']' -> {
                try {
                    val other = stack.pop()
                    jumpsMap[other] = i
                    jumpsMap[i] = other
                } catch (e: EmptyStackException) {
                    println("Program has unmatched brackets.")
                    exitProcess(1)
                }
            }
        }
    }

    if (!stack.isEmpty()) {
        throw ExceptionInInitializerError("Program has unmatched brackets.")
    }
    return jumpsMap
}