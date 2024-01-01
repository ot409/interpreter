package interpreter.virtualmachine;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class RunTimeStack {

    // this ArrayList is used to represent the runtime stack
    // it will be an ArrayList because we will need to access
    // ALL locations of the runTimeStack
    private ArrayList<Integer> runTimeStack;

    // this Stack is used to record the beginning of
    // each activation record(frame) when calling functions
    private Stack<Integer> framePointer;

    public RunTimeStack() {
        runTimeStack = new ArrayList<>();
        framePointer = new Stack<>();
        // Add initial frame pointer value, main is the entry
        // point of our language, so its frame pointer is 0.
        framePointer.add(0);
    }

    /**
     * Used for dumping the current state of the runTimeStack
     * It will print portions of the stack
     * based on respective frame markers.
     * Example [1,2,3] [4,5,6] [7,8]
     * Frame pointers would be 0,3,6
     */
   /* public String dump() {
        System.out.println("START DUMP METHOD");
        StringBuilder output = new StringBuilder();
        System.out.println("output (before any operation done with it): " + output);
        output.append("{0}-"); // add a space between frame values

        // iterate over the frame pointers
        for (int i = 0; i < framePointer.size(); i++) {
            System.out.println("i: " + i + " -- framePointer Size: " + (framePointer.size()));

            int start = framePointer.get(i); // get the frame start index
            System.out.println("frame start index: "+ start);

            int end = (i + 1 < framePointer.size()) ? framePointer.get(i + 1) : runTimeStack.size(); // get the next frame start index or end of stack
             System.out.println("next frame start index /end of stack - (end) : "+ end);

            List<Integer> frameValues = runTimeStack.subList(start, end); // get the values of the current frame

            output.append(frameValues.toString()); // append the frame values to the output
            System.out.println("output (after appending frameValues): " + output);

             System.out.println("i: " + i);
             System.out.println("framePointer (Size - 1): " + (framePointer.size() - 1));
            // append the frame marker if it's not the last frame
            if (i != framePointer.size() - 1) {
                System.out.println("I'm here");
                output.append(" "); // add a space between frame values
                output.append("{").append(end).append("}-"); // append the frame marker
                System.out.println("output (appending frame marker): " + output);
            }

        }
        System.out.println("END DUMP METHOD");
        System.out.println("");
        return output.toString(); // return the output as a string
    }*/

    public String dump() {
        StringBuilder output = new StringBuilder();
        output.append("{0}-"); // add a space between frame values

        // iterate over the frame pointers
        for (int i = 0; i < framePointer.size(); i++) {

            // get the frame start index
            int start = framePointer.get(i);

            // get the next frame start index or end of stack
            int end = (i + 1 < framePointer.size()) ? framePointer.get(i + 1) : runTimeStack.size();

            // get the values of the current frame
            List<Integer> frameValues = runTimeStack.subList(start, end);

            // append the frame values to the output
            output.append(frameValues.toString());

            // append the frame marker if it's not the last frame
            if (i != framePointer.size() - 1) {
                output.append(" "); // add a space between frame values
                output.append("{").append(end).append("}-"); // append the frame marker

            }
        }
        return output.toString(); // return the output as a string
    }


    /*public String dump() {
        String dumpVal = "";
        if (!framePointer.isEmpty()) {
            for (int i = 0; i < framePointer.size() - 1; i++) {
                int start = framePointer.get(i);
                int end = framePointer.get(i + 1);

                dumpVal += "[";
                for (int j = start; j < end; j++) {
                    dumpVal += runTimeStack.get(j);
                    if (j != end - 1) {
                        dumpVal += ",";
                    }
                }
                dumpVal += "] ";
            }
            int lastIndex = framePointer.size() - 1;
            int lastStart = framePointer.get(lastIndex);
            dumpVal += "[";
            for (int j = lastStart; j < runTimeStack.size(); j++) {
                dumpVal += runTimeStack.get(j);
                if (j != runTimeStack.size() - 1) {
                    dumpVal += ",";
                }
            }
            dumpVal += "] ";
        }
        return dumpVal;
    }*/

    public int size() {
        return this.runTimeStack.size();
    }

    /**
     * returns the top of the runtime stack, but
     * @return copy of the top of the stack.
     */
    public int peek(){
        /*System.out.println("PEEK METHOD (Top of runTimeStack): "
                + this.runTimeStack.get(this.runTimeStack.size()-1));
        System.out.println("runTimeStack size : " + runTimeStack.size());*/
        return this.runTimeStack.get(this.runTimeStack.size()-1);
    }


    /**
     * push the value i to the top of the stack.
     * @param i value to be pushed.
     * @return value pushed
     */
    public int push(int i) {
        this.runTimeStack.add(i);
        return i;
    }


    /**
     * removes to the top of the runtime stack.
     * @return the value popped.
     */
    public int pop() {
        return this.runTimeStack.remove(this.runTimeStack.size()-1);
    }

    /**
     * Removes the specified number of values from the top of the runtime stack.
     * @param valuesToRemove the number of values to remove.
     * @return the value popped.
     */
    public int popNum(int valuesToRemove) {
        int stackSize = runTimeStack.size();
        int startIndex = stackSize - valuesToRemove;
        int poppedValue = 0;

        // iterate from the top of the stack to the specified startIndex
        for (int i = stackSize - 1; i >= startIndex; i--) {
            // remove the value from the stack and store it in poppedValue
            poppedValue = runTimeStack.remove(i);
        }
        // return the last popped value
        return poppedValue;
    }

    /**
     * Takes the top item of the run time stack, and stores
     * it into a offset starting from the current frame.
     * @param offset number of slots above current frame marker
     * @return the item just stored
     */
    public int store(int offsetFromFramePointer) {
        // get the value from the top of the runtime stack
        int value = this.runTimeStack.remove(this.runTimeStack.size()-1);

        // calculate the index based on the offset and current frame pointer
        int index = this.framePointer.peek() + offsetFromFramePointer;

        // store the value at the calculated index
        this.runTimeStack.set(index, value);

        // return the stored value
        return value;
    }

    /**
     * Takes a value from the run time stack that is at offset
     * from the current frame marker and pushes it onto the top of
     * the stack.
     * @param offset number of slots above current frame marker
     * @return item just loaded into the offset
     */
    public int load(int offsetFromFramePointer) {
        /*System.out.println("START LOAD METHOD");*/

        // calculate the index based on the offset and current frame pointer
        int index = this.framePointer.peek() + offsetFromFramePointer;
        /*System.out.println("index : " + index);*/

        // retrieve the value at the calculated index from the run time stack
        int offsetValue = this.runTimeStack.get(index);
        /*System.out.println("offsetValue : " + offsetValue);*/

        // push the offset value onto the top of the stack
        this.runTimeStack.add(offsetValue);

        /*System.out.println("END LOAD METHOD");*/
        // return the offset value that was loaded
        return offsetValue;
    }

    /*public int load(int offsetFromFramePointer) {
        int currentFramePointer = this.framePointer.peek();
        int index = currentFramePointer - offsetFromFramePointer;

        if (index >= 0 && index < this.runTimeStack.size()) {
            int offsetValue = this.runTimeStack.get(index);
            this.runTimeStack.add(offsetValue);
            return offsetValue;
        } else {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
    }*/

    /**
     * create a new frame pointer at the index offset slots down
     * from the top of the runtime stack.
     * @param offset slots down from the top of the runtime stack
     */
    /*public void newFrameAt(int offsetFromTopOfRunStack) {
        if (!this.runTimeStack.isEmpty()) {
            // get index of the top of the runTimeStack
            int topOfRunTimeStack = this.runTimeStack.get(this.runTimeStack.size() - 1);
            // System.out.println("Top of runTimeStack: " + topOfRunTimeStack);

            // calculate the index based on the offset and current frame pointer
            int index = topOfRunTimeStack - offsetFromTopOfRunStack;
            // System.out.println("Index of offset slow down from top of the runtime stack (to be push in the framePointer): " + index);

            // push the calculated index onto the frame pointer stack
            this.framePointer.push(index);
        } else {
            // handle the case when the runtime stack is empty
            System.out.println("Runtime stack is empty. Cannot create a new frame.");
        }
    }*/

    public void newFrameAt(int offsetFromTopOfRunStack){
        framePointer.push(runTimeStack.size() - offsetFromTopOfRunStack);
    }




    /**
     * pop the current frame off the runtime stack. Also removes
     * the frames pointer value from the FramePointer Stack.
     */
    public void popFrame() {
        // remove the top frame (frame start index) from the frame pointer stack
        int frameStartIndex = this.framePointer.pop();
        // System.out.println("frameStartIndex: "+ frameStartIndex);

        // get the current size of the runTimeStack as the frame end index
        int frameEndIndex = this.runTimeStack.size();
        // System.out.println("frameEndIndex: "+ frameEndIndex);

        // System.out.println("Printing each value of the runTimeStack sublist (before applying clear) : ");
        // this.runTimeStack.subList(frameStartIndex, frameEndIndex).forEach(v -> System.out.print(v));*/
        // System.out.println("\n");

        // clear the elements in the runtime stack from the frame start index up to the frame end index
        this.runTimeStack.subList(frameStartIndex, frameEndIndex).clear();
    }

    public static void main(String[] args) {

        // * push test method
        /*System.out.println("TESTING PUSH METHOD");
        RunTimeStack rts = new RunTimeStack();
        rts.push(6);
        rts.push(5);
        rts.push(4);
        rts.push(3);
        rts.push(2);
        rts.push(1);

        // assert the size of the runTimeStack after pushing 6 value
        assert rts.runTimeStack.size() == 6;
        System.out.println("ASSERT PASSED! -> TEST SUCCESSFUL!");*/

        // * pop test method
        /*System.out.println("TESTING POP METHOD");
        RunTimeStack rts = new RunTimeStack();
        rts.push(6);
        rts.push(5);
        rts.push(4);
        rts.push(3);
        rts.push(2);
        rts.push(1);

        int poppedValue = rts.pop();
        System.out.println("Popped Value: " + poppedValue);
        // assert the size of the runTimeStack after popping top of the stack
        assert rts.runTimeStack.size() == 5;
        System.out.println("ASSERT PASSED! -> TEST SUCCESSFUL!");*/

        // * peek test method
       /* System.out.println("TESTING PEEK METHOD");
        RunTimeStack rts = new RunTimeStack();
        rts.push(6);
        rts.push(5);
        rts.push(4);
        rts.push(3);
        rts.push(2);
        rts.push(1);

        int peekValue = rts.peek();
        System.out.println("Peek Value: " + peekValue);
        // assert the size of the runTimeStack after popping top of the stack
        assert rts.runTimeStack.size() == 6;
        System.out.println("ASSERT PASSED! -> TEST SUCCESSFUL!");*/


        // * store test method
        /* System.out.println("TESTING STORE METHOD");
        RunTimeStack rts = new RunTimeStack();
        rts.push(6);
        rts.push(5);
        rts.push(4);
        rts.push(3);
        rts.push(2);
        rts.push(1);

        int offset = 2;
        rts.store(offset);

        //Assert the size of the runTimeStack after storing a value
        assert rts.runTimeStack.size() == 5;
        System.out.println("ASSERT PASSED! -> TEST SUCCESSFUL!");*/

        // * load test method
        /*System.out.println("TESTING LOAD METHOD");
        RunTimeStack rts = new RunTimeStack();
        rts.push(6);
        rts.push(5);
        rts.push(4);
        rts.push(3);
        rts.push(2);
        rts.push(1);

        rts.load(2);
        System.out.println("Top of the stack after loading value at index 2: " + rts.peek()); // should be 4

        // assert the size of the runTimeStack after popping top of the stack
        assert rts.runTimeStack.size() == 7;
        System.out.println("ASSERT PASSED! -> TEST SUCCESSFUL!");*/

        // ? check the value pushed into the frame?
        // * newframeAt test method
        /*System.out.println("TESTING NEWFRAMEAT METHOD");
        RunTimeStack rts = new RunTimeStack();
        rts.push(6);
        rts.push(5);
        rts.push(4);
        rts.push(3);
        rts.push(2);
        rts.push(1);

        rts.newFrameAt(2);

        // assert the size of the runTimeStack after popping top of the stack
        assert rts.framePointer.size() == 2;
        System.out.println("ASSERT PASSED! -> TEST SUCCESSFUL!");*/

        // * popFrame test method
       /* System.out.println("TESTING POPFRAME METHOD");
        RunTimeStack rts = new RunTimeStack();

        rts.push(6);
        rts.push(5);
        rts.push(4);
        rts.push(3);

        // create a new frame at index 2
        rts.newFrameAt(2);

        // push more values onto the stack within the frame
        rts.push(2);
        rts.push(1);
        System.out.println("Size of runTimeStack after 2 new value have been pushed : " + rts.runTimeStack.size());

        // assert the size of the runTimeStack before popping the frame
        assert rts.runTimeStack.size() == 6;
        System.out.println("FIRST ASSERT PASSED!");

        // pop the frame
        rts.popFrame();
        System.out.println("RunTimeStack size (after popping the current frame) : " + rts.runTimeStack.size());

        // assert the size of the runTimeStack after popping the frame
        assert rts.runTimeStack.size() == 1;
        System.out.println("SECOND ASSERT PASSED! -> TEST SUCCESSFUL!");*/

        // * dump test method
        System.out.println("TESTING DUMP METHOD");

        // create a new RunTimeStack instance
        RunTimeStack rts = new RunTimeStack();

        // push values onto the runtime stack
        rts.push(1);
        rts.push(2);
        rts.push(3);
        rts.push(4);
        rts.push(5);
        rts.push(6);
        rts.push(7);
        rts.push(8);

        // set the frame pointers
        rts.framePointer.push(3);
        rts.framePointer.push(6);

        // call the dump() method and assert the expected output
        String expectedOutput = "{0}-[] {3}-[4, 5, 6] {6}-[7, 8]";
        String actualOutput = rts.dump();
        System.out.println("Actual output: " + actualOutput);
        System.out.println("Expected Output: " + expectedOutput);

        if (expectedOutput.equals(actualOutput)) {
            System.out.println("MANUAL CHECK SUCCESSFUL!");
        } else {
            System.out.println("MANUAL CHECK UNSUCCESSFUL!");
        }
        assert expectedOutput.equals(actualOutput) : "Expected output and actual output do not match!";
        System.out.println("ASSERT PASSED! -> TEST SUCCESSFUL!");
    }


}

