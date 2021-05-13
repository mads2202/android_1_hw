package com.mads2202.android_1_hw.services

import android.os.Build
import androidx.annotation.RequiresApi
import java.math.BigDecimal
import java.math.RoundingMode

class CalculatorService(private var calculations: String) {
    private var numbers = mutableListOf<BigDecimal>()
    private var signs = mutableListOf<Char>()
    private val ERROR_MASSAGE = "Please enter correct expression"
    private val signsArray = arrayOf('.', '-', '+', '-', '*', '/')


    fun startCalculations(): String {
        if (checkUserInput()) {
            parseString()
            var result: BigDecimal = BigDecimal(0.0)

            var sIterator = signs.iterator()
            var nIterator = numbers.listIterator()
            while (sIterator.hasNext() && nIterator.hasNext()) {
                val sign = sIterator.next()
                val number = nIterator.next()
                val number1 = numbers[nIterator.nextIndex()]
                result.setScale(if (number.scale() < number1.scale()) number1.scale() else number.scale(), RoundingMode.HALF_UP)
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
                result.setScale(if (number.scale() < number1.scale()) number1.scale() else number.scale(), RoundingMode.HALF_UP)
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

            return calculations.plus(numbers[0].toString())
        } else {
            return ERROR_MASSAGE
        }
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
                numbers.add(BigDecimal(subString))
                if (char.value != '=')
                    signs.add(char.value)
            }
        }
    }

    private fun checkUserInput(): Boolean {
        if (calculations.isNotEmpty()) {
            while (!startWithNumber() || !endsWithNumber() || hasDoubleSigns()) {
                if (!startWithNumber()) {
                    calculations = calculations.drop(1)
                }
                if (!endsWithNumber()) {
                    calculations = calculations.dropLast(1)
                }
                if (hasDoubleSigns()) {
                    var charValue = ' '
                    var bufferString = ""
                    for (char in calculations.asSequence().withIndex()) {
                        if (signsArray.contains(char.value) && signsArray.contains(charValue)) {
                            bufferString = calculations.drop(char.index)
                            calculations = calculations.substring(0, char.index - 1).plus(bufferString)
                        } else charValue = char.value
                    }

                }


            }
            calculations = calculations.plus('=')
            return true
        } else return false
    }

    private fun startWithNumber(): Boolean {
        return (
                calculations.startsWith('0') || calculations.startsWith('1')
                        || calculations.startsWith('2') || calculations.startsWith('3')
                        || calculations.startsWith('4') || calculations.startsWith('5')
                        || calculations.startsWith('6') || calculations.startsWith('7')
                        || calculations.startsWith('8') || calculations.startsWith('9')
                )
    }

    private fun endsWithNumber(): Boolean {
        return (
                calculations.startsWith('0') || calculations.startsWith('1')
                        || calculations.startsWith('2') || calculations.startsWith('3')
                        || calculations.startsWith('4') || calculations.startsWith('5')
                        || calculations.startsWith('6') || calculations.startsWith('7')
                        || calculations.startsWith('8') || calculations.startsWith('9')
                )
    }

    private fun hasDoubleSigns(): Boolean {
        var charValue = ' '
        for (char in calculations.asSequence()) {
            if (signsArray.contains(char) && signsArray.contains(charValue))
                return true
            else charValue = char
        }
        return false
    }
}