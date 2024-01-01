package interpreter.bytecodes;

import interpreter.virtualmachine.VirtualMachine;

public class LitByteCode implements ByteCode, Dumpable {
    private String id;
    private int valueToPush;

    /**
     * Constructs a LitByteCode object with the given arguments.
     * @param args the arguments passed to the LitByteCode
     * bytecode, including the value to push and
     * an optional identifier.
     */
    public LitByteCode(String[] args){
        // validate the number of arguments
        if (args.length == 0 || args.length > 3) {
            throw new IllegalArgumentException("Invalid number of arguments for StoreCode.");
        }

        // parse the value to push
        this.valueToPush = Integer.parseInt(args[1]);

        // check if an identifier is present
        if(args.length ==  3 ){
            this.id = args[2];
        }
    }

    /**
     * Executes the LitByteCode bytecode by pushing the value
     * onto the virtual machine's runtime stack.
     * @param virtualMachine the virtual machine executing
     * the bytecode.
     */
    @Override
    public void execute(VirtualMachine virtualMachine) {
        virtualMachine.push(this.valueToPush);
    }


    /**
     * Returns a string representation of the LitByteCode bytecode.
     * If an identifier is present, it includes the identifier.
     * @return a string representation of the LitByteCode bytecode.
     */
    @Override
    public String toString(){
        String base = "LIT " + this.valueToPush;
        if(this.id != null){
            base += " " + this.id + "\tint " + this.id;
        }
        return base;
    }

    /**
     * Test the LitByteCode class by creating an instance and printing it.
     * @param args command line arguments (unused)
     */
    public static void main(String[] args) {
        String[] x = {"LIT ", "1", "variableName"};
        LitByteCode c = new LitByteCode(x);
        System.out.println(c);
    }
}
