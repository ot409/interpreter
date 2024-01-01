package interpreter.bytecodes;

import interpreter.virtualmachine.VirtualMachine;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ReadByteCode implements ByteCode, Dumpable {

    public ReadByteCode(String[] args) {
        // No arguments required
    }

    /**
     * Executes the ReadByteCode bytecode by reading an integer from the user and pushing it to the runtime stack.
     * If the user provides an invalid input, an error message is displayed and the user is prompted again
     * until a valid integer is entered.
     * @param virtualMachine the virtual machine executing the bytecode.
     */
    @Override
    public void execute(VirtualMachine virtualMachine) {
        boolean validInput = false;
        int value = 0;

        //  the user to enter an integer until a valid input is provided
        while (!validInput) {
            try {
                Scanner scanner = new Scanner(System.in);
                System.out.print("Please enter an integer: ");
                value = scanner.nextInt();
                validInput = true;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer.");
            }
        }
        virtualMachine.push(value); // push the validated integer to the runtime stack
    }

    /**
     * Returns a string representation of the ReadByteCode bytecode.
     * @return a string representation of the ReadByteCode bytecode.
     */
    @Override
    public String toString(){
        return "READ";
    }

    /**
     * Test the ReadByteCode class by creating an instance and printing it.
     * @param args command line arguments (unused)
     */
    /*@Override
    public static void main(String[] args) {
        String[] arguments = {"READ"};
        ReadByteCode readByteCode = new ReadByteCode(arguments);
        System.out.println(readCode);
    }*/
}
