package com.example.basic_calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var textView2: TextView
    private var lastNumeric: Boolean = false
    private var lastDot: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView2 = findViewById(R.id.textView2)
    }

    fun onDigit(view: View) {
        textView2.append((view as Button).text)
        lastNumeric = true
    }

    fun onClear(view: View) {
        textView2.text = ""
        lastNumeric = false
        lastDot = false
    }

    fun onDecimal(view: View) {
        if (lastNumeric && !lastDot) {
            textView2.append(".")
            lastDot = true
            lastNumeric = false
        }
    }

    fun onOperator(view: View) {
      //  val firstNum: String = textView2.text.toString()
       // val operator: String = (view as Button).text.toString()
        //val firstNumInt: Int = firstNum.toInt()
       // textView2.append("   $firstNumInt ")
        if(lastNumeric && !isOperatorAdded(textView2.text.toString())){
            textView2.append(" "+(view as Button).text+" ")

            lastNumeric= false
            lastDot=false
        }
    }

    private fun isOperatorAdded(data:String) : Boolean{
        return if(data.startsWith("-")){
            false
        }else{
            data.contains("/")|| data.contains("*") || data.contains("-")|| data.contains("+")
        }
    }


    fun onEqual(view : View){
        if(lastNumeric){
            var input:String = textView2.text.toString()
            var prefix: String =""

            try{
                if(input.startsWith("-")){
                    prefix = "-"
                    input = input.substring(1)

                }


                 if(input.contains("-")) {

                    var splitString = input.split("-")

                    var digitOne = splitString[0]
                    var digitTwo = splitString[1]


                     if(!prefix.isEmpty()){
                         digitOne =  prefix+ digitOne
                     }
                    textView2.text = removeZeroAfterDot((digitOne.toDouble() - digitTwo.toDouble()).toString())

                }else if(input.contains("+")) {

                     var splitString = input.split("+")

                     var digitOne = splitString[0]
                     var digitTwo = splitString[1]


                     if(!prefix.isEmpty()){
                         digitOne =  prefix+ digitOne
                     }
                     textView2.text = removeZeroAfterDot((digitOne.toDouble() + digitTwo.toDouble()).toString())

                 }else if(input.contains("*")) {

                     var splitString = input.split("*")

                     var digitOne = splitString[0]
                     var digitTwo = splitString[1]


                     if(!prefix.isEmpty()){
                         digitOne =  prefix+ digitOne
                     }
                     textView2.text = removeZeroAfterDot((digitOne.toDouble() * digitTwo.toDouble()).toString())

                 }else if(input.contains("/"))  {

                     var splitString = input.split("/")

                     var digitOne = splitString[0]
                     var digitTwo = splitString[1]


                     if(!prefix.isEmpty()){
                         digitOne =  prefix+ digitOne
                     }
                     textView2.text = removeZeroAfterDot((digitOne.toDouble() / digitTwo.toDouble()).toString())

                 }else{
                     textView2.setText(input)
                 }

            }catch(e: ArithmeticException){

                }
            }
        }

        private fun removeZeroAfterDot(result:String):String{
            var value = result

            if(result.endsWith(".0"))
                value = result.substring(0,result.length-2)
            return value
        }
    }