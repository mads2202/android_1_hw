package com.mads2202.android_1_hw.services

import android.os.Build
import androidx.annotation.RequiresApi

class CalculatorService(private var calculations: String) {
    private var numbers = mutableListOf<Double>()
    private var signs = mutableListOf<Char>()


    fun startCalculations(): String {
        checkUserInput()
        parseString()
        var result = 0.0

        var sIterator = signs.iterator()
        var nIterator = numbers.listIterator()
        while (sIterator.hasNext() && nIterator.hasNext()) {
            val sign = sIterator.next()
            val number = nIterator.next()
            val number1 = numbers[nIterator.nextIndex()]
            if (sign == '*') {
                result = number * number1
                numbers[numbers.indexOf(number)] = result
                if (nIterator.hasNext())
                    nIterator.next()
                nIterator.remove()
                if (nIterator.hasPrevious())
                    nIterator.previous()
                sIterator.remove()
            } else if (sign == '/') {
                result = number / number1
                numbers[numbers.indexOf(number)] = result
                if (nIterator.hasNext())
                    nIterator.next()
                nIterator.remove()
                if (nIterator.hasPrevious())
                    nIterator.previous()
                sIterator.remove()

            }
        }


        sIterator = signs.iterator()
        nIterator = numbers.listIterator()
        while (sIterator.hasNext() && nIterator.hasNext()) {
            val sign = sIterator.next()
            val number = nIterator.next()
            val number1 = numbers[nIterator.nextIndex()]
            if (sign == '+') {
                result = number + number1
                numbers[numbers.indexOf(number)] = result
                if (nIterator.hasNext())
                    nIterator.next()
                nIterator.remove()
                if (nIterator.hasPrevious())
                    nIterator.previous()
                sIterator.remove()
            } else if (sign == '-') {
                result = number - number1
                numbers[numbers.indexOf(number)] = result
                if (nIterator.hasNext())
                    nIterator.next()
                nIterator.remove()
                if (nIterator.hasPrevious())
                    nIterator.previous()
                sIterator.remove()

            }
        }

        return numbers[0].toString()
    }


    private fun parseString() {
        var index = 0
        var subString: String
        for (char in calculations.asSequence().withIndex()) {
            if (char.value == '*' || char.value == '/' || char.value == '+' || (char.value == '-' && char.index != 0) || char.value == '=') {
                subString = if (calculations[index] == '+' || (calculations[index] == '-' && index != 0) || calculations[index] == '*' || calculations[index] == '/') {
                    calculations.substring(index + 1, char.index)
                } else {
                    calculations.substring(index, char.index)
                }
                index = char.index
                numbers.add(subString.toDouble())
                if (char.value != '=')
                    signs.add(char.value)
            }
        }
    }

    private fun checkUserInput() {
        if (calculations[calculations.length - 1] == '/' || calculations[calculations.length - 1] == '*' ||
                calculations[calculations.length - 1] == '-' || calculations[calculations.length - 1] == '+'
                || calculations[calculations.length - 1] == '.') {
            calculations = calculations.plus("0")
        }
        if (calculations.startsWith('.') || calculations.startsWith('+') || calculations.startsWith('*') || calculations.startsWith('/')) {
            calculations.drop(0)
        }
        for (char in calculations.asSequence().withIndex()) {
            if (char.value == '.' && (calculations[char.index + 1] == '.')) {
                calculations.drop(char.index + 1)
            }
            if (char.value == '.' && (calculations[char.index + 1] == '+' || calculations[char.index + 1] == '-' || calculations[char.index + 1] == '*' || calculations[char.index + 1] == '/')) {
                calculations = calculations.substring(0, char.index + 1).plus(0).plus(calculations.substring(char.index + 1))
            }
        }
        calculations = calculations.plus('=')
    }

}