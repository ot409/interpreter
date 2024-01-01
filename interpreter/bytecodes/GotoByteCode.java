package interpreter.bytecodes;

import interpreter.virtualmachine.VirtualMachine;

public class GotoByteCode implements ByteCode, Dumpable  {
    private String label;
    private int address; // store the resolved address

    /**
     * Constructs a GotoByteCode object with the given arguments.
     * @param args the arguments passed to the GotoByteCode
     * bytecode, including the label to jump to.
     */
    public GotoByteCode(String[] args){
        // validate the number of arguments
        if ( args.length > 2) {
            throw new IllegalArgumentException("Invalid number of arguments for StoreCode.");
        }
        this.label = args[1];
    }

    /**
     * Executes the GotoByteCode bytecode by setting the program counter
     * to the address of the specified label in the virtual machine.
     * @param virtualMachine the virtual machine executing the bytecode.
     */
    @Override
    public void execute(VirtualMachine virtualMachine) {
        // set the program counter to the address of the specified label
        virtualMachine.setProgramCounter(address - 1);
    }

    /**
     * Returns a string representation of the GotoByteCode bytecode.
     * @return a string representation of the GotoByteCode bytecode.
     */
    @Override
    public String toString(){
        return "GOTO " + label;
    }

    /**
     * Retrieves the label to jump to.
     * @return the label to jump to.
     */
    public String getLabel() {
        return label;
    }

    /**
     * Sets the resolved address for the GotoCode instruction.
     * @param address the resolved address to set.
     */
    public void setAddress(int address) {
        this.address = address;
    }

    /**
     * Test the GotoCode class by creating an instance and printing it.
     * @param args command line arguments (unused)
     */
    public static void main(String[] args) {
        String[] arguments = {"GOTO", "label1"};
        GotoByteCode gotoByteCode = new GotoByteCode(arguments);
        System.out.println(gotoByteCode);
    }
}
