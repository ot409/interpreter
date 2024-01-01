package interpreter.bytecodes;

import interpreter.virtualmachine.VirtualMachine;

/**
 * The LabelCode bytecode is used to mark locations in the program where other bytecodes can jump to.
 * Label bytecodes are used in address resolution to specify the target jump locations.
 */
public class LabelByteCode implements ByteCode {
    private String label;
    public LabelByteCode(String[] args) {
        if(args.length != 2){
            throw new IllegalArgumentException("Invalid number of arguments for LabelCode.");
        }
        this.label = args[1];
    }

    /**
     * Executes the LabelByteCode bytecode. No functionality is defined for this bytecode.
     * @param virtualMachine the virtual machine executing the bytecode.
     */
    @Override
    public void execute(VirtualMachine virtualMachine) {
        // no functionality for the LabelByteCode bytecode
    }

    /**
     * Returns a string representation of the LabelCode bytecode.
     * @return a string representation of the LabelCode bytecode.
     */
    @Override
    public String toString(){
        return "LABEL " + this.label;
    }

    /**
     * Gets the label of the LabelCode.
     * @return the label.
     */
    public String getLabel() {
        return label;
    }


    /**
     * Test the LabelCode class by creating an instance and printing it.
     * @param args command line arguments (unused)
     */
    public static void main(String[] args) {
        String[] arguments = {"LABEL", "label"};
        LabelByteCode labelCode = new LabelByteCode(arguments);
        System.out.println(labelCode);
    }
}
