package datastructure.stack.calculator;

import java.util.Scanner;
import java.util.Stack;

/**
 * 不带小括号,简单正数四则运算
 * 中缀表达式
 */
public class Test1 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String expression = sc.nextLine();
        System.out.println(scanner(expression));
    }

    //判断优先级（由程序员决定），返回数字越大，优先级越高
    //利用char和int的关系
    public static int priority(char operator){
        if (operator == '*' || operator == '/'){
            return 1;
        }else if (operator == '+' || operator == '-'){
            return 0;
        }else {
            throw new RuntimeException("运算符不合法！");
        }
    }

    //判断是否是运算符
    public static boolean isOperator(char value){
        return value == '+' || value == '-' || value == '*' || value == '/';
    }

    //运算
    public static int calculate(int num1,int num2,char operator){
        int result = 0;
        //注意除法和减法的顺序
        switch (operator){
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num2 - num1;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                result = num2 / num1;
                break;
            default:
                break;
        }
        return result;
    }

    //扫描计算
    public static int scanner(String expression){
        //建立一个数栈和符号栈
        Stack<Integer> numStack = new Stack<>();
        Stack<Character> charStack = new Stack<>();

        int num1;
        int num2;

        char operator;
        char temp;

        int index = 0;
        int result;
        String str = "";//用于拼接字符串

        //利用String的方法进行扫描
        while (true){
            //扫描每个字符
            temp = expression.substring(index, index + 1).charAt(0);

            if (isOperator(temp)){
                //符号栈不为空
                if (!charStack.empty()){
                    if ((temp == '+' || temp == '-') && (charStack.peek() == '*' || charStack.peek() == '/')){
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        operator = charStack.pop();
                        result = calculate(num1, num2, operator);
                        numStack.push(result);
                        charStack.push(temp);

                        index++;
                        continue;
                    }

                    if ((temp == '*' || temp == '/') && (charStack.peek() == '*' || charStack.peek() == '/')){
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        operator = charStack.pop();
                        result = calculate(num1, num2, operator);
                        numStack.push(result);
                        charStack.push(temp);

                        index++;
                        continue;
                    }

                    if ((temp == '+' || temp == '-') && (charStack.peek() == '+' || charStack.peek() == '-')){
                        charStack.push(temp);

                        index++;
                        continue;
                    }

                    if ((temp == '*' || temp == '/') && (charStack.peek() == '+' || charStack.peek() == '-')){
                        charStack.push(temp);

                        index++;
                        continue;
                    }

                    /*if (priority(temp) <= priority(charStack.peek())){
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        operator = charStack.pop();
                        result = calculate(num1, num2, operator);
                        numStack.push(result);
                        charStack.push(temp);

                    }*/

                    /*if (priority(temp) > priority(charStack.peek())){
                        charStack.push(temp);
                    }*/
                }else {
                    charStack.push(temp);
                }
            }else {
                //numStack.push(temp - 48);

                //多位数情况
                str = str + temp;

                //如果temp已经是最后一位，则直接入数栈即可
                if (index == expression.length() - 1){
                    numStack.push(Integer.parseInt(str));
                }else if (isOperator(expression.substring(index + 1,index + 2).charAt(0))){
                    numStack.push(Integer.parseInt(str));
                    //重要：清空str
                    str = "";
                }

            }

            index++;
            //判断是否扫描结束
            if (index >= expression.length()){
                break;
            }
        }

        //判断符号栈栈顶是否是*或/
        if(charStack.peek() == '*' || charStack.peek() == '/'){
            num1 = numStack.pop();
            num2 = numStack.pop();
            operator = charStack.pop();
            result = calculate(num1, num2, operator);
            numStack.push(result);
        }

        return result(numStack,charStack);
    }

    //定值计算
    public static int result(Stack<Integer> numStack, Stack<Character> charStack){
        Stack<Integer> newNumStack = new Stack<>();
        Stack<Character> newCharStack = new Stack<>();

        while (numStack.size() > 0){
            newNumStack.push(numStack.pop());
        }

        while (charStack.size() > 0){
            newCharStack.push(charStack.pop());
        }

        int num1 = 0;
        int num2 = 0;
        int result = 0;
        while (newCharStack.size() > 0){
            num2 = newNumStack.pop();
            num1 = newNumStack.pop();
            result =  calculate(num1,num2,newCharStack.pop());
            newNumStack.push(result);
        }

        return newNumStack.pop();
    }

}
