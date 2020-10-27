import java.util.Stack;
import java.util.Scanner;
public class Program2 {
    public static int priority(char oper)
	{
		switch(oper)
		{
		case '+':
		{ return 1; }
		case '-':
		{ return 1; }
		case '*':
		{ return 2; }
		case '/':
		{ return 2; }
		}
		return 0;
	}
    public static int operate(int a, int b ,char oper)
    {
    	switch(oper)
		{
		case '+':
		{ return a+b; }
		case '-':
		{ return a-b; }
		case '*':
		{ return a*b; }
		case '/':
		{ return a/b; }
		}
		return 0;
    }

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		Stack<Integer> operands=new Stack<Integer>();
        Stack<Character> functions=new Stack<Character>();
        System.out.println("¬ведите выражение");
        String expr=in.nextLine();
        String tempnum="";
        for(int i=0;i<expr.length();i++)
        {
        	if(expr.charAt(i)>='0'&&expr.charAt(i)<='9')
        		tempnum+=expr.charAt(i);
        	if(expr.charAt(i)=='+'||expr.charAt(i)=='-'
        		||expr.charAt(i)=='*'||expr.charAt(i)=='/')
        	{
        		if(!(tempnum==""))
            		operands.push(Integer.parseInt(tempnum));
        		tempnum="";
        		if (!functions.empty()&&functions.peek()!='(')
    			{
    				while (priority(expr.charAt(i)) <= priority(functions.peek()) && !functions.empty())
    				{
    					int temp1 = operands.peek();
    					operands.pop();
    					int temp2 = operands.peek();
    					operands.pop();
    					operands.push(operate(temp2, temp1, functions.peek()));
    					functions.pop();
    					if (functions.empty()) break;
    				}
    			}
    			functions.push(expr.charAt(i));
        	}
        	if(expr.charAt(i)=='(')
        	{
        		functions.push(expr.charAt(i));
        	}
        	if(expr.charAt(i)==')')
        	{
        		operands.push(Integer.parseInt(tempnum));
        		tempnum="";
        		while(functions.peek()!='(')
        		{
        			int temp1 = operands.peek();
        			operands.pop();
        			int temp2 = operands.peek();
        			operands.pop();
        			operands.push(operate(temp2, temp1, functions.peek()));
        			functions.pop();
        		}
        		functions.pop();
        	}
        	if(i==expr.length()-1&&!(tempnum==""))
        		operands.push(Integer.parseInt(tempnum));
        	
        }
        while (!functions.empty())
    	{
        	int temp1 = operands.peek();
			operands.pop();
			int temp2 = operands.peek();
			operands.pop();
			operands.push(operate(temp2, temp1, functions.peek()));
			functions.pop();
    	}
        System.out.println(operands.peek());
	}

}
