package com.example.hesapmakinesikotlin

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import com.example.hesapmakinesikotlin.databinding.ActivityMainBinding
import java.lang.StringBuilder
import java.util.Stack

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var myStringInput : ArrayList<String>

    private var text : String? = null
    private var operationChar :String = ""
    private var oldOperationChar :String = ""

    private var result : Double? = null
    private var prevResult : Double? = null
    private var numberForConversion :Double = 0.0

    private val stackForHoldResult = Stack<Double>()
    private var isNecessaryPopFromStack = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
    }

    private fun init() {
        myStringInput = ArrayList<String>().apply {  }
    }

    fun clearAll_button_onClick(view : View){
        binding.transactionScreen.text = ""
        binding.resultScreen.text = ""
        myStringInput.clear()
        result = null
        stackForHoldResult.clear()
        isNecessaryPopFromStack = false
        operationChar = ""
        oldOperationChar = ""
        prevResult = null
        numberForConversion = 0.0
    }

    fun backspace_button_onClick(view: View) {
        // will uptade later
    }

    fun comma_button_onClick(view: View) {
        writeDigitOnTheTransactionScreen(",")
    }

    fun equal_button_onClick(view: View) {
        binding.transactionScreen.text = ""
        myStringInput.clear()
        result = null
        stackForHoldResult.clear()
        isNecessaryPopFromStack = false
        operationChar = ""
        oldOperationChar = ""
        prevResult = null
        numberForConversion
    }

    fun digitsOnClick(view :View){
        if(view.id == R.id.zero_button){
            writeDigitOnTheTransactionScreen("0")

            if(convertToDoubleNumber()!= 0.0){
                calculator(convertToDoubleNumber(),operationChar)
            } else if(convertToDoubleNumber() == 0.0 && operationChar == "/"){
                Toast.makeText(this,"Dividing by zero is wrong!",Toast.LENGTH_LONG).show()
            }

        }
        if(view.id == R.id.one_button){
            writeDigitOnTheTransactionScreen("1")

            calculator(convertToDoubleNumber(),operationChar)
        }
        if(view.id == R.id.two_button){
            writeDigitOnTheTransactionScreen("2")

            calculator(convertToDoubleNumber(),operationChar)
        }
        if(view.id == R.id.three_button){
            writeDigitOnTheTransactionScreen("3")

            calculator(convertToDoubleNumber(),operationChar)
        }
        if(view.id == R.id.four_button){
            writeDigitOnTheTransactionScreen("4")

            calculator(convertToDoubleNumber(),operationChar)
        }
        if(view.id == R.id.five_button){
            writeDigitOnTheTransactionScreen("5")

            calculator(convertToDoubleNumber(),operationChar)
        }
        if(view.id == R.id.six_button){
            writeDigitOnTheTransactionScreen("6")

            calculator(convertToDoubleNumber(),operationChar)
        }
        if(view.id == R.id.seven_button){
            writeDigitOnTheTransactionScreen("7")

            calculator(convertToDoubleNumber(),operationChar)
        }
        if(view.id == R.id.eight_button){
            writeDigitOnTheTransactionScreen("8")

            calculator(convertToDoubleNumber(),operationChar)
        }
        if(view.id == R.id.nine_button){
            writeDigitOnTheTransactionScreen("9")

            calculator(convertToDoubleNumber(),operationChar)
        }
    }

    fun operatorOnClick(view: View){
        if(view.id == R.id.plus_button){
            writeOperationOnTheTransactionScreen("+")
            operationChar = "+"
            oldOperationChar = "+"
            stackForHoldResult.push(result)
            myStringInput.clear()
        }
        if(view.id == R.id.minus_button){
            writeOperationOnTheTransactionScreen("-")
            operationChar = "-"
            oldOperationChar = "-"
            stackForHoldResult.push(result)
            myStringInput.clear()
        }
        if(view.id == R.id.multiply_button){
            writeOperationOnTheTransactionScreen("*")
            operationChar = "*"
            stackForHoldResult.push(result)
            isNecessaryPopFromStack = true
            myStringInput.clear()
        }
        if(view.id == R.id.divide_button){
            writeOperationOnTheTransactionScreen("/")
            operationChar = "/"
            stackForHoldResult.push(result)
            isNecessaryPopFromStack = true
            myStringInput.clear()
        }
    }

    fun mod_button_onClick(view: View) {
        //optional
    }

    private fun calculator(number : Double, operation : String){
        if(operation == "*"){
            if(oldOperationChar == ""){
                result = stackForHoldResult.peek() * number
                writeResultOnTheResultScreen()
            } else if(oldOperationChar == "+"){
                if(isNecessaryPopFromStack){
                    prevResult = stackForHoldResult.peek()
                    stackForHoldResult.pop()
                    result = stackForHoldResult.peek() + (prevResult!! - stackForHoldResult.peek()) * number
                    writeResultOnTheResultScreen()
                    isNecessaryPopFromStack = false
                } else{
                    result = stackForHoldResult.peek() +  (prevResult!! - stackForHoldResult.peek()) * number
                    writeResultOnTheResultScreen()
                }

            } else if(oldOperationChar == "-"){
                if(isNecessaryPopFromStack){
                    prevResult = stackForHoldResult.peek()
                    stackForHoldResult.pop()
                    result = stackForHoldResult.peek() - (stackForHoldResult.peek() - prevResult!!) * number
                    writeResultOnTheResultScreen()
                    isNecessaryPopFromStack = false
                } else{
                    result = stackForHoldResult.peek() -  (stackForHoldResult.peek() - prevResult!!) * number
                    writeResultOnTheResultScreen()
                }
            }

        } else if(operation == "/"){
            if(oldOperationChar == ""){
                result = stackForHoldResult.peek() / number
                writeResultOnTheResultScreen()
            } else if(oldOperationChar == "+"){
                if(isNecessaryPopFromStack){
                    prevResult = stackForHoldResult.peek()
                    stackForHoldResult.pop()
                    result = stackForHoldResult.peek() + (prevResult!! - stackForHoldResult.peek()) / number
                    writeResultOnTheResultScreen()
                    isNecessaryPopFromStack = false
                } else{
                    result = stackForHoldResult.peek() +  (prevResult!! - stackForHoldResult.peek()) / number
                    writeResultOnTheResultScreen()
                }

            } else if(oldOperationChar == "-"){
                if(isNecessaryPopFromStack){
                    prevResult = stackForHoldResult.peek()
                    stackForHoldResult.pop()
                    result = stackForHoldResult.peek() - (stackForHoldResult.peek() - prevResult!!) / number
                    writeResultOnTheResultScreen()
                    isNecessaryPopFromStack = false
                } else{
                    result = stackForHoldResult.peek() -  (stackForHoldResult.peek() - prevResult!!) / number
                    writeResultOnTheResultScreen()
                }
            }

        } else if(operation == "+"){
            result = stackForHoldResult.peek() + number
            writeResultOnTheResultScreen()

        } else if(operation == "-"){
            result = stackForHoldResult.peek() - number
            writeResultOnTheResultScreen()
        } else if(operation == ""){
            result = number
            stackForHoldResult.push(result)
            writeResultOnTheResultScreen()
        }
    }

    private fun writeDigitOnTheTransactionScreen(characterToBeWritten :String){
        if(characterToBeWritten == ","){
            if(binding.transactionScreen.text.isEmpty()){

                //do nothing
                return

            } else if(binding.transactionScreen.text.isNotEmpty() && binding.transactionScreen
                    .text[binding.transactionScreen
                    .text.length -1] == ','){

                //do nothing
                return

            } else if(binding.transactionScreen.text.isNotEmpty() && (binding.transactionScreen
                    .text[binding.transactionScreen
                    .text.length-1] == '+'
                        || binding.transactionScreen.text[binding.transactionScreen
                    .text.length-1] == '-'
                        || binding.transactionScreen.text[binding.transactionScreen
                    .text.length-1] == '*'
                        || binding.transactionScreen.text[binding.transactionScreen
                    .text.length-1] == '/')){

                //do nothing
                return
            } else if(myStringInput.contains(",")){
                //do nothing
                return

            } else {
                myStringInput.add(characterToBeWritten)
                text = binding.transactionScreen.text.toString()
                binding.transactionScreen.text = ""
                binding.transactionScreen.text = StringBuilder(text!!).append(characterToBeWritten).toString()
            }

        }  else{
            if(myStringInput.size == 1 && myStringInput[0] == "0"){
                myStringInput.clear()
                myStringInput.add(characterToBeWritten)
                text = binding.transactionScreen.text.toString().substring(0,binding.transactionScreen.text.length-1)
                binding.transactionScreen.text = ""
                binding.transactionScreen.text = StringBuilder(text!!).append(characterToBeWritten).toString()

                return
            }
            myStringInput.add(characterToBeWritten)
            text = binding.transactionScreen.text.toString()
            binding.transactionScreen.text = ""
            binding.transactionScreen.text = StringBuilder(text!!).append(characterToBeWritten).toString()
        }

    }

    private fun writeOperationOnTheTransactionScreen(operationToBeWritten :String){

        if(operationToBeWritten == "+" || operationToBeWritten == "*" || operationToBeWritten == "/"){

            if(binding.transactionScreen.text.isNotEmpty() && (binding.transactionScreen.text[binding.transactionScreen.text.length-1] == '0'
                        || binding.transactionScreen.text[binding.transactionScreen.text.length-1] == '1'
                        || binding.transactionScreen.text[binding.transactionScreen.text.length-1] == '2'
                        || binding.transactionScreen.text[binding.transactionScreen.text.length-1] == '3'
                        || binding.transactionScreen.text[binding.transactionScreen.text.length-1] == '4'
                        || binding.transactionScreen.text[binding.transactionScreen.text.length-1] == '5'
                        || binding.transactionScreen.text[binding.transactionScreen.text.length-1] == '6'
                        || binding.transactionScreen.text[binding.transactionScreen.text.length-1] == '7'
                        || binding.transactionScreen.text[binding.transactionScreen.text.length-1] == '8'
                        || binding.transactionScreen.text[binding.transactionScreen.text.length-1] == '9')){

                binding.transactionScreen.text = StringBuilder(binding.transactionScreen.text.toString()).append(operationToBeWritten).toString()

            }
        }  else if(operationToBeWritten == "-"){

            if(binding.transactionScreen.text.isEmpty()){
                binding.transactionScreen.text = "-"
                myStringInput.add(operationToBeWritten)
            }  else if(binding.transactionScreen.text.isNotEmpty() && (binding.transactionScreen.text[binding.transactionScreen.text.length-1] == '0'
                        || binding.transactionScreen.text[binding.transactionScreen.text.length-1] == '1'
                        || binding.transactionScreen.text[binding.transactionScreen.text.length-1] == '2'
                        || binding.transactionScreen.text[binding.transactionScreen.text.length-1] == '3'
                        || binding.transactionScreen.text[binding.transactionScreen.text.length-1] == '4'
                        || binding.transactionScreen.text[binding.transactionScreen.text.length-1] == '5'
                        || binding.transactionScreen.text[binding.transactionScreen.text.length-1] == '6'
                        || binding.transactionScreen.text[binding.transactionScreen.text.length-1] == '7'
                        || binding.transactionScreen.text[binding.transactionScreen.text.length-1] == '8'
                        || binding.transactionScreen.text[binding.transactionScreen.text.length-1] == '9')){

                binding.transactionScreen.text = StringBuilder(binding.transactionScreen.text.toString()).append(operationToBeWritten).toString()

            } else if(!myStringInput.contains("-")){
                binding.transactionScreen.text = StringBuilder(binding.transactionScreen.text.toString()).append(operationToBeWritten).toString()
                myStringInput.add(operationToBeWritten)
            }
        }
    }

    private fun writeResultOnTheResultScreen(){
        binding.resultScreen.text = result.toString()
    }

    private fun convertToDoubleNumber():Double{
        numberForConversion = myStringInput.joinToString("").replace(',','.').toDouble()
        return numberForConversion
    }


}