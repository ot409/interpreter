package interpreter.bytecodes;

import interpreter.virtualmachine.VirtualMachine;

public class PopByteCode implements ByteCode, Dumpable {
    private int numValuesToRemove;

    /**
     * Constructs a PopByteCode object with the given arguments.
     * @param args the arguments passed to the PopByteCode bytecode,
     *             including the number of values to remove.
     */
    public PopByteCode(String[] args) {
        this.numValuesToRemove = Integer.parseInt(args[1]);
    }

    /**
     * Executes the PopByteCode bytecode by removing the specified number
     * of values from the run-time stack of the virtual machine.
     * @param virtualMachine the virtual machine executing the bytecode.
     */
    @Override
    public void execute(VirtualMachine virtualMachine) {
        virtualMachine.popStack();
    }

    /**
     * Returns a string representation of the PopCode bytecode.
     * @return a string representation of the PopCode bytecode.
     */
    @Override
    public String toString(){
        String base = "POP " + this.numValuesToRemove;
        return base;
    }

    /**
     * Test the PopByteCode class by creating an instance and printing it.
     * @param args command line arguments (unused)
     */
    public static void main(String[] args) {
        String[] x = {"POP ", "1"};
        PopByteCode c = new PopByteCode(x);
        System.out.println(c);
    }
}
