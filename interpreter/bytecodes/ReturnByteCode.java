package interpreter.bytecodes;

import interpreter.virtualmachine.Program;
import interpreter.virtualmachine.VirtualMachine;

import java.util.Arrays;

public class ReturnByteCode implements ByteCode, Dumpable {

    private String label;
    private String[] arguments;
    private VirtualMachine virtualMachine;

    /**
     * Constructs a ReturnByteCode object with the given arguments.
     * @param args the arguments passed to the ReturnCode bytecode, including the label and additional information if present.
     */
    public ReturnByteCode(String[] args) {
        arguments = args;
        /*System.out.println("args length: " + args.length);*/
        // validate the number of arguments
        if (args.length > 2) {
            throw new IllegalArgumentException("Invalid number of arguments for ReturnCode.");
        }

        /*if (args.length == 1) {
            // no arguments
            System.out.println("args length = 1");
            System.out.println("args[0]: " + args[0]);
        } else if (args.length == 2) {
            // 1 argument
            System.out.println("args length = 2");
            System.out.println("args[0]: " + args[0]);
            System.out.println("args[1]: " + args[1]);
        }*/

        // extract the label if provided
        if (args.length > 0) {
            label = args[0];
            /*System.out.println("label: " + label);*/
        } else {
            label = null;
        }

    }

    /**
     * Executes the ReturnCode bytecode by returning from the current function.
     * @param virtualMachine the virtual machine executing the bytecode.
     */
    @Override
    public void execute(VirtualMachine virtualMachine) {
        this.virtualMachine = virtualMachine; // assign the VirtualMachine object to the instance variable

        // store the return value at the top of the runtime stack
        int returnValue = virtualMachine.peek();
        /*System.out.println("returnValue: " + returnValue);*/

        // empty the current frame & remove the frame boundary
        virtualMachine.popFrame();

        // pop the top of the return address stack and save it into the program counter
        int returnAddress = virtualMachine.popReturnAddress();
        virtualMachine.setProgramCounter(returnAddress);

        // put the return value in the correct spot on the runtime stack
        virtualMachine.push(returnValue);
    }

    /**
     * Returns a string representation of the ReturnCode bytecode.
     * If additional information is present, it includes the label and the value being returned from the function.
     * @return a string representation of the ReturnCode bytecode.
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("");

        if (arguments.length == 1 && label != null) {
            // handle case with no arguments
            /* System.out.println("arguments length: " + arguments.length);
             for (int i = 0; i < arguments.length; i++) {
                 System.out.println("arguments[" + i + "]: " + arguments[i]);
             }*/
            builder.append("RETURN");
        } else if (arguments.length == 2 && label != null) {
            // handle case with two arguments
            /* System.out.println("arguments length: " + arguments.length);
             for (int i = 0; i < arguments.length; i++) {
                 System.out.println("arguments[" + i + "]: " + arguments[i]);
             }*/
            String newArg = arguments[0];
            String newArg2 = arguments[1];

            // extract the substring before "<<"
            int endIndex = newArg2.indexOf("<<"); // find the index of "<<"
            String extractedString = newArg2.substring(0, endIndex); // extract the substring from the start to the "<<"
            /*System.out.println("extractedString: " + extractedString);*/

            /*System.out.println("newArg: " + newArg);*/
            builder.append("" + newArg + " " + newArg2 + " EXIT " + extractedString ); // constructing the initial part of the dump syntax

            // append the value being returned from the function to the dump syntax
            builder.append(": ").append(virtualMachine.peekStack());
        }

        String result = builder.toString();
        return result;
    }
}
