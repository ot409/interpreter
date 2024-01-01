package interpreter.virtualmachine;

import interpreter.bytecodes.*;

import java.util.Stack;

public class VirtualMachine {

    private RunTimeStack   runTimeStack;
    private Stack<Integer> returnAddress;
    private Program        program;
    private int            programCounter;
    private boolean        isRunning, isDumpable;

    public VirtualMachine(Program program) {
        this.program = program;
        this.runTimeStack = new RunTimeStack();
        this.returnAddress = new Stack<>();
        this.programCounter = 0;
    }

    /**
     * Executes the program stored in the virtual machine.
     */
    public void executeProgram() {
        isRunning = true;
        isDumpable = true;

        while(isRunning){
            // get the bytecode at the current program counter
            ByteCode code = program.getCode(programCounter);
            code.execute(this);

            // print bytecode and run-time stack if dumpable
            if (isDumpable && code instanceof Dumpable) {
                System.out.println(code);
                System.out.println(this.runTimeStack.dump());
            }
            programCounter++;
        }
    }

    /**
     * Pops the top value from the runtime stack.
     * @return the popped value.
     */
    public int popStack(){
        return this.runTimeStack.pop();
    }

    /**
     * Pushes a value onto the runtime stack.
     * @param valueToPush the value to push.
     */
    public void push(int valueToPush) {
        this.runTimeStack.push(valueToPush);
    }

    /**
     * Pops a specified number of values from the runtime stack.
     * @param numValuesToRemove the number of values to pop.
     */
    public void popNum(int numValuesToRemove) {
        this.runTimeStack.popNum(numValuesToRemove);
    }

    /**
     * Stores a value at the specified offset in the current frame.
     * @param offsetOfCurrentFrame the offset in the current frame.
     */
    public void store(int offsetOfCurrentFrame) {
        this.runTimeStack.store(offsetOfCurrentFrame);
    }

    /**
     * Loads a value from the specified offset in the current frame
     * and pushes it onto the runtime stack.
     * @param offsetOfCurrentFrame the offset in the current frame.
     */
    public void load(int offsetOfCurrentFrame) {
        this.runTimeStack.load(offsetOfCurrentFrame);
    }

    /**
     * Creates a new frame in the runtime stack at the specified
     * number of values.
     * @param numValuesNewFrame the number of values in the new frame.
     */
    public void newFrameAt(int numValuesNewFrame) {
        this.runTimeStack.newFrameAt(numValuesNewFrame);
    }

    /**
     * Halts the execution of the virtual machine.
     */

    public void halt() {
        // set the isRunning flag to false to stop program to stop execution
        isRunning = false;
    }

    /**
     * Retrieves the value at the top of the runtime stack without removing it.
     * @return the value at the top of the runtime stack.
     */
    public int peek() {
        return this.runTimeStack.peek();
    }

    /**
     * Prints the value at the top of the runtime stack.
     */
    public void printPeek() {
        System.out.println(peek());
    }

    /**
     * Pops the top frame from the runtime stack.
     */
    public void popFrame() {
        this.runTimeStack.popFrame();
    }

    /**
     * Performs the specified binary operation on the top two values
     * of the runtime stack and pushes the result back to the stack.
     * @param operator the binary operator.
     */
    public void bop(String operator) {
        int operand2 = runTimeStack.pop();  // pop the second operand from the runtime stack
        int operand1 = runTimeStack.pop();  // pop the first operand from the runtime stack
        int result;

        switch (operator) {
            // binary arithmetic operations
            case "+":
                result = operand1 + operand2;
                break;
            case "-":
                result = operand1 - operand2;
                break;
            case "*":
                result = operand1 * operand2;
                break;
            case "/":
                result = operand1 / operand2;
                break;
            case "%":
                result = operand1 % operand2;
                break;
            // binary comparison operations
            case "==":
                result = operand1 == operand2 ? 1 : 0;
                break;
            case "!=":
                result = operand1 != operand2 ? 1 : 0;
                break;
            case "<":
                result = operand1 < operand2 ? 1 : 0;
                break;
            case "<=":
                result = operand1 <= operand2 ? 1 : 0;
                break;
            case ">":
                result = operand1 > operand2 ? 1 : 0;
                break;
            case ">=":
                result = operand1 >= operand2 ? 1 : 0;
                break;
            case "|":
                result = operand1 | operand2;
                break;
            case "^":
                result = operand1 ^ operand2;
                break;
            // binary bitwise operations
            case "&":
                result = operand1 & operand2;
                break;
            default:
                // handle unsupported operator
                throw new IllegalArgumentException("Unsupported operator: " + operator);
        }
        runTimeStack.push(result);  // push the result back to the runtime stack
    }

    /**
     * Sets the dumpability of the virtual machine.
     * @param b the flag indicating whether dumping is enabled or disabled.
     */
    public void setDumping(boolean b) {
        isDumpable = b;
    }

    /**
     * Sets the program counter to the specified address.
     * @param address the address to set.
     */
    public void setProgramCounter(int address) {
        programCounter = address;
    }

    /**
     * Retrieves the address of the label in the program.
     * @param label the label to search for.
     * @return the address of the label.
     * @throws IllegalArgumentException if the label is not found.
     */
    public int getLabelAddress(String label) {
        for (int i = 0; i < program.getSize(); i++) {
            ByteCode bytecode = program.getCode(i);
            if (bytecode instanceof LabelByteCode) {
                LabelByteCode labelCode = (LabelByteCode) bytecode;
                if (labelCode.getLabel().equals(label)) {
                    return i;
                }
            }
        }
        throw new IllegalArgumentException("Label not found: " + label);
    }

    /**
     * Retrieves the current value of the program counter.
     * @return the program counter value.
     */
    public int getProgramCounter() {
        return programCounter;
    }

    /**
     * Pushes a return address onto the return address stack.
     * @param address the address to push.
     */
    public void pushReturnAddress(int address) {
        returnAddress.push(address);
    }

    /**
     * Pops a return address from the return address stack.
     * @return the popped address.
     */
    public int popReturnAddress() {
        return returnAddress.pop();
    }

    /**
     * Pops a return address from the return address stack.
     * @return the popped address.
     */
    public int pop() {
        return this.runTimeStack.pop();
    }

    /**
     * Retrieves the value at the top of the runtime stack without removing it.
     * @return the value at the top of the runtime stack.
     */
    public int peekStack() {
        /*System.out.println("peekStack Method : " + getRunTimeStack().peek());*/
        return getRunTimeStack().peek();
    }

    /**
     * Retrieves the run-time stack of the virtual machine.
     * @return the run-time stack.
     */
    public RunTimeStack getRunTimeStack() {
        return runTimeStack;
    }

    /**
     * Retrieves the run-time stack of the virtual machine.
     * @return the run-time stack.
     */
    public int getStackSize() {
        return this.runTimeStack.size();
    }
}
