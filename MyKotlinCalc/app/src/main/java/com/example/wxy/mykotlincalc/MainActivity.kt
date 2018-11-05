package com.example.wxy.mykotlincalc

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import java.math.BigDecimal as bd

public class MainActivity : AppCompatActivity() {

    public var Firstnumber = 0.0;//第一次按键结果
    public var Secondnumber = 0.0 //第二次按键结果
    public var Thirdnumber = 0.0 ///第三个记数量
    public var Forthnumber = 0.0 //第四个记数量
    public var Secondnumber1 = 0.0 ///一个中间量
    public var number = 0//一个中间量
    public var Result = 0.0///计算结果
    public var op = 0//判断操作数
    public var op1 = 0//存储上一个按键的操作数
    public var op3 = 0//判断是否继续刚才的计算
    public var count1 = 0//一个记数器
    public var count2 = 0//也是一个记数器
    public var isFirst = false//判断是否有第一操作数
    public var isPoint = false//判断是否按了小数点
    public var isSecNull = false//判断是否有第二次按键
    public var isEqual = false//判断是否按了“=”按钮
    public var isBack = true//判断能否按下BACK按钮
    public var isClick = false//判断是否按下了几个数字键


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var t = findViewById<TextView>(R.id.TxtDisplay);
        t.setText("0");
        setButtonListener(R.id.Btn1)
        setButtonListener(R.id.Btn2)
        setButtonListener(R.id.Btn3)
        setButtonListener(R.id.Btn4)
        setButtonListener(R.id.Btn5)
        setButtonListener(R.id.Btn6)
        setButtonListener(R.id.Btn7)
        setButtonListener(R.id.Btn8)
        setButtonListener(R.id.Btn9)
        setButtonListener(R.id.Btn0)
    }

    public fun setButtonListener(viewId: Int) {     //绑定监听器
        var but = findViewById<Button>(viewId);
        but.setOnClickListener(NumListener2)
    }

    public var NumListener2 = object : View.OnClickListener {
        override fun onClick(view: View?) {
            var text = findViewById<TextView>(R.id.TxtDisplay)
            if (text.text.equals("NaN")) {
                text.setText("Error!"); EnableButton();
            }
            var but = (view) as Button
            isClick = true;//按下了数字键
            count2++;
            isBack = true;//可以按下BACK键
            when (1) {
                1 -> {
                    if (op == 0) {
                        if (isPoint == false) {
                            Firstnumber = (text.text.toString() + but.text.toString()).toDouble()
                            val first = Firstnumber;
                            number = first.toInt()
                            if (Firstnumber % 1 != 0.0)//如果结果是小数，直接输出结果
                                text.setText(text.getText().toString() + but.getText().toString());
                            else//如果结果是整数，将浮点数转为整数显示
                                text.setText(number.toString());
                        } else if (isPoint == true) {
                            text.setText(text.text.toString() + but.text.toString())
                            Firstnumber = text.text.toString().toDouble()
                            Thirdnumber = Firstnumber
                        }
                        Thirdnumber = Firstnumber//记录最近一次操作的结果
                        isFirst = true
                    } else if (op != 0) run {
                        if (text.text.toString() == "0") ;
                        else {
                            val second = Secondnumber
                            number = second.toInt()
                            if (isPoint == false) {//判断是否按下了小数点键，其余判断和显示同上
                                isSecNull = true
                                Secondnumber1 = (number.toString() + but.text.toString()).toDouble()
                                Secondnumber = Secondnumber1
                                if (Secondnumber % 1 != 0.0)
                                    text.text = (Secondnumber.toString() + but.text.toString())
                                else
                                    text.text = Secondnumber1.toInt().toString()
                            } else if (isPoint == true) {//按了小数点键后的计算
                                isSecNull = true
                                text.text = text.text.toString() + but.text.toString()
                                Secondnumber = text.text.toString().toDouble()
                            }
                            Thirdnumber = Secondnumber//记录最近一次的操作结果
                        }
                    }
                }
            }
        }
    }
        public fun BtnAddClick(v: View) {
            isPoint = false;
            val text = findViewById<TextView>(R.id.TxtDisplay);
            if (isEqual == true && isClick == true) {//如果按下=号后又按了数字键，又过来按了加号，证明是重新开始计算，自动清零
                Firstnumber = Secondnumber;
                Secondnumber = 0.0
                isEqual = false;
                Result = 0.0
            }
            op = 1;//记录操作数为1
            when (op1) {//对于刚才记录下来的第一第二操作数进行运算，如果刚才没有按下加减乘除，就跳出
                1 ->//上一次是加法的情况
                    if (isEqual == true && isClick == false)//如果按了等于号直接按的运算符，不进行上一步运算的逻辑
                        ;
                    else {
                        Firstnumber = Firstnumber + Secondnumber;
                        Secondnumber = 0.0
                    }
                2 ->//上一次是减法
                    if (isEqual == true && isClick == false)//如果按了等于号直接按的运算符，不进行上一步运算的逻辑
                        ;
                    else {
                        Firstnumber = Firstnumber - Secondnumber;
                        Secondnumber = 0.0
                    }
                3 ->//上一次是乘法
                    if (isEqual == true && isClick == false)//如果按了等于号直接按的运算符，不进行上一步运算的逻辑
                        ;
                    else {
                        Firstnumber = Firstnumber * Secondnumber;
                        Secondnumber = 0.0
                    }
                4 ->//上一次是除法
                {
                    if (Secondnumber == 0.0) {//如果被除数为零的话，报错
                        text.setText("Error!");
                        EnableButton();
                    }
                    if (isEqual == true && isClick == false) ;
                    //如果按了等于号直接按的运算符，不进行上一步运算的逻辑
                    else {
                        Firstnumber = Firstnumber / Secondnumber
                        Secondnumber = 0.0
                    }
                    op1 = op;//记录最近一次的操作数
                    op3 = 1;//证明按下了四则运算按键

                }
            }
        }

        public fun BtnMinusClick(v: View) {//减法计算按键
            val text = findViewById<View>(R.id.TxtDisplay) as TextView
            isPoint = false
            if (isEqual == true && isClick == true) {//如果按下=号后又按了数字键，又过来按了加号，证明是重新开始计算，自动清零
                Firstnumber = Secondnumber
                Secondnumber = 0.0
                isEqual = false
                Result = 0.0
            }
            op = 2//记录操作数为2
            when (op1) {
                //对于刚才记录下来的第一第二操作数进行运算，如果刚才没有按下加减乘除，就跳出
                1//上一次是加法
                -> {
                    if (isEqual == true && isClick == false) ;
                    else {
                        Firstnumber = Firstnumber + Secondnumber
                        Secondnumber = 0.0
                    }
                    //如果按了等于号直接按的运算符，不进行上一步运算的逻辑
                }
                2//上一次是减法
                -> {
                    if (isEqual == true && isClick == false) ;
                    else {
                        //如果按了等于号直接按的运算符，不进行上一步运算的逻辑
                        Firstnumber = Firstnumber - Secondnumber
                        Secondnumber = 0.0
                    }
                }
                3//上一次是乘法
                -> {
                    if (isEqual == true && isClick == false) ;
                    //如果按了等于号直接按的运算符，不进行上一步运算的逻辑
                    else {
                        Firstnumber = Firstnumber * Secondnumber
                        Secondnumber = 0.0
                    }
                }
                4//上一次是除法
                -> {
                    if (Secondnumber == 0.0) {//如果被除数为零，报错
                        text.text = "Error!"
                        EnableButton()
                    }
                    if (isEqual == true && isClick == false)
                    //如果按了等于号直接按的运算符，不进行上一步运算的逻辑
                        ;
                    else {
                        Firstnumber = Firstnumber / Secondnumber
                        Secondnumber = 0.0
                    }
                }
            }
            op1 = op//记录最近一次的操作数
            op3 = 1//证明按下了四则运算按键
        }

        public fun BtnMultiplyClick(v: View) {//乘法运算按键
            val text = findViewById<View>(R.id.TxtDisplay) as TextView
            isPoint = false
            if (isEqual == true && isClick == true) {//如果按下=号后又按了数字键，又过来按了加号，证明是重新开始计算，自动清零
                Firstnumber = Secondnumber
                Secondnumber = 0.0
                isEqual = false
                Result = 0.0
            }
            op = 3//记录操作数为3
            when (op1) {
                //对于刚才记录下来的第一第二操作数进行运算，如果刚才没有按下加减乘除，就跳出
                1//上一次是加法
                -> {
                    if (isEqual == true && isClick == false)
                    //如果按了等于号直接按的运算符，不进行上一步运算的逻辑
                        ;
                    else {
                        Firstnumber = Firstnumber + Secondnumber
                        Secondnumber = 0.0
                    }
                }
                2//上一次是减法
                -> {
                    if (isEqual == true && isClick == false) ;
                    //如果按了等于号直接按的运算符，不进行上一步运算的逻辑
                    else {
                        Firstnumber = Firstnumber - Secondnumber
                        Secondnumber = 0.0
                    }
                }
                3//上一次是乘法
                -> {
                    if (isEqual == true && isClick == false) ;
                    //如果按了等于号直接按的运算符，不进行上一步运算的逻辑
                    else {
                        Firstnumber = Firstnumber * Secondnumber
                        Secondnumber = 0.0
                    }
                }
                4//上一次是除法
                -> {
                    if (Secondnumber == 0.0) {//如果被除数为零，报错
                        text.text = ("Error!")
                        EnableButton()
                    }
                    if (isEqual == true && isClick == false) ;
                    //如果按了等于号直接按的运算符，不进行上一步运算的逻辑
                    else {
                        Firstnumber = Firstnumber / Secondnumber
                        Secondnumber = 0.0
                    }
                }
                else -> {
                }
            }
            op1 = op//记录最近一次的操作数
            op3 = 1//证明按下了四则运算按键
        }

        public fun BtnDivideClick(v: View) {
            val text = findViewById<View>(R.id.TxtDisplay) as TextView
            isPoint = false
            if (isEqual == true && isClick == true) {//如果按下=号后又按了数字键，又过来按了加号，证明是重新开始计算，自动清零
                Firstnumber = Secondnumber
                Secondnumber = 0.0
                isEqual = false
                Result = 0.0
            }
            op = 4//记录操作数为4
            when (op1) {
                //对于刚才记录下来的第一第二操作数进行运算，如果刚才没有按下加减乘除，就跳出
                1//上一次是加法
                -> {
                    if (isEqual == true && isClick == false) ;
                    //如果按了等于号直接按的运算符，不进行上一步运算的逻辑
                    else {
                        Firstnumber = Firstnumber + Secondnumber
                        Secondnumber = 0.0
                    }
                }
                2//上一次是减法
                -> {
                    if (isEqual == true && isClick == false) ;
                    //如果按了等于号直接按的运算符，不进行上一步运算的逻辑
                    else {
                        Firstnumber = Firstnumber - Secondnumber
                        Secondnumber = 0.0
                    }
                }
                3//上一次是乘法
                -> {
                    if (isEqual == true && isClick == false) ;
                    //如果按了等于号直接按的运算符，不进行上一步运算的逻辑
                    else {
                        Firstnumber = Firstnumber * Secondnumber
                        Secondnumber = 0.0
                    }
                }
                4//上一次是除法
                -> {
                    if (Secondnumber == 0.0) {//如果被除数为零，报错
                        text.text = "Error!"
                        EnableButton()
                    }
                    if (isEqual == true && isClick == false) ;
                    //如果按了等于号直接按的运算符，不进行上一步运算的逻辑
                    else {
                        Firstnumber = Firstnumber / Secondnumber
                        Secondnumber = 0.0
                    }
                }
                else -> {
                }
            }
            op1 = op//记录最近一次的操作数
            op3 = 1//证明按下了四则运算按键
        }

        public fun BtnEqualClick(v: View) {
            isEqual = true//表示按下了等于键
            isPoint = false//小数点键复位
            val text11 = findViewById<View>(R.id.TxtDisplay) as TextView
            isBack = false//结果不能进行BACK运算
            if (op3 == 0) {//如果在上一次按下了等于键后只按了数字键没有按四则运算键，则直接根据上一次的四则运算符号对此数和上次最近操作数进行运算
                when (op1) {
                    1 -> {
                        if (isClick == true)
                        //如果按了数字键，则证明不是连续运算
                            Result = Forthnumber + Thirdnumber//Forthnumber是上次最近的操作数，Thridnumber是本次最近操作数
                        if (isClick == false) {
                            Result = Result + Thirdnumber//Forthnumber是上次最近的操作数，Thridnumber是本次最近操作数
                            Firstnumber = Result
                        }
                        if (Result % 1 != 0.0)
                        //判断计算结果是否是整数
                            text11.text = Result.toString()
                        else
                            text11.text = Result.toInt().toString()
                    }
                    2 -> {
                        if (isClick == true)
                        //如果按了数字键，则证明不是连续运算
                            Result = Forthnumber - Thirdnumber//Forthnumber是上次最近的操作数，Thridnumber是本次最近操作数
                        if (isClick == false) {
                            Result = Result - Thirdnumber
                            Firstnumber = Result
                        }//Forthnumber是上次最近的操作数，Thridnumber是本次最近操作数
                        if (Result % 1 != 0.0)
                        //判断计算结果是否是整数
                            text11.text = Result.toString()
                        else
                            text11.text = Result.toInt().toString()
                    }
                    3 -> {
                        if (isClick == true)
                        //如果按了数字键，则证明不是连续运算
                            Result = Forthnumber * Thirdnumber//Forthnumber是上次最近的操作数，Thridnumber是本次最近操作数
                        if (isClick == false) {
                            Result = Result * Thirdnumber
                            Firstnumber = Result
                        }//Forthnumber是上次最近的操作数，Thridnumber是本次最近操作数
                        if (Result % 1 != 0.0)
                        //判断计算结果是否是整数
                            text11.text = Result.toString()
                        else
                            text11.text = Result.toInt().toString()
                    }
                    4 -> {
                        if (Thirdnumber == 0.0) {//如果最近的数字是零并且上一次操作数为4，那么零就要成为被除数，报错
                            text11.text = "Error!"
                            EnableButton()
                        } else if (isClick == true)
                        //如果按了数字键，则证明不是连续运算
                            Result = Forthnumber / Thirdnumber//Forthnumber是上次最近的操作数，Thridnumber是本次最近操作数
                        else if (isClick == false) {
                            Result = Result / Thirdnumber
                            Firstnumber = Result
                        }//Forthnumber是上次最近的操作数，Thridnumber是本次最近操作数
                        else if (Result % 1 != 0.0)
                        //判断计算结果是否是整数
                            text11.text = Result.toString()
                        else
                            text11.text = Result.toInt().toString()
                    }
                    else -> {
                    }
                }
                Secondnumber = 0.0
            }
            if (op3 != 0)
            //如果有操作数
            {
                if (isClick != true){
                    Thirdnumber = Firstnumber
                }
                if (op == 1)
                //加法
                {
                    Result = Firstnumber + Thirdnumber//最近操作数和第一计算数和最近的计算数进行运算
                    if (Result % 1 != 0.0)
                    //判断是否是整数然后决定如何显示
                        text11.text = Result.toString()
                    else
                        text11.text = Result.toInt().toString()
                    Forthnumber = Thirdnumber//保存最近的计算数字
                    Secondnumber = 0.0 //第二计算数清零
                    Firstnumber = Result
                }//第一计算数等于本次计算结果
                if (op == 2) {//减法
                    Result = Firstnumber - Thirdnumber//最近操作数和第一计算数和最近的计算数进行运算
                    if (Result % 1 != 0.0)
                    //判断是否是整数然后决定如何显示
                        text11.text = Result.toString()
                    else
                        text11.text = Result.toInt().toString()
                    Forthnumber = Thirdnumber//保存最近的计算数字
                    Secondnumber = 0.0//第二计算数清零
                    Firstnumber = Result
                }//第一计算数等于本次计算结果
                if (op == 3) {
                    Result = Firstnumber * Thirdnumber//最近操作数和第一计算数和最近的计算数进行运算
                    if (Result % 1 != 0.0)
                    //判断是否是整数然后决定如何显示
                        text11.text = Result.toString()
                    else
                        text11.text = Result.toInt().toString()
                    Forthnumber = Thirdnumber//保存最近的计算数字
                    Secondnumber = 0.0 //第二计算数清零
                    Firstnumber = Result
                }//第一计算数等于本次计算结果
                if (op == 4) {
                    if (Thirdnumber == 0.0) {
                        text11.text = "Error!"
                        EnableButton()
                    } else {
                        Result = Firstnumber / Thirdnumber//最近操作数和第一计算数和最近的计算数进行运算
                        if (Result % 1 != 0.0)
                        //判断是否是整数然后决定如何显示
                            text11.text = Result.toString()
                        else
                            text11.text = Result.toInt().toString()
                        Forthnumber = Thirdnumber//保存最近的计算数字
                        Secondnumber = 0.0//第二计算数清零
                        Firstnumber = Result
                    }//第一计算数等于本次计算结果
                }
            }
            isClick = false//恢复默认值
            op3 = 0
            count2 = 0
        }

        public fun BtnCClick(v: View) {//按键C的事件，所有计算都清空
            val text = findViewById<View>(R.id.TxtDisplay) as TextView
            text.text = "0"
            Result = 0.0
            Firstnumber = 0.0
            Secondnumber = 0.0
            Thirdnumber = 0.0
            Forthnumber = 0.0
            isPoint = false
            isSecNull = false
            isEqual = false
            isClick = false
            op = 0
            op1 = 0
            op3 = 0
            Return()
        }

        public fun EnableButton() {//使发生错误时除了C以外的按键都无法使用

            val btnPlus = findViewById<View>(R.id.BtnPlus) as Button
            val btnMinus = findViewById<View>(R.id.BtnMinus) as Button
            val btnMultiply = findViewById<View>(R.id.BtnMultiply) as Button
            val btnDivide = findViewById<View>(R.id.BtnDivide) as Button
            val btnEqual = findViewById<View>(R.id.BtnEqual) as Button
            val btnRotting = findViewById<View>(R.id.BtnRooting) as Button
            val btnSquare = findViewById<View>(R.id.BtnSquare) as Button
            val btnReciprocal = findViewById<View>(R.id.BtnReciprocal) as Button
            val btnCE = findViewById<View>(R.id.BtnCE) as Button
            val btnC = findViewById<View>(R.id.BtnC) as Button
            val btnBack = findViewById<View>(R.id.BtnBack) as Button
            val btnSign = findViewById<View>(R.id.BtnSign) as Button
            val btnPoint = findViewById<View>(R.id.BtnPoint) as Button
            btnPlus.isEnabled = false
            btnMinus.isEnabled = false
            btnMultiply.isEnabled = false
            btnDivide.isEnabled = false
            btnEqual.isEnabled = false
            btnRotting.isEnabled = false
            btnSquare.isEnabled = false
            btnReciprocal.isEnabled = false
            btnCE.isEnabled = false
            btnC.isEnabled = true
            btnBack.isEnabled = false
            btnSign.isEnabled = false
            btnPoint.isEnabled = false
            val btn1 = findViewById<View>(R.id.Btn1) as Button
            val btn2 = findViewById<View>(R.id.Btn2) as Button
            val btn3 = findViewById<View>(R.id.Btn3) as Button
            val btn4 = findViewById<View>(R.id.Btn4) as Button
            val btn5 = findViewById<View>(R.id.Btn5) as Button
            val btn6 = findViewById<View>(R.id.Btn6) as Button
            val btn7 = findViewById<View>(R.id.Btn7) as Button
            val btn8 = findViewById<View>(R.id.Btn8) as Button
            val btn9 = findViewById<View>(R.id.Btn9) as Button
            val btn0 = findViewById<View>(R.id.Btn0) as Button
            btn1.isEnabled = false
            btn2.isEnabled = false
            btn3.isEnabled = false
            btn4.isEnabled = false
            btn5.isEnabled = false
            btn6.isEnabled = false
            btn7.isEnabled = false
            btn8.isEnabled = false
            btn9.isEnabled = false
            btn0.isEnabled = false
        }

        public fun Return1() {//恢复所有按键但是不清空操作
            val btnPlus = findViewById<View>(R.id.BtnPlus) as Button
            val btnMinus = findViewById<View>(R.id.BtnMinus) as Button
            val btnMultiply = findViewById<View>(R.id.BtnMultiply) as Button
            val btnDivide = findViewById<View>(R.id.BtnDivide) as Button
            val btnEqual = findViewById<View>(R.id.BtnEqual) as Button
            val btnRotting = findViewById<View>(R.id.BtnRooting) as Button
            val btnSquare = findViewById<View>(R.id.BtnSquare) as Button
            val btnReciprocal = findViewById<View>(R.id.BtnReciprocal) as Button
            val btnCE = findViewById<View>(R.id.BtnCE) as Button
            val btnC = findViewById<View>(R.id.BtnC) as Button
            val btnBack = findViewById<View>(R.id.BtnBack) as Button
            val btnSign = findViewById<View>(R.id.BtnSign) as Button
            val btnPoint = findViewById<View>(R.id.BtnPoint) as Button
            btnPlus.isEnabled = true
            btnMinus.isEnabled = true
            btnMultiply.isEnabled = true
            btnDivide.isEnabled = true
            btnEqual.isEnabled = true
            btnRotting.isEnabled = true
            btnSquare.isEnabled = true
            btnReciprocal.isEnabled = true
            btnCE.isEnabled = true
            btnC.isEnabled = true
            btnBack.isEnabled = true
            btnSign.isEnabled = true
            btnPoint.isEnabled = true
            val btn1 = findViewById<View>(R.id.Btn1) as Button
            val btn2 = findViewById<View>(R.id.Btn2) as Button
            val btn3 = findViewById<View>(R.id.Btn3) as Button
            val btn4 = findViewById<View>(R.id.Btn4) as Button
            val btn5 = findViewById<View>(R.id.Btn5) as Button
            val btn6 = findViewById<View>(R.id.Btn6) as Button
            val btn7 = findViewById<View>(R.id.Btn7) as Button
            val btn8 = findViewById<View>(R.id.Btn8) as Button
            val btn9 = findViewById<View>(R.id.Btn9) as Button
            val btn0 = findViewById<View>(R.id.Btn0) as Button
            btn1.isEnabled = true
            btn2.isEnabled = true
            btn3.isEnabled = true
            btn4.isEnabled = true
            btn5.isEnabled = true
            btn6.isEnabled = true
            btn7.isEnabled = true
            btn8.isEnabled = true
            btn9.isEnabled = true
            btn0.isEnabled = true
        }

        public fun Return() {//使所有按键恢复并清空操作
            val btnPlus = findViewById<View>(R.id.BtnPlus) as Button
            val btnMinus = findViewById<View>(R.id.BtnMinus) as Button
            val btnMultiply = findViewById<View>(R.id.BtnMultiply) as Button
            val btnDivide = findViewById<View>(R.id.BtnDivide) as Button
            val btnEqual = findViewById<View>(R.id.BtnEqual) as Button
            val btnRotting = findViewById<View>(R.id.BtnRooting) as Button
            val btnSquare = findViewById<View>(R.id.BtnSquare) as Button
            val btnReciprocal = findViewById<View>(R.id.BtnReciprocal) as Button
            val btnCE = findViewById<View>(R.id.BtnCE) as Button
            val btnC = findViewById<View>(R.id.BtnC) as Button
            val btnBack = findViewById<View>(R.id.BtnBack) as Button
            val btnSign = findViewById<View>(R.id.BtnSign) as Button
            val btnPoint = findViewById<View>(R.id.BtnPoint) as Button
            btnPlus.isEnabled = true
            btnMinus.isEnabled = true
            btnMultiply.isEnabled = true
            btnDivide.isEnabled = true
            btnEqual.isEnabled = true
            btnRotting.isEnabled = true
            btnSquare.isEnabled = true
            btnReciprocal.isEnabled = true
            btnCE.isEnabled = true
            btnC.isEnabled = true
            btnBack.isEnabled = true
            btnSign.isEnabled = true
            btnPoint.isEnabled = true
            val btn1 = findViewById<View>(R.id.Btn1) as Button
            val btn2 = findViewById<View>(R.id.Btn2) as Button
            val btn3 = findViewById<View>(R.id.Btn3) as Button
            val btn4 = findViewById<View>(R.id.Btn4) as Button
            val btn5 = findViewById<View>(R.id.Btn5) as Button
            val btn6 = findViewById<View>(R.id.Btn6) as Button
            val btn7 = findViewById<View>(R.id.Btn7) as Button
            val btn8 = findViewById<View>(R.id.Btn8) as Button
            val btn9 = findViewById<View>(R.id.Btn9) as Button
            val btn0 = findViewById<View>(R.id.Btn0) as Button
            btn1.isEnabled = true
            btn2.isEnabled = true
            btn3.isEnabled = true
            btn4.isEnabled = true
            btn5.isEnabled = true
            btn6.isEnabled = true
            btn7.isEnabled = true
            btn8.isEnabled = true
            btn9.isEnabled = true
            btn0.isEnabled = true
            val text = findViewById<View>(R.id.TxtDisplay) as TextView
            text.text = "0"
            Result = 0.0
            Firstnumber = 0.0
            Secondnumber = 0.0
            Thirdnumber = 0.0
            Forthnumber = 0.0
            isPoint = false
            isSecNull = false
            isEqual = false
            isFirst = false
            isBack = true
            op = 0
            isClick = false
            op1 = 0
        }

        public fun ButSignClick(v: View) {//正负号计算
            val textP = findViewById<View>(R.id.TxtDisplay) as TextView
            if (op == 0) {//证明是第一计算数
                if (isPoint == false) {
                    Firstnumber = (textP.text.toString()).toDouble()
                    val First = Firstnumber.toInt()
                    if (Firstnumber % 1 != 0.0)
                        textP.text = Firstnumber.toString()
                    else
                        textP.text = First.toString()
                }
                if (isPoint == true) {
                    textP.text = "-" + textP.text.toString()
                    Firstnumber = (textP.text.toString()).toDouble()
                }
                Thirdnumber = Firstnumber
            } else if (op != 0)
            //证明是第二计算数
            {
                if (isPoint == false) {
                    Secondnumber = (textP.text.toString()).toDouble()
                    val Sec = Secondnumber.toInt()
                    if (Secondnumber % 1 != 0.0)
                        textP.text = Secondnumber.toString()
                    else
                        textP.text = Sec.toString()
                }
                if (isPoint == true) {
                    textP.text = "-" + textP.text.toString()
                    Secondnumber = (textP.text.toString()).toDouble()
                }
                Thirdnumber = Secondnumber
            }
        }

        public fun BtnCEClick(v: View) {//CE键，清空当前计算数
            val textCE = findViewById<View>(R.id.TxtDisplay) as TextView
            textCE.text = "0"
            if (op == 0)
                Firstnumber = 0.0
            if (op != 0)
                Secondnumber = 0.0
            Thirdnumber = 0.0
            Return1()
        }

        public fun BtnBackClick(v: View) {//BACK键，从右到左删除一位
            val text = findViewById<View>(R.id.TxtDisplay) as TextView
            if (isBack == true) {//判断是否可以进行此操作，如果不可以就直接跳出，不做任何操作
                val str = text.text.toString()
                val str1 = str.substring(0, str.length - 1)
                if (str1 == "-") {
                    text.text = "0"
                    EnableButton()
                } else if (str.length > 0 && str.substring(0, str.length - 1).length > 0) {//如果删除一位后剩余位数不为零
                    text.text = str.substring(0, str.length - 1)
                    if (op1 == 0 && isEqual == false || op1 != 0 && isEqual == true)
                    //如果是第一计算数
                    {
                        Firstnumber = java.lang.Double.parseDouble(str.substring(0, str.length - 1))
                        Thirdnumber = Firstnumber
                    } else {//如果是第二计算数
                        text.text = str.substring(0, str.length - 1)
                        Secondnumber = java.lang.Double.parseDouble(text.text.toString())
                        Thirdnumber = Secondnumber
                    }
                } else if (str.length > 0 && str.substring(0, str.length - 1).length == 0) {//如果删除一位后剩余位数为零，那么就将该计算数置为零
                    text.text = "0"
                    if (op1 == 0 && isEqual == false || op1 != 0 && isEqual == true)
                        Firstnumber = 0.0
                    else
                        Secondnumber = 0.0
                    Thirdnumber = 0.0
                } else if (str.length > 0 && str.substring(0, str.length - 1) === "-") {//如果删除一位后剩余位数为负号，那么就将该计算数置为零
                    text.text = "0"
                    if (op1 == 0 && isEqual == false || op1 != 0 && isEqual == true)
                        Firstnumber = 0.0
                    else
                        Secondnumber = 0.0
                    Thirdnumber = 0.0
                } else {//其他情况
                    text.text = "0"
                    if (op == 0)
                        Firstnumber = 0.0
                    else
                        Secondnumber = 0.0
                    Thirdnumber = 0.0
                }
            }
            Return1()
        }

        public fun BtnPointClick(v: View) {//小数点
            if (isPoint == false) {//如果之前没有按过小数点键，如果按了不做处理
                isPoint = true
                val text = findViewById<View>(R.id.TxtDisplay) as TextView
                val Text1 = java.lang.Double.parseDouble(text.text.toString())
                if (Text1 == 0.0 || Text1 == Result && count2 == 0)
                //如果开始为0，
                    text.text = "0" + "."
                else
                    text.text = Text1.toInt().toString() + "."
            }
        }

        public fun BtnSquareClick(v: View) {//平方
            val text = findViewById<View>(R.id.TxtDisplay) as TextView
            val res = java.lang.Double.parseDouble(text.text.toString())
            if (op == 0)
                Firstnumber = res * res
            else
                Secondnumber = res * res
            Thirdnumber = res * res
            val result = (res * res).toString()
            val result2 = (res * res).toInt()
            if (java.lang.Double.parseDouble(result) % 1 != 0.0)
                text.text = result
            else
                text.text = result2.toString()
        }

        public fun BtnReciprocalClick(v: View) {//取倒数
            val text = findViewById<View>(R.id.TxtDisplay) as TextView
            val res = java.lang.Double.parseDouble(text.text.toString())
            if (res != 0.0) {//被除数不为零
                if (op == 0)
                    Firstnumber = 1 / res
                else
                    Secondnumber = 1 / res
                Thirdnumber = 1 / res
                val result1 = 1 / res
                val result2 = (1 / res).toInt()
                val number1 = java.lang.Double.parseDouble(result1.toString())
                if (result1 % 1 != 0.0)
                    text.text = result1.toString()
                else
                    text.text = result2.toString()
                op = 0
            } else {//被除数为零，报错
                text.text = "ERROR!"
                EnableButton()
            }
        }

        public fun BtnRootingClick(v: View) {//开平方
            val text = findViewById<View>(R.id.TxtDisplay) as TextView
            val res = java.lang.Double.parseDouble(text.text.toString())
            if (res >= 0) {//如果底数大于等于零，正常计算
                if (op == 0)
                    Firstnumber = Math.sqrt(res)
                else
                    Secondnumber = Math.sqrt(res)
                Thirdnumber = Math.sqrt(res)
                val result1 = Math.sqrt(res)
                val number1 = java.lang.Double.parseDouble(result1.toString())
                val number2 = number1.toInt()
                if (result1 % 1 != 0.0)
                    text.text = result1.toString()
                else
                    text.text = number2.toString()
            } else {//如果底数等于零，报错
                text.text = "ERROR!"
                EnableButton()
            }
        }
}

