package datastructure.stack.calculator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

/**
 * 正整数逆波兰表达式求解
 */
public class PolandNotation {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        System.out.println(result(s));
    }

    //输出结果
    public static int result(String suffixExpression){
        List<String> list = infixToSuffix(createInfixExpressionList(suffixExpression));

        Stack<Integer> numStack = new Stack<>();

        int num1 = 0;
        int num2 = 0;
        int result = 0;
        for (String str:list){
            if (str.equals("+") || str.equals("-") || str.equals("*") || str.equals("/")){
                num2 = numStack.pop();
                num1 = numStack.pop();
                result = calcultor(num1,num2,str);
                numStack.push(result);
                continue;
            }

            numStack.push(Integer.parseInt(str));
        }

        return numStack.pop();
    }

    //计算
    public static int calcultor(int num1,int num2,String operator){
        int result = 0;
        switch (operator){
            case "+":
                result = num1 + num2;
                break;
            case "-":
                result = num1 - num2;
                break;
            case "*":
                result = num1 * num2;
                break;
            case "/":
                result = num1 / num2;
                break;
            default:
                break;
        }
        return result;
    }

    //生成中缀表达式集合
    public static List<String> createInfixExpressionList(String infixExpression){
        if (infixExpression == null){
            throw new RuntimeException("输入的中缀表达式不合法！");
        }

        List<String> list = new ArrayList<>();
        int index = 0;//扫描索引
        String str = "";//用于拼接字符串
        char ele;

        while (true){
            ele = infixExpression.substring(index,index + 1).charAt(0);

            if (index == infixExpression.length() - 1){
                str = str + ele;
                list.add(str);
                break;
            }

            if (48 <= ele && ele <= 57){
                if ((48 <= infixExpression.substring(index + 1,index + 2).charAt(0)) && (57 >= infixExpression.substring(index + 1,index + 2).charAt(0))){
                    str = str + ele;
                    index++;
                    continue;
                }
            }

            str = str + ele;
            list.add(str);
            str = "";
            index++;

        }

        return list;

    }

    //中缀表达式转后缀表达式
    public static List<String> infixToSuffix(List<String> infixExpression){
        //运算符栈
        Stack<String> operStack = new Stack<>();
        //中间结果栈
        Stack<String> tempStack = new Stack<>();

        Stack<String> resultStack = new Stack<>();
        List<String> resultList = new ArrayList<>();

        for (String str:infixExpression){
            //左括号直接入运算符栈
            if (str.equals("(")){
                operStack.push(str);
                continue;
            }

            //运算符
            if (str.equals("+") || str.equals("-") || str.equals("*") || str.equals("/")){
                while (true){
                    //运算符栈顶为左括号或空，直接入运算符栈
                    if (operStack.size() == 0 || operStack.peek().equals("(")){
                        operStack.push(str);
                        break;
                    }

                    //优先级比运算符栈顶更高也入栈
                    if (priority(str) > priority(operStack.peek())){
                        operStack.push(str);
                        break;
                    }

                    tempStack.push(operStack.pop());
                }
                continue;
            }

            //右括号
            if (str.equals(")")){
                while (true){
                    if (operStack.peek().equals("(")){
                        operStack.pop();
                        break;
                    }
                    tempStack.push(operStack.pop());
                }
                continue;
            }

            tempStack.push(str);
        }

        while (operStack.size() > 0){
            tempStack.push(operStack.pop());
        }

        while (tempStack.size() > 0){
            resultStack.push(tempStack.pop());
        }

        while (resultStack.size() > 0){
            resultList.add(resultStack.pop());
        }

        return resultList;
    }

    //判断运算符优先级
    public static int priority(String operator){
        if (operator.equals("*") || operator.equals("/")){
            return 1;
        }else {
            return 0;
        }
    }

}
