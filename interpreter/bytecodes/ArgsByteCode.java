package interpreter.bytecodes;

import interpreter.virtualmachine.VirtualMachine;

public class ArgsByteCode implements ByteCode, Dumpable {
    private int frame;

    /**
     * Constructs an ArgsByteCode object with the given arguments.
     * @param args the arguments passed to the ArgsByteCode bytecode, including the value to push and
     * the number of values to reserve for the new frame.
     */
    public ArgsByteCode(String[] args) {
        // This validates the number of arguments
        if (args.length != 2) {
            throw new IllegalArgumentException("Invalid number of arguments for ArgsByteCode.");
        }

        this.frame = Integer.parseInt(args[1]);
    }

    /**
     * Executes the ArgsByteCode bytecode by pushing
     * the starting index of the new frame to the framePointer stack.
     * @param virtualMachine the virtual machine executing the bytecode.
     */
    @Override
    public void execute(VirtualMachine virtualMachine) {
        // Push the starting index of the new frame to the framePointer stack
        virtualMachine.newFrameAt(this.frame);
    }

    /**
     * Returns a string representation of the ArgsByteCode bytecode. If a number of arguments is present, it includes the count.
     * @return a string representation of the ArgsByteCode bytecode.
     */
    @Override
    public String toString(){
        return "ARGS " + frame;
    }

    /**
     * Test the ArgsByteCode class by creating an instance and printing it.
     * @param args command line arguments (unused)
     */
    public static void main(String[] args) {
        String[] arguments = {"ARGS", "1"};
        ArgsByteCode argsByteCode = new ArgsByteCode(arguments);
        System.out.println(argsByteCode);
    }
}
