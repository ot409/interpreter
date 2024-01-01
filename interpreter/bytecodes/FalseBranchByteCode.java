package interpreter.bytecodes;

import interpreter.virtualmachine.VirtualMachine;

public class FalseBranchByteCode implements ByteCode, Dumpable {

    private String label;
    private int address;

    /**
     * Constructs a FalseBranchByteCode object with the given arguments.
     * @param args the arguments passed to the FalseBranchCode
     * bytecode, including the label to jump to.
     */
    public FalseBranchByteCode(String[] args) {
        this.label = args[1];
    }

    /**
     * Executes the FalseBranchCode bytecode.
     * If the top value of the stack is 0, it jumps to the specified label.
     * If the value is not 0, it moves to the next ByteCode.
     *
     * @param virtualMachine the virtual machine executing the bytecode.
     */
    @Override
    public void execute(VirtualMachine virtualMachine) {
        // remove the top value from the stack
        int value = virtualMachine.pop();

        // check if the value is 0
        if (value == 0) {
            // if the value is 0, jump to the specified label
            virtualMachine.setProgramCounter(virtualMachine.getLabelAddress(label) - 1);
            // we subtract 1 from the label address because the program counter
            // will be incremented after executing this bytecode, so we need to jump
            // to the correct address.
        }
        // If the value is not 0, move to the next ByteCode (do nothing)
    }

    /**
     * Returns a string representation of the FalseBranchByteCode bytecode.
     * @return a string representation of the FalseBranchByteCode bytecode.
     */
    @Override
    public String toString() {
        return "FALSEBRANCH " + label;
    }

    /**
     * Test the FalseBranchByteCode class by creating an instance and printing it.
     * @param args command line arguments (unused)
     */
    public static void main(String[] args) {
        String[] x = {"FALSEBRANCH", "else<<4>>"};
        FalseBranchByteCode c = new FalseBranchByteCode(x);
        System.out.println(c);
    }

    /**
     * Retrieves the label to jump to.
     * @return the label to jump to.
     */
    public String getLabel() {
        return label;
    }

    /**
     * Sets the resolved address for the FalseBranchCode instruction.
     * @param address the resolved address to set.
     */
    public void setAddress(int address) {
        this.address = address;
    }
}
