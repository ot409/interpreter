package interpreter.bytecodes;

import interpreter.virtualmachine.VirtualMachine;

public class CallByteCode implements ByteCode, Dumpable {
    private String label;
    private int address;

    /**
     * Constructs a CallCode object with the given arguments.
     * @param args the arguments passed to the CallCode bytecode, including the label to call.
     */
    public CallByteCode(String[] args) {
        // validate the number of arguments
        if (args.length != 2) {
            throw new IllegalArgumentException("Invalid number of arguments for CallByteCode.");
        }

        this.label = args[1];
    }

    /**
     * Executes the CallByteCode bytecode by performing a function call.
     * @param virtualMachine the virtual machine executing the bytecode.
     */
    @Override
    public void execute(VirtualMachine virtualMachine) {
        // Perform address resolution to get the jump address
        int jumpAddress = virtualMachine.getLabelAddress(label);

        // Store the return address onto the Return Address Stack
        virtualMachine.pushReturnAddress(virtualMachine.getProgramCounter());

        // Jump to the address corresponding to the label
        virtualMachine.setProgramCounter(jumpAddress);
    }

    /**
     * Returns a string representation of the CallCode bytecode.
     * @return a string representation of the CallCode bytecode.
     */
    @Override
    public String toString() {
        return "CALL " + label;
    }

    /**
     * Retrieves the label to be called.
     * @return the label to be called.
     */
    public String getLabel() {
        return label;
    }

    /**
     * Sets the resolved address for the CallCode instruction.
     * @param address the resolved address to set.
     */
    public void setAddress(int address) {
        this.address = address;
    }

}
