package com.example.geektrust;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MainTest {

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor)); // Capture System.out output
    }

    @Test
    void testLoanAndBalanceCommands() {
        String[] inputs = new String[]{"sample_input/input1.txt"};
        Main.main(inputs);

        String expected = "IDIDI Dale 1326 9\n" +
                "IDIDI Dale 3652 4\n" +
                "UON Shelly 15856 3\n" +
                "MBI Harry 9044 10";
        String outContent = outputStreamCaptor.toString();

        String normalizedActual = outContent.replaceAll("\\r\\n|\\n", "").trim();
        String normalizedExpected = expected.replaceAll("\\r\\n|\\n", "").trim();

        assertEquals(normalizedActual, normalizedExpected);
    }

}
